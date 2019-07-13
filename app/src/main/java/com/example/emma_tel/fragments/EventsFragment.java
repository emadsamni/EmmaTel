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
import com.example.emma_tel.adapters.EventAdapter;
import com.example.emma_tel.models.Event;
import com.example.emma_tel.viewmodels.DataViewModels;

import java.util.List;

public class EventsFragment  extends Fragment {
    DataViewModels dataViewModels;
    private RecyclerView myRecyclerView;
    private EventAdapter mAdapter;

    public EventsFragment()
    {

    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_events, container,false);
        assignUIReference(view);
        return  view;
    }
    private void assignUIReference(View view){
        dataViewModels = ViewModelProviders.of(getActivity()).get(DataViewModels.class);
        myRecyclerView =(RecyclerView) view.findViewById(R.id.recycler_events);
        dataViewModels.getEventList(getActivity()).observe(getActivity(), new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                mAdapter =new EventAdapter(events,getActivity());
                myRecyclerView.setAdapter(mAdapter);
                LinearLayoutManager layoutManager =new LinearLayoutManager( getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                myRecyclerView.setLayoutManager(layoutManager);
            }
        });

    }
}
