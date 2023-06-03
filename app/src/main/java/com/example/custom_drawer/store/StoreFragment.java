package com.example.custom_drawer.store;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.custom_drawer.R;
import com.example.custom_drawer.database.Arraylist_games;
import com.example.custom_drawer.recy_adapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class StoreFragment extends Fragment {
    TextView txt;
    RecyclerView store_recy;
    String result = "";
    ProgressDialog progressDialog;

      ArrayList<STORE_GAMES> arrayList_games = new ArrayList<>();







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        store_recy=view.findViewById(R.id.store_recy);


        try {

            progressDialog = new ProgressDialog(view.getContext());
            progressDialog.setMessage("Loading..."); // Setting Message
            progressDialog.setTitle("Loading Games"); // Setting Title
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
            progressDialog.show(); // Display Progress Dialog
            progressDialog.setCancelable(false);





            queryData();


        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(view.getContext(), arrayList_games + "", Toast.LENGTH_SHORT).show();

        return view;
    }


    public void queryData() throws IOException {
        URL url = networkUtils.buildUrl();

        new DataTask().execute(url);


    }


    public class DataTask extends AsyncTask<URL, Void, String> {


        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String data = null;
            try {
                data = networkUtils.getDatafromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {

            setcityData(s);
        }

        public void setcityData(String data) {








            JSONObject myObject = null;
            String id_ = "a";

            try {
                JSONArray datar = new JSONArray(data);

                JSONObject cityo = null;

                for (int i = 0; i < datar.length(); i++) {
                    cityo = datar.getJSONObject(i);

                    arrayList_games.add(
                            new STORE_GAMES(
                                    cityo.get("id").toString(),
                                    cityo.get("name").toString(),
                                    cityo.get("price").toString(),
                                    cityo.get("rating").toString(),
                                    cityo.get("rating_count").toString(),
                                    cityo.get("genres").toString(),
                                    cityo.get("modes").toString(),
                                    cityo.get("release_dates").toString(),
                                    cityo.get("summary").toString(),
                                    cityo.get("cover_image_id").toString(),
                                    cityo.get("video_id").toString(),
                                    cityo.get("screenshots_image_id").toString()

                            )
                    );


                }

                recy_adapter_store adapter1 = null;

                adapter1=new recy_adapter_store(arrayList_games,getContext());


                GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
                store_recy.setLayoutManager(layoutManager);
                store_recy.setAdapter(adapter1);

                Arraylist_games.arraylist=arrayList_games;
                progressDialog.dismiss();



//
//                String genres_array=datar.getJSONObject(0).get("genres").toString();
//                JSONArray genres_array_json=new JSONArray(genres_array);
//
//                String genres="";
//                for (int i = 0; i < genres_array_json.length(); i++) {
//                    genres+=",";
//                    genres+=genres_array_json.getJSONObject(i).get("name");
//
//                }

                //txt.setText(genres.replaceFirst(",",""));
                //txt.setText(arrayList_games.get(0).screenshots_image_id);



            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }


}