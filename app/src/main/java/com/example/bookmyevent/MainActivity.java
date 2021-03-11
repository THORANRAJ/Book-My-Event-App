package com.example.bookmyevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView logo,name;
    Animation top,bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo=findViewById(R.id.logo);
        name=findViewById(R.id.name);

        top= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logoanimation);
        bottom= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.nameanimation);

        logo.setAnimation(top);
        name.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        },3000);
    }
}