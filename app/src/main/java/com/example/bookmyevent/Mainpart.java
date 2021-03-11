package com.example.bookmyevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Mainpart extends AppCompatActivity {

    RadioButton r1,r2;
    Button btn;
    TextView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpart);
        r1=findViewById(R.id.r1);
        r2=findViewById(R.id.r2);
        btn=findViewById(R.id.okbtn);
        profile=findViewById(R.id.profile);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(r1.isChecked())
                {
                    Toast.makeText(Mainpart.this,"selected for movies!",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Mainpart.this,Movies.class);
                    startActivity(intent);

                }
                else if(r2.isChecked())
                {

                    Toast.makeText(Mainpart.this,"selected for sports!",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Mainpart.this,Sports.class);
                    startActivity(intent);
                }
                else
                {

                    Toast.makeText(Mainpart.this,"please select any one option",Toast.LENGTH_LONG).show();
                }
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Mainpart.this,Profile.class);
                startActivity(intent);
            }
        });
    }
}