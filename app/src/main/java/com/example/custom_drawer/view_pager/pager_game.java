package com.example.custom_drawer.view_pager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.custom_drawer.R;
import com.squareup.picasso.Picasso;

import proto.Game;

public class pager_game extends AppCompatActivity {
    ImageView img;
    TextView name;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_game);
        img=findViewById(R.id.game_img);
        name=findViewById(R.id.game_name);

        Game game= (Game) getIntent().getSerializableExtra("game");


        String imageurl="";
        imageurl="https:"+"//images.igdb.com/igdb/image/upload/t_720p/"+game.getCover().getImageId()+".jpg";

        Picasso.get().load(imageurl).into(img);


        name.setText(game.getName());
    }
}