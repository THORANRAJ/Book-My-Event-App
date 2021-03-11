package com.example.bookmyevent;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class res_adapter extends FirebaseRecyclerAdapter<res_model,res_adapter.myviewholder>
{
    Context context;
    public res_adapter(@NonNull FirebaseRecyclerOptions<res_model> options, Context ct) {
        super(options);
        this.context=ct;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int i, @NonNull final res_model r_model) {

       /* try {
            holder.tvmp.setText(ord_model.getName());
            holder.tvs.setText(ord_model.getPlace());
            holder.tvtic.setText(ord_model.getTickets());
            holder.datetv.setText(ord_model.getDate());
            // Log.d("mian", ord_model.getName());
        }
        catch (Exception e)
        {
            Log.d("debug",e.toString());
        }*/
       holder.hname.setText(r_model.getHname());
       holder.resloc.setText(r_model.getLocation());
       String str=r_model.getRating();
    //   Log.d("thoran",str);
        try{
            
            if(str.equals("r1"))
            {
                holder.rat2.setVisibility(View.GONE);
                holder.rat3.setVisibility(View.GONE);
                holder.rat4.setVisibility(View.GONE);
                holder.rat5.setVisibility(View.GONE);
            }
            else if(str.equals("r2"))
            {
                holder.rat3.setVisibility(View.GONE);
                holder.rat4.setVisibility(View.GONE);
                holder.rat5.setVisibility(View.GONE);
            }
            else if(str.equals("r3"))
            {
                holder.rat4.setVisibility(View.GONE);
                holder.rat5.setVisibility(View.GONE);
            }
            else if(str.equals("r4"))
            {
                holder.rat5.setVisibility(View.GONE);
            }
            else if(str.equals("r5"))
            {
                holder.rat1.setVisibility(View.VISIBLE);
                holder.rat2.setVisibility(View.VISIBLE);
                holder.rat3.setVisibility(View.VISIBLE);
                holder.rat4.setVisibility(View.VISIBLE);
                holder.rat5.setVisibility(View.VISIBLE);
            }
            
            
        }catch (Exception e)
        {
            Log.d("debug",e.toString());
        }


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.res_layout,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {


      //  TextView tvmp,tvs,tvtic,datetv;
        TextView hname,resloc,rat1,rat2,rat3,rat4,rat5;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            hname=itemView.findViewById(R.id.hname);
            resloc=itemView.findViewById(R.id.resloc);
            rat1=itemView.findViewById(R.id.rat1);
            rat2=itemView.findViewById(R.id.rat2);
            rat3=itemView.findViewById(R.id.rat3);
            rat4=itemView.findViewById(R.id.rat4);
            rat5=itemView.findViewById(R.id.rat5);


        }
    }
}
