package com.example.bookmyevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Confirm_page extends AppCompatActivity {

    TextView tvmain,tvtics,tvamt,tvpl,tvdat,tvtime,t1,t2,t3,t4,t5;
    Button btngo;
    String s1,s2,s3,s4,s5,s6,s7;
    DatabaseReference ref2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_page);
        tvmain=findViewById(R.id.tvmain);
        tvtics=findViewById(R.id.tvtics);
        tvamt=findViewById(R.id.tvamt);
        tvpl=findViewById(R.id.tvpl);
        tvdat=findViewById(R.id.tvdat);
        tvtime=findViewById(R.id.tvtime);
        btngo=findViewById(R.id.btngo);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t5=findViewById(R.id.t5);

        Bundle b1=getIntent().getExtras();
        s1=b1.getString("main");
        s2=b1.getString("tickets");
        s3=b1.getString("amount");
        s4=b1.getString("place");
        s5=b1.getString("date");
        s6=b1.getString("time");
        s7=b1.getString("rating");

        ref2= FirebaseDatabase.getInstance().getReference().child("users");

        tvmain.setText(s1);
        tvtics.setText(s2);
        tvamt.setText(s3);
        tvpl.setText(s4);
        tvdat.setText(s5);
        tvtime.setText(s6);
        try {
            if (s7.equals("r4")) {
                t5.setVisibility(View.GONE);
            } else if (s7.equals("r3")) {
                t4.setVisibility(View.GONE);
                t5.setVisibility(View.GONE);
            } else if (s7.equals("r2")) {
                t3.setVisibility(View.GONE);
                t4.setVisibility(View.GONE);
                t5.setVisibility(View.GONE);
            } else if (s7.equals("r5")) {
                t1.setVisibility(View.VISIBLE);
                t2.setVisibility(View.VISIBLE);
                t3.setVisibility(View.VISIBLE);
                t4.setVisibility(View.VISIBLE);
                t5.setVisibility(View.VISIBLE);
            }
        }
        catch (Exception e)
        {
            Toast.makeText(Confirm_page.this,e.toString(),Toast.LENGTH_LONG).show();
        }


        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                    if(user==null)
                    {
                        return;
                    }
                    String userId=user.getUid();
                    store ee=new store(s1,s3,s2,s4,s5);

                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    DatabaseReference st=database.getReference("tickets");
                    st.child(userId).push().setValue(ee);

                    Log.d("sam",userId);
                }catch (Exception e)
                {
                    Toast.makeText(Confirm_page.this,e.toString(),Toast.LENGTH_LONG).show();
                }


                /*String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();

                  ref2.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          if(snapshot.exists())
                          {
                              String phone = snapshot.child("num").getValue().toString().trim();
                              String username = snapshot.child("name").getValue().toString().trim();
                              String msg = "Hello " + username + ", your tickets are booked";
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
                  });  */


                Intent intent=new Intent(Confirm_page.this,Payment_mode.class);
                intent.putExtra("price",s3);
                startActivity(intent);




            }
        });

    }
}