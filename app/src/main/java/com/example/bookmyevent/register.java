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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    EditText etname,etmail,etpass,etphone;
    Button btn;
    ProgressBar pbar;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etname=findViewById(R.id.etname);
        etmail=findViewById(R.id.etmail);
        etpass=findViewById(R.id.etpass);
        etphone=findViewById(R.id.etphone);
        btn=findViewById(R.id.btn);
        pbar=findViewById(R.id.pbar);

        fAuth=FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name,email,pass,phone;
                name=etname.getText().toString().trim();
                email=etmail.getText().toString().trim();
                pass=etpass.getText().toString().trim();
                phone=etphone.getText().toString().trim();
                if(TextUtils.isEmpty(name))
                {
                    etname.setError("Name is required!");
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    etmail.setError("Mail id is required!");
                    return;
                }
                if(TextUtils.isEmpty(pass))
                {
                    etpass.setError("Password is required!");
                    return;
                }
                if(pass.length()<6)
                {
                    etpass.setError("please set strong password!");
                    return;
                }
                if(TextUtils.isEmpty(phone))
                {
                    etphone.setError("Phone Number is required!");
                    return;
                }
                pbar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {

                            user us=new user(name,email,pass,phone);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(us).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(register.this,"user data saved sucessfull!",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(register.this,"user data not saved!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Toast.makeText(register.this,"user account created",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),Login.class));
                          //  pbar.setVisibility(View.GONE);
                        }
                        else
                        {
                            Toast.makeText(register.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            pbar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
    }
}