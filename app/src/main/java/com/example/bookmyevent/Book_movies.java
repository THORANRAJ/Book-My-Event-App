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

public class Book_movies extends AppCompatActivity {

    ImageView ivmov;
    TextView tvmov,tvhero,tvheroine,tvt,tvprice,tvdate;
    Button btnmov;
    Spinner spinner;
    String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13;
    int tic=0;
    int result;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_movies);
        ivmov=findViewById(R.id.ivmov);
        tvmov=findViewById(R.id.tvmov);
        tvhero=findViewById(R.id.tvhero);
        tvheroine=findViewById(R.id.tvheroine);
        tvt=findViewById(R.id.tvtheatre);
        btnmov=findViewById(R.id.btnmov);
        tvprice=findViewById(R.id.tvprice);
        spinner=findViewById(R.id.spinner);
        tvdate=findViewById(R.id.movdate);
        Bundle b1=getIntent().getExtras();
        // int s=b1.getInt("purl");
        //String s=b1.getString("purl");
         s1=b1.getString("movie");
         s2=b1.getString("hero");
         s3=b1.getString("heroine");
         s4=b1.getString("theatre");
         s5=b1.getString("purl");
         s6=b1.getString("price");
         s8=b1.getString("date");
         s7=s6.substring(2);
         s9=b1.getString("tickets");
         s10=s9.substring(2);
        result = Integer.valueOf(s10);
        Log.d("s10",s10);

        s11=b1.getString("time");
        s12=b1.getString("rating");
        s13=b1.getString("id");
        ref= FirebaseDatabase.getInstance().getReference().child("movies");

        tvmov.setText(s1);
        tvhero.setText(s2);
        tvheroine.setText(s3);
        tvt.setText(s4);
        tvprice.setText(s7);
        tvdate.setText(s8);
        Glide.with(this).load(s5).into(ivmov);

        btnmov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int x=spinner.getSelectedItemPosition();
                double res=0;

                int num = Integer.valueOf(tvprice.getText().toString());
                //int tic_price=Integer.parseInt();

                if(x==1)
                {
                    tic=1;
                   res=1*num;
                  // Log.d("result", String.valueOf(res));
                }
                else if(x==2)
                {
                    tic=2;
                    res=2*num;

                }
                else if(x==3)
                {
                    tic=3;
                    res=3*num;

                }

                else if(x==4)
                {
                    tic=4;
                    res=4*num;

                }

                else if(x==5)
                {
                    tic=5;
                    res=5*num;

                }

                else if(x==6)
                {
                    tic=6;
                    res=6*num;

                }

                else if(x==7)
                {
                    tic=7;
                    res=7*num;

                }

                else if(x==8)
                {
                    tic=8;
                    res=8*num;

                }
                else if(x==0)
                {
                    tic=0;
                    Toast.makeText(getApplicationContext(),"please select option",Toast.LENGTH_SHORT).show();

                }
                //Log.d("result", String.valueOf(res));


                if(tic==0)
                {
                    Toast.makeText(getApplicationContext(),"please select option",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    result = result - tic;
                    String ticket = "t-";
                    ticket = ticket.concat(String.valueOf(result));
                    Log.d("e", ticket);
                    String th, ram;
                    th = String.valueOf(tic);
                    ram = String.valueOf(res);
                    Intent i = new Intent(Book_movies.this, Confirm_page.class);
                    i.putExtra("main", s1);
                    i.putExtra("tickets", th);
                    i.putExtra("amount", ram);
                    i.putExtra("place", s4);
                    i.putExtra("date", s8);
                    i.putExtra("time",s11);
                    i.putExtra("rating",s12);
                    ref.child(s13).child("tickets").setValue(ticket);
                    startActivity(i);
                }


            }
        });


    }
}