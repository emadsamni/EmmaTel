package com.example.emma_tel.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allattentionhere.autoplayvideos.AAH_CustomRecyclerView;

import com.example.emma_tel.R;
import com.example.emma_tel.activites.ContactActivity;
import com.example.emma_tel.activites.LoginActivity;
import com.example.emma_tel.activites.MainActivity;
import com.example.emma_tel.adapters.MainSliderAdapter;
import com.example.emma_tel.adapters.MediaAdapter;
import com.example.emma_tel.adapters.MyVideosAdapter;
import com.example.emma_tel.adapters.SliderAdapter;
import com.example.emma_tel.helprs.CustomerUtils;
import com.example.emma_tel.models.EMedia;
import com.example.emma_tel.models.MainSlider;
import com.example.emma_tel.utils.Constants;
import com.example.emma_tel.viewmodels.DataViewModels;
import com.example.emma_tel.viewmodels.LoginViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    DataViewModels dataViewModels;
    LoginViewModel loginViewModel;
    private ViewPager viewPager;
    private  LinearLayout  linearLayout;
    private SliderAdapter sliderAdapter;
    private RecyclerView myRecyclerView;
    private AAH_CustomRecyclerView mediaRecyclerView;
    private MainSliderAdapter mAdapter;
    private MediaAdapter mediaAdapter;
    Picasso p;
    CustomerUtils customerUtils;
    LinearLayout mobilesLinearLayout ,accessoriesLinearLayout ,offersLinearLayout ,branchesLinearLayout ,contactLinearLayout ,aboutLinearLayout;


    public HomeFragment()
    {

    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_home, container,false);
        ButterKnife.bind(getActivity());
         p = Picasso.with(getActivity());

        assignUIReference(view);
          assignAction();

          //getMainSlider();
         return  view;
    }
    private void assignUIReference(final View view){
        mobilesLinearLayout = view.findViewById(R.id.linearLayout_mobiles);
        accessoriesLinearLayout = view.findViewById(R.id.linearLayout_accessory);
        aboutLinearLayout = view.findViewById(R.id.linearLayout_about);
        offersLinearLayout= view.findViewById(R.id.linearLayout_offers);
        branchesLinearLayout = view.findViewById(R.id.linearLayout_branches);
        contactLinearLayout = view.findViewById(R.id.linearLayout_contact);
        customerUtils = CustomerUtils.getInstance(getActivity());

        //viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        linearLayout= (LinearLayout)view.findViewById(R.id.dots);
        myRecyclerView =(RecyclerView) view.findViewById(R.id.recycler_main_slider);
        mediaRecyclerView =(AAH_CustomRecyclerView) view.findViewById(R.id.rv_home);

        dataViewModels = ViewModelProviders.of(getActivity()).get(DataViewModels.class);
        loginViewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
        dataViewModels.getMainSliderList(getActivity()).observe(getActivity(), new Observer<List<MainSlider>>() {
            @Override
            public void onChanged(@Nullable List<MainSlider> mainSliders) {
                mAdapter =new MainSliderAdapter(mainSliders,getActivity());

                myRecyclerView.setAdapter(mAdapter);
                LinearLayoutManager layoutManager =new LinearLayoutManager( getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                myRecyclerView.setLayoutManager(layoutManager);


            }
        });

        dataViewModels.getMediaList(getActivity()).observe(getActivity(), new Observer<List<EMedia>>() {
            @Override
            public void onChanged(@Nullable List<EMedia> eMedia) {
                MyVideosAdapter mAdapter = new MyVideosAdapter(eMedia, p);
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

                mediaRecyclerView.setLayoutManager(mLayoutManager);
                mediaRecyclerView.setItemAnimator(new DefaultItemAnimator());

                //todo before setAdapter
                mediaRecyclerView.setActivity(getActivity());

                //optional - to play only first visible video
                mediaRecyclerView.setPlayOnlyFirstVideo(true); // false by default

                //optional - by default we check if url ends with ".mp4". If your urls do not end with mp4, you can set this param to false and implement your own check to see if video points to url
                mediaRecyclerView.setCheckForMp4(false); //true by default

                //optional - download videos to local storage (requires "android.permission.WRITE_EXTERNAL_STORAGE" in manifest or ask in runtime)
                mediaRecyclerView.setDownloadPath(Environment.getExternalStorageDirectory() + "/MyVideo"); // (Environment.getExternalStorageDirectory() + "/Video") by default

                mediaRecyclerView.setDownloadVideos(true); // false by default

                mediaRecyclerView.setVisiblePercent(100); // percentage of View that needs to be visible to start playing

                //extra - start downloading all videos in background before loading RecyclerView
                List<String> urls = new ArrayList<>();
                for (EMedia object : eMedia) {
                    if (object.getName() != null )
                        urls.add("https://api.emma-tel.com/upload/"+object.getName());
                }
                mediaRecyclerView.preDownload(urls);

                mediaRecyclerView.setAdapter(mAdapter);
                //call this functions when u want to start autoplay on loading async lists (eg firebase)
                mediaRecyclerView.smoothScrollBy(0,1);
                mediaRecyclerView.smoothScrollBy(0,-1);

                ;
            }
        });




    }
    private void assignAction(){
        mobilesLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MobilesFragment();
                ((MainActivity)getActivity()).navigationView.getMenu().getItem(1).setChecked(true);
                ((MainActivity)getActivity()).loadFragment(fragment);
            }
        });
        accessoriesLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AccessoriesFragment();
                ((MainActivity)getActivity()).navigationView.getMenu().getItem(2).setChecked(true);
                ((MainActivity)getActivity()).loadFragment(fragment);
            }
        });
        offersLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new OffersFragment();
                ((MainActivity)getActivity()).navigationView.getMenu().getItem(3).setChecked(true);
                ((MainActivity)getActivity()).loadFragment(fragment);
            }
        });
        branchesLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MapFragment();
                ((MainActivity)getActivity()).navigationView.getMenu().getItem(4).setChecked(true);
                ((MainActivity)getActivity()).loadFragment(fragment);
            }
        });
        contactLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (customerUtils.isFound(Constants.PREF_TOKEN))
                     startActivity(new Intent(getActivity(), ContactActivity.class));
                else
                    startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        aboutLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AboutUsFragment();
                ((MainActivity)getActivity()).navigationView.getMenu().getItem(6).setChecked(true);
                ((MainActivity)getActivity()).loadFragment(fragment);
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


    @Override
    public void onStop() {
        super.onStop();
        mediaRecyclerView.stopVideos();
    }
}
