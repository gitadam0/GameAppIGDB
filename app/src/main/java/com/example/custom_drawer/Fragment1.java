package com.example.custom_drawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.custom_drawer.database.igdbdata_popular5;
import com.example.custom_drawer.database.igdbdata_top25;
import com.example.custom_drawer.view_pager.SliderAdapter;
import com.google.protobuf.Any;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import me.relex.circleindicator.CircleIndicator3;

public class Fragment1 extends Fragment {

    TextView t;
    ViewPager2 viewPager2;
    
    RecyclerView recy,recy_hory;
    ProgressBar progressBar;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        
        View view= inflater.inflate(R.layout.fragment1,container,false);




        igdbdata_popular5 task;
        task = new igdbdata_popular5();

        task.execute();


         viewPager2=view.findViewById(R.id.viewpager2);
        CircleIndicator3 indicator=view.findViewById(R.id.indicator);
        


        SliderAdapter adapter = null;
        try {
            adapter = new SliderAdapter(getContext(),task.get(),viewPager2);
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer transformer=new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(8));
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1- Math.abs(position);
                page.setScaleY(0.8f+r*0.2f);
            }
        });

        viewPager2.setPageTransformer(transformer);
        viewPager2.setAdapter(adapter);

        indicator.setViewPager(viewPager2);


        igdbdata_top25 task2;

        task2 = new igdbdata_top25(view,getActivity());

        task2.execute();
        
        recy=view.findViewById(R.id.recy);
        recy_hory=view.findViewById(R.id.recy_hory);



        recy_adapter adapter1 = null;
        recy_adapter_hory adapter2 = null;
        try {
             adapter1=new recy_adapter(task2.get(),getContext());
             adapter2=new recy_adapter_hory(task2.get(),getContext());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //        GridLayoutManager
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recy.setLayoutManager(layoutManager);
        recy.setAdapter(adapter1);



        LinearLayoutManager layoutManager_hory = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recy_hory.setLayoutManager(layoutManager_hory);

        recy_hory.setAdapter(adapter2);
        SnapHelper snapHelper = new LinearSnapHelper();

        // on below line we are attaching this snap helper to our recycler view.
        snapHelper.attachToRecyclerView(recy_hory);



        Spinner mSpinner = view.findViewById(R.id.spinner);

                // Create a list to display in the Spinner
        List<String> mList = new ArrayList<String>() ;
        mList.add("Ddd");

        // Create an adapter as shown below
        ArrayAdapter mArrayAdapter =new ArrayAdapter<>(view.getContext(), R.layout.spinner_list, mList);
        mArrayAdapter.setDropDownViewResource(R.layout.spinner_list);

        // Set the adapter to the Spinner
        mSpinner.setAdapter(mArrayAdapter);


        return view;

    }











}
