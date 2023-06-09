package com.example.custom_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.custom_drawer.database.Igdbdata_top25_activity;
import com.example.custom_drawer.database.igdbdata_popular5;
import com.example.custom_drawer.database.igdbdata_top25;
import com.example.custom_drawer.screenshots_singlegam.Screenshots_view_adapter;
import com.example.custom_drawer.view_pager.SliderAdapter;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.DefaultPlayerUiController;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import proto.Game;
import proto.Genre;

public class single_game extends AppCompatActivity {
    ImageView img;
    TextView name,progress,genre,mode,release,description;
    ImageButton back;
    ProgressBar progressBar;
    ViewPager2 viewPager2;
    RecyclerView recy_hory;
    ProgressDialog progressDialog;



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
        genre=findViewById(R.id.genre);
        mode=findViewById(R.id.mode);
        release=findViewById(R.id.release);
        description=findViewById(R.id.description);



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

        String g="";
        for(Genre a :game.getGenresList()){
            g+=",";
            g+=a.getName();

        }

        genre.setText(g.replaceFirst(",",""));

        String m="";
        for(proto.GameMode a :game.getGameModesList()){
            m+=",";
            m+=a.getName();

        }

        mode.setText(m.replaceFirst(",",""));

        release.setText(game.getReleaseDates(0).getHuman());

        description.setText(game.getSummary());


        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);



        YouTubePlayerListener listener = new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                // using pre-made custom ui
//                DefaultPlayerUiController defaultPlayerUiController = new DefaultPlayerUiController(youTubePlayerView, youTubePlayer);
//                youTubePlayerView.setCustomPlayerUi(defaultPlayerUiController.getRootView());
                String videoId = game.getVideos(0).getVideoId().toString();
                youTubePlayer.cueVideo(videoId,0);

            }
        };

// disable iframe ui
        IFramePlayerOptions options = new IFramePlayerOptions.Builder().controls(1).fullscreen(0).build();

        IFramePlayerOptions iFramePlayerOptions = new IFramePlayerOptions.Builder()
                .controls(0)
                // enable full screen button
                .fullscreen(1)
                .build();

        youTubePlayerView.initialize(listener, options);

        youTubePlayerView.addFullscreenListener(new FullscreenListener() {
            @Override
            public void onEnterFullscreen(@NonNull View fullscreenView, @NonNull Function0<Unit> exitFullscreen) {
                Toast.makeText(single_game.this, "entered full screen", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onExitFullscreen() {
                Toast.makeText(single_game.this, "exit full screen", Toast.LENGTH_SHORT).show();
            }
        });











        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();


            }
        });



        Screenshots_view_adapter adapter = null;

        adapter = new Screenshots_view_adapter(single_game.this, game ,viewPager2);






//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    Thread.sleep(10000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                progressDialog.dismiss();
//            }
//        }).start();


        Igdbdata_top25_activity task2;

        task2 = new Igdbdata_top25_activity();

        task2.execute();

        recy_adapter_hory adapter2 = null;
        try {

            adapter2=new recy_adapter_hory(task2.get(),single_game.this);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recy_hory=findViewById(R.id.recy_hory);
        LinearLayoutManager layoutManager_hory = new LinearLayoutManager(single_game.this,LinearLayoutManager.HORIZONTAL,false);
        recy_hory.setLayoutManager(layoutManager_hory);

        recy_hory.setAdapter(adapter);
        SnapHelper snapHelper = new LinearSnapHelper();

        // on below line we are attaching this snap helper to our recycler view.
        snapHelper.attachToRecyclerView(recy_hory);






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