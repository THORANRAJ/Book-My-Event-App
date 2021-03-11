package com.example.bookmyevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText et1,et2;
    Button btn;
    TextView tv3,tv4;
    FirebaseAuth fAuth;
    ProgressBar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        btn=findViewById(R.id.btn);
        tv3=findViewById(R.id.tv3);
    //    tv4=findViewById(R.id.tv4);
        pbar=findViewById(R.id.pbar);
        fAuth=FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,pass;
                email=et1.getText().toString().trim();
                pass=et2.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    et1.setError("Mail id is required!");
                    return;
                }
                if(TextUtils.isEmpty(pass))
                {
                    et2.setError("Password is required!");
                    return;
                }

                pbar.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            Toast.makeText(Login.this,"Login successfull!",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),Mainpart.class));
                        }
                        else {
                            Toast.makeText(Login.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            pbar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,register.class);
                startActivity(intent);
            }
        });


    }
}