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

import com.example.emma_tel.R;
import com.example.emma_tel.adapters.BranchAdapter;
import com.example.emma_tel.adapters.MobileAdapter;
import com.example.emma_tel.models.Accessory;
import com.example.emma_tel.models.Branch;
import com.example.emma_tel.viewmodels.DataViewModels;

import java.util.List;

public class BranchesFragment  extends Fragment {

    DataViewModels dataViewModels;
    private RecyclerView myRecyclerView;
    private BranchAdapter mAdapter;
    public BranchesFragment()
    {

    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_branches, container,false);
        assignUIReference(view);
        //getMainSlider();
        return  view;
    }
    private void assignUIReference(View view){
        dataViewModels = ViewModelProviders.of(getActivity()).get(DataViewModels.class);
        myRecyclerView =(RecyclerView) view.findViewById(R.id.recycler_branch);
        dataViewModels.getBranchList(getActivity()).observe(getActivity(), new Observer<List<Branch>>() {
            @Override
            public void onChanged(@Nullable List<Branch> branches) {
                mAdapter =new BranchAdapter(branches,getActivity());
                myRecyclerView.setAdapter(mAdapter);
                LinearLayoutManager layoutManager =new LinearLayoutManager( getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                myRecyclerView.setLayoutManager(layoutManager);
            }
        });
    }
}
