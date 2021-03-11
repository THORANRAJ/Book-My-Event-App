package com.example.bookmyevent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Purchase_history extends AppCompatActivity {

    order_adapter adp11;
    RecyclerView rc2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);
        rc2=findViewById(R.id.rc2);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user==null)
        {
            return;
        }
        String userId=user.getUid();
        rc2.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<order_model> options =
                new FirebaseRecyclerOptions.Builder<order_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("tickets").child(userId), order_model.class)
                        .build();

        adp11=new order_adapter(options,this);
        rc2.setAdapter(adp11);

    }
    @Override
    protected void onStart() {
        super.onStart();
        adp11.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adp11.stopListening();
    }
}