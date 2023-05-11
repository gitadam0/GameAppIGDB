package com.example.custom_drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import proto.Game;

public class recy_adapter extends RecyclerView.Adapter<recy_adapter.ViewHolder> {
    private List<Game> dataList;
    private Context context;

    public recy_adapter(List<Game> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game data = dataList.get(position);
//        holder.img.setImageResource(data);
        String imageurl="";
        imageurl="https:"+"//images.igdb.com/igdb/image/upload/t_720p/"+data.getCover().getImageId()+".jpg";
        Picasso.get().load(imageurl).into(holder.img);
    }






    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }
}