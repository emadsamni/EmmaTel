package com.example.emma_tel.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.emma_tel.R;
import com.example.emma_tel.activites.MainActivity;
import com.example.emma_tel.adapters.SliderAdapter;
import com.example.emma_tel.models.MainSlider;
import com.example.emma_tel.viewmodels.DataViewModels;

import java.util.List;

public class HomeFragment extends Fragment {
    DataViewModels dataViewModels;
    private ViewPager viewPager;
    private  LinearLayout  linearLayout;
    private SliderAdapter sliderAdapter;


    public HomeFragment()
    {

    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_home, container,false);
          assignUIReference(view);
          //getMainSlider();
         return  view;
    }
    private void assignUIReference(final View view){
        viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        linearLayout= (LinearLayout)view.findViewById(R.id.dots);

        dataViewModels = ViewModelProviders.of(getActivity()).get(DataViewModels.class);
        dataViewModels.getMainSliderList(getActivity()).observe(getActivity(), new Observer<List<MainSlider>>() {
            @Override
            public void onChanged(@Nullable List<MainSlider> mainSliders) {
                sliderAdapter =new SliderAdapter(getActivity(),mainSliders);
                viewPager.setAdapter(sliderAdapter);
               // addDotsIndicator(mainSliders.size());
                 autoSwip(mainSliders.size(),1);
               // addDotsIndicator(mainSliders.size(),linearLayout,1);

            }
        });



    }
    public void autoSwip(int size ,int current)
    {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                viewPager.setCurrentItem(current+1);
                if (size == current+1)
                {
                    autoSwip(size,-1);
                }
                else
                {
                    autoSwip(size,current+1);
                }

            }
        }, 2000);

    }

    public  void addDotsIndicator(int size , LinearLayout linearLayout , int position)
    {
        TextView[] mDots;
        mDots = new TextView[size];
        for (int i=0; i<size;i++)
        {
            mDots[i] = new TextView(getActivity());
            mDots[i].setText(Html.fromHtml("&#8226;"));
            if (i ==position)
                mDots[i].setTextSize(45);
            else
                mDots[i].setTextSize(35);
            mDots[i].setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
            mDots[i].setGravity(Gravity.CENTER);
            linearLayout.addView(mDots[i]);
            linearLayout.setGravity(Gravity.CENTER);

        }
    }





}
