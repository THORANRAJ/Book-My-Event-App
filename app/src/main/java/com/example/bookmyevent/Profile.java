package com.example.bookmyevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    TextView ph,hp,rt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ph=findViewById(R.id.ph);
        hp=findViewById(R.id.hp);
        rt=findViewById(R.id.rt);
        ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Profile.this,Purchase_history.class);
                startActivity(intent);
            }
        });
        hp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Profile.this,Help.class);
                startActivity(intent);
            }
        });
        rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Profile.this,Rating.class);
                startActivity(intent);
            }
        });
    }
}