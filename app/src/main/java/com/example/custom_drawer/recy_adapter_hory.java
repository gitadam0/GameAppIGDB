package com.example.custom_drawer;

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
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import proto.Game;

public class recy_adapter_hory extends RecyclerView.Adapter<recy_adapter_hory.ViewHolder> {
    private List<Game> dataList;
    private Context context;

    public recy_adapter_hory(List<Game> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_item_hory, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game data = dataList.get(position);
//        holder.img.setImageResource(data);
        String imageurl="";
        imageurl="https:"+"//images.igdb.com/igdb/image/upload/t_720p/"+data.getCover().getImageId()+".jpg";
        Picasso.get().load(imageurl).into(holder.img);
        Picasso.get().load(imageurl).into(holder.img2);

        holder.name.setText(data.getName());

        String r= String.valueOf(data.getRating());
        String r2= Double.toString(data.getRating());

//        holder.rating.setText(r.charAt(0)+"⭐");


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context,  pager_game.class);
//                intent.putExtra("game",data);
//                context.startActivity(intent);




                Intent intent=new Intent(context,  single_game.class);
                intent.putExtra("game",data);
                ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle();
                ActivityOptionsCompat activityOptionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,holder.img, ViewCompat.getTransitionName(holder.img));
                context.startActivity(intent);








            }
        });
    }






    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img2;
        TextView name,rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            img2 = itemView.findViewById(R.id.img2);
             name=itemView.findViewById(R.id.txtname);
//             rating=itemView.findViewById(R.id.txtrating);
        }
    }
}