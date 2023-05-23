package com.example.custom_drawer.view_pager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.custom_drawer.R;

import com.example.custom_drawer.single_game;
import com.squareup.picasso.Picasso;

import java.util.List;

import proto.Game;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>{

    private List<Game> list;

    private ViewPager2 viewPager2;
    Context c;

     public SliderAdapter(Context context, List<Game> list, ViewPager2 viewPager2) {
        this.list = list;
        this.viewPager2 = viewPager2;
         this.c = context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_item,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        if (position >list.size()-2) {
//            viewPager2.setCurrentItem(0, true);
//        }

        String imageurl="";
        imageurl="https:"+"//images.igdb.com/igdb/image/upload/t_1080p/"+list.get(position).getCover().getImageId()+".jpg";

//        holder.Setimage(imageurl);
        Picasso.get().load(imageurl).into(holder.imageView);
        
//        holder.name.setText(list.get(position).getName());
//        holder.Setname(list.get(positi0on));

        Game data = list.get(position);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.showtoast(position);

//                Toast.makeText(c, list.get(position).getName()+"", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(c,  single_game.class);
                intent.putExtra("game",list.get(position));
                c.startActivity(intent);

//                Intent intent=new Intent(c,  pager_game.class);
//                intent.putExtra("game",data);
//                ActivityOptions.makeSceneTransitionAnimation((Activity) c).toBundle();
//                ActivityOptionsCompat activityOptionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) c,holder.imageView, ViewCompat.getTransitionName(holder.imageView));
//                c.startActivity(intent,activityOptionsCompat.toBundle());


            }
        });
        
        
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
//            name=itemView.findViewById(R.id.name);
        }

        void Setimage(String slideritem){

            Picasso.get().load(slideritem).into(imageView);

        }
//        void Setname(Slideritem slideritem){
//            name.setText(slideritem.getName());
//        }

        void showtoast(int pos){

            Toast.makeText(c, list.get(pos).getName()+"", Toast.LENGTH_SHORT).show();


        }


    }
}
