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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Upi_activity extends AppCompatActivity {

    String s1;
    TextView upiprice;
    Button upibtn;
    PinView pin;
    DatabaseReference ref4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upi_activity);
        upiprice=findViewById(R.id.upiprice);
        Bundle b1=getIntent().getExtras();
        s1=b1.getString("price2");
        upiprice.setText(s1);
        upibtn=findViewById(R.id.upibtn);
        pin=findViewById(R.id.pin);
        ref4= FirebaseDatabase.getInstance().getReference().child("users");

        upibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pin.getText().toString().length()<6)
                {
                    Toast.makeText(Upi_activity.this," please enter proper upi pin",Toast.LENGTH_LONG).show();
                }
                else
                {

                    String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

                    ref4.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
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
                    Toast.makeText(Upi_activity.this,s1+" rs got deducted",Toast.LENGTH_LONG).show();

                    AlertDialog.Builder builder=new AlertDialog.Builder(Upi_activity.this);
                    builder.setMessage("Do you wanna see some Restaurants?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent=new Intent(Upi_activity.this,Restaurant.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent=new Intent(Upi_activity.this,Mainpart.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();

                }

             //  Intent intent=new Intent(Upi_activity.this,Restaurant.class);
               // startActivity(intent);
            }
        });
    }
}