package com.example.custom_drawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.custom_drawer.database.igdbdata_top5;
import com.example.custom_drawer.view_pager.SliderAdapter;

import java.util.concurrent.ExecutionException;

import me.relex.circleindicator.CircleIndicator3;

public class Fragment1 extends Fragment {

    TextView t;
    ViewPager2 viewPager2;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment1,container,false);

        igdbdata_top5 task;
        task = new igdbdata_top5();
        task.execute();

//
//
//t=view.findViewById(R.id.textView);
//
//        view.findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    String ss=task.get()+"";
//                    t.setText(ss);
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });


         viewPager2=view.findViewById(R.id.viewpager2);
        CircleIndicator3 indicator=view.findViewById(R.id.indicator);





        SliderAdapter adapter = null;
        try {
            adapter = new SliderAdapter(task.get(),viewPager2);
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        viewPager2.setAdapter(adapter);


        indicator.setViewPager(viewPager2);







        return view;
    }











}
