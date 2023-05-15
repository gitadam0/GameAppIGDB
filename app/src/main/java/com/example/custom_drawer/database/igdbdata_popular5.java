package com.example.custom_drawer.database;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.api.igdb.apicalypse.APICalypse;
import com.api.igdb.apicalypse.Sort;
import com.api.igdb.exceptions.RequestException;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.ProtoRequestKt;

import java.util.List;

import proto.Game;

public class igdbdata_popular5 extends AsyncTask<Void, Void, List<Game>> {

        List<Game> games;




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected List<Game> doInBackground(Void... params) {

        IGDBWrapper wrapper = IGDBWrapper.INSTANCE;
        wrapper.setCredentials("bq2pe47e9t3uulm2oqj4fddej6z6z3", "11d0g80phx6lg799j2b5njk5lay2cl");
        APICalypse apicalypse1 = new APICalypse().fields("name,cover.*,rating_count,rating")
                .where("cover != null & rating != null & aggregated_rating_count != null & rating_count>1000 ")
                .limit(5)
                .sort("popularity", Sort.DESCENDING);
        try{
            games = ProtoRequestKt.games(wrapper, apicalypse1);

        } catch(RequestException e) {

        }

        return games;
    }



}



