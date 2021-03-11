package com.example.bookmyevent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.FirebaseDatabase;

//import static com.google.firebase.database.FirebaseDatabase.*;

public class Sports extends AppCompatActivity {

    RecyclerView rv1;
    adapter1 adp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        try {
            rv1=findViewById(R.id.rv1);
            rv1.setLayoutManager(new LinearLayoutManager(this));
            FirebaseRecyclerOptions<model1> options =
                    new FirebaseRecyclerOptions.Builder<model1>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("sports"), model1.class)
                            .build();
            adp1=new adapter1(options,this);
            rv1.setAdapter(adp1);
        }catch (Exception e)
        {

        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        adp1.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adp1.stopListening();
    }

}