package com.example.emma_tel.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emma_tel.R;
import com.example.emma_tel.activites.StepperListener;

public class FinishFragment  extends Fragment implements StepperListener {
    public FinishFragment() {
    }


    public static FinishFragment newInstance() {
        FinishFragment fragment = new FinishFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_finish, container,false);
        return  view;

    }

    @Override
    public void onNextClicked() {

    }

    @Override
    public void onBackClicked() {

    }
}
