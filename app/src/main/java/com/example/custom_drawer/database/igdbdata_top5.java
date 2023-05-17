package com.example.custom_drawer.database;



import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;

import com.api.igdb.apicalypse.APICalypse;
import com.api.igdb.apicalypse.Sort;
import com.api.igdb.exceptions.RequestException;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.ProtoRequestKt;
import com.example.custom_drawer.Fragment1;
import com.example.custom_drawer.R;

import java.util.List;

import proto.Game;

public class igdbdata_top5 extends AsyncTask<Void, Void, List<Game>> {

        List<Game> games;
    ProgressBar progressBar;
        View c;

    public igdbdata_top5(View context) {
        this.c = context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected List<Game> doInBackground(Void... params) {

        IGDBWrapper wrapper = IGDBWrapper.INSTANCE;
        wrapper.setCredentials("bq2pe47e9t3uulm2oqj4fddej6z6z3", "11d0g80phx6lg799j2b5njk5lay2cl");
        APICalypse apicalypse1 = new APICalypse().fields("name,cover.*,rating_count,rating,screenshots.*")
                .where("cover != null & rating != null & aggregated_rating_count != null ")
                .limit(25).sort("rating_count",Sort.DESCENDING);
        try{
            games = ProtoRequestKt.games(wrapper, apicalypse1);

        } catch(RequestException e) {

        }

        return games;
    }

    @Override
    protected void onPostExecute(List<Game> games) {
        super.onPostExecute(games);
        progressBar=c.findViewById(R.id.loadingbar);
        progressBar.setVisibility(View.GONE);
    }
}



