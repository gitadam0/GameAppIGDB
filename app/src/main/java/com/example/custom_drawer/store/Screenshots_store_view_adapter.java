package com.example.custom_drawer.store;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.custom_drawer.R;
import com.squareup.picasso.Picasso;

import proto.Game;

public class Screenshots_store_view_adapter extends RecyclerView.Adapter<Screenshots_store_view_adapter.SliderViewHolder>{

    private String[] game;

    private ViewPager2 viewPager2;
    Context c;

     public Screenshots_store_view_adapter(Context context, String[] list, ViewPager2 viewPager2) {
        this.game = list;
        this.viewPager2 = viewPager2;
         this.c = context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_screenshots,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        if (position >list.size()-2) {
//            viewPager2.setCurrentItem(0, true);
//        }
        String imageurl="";
//        for(int i=0; i<5;i++){}
        
        imageurl="https:"+"//images.igdb.com/igdb/image/upload/t_720p/"
                +game[position]+
                ".jpg";



//        holder.Setimage(imageurl);
        Picasso.get().load(imageurl).into(holder.imageView);
        
//        holder.name.setText(list.get(position).getName());
//        holder.Setname(list.get(positi0on));



        
    }

    @Override
    public int getItemCount() {
        return game.length;
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView name;

         SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
//            name=itemView.findViewById(R.id.name);
        }

        void Setimage(String slideritem){

            Picasso.get().load(slideritem).into(imageView);

        }
//        void Setname(Slideritem slideritem){
//            name.setText(slideritem.getName());
//        }




    }
}
