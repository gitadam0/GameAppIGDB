package com.example.custom_drawer.view_pager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.custom_drawer.MainActivity;
import com.example.custom_drawer.R;
import com.squareup.picasso.Picasso;

import proto.Game;

public class pager_game extends AppCompatActivity {
    ImageView img;
    TextView name,progress;
    ImageButton back;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_game);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();


        img=findViewById(R.id.game_img);
        name=findViewById(R.id.game_name);
        back=findViewById(R.id.backbutton);
        progressBar=findViewById(R.id.progressBar);
        progress=findViewById(R.id.progress);

        Game game= (Game) getIntent().getSerializableExtra("game");


        String imageurl="";
        imageurl="https:"+"//images.igdb.com/igdb/image/upload/t_720p/"+game.getCover().getImageId()+".jpg";

        Picasso.get().load(imageurl).into(img);


        name.setText(game.getName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress(0,true);
        }

        ProgressBarAnimation anim = new ProgressBarAnimation(progressBar,  0, (float) game.getRating());
        anim.setDuration(1000);
        progressBar.startAnimation(anim);
        progress.setText((int)game.getRating()+"");




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();


            }
        });



    }

    public class ProgressBarAnimation extends Animation {
        private ProgressBar progressBar;
        private float from;
        private float  to;

        public ProgressBarAnimation(ProgressBar progressBar, float from, float to) {
            super();
            this.progressBar = progressBar;
            this.from = from;
            this.to = to;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            float value = from + (to - from) * interpolatedTime;
            progressBar.setProgress((int) value);
        }

    }


}