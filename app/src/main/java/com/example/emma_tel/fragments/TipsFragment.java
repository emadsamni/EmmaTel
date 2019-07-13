package com.example.emma_tel.fragments;

import android.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.models.Mobile;
import com.example.emma_tel.models.Tips;
import com.example.emma_tel.viewmodels.DataViewModels;

import java.util.List;

public class TipsFragment extends Fragment {

    int type=0;
    DataViewModels dataViewModels;

    TextView typeText , tipsText;
    Button filterButton;
    private  List<Mobile> mobileList;

    public TipsFragment() {

    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tips, container, false);
        assignUIReference(view);
        return view;
    }
    private void assignUIReference(View view) {
        type = 0;
        typeText = (TextView) view.findViewById(R.id.text_type);
        tipsText = (TextView) view.findViewById(R.id.tips);
        dataViewModels = ViewModelProviders.of(getActivity()).get(DataViewModels.class);
        filterButton = (Button) view.findViewById(R.id.filter);
        dataViewModels.getMobileList(getActivity()).observe(getActivity(), new Observer<List<Mobile>>() {
            @Override
            public void onChanged(@Nullable List<Mobile> mobiles) {
                mobileList = mobiles;
                setFilterButton();
            }
        });


    }
    public  void  setFilterButton()
    {
        String[] mobiles = new String[mobileList.size()];
        for (int i=0;i<mobileList.size();i++)
        {
            mobiles[i] =mobileList.get(i).getName();
        }
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type =0;
                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                adb.setSingleChoiceItems(mobiles, 0, new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        type = which;
                    }

                });
                adb.setNegativeButton(getActivity().getResources().getString(R.string.cancel), null);

                adb.setPositiveButton(getActivity().getResources().getString(R.string.select), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        typeText.setText(mobileList.get(type).getName());
                        dataViewModels.getTips(getActivity() ,mobileList.get(type).getName()).observe(getActivity(), new Observer<Tips>() {
                            @Override
                            public void onChanged(@Nullable Tips tips) {
                                  tipsText.setText(tips.getContent());
                            }
                        });
                    }
                });
                adb.setTitle(getActivity().getResources().getString(R.string.select_mobile));
                adb.show();
            }
        });
    }


}