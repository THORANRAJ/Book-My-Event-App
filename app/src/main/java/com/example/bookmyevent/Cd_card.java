package com.example.bookmyevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Cd_card extends AppCompatActivity {

    TextView pricepay;
    EditText cdno,cvv,et1;
    String s1;
    Button cdbtn;
    Spinner sp1,sp2;
    DatabaseReference ref3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cd_card);
        Bundle b1=getIntent().getExtras();
        s1=b1.getString("price1");
        pricepay=findViewById(R.id.payprice);
        pricepay.setText(s1);
        cdno=findViewById(R.id.cdno1);

        sp1=findViewById(R.id.spinner3);
        sp2=findViewById(R.id.spinner4);
        cvv=findViewById(R.id.cvv);
        cdbtn=findViewById(R.id.cdbtn);
        ref3= FirebaseDatabase.getInstance().getReference().child("users");
        cdno.addTextChangedListener(new FourDigitCardFormatWatcher());
        cdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int x2=sp2.getSelectedItemPosition();
                int x1=sp1.getSelectedItemPosition();
                if(cdno.getText().toString().length()==19)
                {
                    Toast.makeText(Cd_card.this,"card accepted",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Cd_card.this,"card not accepted",Toast.LENGTH_LONG).show();
                }

                if(x1==0&&x2==0)
                {
                    Toast.makeText(Cd_card.this,"select the option",Toast.LENGTH_LONG).show();
                }
                else {

                    String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

                    ref3.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists())
                            {
                                String phone = snapshot.child("num").getValue().toString().trim();
                                String username = snapshot.child("name").getValue().toString().trim();
                                String msg = "Hello " + username + ", Thanks for choosing us!! your tickets are booked";
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                    if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                                    {
                                        try {
                                            SmsManager smsManager = SmsManager.getDefault();
                                            smsManager.sendTextMessage(phone, null, msg, null, null);
                                            Toast.makeText(getApplicationContext(), "Message sent successfully", Toast.LENGTH_LONG).show();

                                        }
                                        catch (Exception e)
                                        {
                                            e.printStackTrace();
                                            Toast.makeText(getApplicationContext(), "Something went wrong!!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else{
                                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                                    }
                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                    Toast.makeText(Cd_card.this,s1+" rs got deducted",Toast.LENGTH_LONG).show();

                    AlertDialog.Builder builder=new AlertDialog.Builder(Cd_card.this);
                    builder.setMessage("Do you wanna see some Restaurants?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent=new Intent(Cd_card.this,Restaurant.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent=new Intent(Cd_card.this,Mainpart.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();

                }


            }
        });

    }
    public static class FourDigitCardFormatWatcher implements TextWatcher {


        private static final char space = '-';

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0 && (s.length() % 5) == 0) {
                final char c = s.charAt(s.length() - 1);
                if (space == c) {
                    s.delete(s.length() - 1, s.length());
                }
            }

            if (s.length() > 0 && (s.length() % 5) == 0) {
                char c = s.charAt(s.length() - 1);
                if (Character.isDigit(c) && TextUtils.split(s.toString(), String.valueOf(space)).length <= 3) {
                    s.insert(s.length() - 1, String.valueOf(space));
                }
            }
        }
    }
}