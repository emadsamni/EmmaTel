package com.example.emma_tel.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.emma_tel.R;
import com.example.emma_tel.activites.MainActivity;
import com.example.emma_tel.adapters.AccessoryAdapter;
import com.example.emma_tel.models.Accessory;
import com.example.emma_tel.models.MainSlider;
import com.example.emma_tel.viewmodels.DataViewModels;

import java.util.List;

public class AccessoriesFragment extends Fragment {

    DataViewModels dataViewModels;
    private RecyclerView myRecyclerView;
    private AccessoryAdapter mAdapter;

    public AccessoriesFragment()
    {

    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_accessories, container,false);
        assignUIReference(view);
        //getMainSlider();
        return  view;
    }
    private void assignUIReference(View view){
        dataViewModels = ViewModelProviders.of(getActivity()).get(DataViewModels.class);
        myRecyclerView =(RecyclerView) view.findViewById(R.id.recycler_accessory);
        dataViewModels.getAccessoryList(getActivity()).observe(getActivity(), new Observer<List<Accessory>>() {
            @Override
            public void onChanged(@Nullable List<Accessory> accessories) {
                mAdapter =new AccessoryAdapter(accessories,getActivity());
                myRecyclerView.setAdapter(mAdapter);
                LinearLayoutManager layoutManager =new LinearLayoutManager( getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                myRecyclerView.setLayoutManager(layoutManager);
            }
        });
    }


}
