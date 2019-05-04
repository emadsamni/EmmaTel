package com.example.emma_tel.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emma_tel.R;
import com.example.emma_tel.adapters.AccessoryAdapter;
import com.example.emma_tel.adapters.MobileAdapter;
import com.example.emma_tel.interfaces.OnItemRecyclerClicked;
import com.example.emma_tel.models.Accessory;
import com.example.emma_tel.models.Branch;
import com.example.emma_tel.models.Mobile;
import com.example.emma_tel.viewmodels.DataViewModels;

import java.util.List;

public class MobilesFragment extends Fragment implements OnItemRecyclerClicked {

    DataViewModels dataViewModels;
    private RecyclerView myRecyclerView;
    private MobileAdapter mAdapter;
    public MobilesFragment()
    {

    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_mobiles, container,false);
        assignUIReference(view);
        return  view;
    }
    private void assignUIReference(View view){
        dataViewModels = ViewModelProviders.of(getActivity()).get(DataViewModels.class);
        myRecyclerView =(RecyclerView) view.findViewById(R.id.recycler_mobile);
        dataViewModels.getMobileList(getActivity()).observe(getActivity(), new Observer<List<Mobile>>() {
            @Override
            public void onChanged(@Nullable List<Mobile> mobiles) {
                mAdapter =new MobileAdapter(mobiles,getActivity(),MobilesFragment.this);
                myRecyclerView.setAdapter(mAdapter);
                LinearLayoutManager layoutManager =new LinearLayoutManager( getActivity());
                layoutManager =new GridLayoutManager(getActivity(),2);
                myRecyclerView.setLayoutManager(layoutManager);
            }
        });
    }

    @Override
    public void onClickedRecyclerItem(Mobile mobile) {
        MobileViewDialog mobileViewDialog = new MobileViewDialog() ;
        mobileViewDialog.setMobile(mobile);
        mobileViewDialog.show(getActivity().getSupportFragmentManager(), "View JobDialog");
    }

    @Override
    public void onClickedRecyclerItem(Accessory accessory) {

    }
}
