package com.example.bookmyevent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class adapter1 extends FirebaseRecyclerAdapter<model1,adapter1.myviewholder>
{
    public adapter1(@NonNull FirebaseRecyclerOptions<model1> options,Context ct) {
        super(options);
        this.context=ct;
    }


    Context context;
    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, int i, @NonNull final model1 model1) {
        try {
            holder.matname.setText(model1.getMatch());
            holder.location.setText(model1.getLocation());
            holder.date.setText(model1.getDate());

            Glide.with(holder.img.getContext()).load(model1.getPurl()).into(holder.img);
            holder.lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent i = new Intent(context, Booking_sports.class);
                    i.putExtra("purl",model1.getPurl().toString());
                    i.putExtra("match",model1.getMatch());
                    i.putExtra("location",model1.getLocation());
                    i.putExtra("date",model1.getDate());
                    i.putExtra("price",model1.getPrice());
                    i.putExtra("tickets",model1.getTickets());
                    i.putExtra("time",model1.getTime());
                    i.putExtra("id",model1.getId());
                    context.startActivity(i);
                }
            });


        }
        catch (Exception e)
        {

        }
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.doublerow,parent,false);
        return new myviewholder(view);

    }



    class myviewholder extends RecyclerView.ViewHolder
    {

        CircleImageView img;
        LinearLayout lay;
        TextView matname,location,date,theatre;

       // TextView mname,hero,heroine;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            try {
                img = itemView.findViewById(R.id.iv);
                matname = itemView.findViewById(R.id.matname);
                location = itemView.findViewById(R.id.location);
                date = itemView.findViewById(R.id.date);

                lay=itemView.findViewById(R.id.wrapper3);

            }catch (Exception e)
            {

                //Toast.makeText(context,e,Toast.LENGTH_LONG).show();
            }
        }
    }
}