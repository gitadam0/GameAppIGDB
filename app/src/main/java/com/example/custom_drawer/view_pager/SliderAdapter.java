package com.example.custom_drawer.view_pager;

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

import java.util.List;

import proto.Game;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>{

    private List<Game> list;

    private ViewPager2 viewPager2;

     public SliderAdapter(List<Game> list, ViewPager2 viewPager2) {
        this.list = list;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
//        if (position >list.size()-2) {
//            viewPager2.setCurrentItem(0, true);
//        }

        String imageurl="";
        imageurl="https:"+"//images.igdb.com/igdb/image/upload/t_720p/"+list.get(position).getCover().getImageId()+".jpg";

        holder.Setimage(imageurl);
        holder.name.setText(list.get(position).getName());
//        holder.Setname(list.get(positi0on));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView name;

         SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
        }

        void Setimage(String slideritem){

            Picasso.get().load(slideritem).into(imageView);

        }
        void Setname(Slideritem slideritem){
            name.setText(slideritem.getName());
        }
    }
}
