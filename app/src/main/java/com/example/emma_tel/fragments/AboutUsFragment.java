package com.example.emma_tel.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.models.Page;
import com.example.emma_tel.viewmodels.DataViewModels;

public class AboutUsFragment extends Fragment  {
    TextView aboutUsText;
    DataViewModels dataViewModels;
    public AboutUsFragment() {
    }


    public static AboutUsFragment newInstance() {
        AboutUsFragment fragment = new AboutUsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_about_us, container,false);
        aboutUsText=view.findViewById(R.id.content);
        dataViewModels = ViewModelProviders.of(getActivity()).get(DataViewModels.class);
        dataViewModels.getAboutUs(getActivity() ).observe(getActivity(), new Observer<Page>() {
            @Override
            public void onChanged(@Nullable Page page) {
                aboutUsText.setText(page.getContent());
            }
        });
        return  view;

    }
}
