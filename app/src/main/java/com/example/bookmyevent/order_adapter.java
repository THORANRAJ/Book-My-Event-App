package com.example.bookmyevent;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class order_adapter extends FirebaseRecyclerAdapter<order_model,order_adapter.myviewholder>
{
    Context context;
    public order_adapter(@NonNull FirebaseRecyclerOptions<order_model> options, Context ct) {
        super(options);
        this.context=ct;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int i, @NonNull final order_model ord_model) {

     /*   holder.mname.setText(model.getName());
        holder.hero.setText(model.getHero());
        holder.heroine.setText(model.getHeroine());
        holder.theatre.setText(model.getTheatre());
        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);
*/
     try {
         holder.tvmp.setText(ord_model.getName());
         holder.tvs.setText(ord_model.getPlace());
         holder.tvtic.setText(ord_model.getTickets());
         holder.datetv.setText(ord_model.getDate());
        // Log.d("mian", ord_model.getName());
     }
     catch (Exception e)
     {
         Log.d("debug",e.toString());
     }
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.triple_rows,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {

      //  CircleImageView img;
        //TextView mname,hero,heroine,theatre;
        //LinearLayout lay1;
        TextView tvmp,tvs,tvtic,datetv;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            tvmp=itemView.findViewById(R.id.tvmp);
            tvs=itemView.findViewById(R.id.tvs);
            tvtic=itemView.findViewById(R.id.tvtic);
            datetv=itemView.findViewById(R.id.datetv);

          /*  img=itemView.findViewById(R.id.iv);
            mname=itemView.findViewById(R.id.matname);
            hero=itemView.findViewById(R.id.location);
            heroine=itemView.findViewById(R.id.date);
            theatre=itemView.findViewById(R.id.theatre);
            lay1=itemView.findViewById(R.id.layout1);*/

        }
    }
}
