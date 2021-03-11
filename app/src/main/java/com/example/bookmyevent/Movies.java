package com.example.bookmyevent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Movies extends AppCompatActivity {

    RecyclerView rv;
    myadapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        rv=findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("movies"), model.class)
                        .build();
        adp=new myadapter(options,this);
        rv.setAdapter(adp);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adp.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adp.stopListening();
    }
}