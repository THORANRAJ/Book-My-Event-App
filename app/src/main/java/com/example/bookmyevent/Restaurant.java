package com.example.bookmyevent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Restaurant extends AppCompatActivity {

    RecyclerView re;
    res_adapter adp22;
    Button hotelbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        re=findViewById(R.id.re);
        hotelbtn=findViewById(R.id.hotelbtn);
       // getActionBar().show();
       // String userId=user.getUid();
        re.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<res_model> options =
                new FirebaseRecyclerOptions.Builder<res_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("hotel"), res_model.class)
                        .build();
        adp22=new res_adapter(options,this);
        re.setAdapter(adp22);
        hotelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Restaurant.this,Mainpart.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        adp22.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adp22.stopListening();

    }
    public boolean onCreateOptionMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                process(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                process(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void process(String s)
    {
        FirebaseRecyclerOptions<res_model> options =
                new FirebaseRecyclerOptions.Builder<res_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("hotel").orderByChild("location").startAt(s).endAt(s+"\uf8ff"), res_model.class)
                        .build();
        adp22=new res_adapter(options,this);
        adp22.startListening();
        re.setAdapter(adp22);
    }
}