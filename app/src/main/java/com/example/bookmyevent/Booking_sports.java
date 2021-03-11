package com.example.bookmyevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Booking_sports extends AppCompatActivity {

    ImageView ivbook;
    TextView tvmat,tvloc,tvdate,tvp;
    Spinner spinner;
    Button btnbook;
    int result1=0;
    int tic1=0;
    String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
    //ref= FirebaseDatabase.getInstance().getReference().child("movies");
    DatabaseReference ref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_sports);

        ivbook=findViewById(R.id.imageViewBook);
        tvmat=findViewById(R.id.tvmat);
        tvloc=findViewById(R.id.tvloc);
        tvdate=findViewById(R.id.movdate);
        tvp=findViewById(R.id.tvprice2);
        btnbook=findViewById(R.id.btnbook);
        spinner=findViewById(R.id.spinner2);
        Bundle b1=getIntent().getExtras();
        s1=b1.getString("match");
        s2=b1.getString("location");
        s3=b1.getString("purl");
        s4=b1.getString("date");
        s5=b1.getString("price");
        s6=s5.substring(2);
        s7=b1.getString("tickets");
        s8=s7.substring(2);
        result1=Integer.valueOf(s8);
        Log.d("dd",String.valueOf(result1));

        s9=b1.getString("time");

        s10=b1.getString("id");
     //   Log.d("s66",s6);
        Glide.with(this).load(s3).into(ivbook);
        tvmat.setText(s1);
        tvloc.setText(s2);
        tvdate.setText(s4);
        tvp.setText(s6);
        ref1= FirebaseDatabase.getInstance().getReference().child("sports");

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int x=spinner.getSelectedItemPosition();
                double res=0;

                int num = Integer.valueOf(tvp.getText().toString());
                if(x==1)
                {
                    res=1*num;
                    // Log.d("result", String.valueOf(res));
                    tic1=1;
                }
                else if(x==2)
                {
                    res=2*num;
                    tic1=2;
                }
                else if(x==3)
                {
                    res=3*num;
                    tic1=3;
                }

                else if(x==4)
                {
                    res=4*num;
                    tic1=4;
                }

                else if(x==5)
                {
                    res=5*num;
                    tic1=5;
                }

                else if(x==6)
                {
                    res=6*num;
                    tic1=6;
                }

                else if(x==7)
                {
                    res=7*num;
                    tic1=7;
                }

                else if(x==8)
                {
                    res=8*num;
                    tic1=8;
                }
                else if(x==0)
                {
                    tic1=0;
                    Toast.makeText(getApplicationContext(),"please select option",Toast.LENGTH_SHORT).show();
                }
                if(tic1==0)
                {

                }
                else {
                    Log.d("res", String.valueOf(res));

                    result1 = result1 - tic1;
                    String ticket1 = "t-";
                    ticket1 = ticket1.concat(String.valueOf(result1));
                    Log.d("thor", ticket1);
                    String tr,rt;
                    tr = String.valueOf(tic1);
                    rt = String.valueOf(res);
                    Intent i=new Intent(Booking_sports.this,Confirm_page.class);
                    i.putExtra("main",s1);
                    i.putExtra("tickets",tr);
                    i.putExtra("amount",rt);
                    i.putExtra("place",s2);
                    i.putExtra("date",s4);
                    i.putExtra("time",s9);
                    i.putExtra("rating","r5");
                    ref1.child(s10).child("tickets").setValue(ticket1);
                    startActivity(i);
                }
            }
        });



    }

}