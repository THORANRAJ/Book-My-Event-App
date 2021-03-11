package com.example.bookmyevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    TextView help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        help=findViewById(R.id.helpbtn);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s="https://drive.google.com/file/d/1jL2oxoYq7VTCj55jdE_H1GmegGOm1OcS/view?usp=drivesdk";
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(s));
                startActivity(intent);
            }
        });
    }
}