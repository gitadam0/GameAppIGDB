package com.example.custom_drawer.store;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.custom_drawer.R;
import com.example.custom_drawer.single_game;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import proto.Game;

public class recy_adapter_store extends RecyclerView.Adapter<recy_adapter_store.ViewHolder> {
    private ArrayList<STORE_GAMES> dataList;
    private Context context;


    public recy_adapter_store(ArrayList<STORE_GAMES> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_item_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos=position;
        STORE_GAMES data = dataList.get(pos);

        String imageurl="";
        imageurl="https:"+"//images.igdb.com/igdb/image/upload/t_720p/"+data.cover_image_id+".jpg";
        Picasso.get().load(imageurl).into(holder.img);
        holder.name.setText(data.name);

        String r= String.valueOf(data.rating);
        String r2= Double.toString(Double.parseDouble(data.rating));

        holder.rating.setText(r.charAt(0)+"⭐"+ "("+data.rating_count+" reviews)");

        holder.price.setText(data.price+"$");







        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context,  pager_game.class);
//                intent.putExtra("game",data);
//                context.startActivity(intent);




                Intent intent=new Intent(context,  single_game_store.class);
                intent.putExtra("game",  data.name);
                ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle();
                ActivityOptionsCompat activityOptionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,holder.img, ViewCompat.getTransitionName(holder.img));
                //context.startActivity(intent,activityOptionsCompat.toBundle());
                ((Activity) context).startActivityForResult(intent,1,activityOptionsCompat.toBundle());









            }
        });



//        holder.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                notifyDataSetChanged();
//                if(dataList.get(pos).added_tocart){
//
//                    holder.fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.add));
//
//                    //dataList.get(pos).added_tocart=false;
//                    data.added_tocart=false;
//
//                }else if(!dataList.get(pos).added_tocart){
//
//
//                    holder.fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.remove_fromcart2));
//
//
//                    //dataList.get(pos).added_tocart=true;
//                    data.added_tocart=true;
//
//                }
//
//            }
//        });





    }






    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name,rating,price;
        FloatingActionButton fab;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
             name=itemView.findViewById(R.id.txtname);
             rating=itemView.findViewById(R.id.txtrating);
             price=itemView.findViewById(R.id.price);
//             fab=itemView.findViewById(R.id.fab);
        }
    }
}