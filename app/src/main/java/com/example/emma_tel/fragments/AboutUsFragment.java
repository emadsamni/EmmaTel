package com.example.emma_tel.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emma_tel.R;
import com.example.emma_tel.activites.StepperListener;

public class AboutUsFragment extends Fragment  {
    public AboutUsFragment() {
    }


    public static AboutUsFragment newInstance() {
        AboutUsFragment fragment = new AboutUsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_about_us, container,false);
        return  view;

    }
}
