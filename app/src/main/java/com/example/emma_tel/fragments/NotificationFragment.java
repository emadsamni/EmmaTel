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
import com.example.emma_tel.adapters.MobileAdapter;
import com.example.emma_tel.adapters.NotificatonAdapter;
import com.example.emma_tel.adapters.OfferAdapter;
import com.example.emma_tel.models.Mobile;
import com.example.emma_tel.models.Notification;
import com.example.emma_tel.viewmodels.DataViewModels;

import java.util.List;

public class NotificationFragment  extends Fragment {

    DataViewModels dataViewModels;
    private RecyclerView myRecyclerView;
    private NotificatonAdapter mAdapter;
    public NotificationFragment()
    {

    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_notifications, container,false);
        assignUIReference(view);
        return  view;
    }
    private void assignUIReference(View view){
        dataViewModels = ViewModelProviders.of(getActivity()).get(DataViewModels.class);
        myRecyclerView =(RecyclerView) view.findViewById(R.id.recycler_note);
        dataViewModels.getNotificationList(getActivity()).observe(getActivity(), new Observer<List<Notification>>() {
            @Override
            public void onChanged(@Nullable List<Notification> notifications) {
                mAdapter =new NotificatonAdapter(notifications,getActivity());
                myRecyclerView.setAdapter(mAdapter);
                LinearLayoutManager layoutManager =new LinearLayoutManager( getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                myRecyclerView.setLayoutManager(layoutManager);

            }
        });
    }
}


