package com.example.bookmyevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Payment_mode extends AppCompatActivity {

 Button okbtn1;
 RadioButton cd,gp;
 String s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_mode);
        okbtn1=findViewById(R.id.okbtn1);
        cd=findViewById(R.id.cd1);
        gp=findViewById(R.id.gp);
        Bundle b1=getIntent().getExtras();
        s1=b1.getString("price");
        okbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(cd.isChecked())
             {
                   Intent intent=new Intent(Payment_mode.this,Cd_card.class);
                   intent.putExtra("price1",s1);
                   startActivity(intent);
             }
             else if(gp.isChecked())
             {
                 Intent intent=new Intent(Payment_mode.this,Upi_activity.class);
                 intent.putExtra("price2",s1);
                 startActivity(intent);
               //  Toast.makeText(Payment_mode.this,"please select any one option",Toast.LENGTH_LONG).show();
             }
             else {
                 Toast.makeText(Payment_mode.this,"please select any one option",Toast.LENGTH_LONG).show();
             }
            }
        });
    }
}