package com.example.emma_tel.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emma_tel.R;
import com.example.emma_tel.adapters.NotificatonAdapter;
import com.example.emma_tel.models.Notification;
import com.example.emma_tel.viewmodels.DataViewModels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

                List<Notification> notifications1 =new ArrayList<>();
                Date today = new Date();
                for (int i=0;i<notifications.size();i++) {

                    long difference = today.getTime() - notifications.get(i).getCreated_at().getTime();
                   float daysBetween = (difference / (1000 * 60 * 60 * 24));
                    if (daysBetween < 7) {
                        notifications1.add(notifications.get(i));
                    }

                }
                mAdapter =new NotificatonAdapter(notifications1,getActivity());
                myRecyclerView.setAdapter(mAdapter);
                LinearLayoutManager layoutManager =new LinearLayoutManager( getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                myRecyclerView.setLayoutManager(layoutManager);

            }
        });
    }
}


