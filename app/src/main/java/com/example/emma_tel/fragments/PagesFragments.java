package com.example.emma_tel.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emma_tel.R;
import com.example.emma_tel.adapters.OfferAdapter;
import com.example.emma_tel.adapters.PageAdapter;
import com.example.emma_tel.models.Offer;
import com.example.emma_tel.models.Page;
import com.example.emma_tel.viewmodels.DataViewModels;

import java.util.List;

public class PagesFragments extends Fragment {

    DataViewModels dataViewModels;
    private RecyclerView myRecyclerView;
    private PageAdapter mAdapter;
    public PagesFragments()
    {

    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_pages, container,false);
        assignUIReference(view);
        return  view;
    }
    private void assignUIReference(View view){
        dataViewModels = ViewModelProviders.of(getActivity()).get(DataViewModels.class);
        myRecyclerView =(RecyclerView) view.findViewById(R.id.recycler_page);
        dataViewModels.getPageList(getActivity()).observe(getActivity(), new Observer<List<Page>>() {
            @Override
            public void onChanged(@Nullable List<Page> pages) {
                mAdapter =new PageAdapter(pages,getActivity());
                myRecyclerView.setAdapter(mAdapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager( getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                myRecyclerView.setLayoutManager(layoutManager);
            }
        });
    }
}

