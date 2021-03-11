package com.example.bookmyevent;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>
{
    Context context;
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options,Context ct) {
        super(options);
        this.context=ct;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int i, @NonNull final model model) {

        holder.mname.setText(model.getName());
        holder.hero.setText(model.getHero());
        holder.heroine.setText(model.getHeroine());
        holder.theatre.setText(model.getTheatre());
        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);
        holder.lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,Book_movies.class);
                intent.putExtra("movie",model.getName());
                intent.putExtra("hero",model.getHero());
                intent.putExtra("heroine",model.getHeroine());
                intent.putExtra("theatre",model.getTheatre());
                intent.putExtra("purl",model.getPurl());
                intent.putExtra("price",model.getPrice());
                intent.putExtra("date",model.getDate());
                intent.putExtra("tickets",model.getTickets());
                intent.putExtra("time",model.getTime());
                intent.putExtra("rating",model.getRating());
                intent.putExtra("id",model.getId());
                context.startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {

        CircleImageView img;
        TextView mname,hero,heroine,theatre;
        LinearLayout lay1;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.iv);
            mname=itemView.findViewById(R.id.matname);
            hero=itemView.findViewById(R.id.location);
            heroine=itemView.findViewById(R.id.date);
            theatre=itemView.findViewById(R.id.theatre);
            lay1=itemView.findViewById(R.id.layout1);

        }
    }
}