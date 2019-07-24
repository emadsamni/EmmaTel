package com.example.emma_tel.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
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
import android.transition.Explode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.emma_tel.video_time_line.RecyclerBaseAdapter;
import com.example.emma_tel.video_time_line.RecyclerItemNormalHolder;
import com.example.emma_tel.video_time_line.RecyclerNormalAdapter;
import com.example.emma_tel.video_time_line.VideoModel;
import com.example.emma_tel.viewmodels.DataViewModels;
import com.example.emma_tel.viewmodels.LoginViewModel;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    @BindView(R.id.list_item_recycler)
    RecyclerView videoList;
    LinearLayoutManager linearLayoutManager;

    RecyclerBaseAdapter recyclerBaseAdapter;

    List<VideoModel> dataList = new ArrayList<>();

    DataViewModels dataViewModels;
    LoginViewModel loginViewModel;
    private ViewPager viewPager;
    private  LinearLayout  linearLayout;
    private SliderAdapter sliderAdapter;
    private RecyclerView myRecyclerView;

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



          //getMainSlider();
         return  view;
    }
    private void assignUIReference(final View view){

        customerUtils = CustomerUtils.getInstance(getActivity());

        //viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        linearLayout= (LinearLayout)view.findViewById(R.id.dots);
        myRecyclerView =(RecyclerView) view.findViewById(R.id.recycler_main_slider);


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
                configVideoTimeline(view ,eMedia );

            }
        });




    }





    private void configVideoTimeline(View view ,List<EMedia> eMedia)
    {
        ButterKnife.bind(getActivity());
        videoList =view.findViewById(R.id.list_item_recycler);
        resolveData(eMedia);
        final RecyclerNormalAdapter recyclerNormalAdapter = new RecyclerNormalAdapter(getActivity(), dataList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        videoList.setLayoutManager(linearLayoutManager);
        videoList.setAdapter(recyclerNormalAdapter);

        videoList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int firstVisibleItem, lastVisibleItem, betItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                firstVisibleItem   = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                 if (lastVisibleItem -firstVisibleItem ==1 )
                 {
                     betItem=-1;
                 }
                 else {
                     betItem =(lastVisibleItem+firstVisibleItem)/2;
                 }


                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(RecyclerItemNormalHolder.TAG)
                            && (position < firstVisibleItem || position > lastVisibleItem)) {

                        //如果滑出去了上面和下面就是否，和今日头条一样
                        //是否全屏
                        if(!GSYVideoManager.isFullState(getActivity())) {
                            GSYVideoManager.releaseAllVideos();
                            recyclerNormalAdapter.notifyDataSetChanged();

                        }
                    }
                }

                int position = GSYVideoManager.instance().getPlayPosition();

                if (firstVisibleItem != position) {
                    if (lastVisibleItem - firstVisibleItem == 1)
                        recyclerNormalAdapter.runVedio(firstVisibleItem);

                }
                else {
                    if (betItem!=-1)
                    {
                        if(betItem!=position)
                           recyclerNormalAdapter.runVedio(betItem);
                    }
                }



            }
        });


    }


    public static int getVisiblePercent(View v) {
        if (v.isShown()) {
            Rect r = new Rect();
            v.getGlobalVisibleRect(r);
            double sVisible = r.width() * r.height();
            double sTotal = v.getWidth() * v.getHeight();
            return (int) (100 * sVisible / sTotal);
        } else {
            return -1;
        }
    }
    private void resolveData(List<EMedia> eMedia) {
        for (int i = 0; i < eMedia.size(); i++) {
            VideoModel videoModel = new VideoModel(eMedia.get(i));
            dataList.add(videoModel);
        }
        if (recyclerBaseAdapter != null)
            recyclerBaseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();

        GSYVideoManager.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.onResume(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }


}
