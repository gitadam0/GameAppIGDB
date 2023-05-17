package com.example.custom_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.custom_drawer.database.igdbdata_popular5;
import com.example.custom_drawer.screenshots_singlegam.Screenshots_view_adapter;
import com.example.custom_drawer.view_pager.SliderAdapter;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

import proto.Game;

public class single_game extends AppCompatActivity {
    ImageView img;
    TextView name,progress;
    ImageButton back;
    ProgressBar progressBar;
    ViewPager2 viewPager2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singl_game);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();


        img=findViewById(R.id.game_img);
        name=findViewById(R.id.game_name);
        back=findViewById(R.id.backbutton);
        progressBar=findViewById(R.id.progressBar);
        progress=findViewById(R.id.progress);


        Game game= (Game) getIntent().getSerializableExtra("game");

        viewPager2=findViewById(R.id.screenshots_pager);
//        igdbdata_popular5 task;
//        task = new igdbdata_popular5();
//
//        task.execute();
        Screenshots_view_adapter adapter = null;

        adapter = new Screenshots_view_adapter(single_game.this, game ,viewPager2);

        viewPager2.setAdapter(adapter);




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