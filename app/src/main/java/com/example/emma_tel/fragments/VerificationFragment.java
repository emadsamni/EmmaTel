package com.example.emma_tel.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emma_tel.R;
import com.example.emma_tel.activites.RegisterActivity;
import com.example.emma_tel.activites.StepperListener;
import com.example.emma_tel.viewmodels.LoginViewModel;
import com.goodiebag.pinview.Pinview;

public class VerificationFragment extends Fragment  implements StepperListener {
    Pinview codeText;
    LoginViewModel viewModel;

    public VerificationFragment() {
    }


    public static VerificationFragment newInstance() {
        VerificationFragment fragment = new VerificationFragment();

        return fragment;
    }

    private void assignUIReference (View view){
        codeText = view.findViewById(R.id.code_id);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_verification, container,false);
        assignUIReference(view);
        return  view;

    }

    private void verifyUser(String code) {
        viewModel.verifyUser(getActivity(),((RegisterActivity)getContext()).getPhone(),code);
    }


    @Override
    public void onNextClicked() {
      verifyUser(codeText.getValue());
    }

    @Override
    public void onBackClicked() {

    }
}
