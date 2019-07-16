package com.example.emma_tel.video_time_line;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.emma_tel.R;
import com.example.emma_tel.adapters.MyVideosAdapter;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerImageHolder extends RecyclerItemBaseHolder {

    public final static String TAG = "RecyclerView2List";

    protected Context context = null;

    @BindView(R.id.image)
    ImageView imageView;
    Picasso picasso;


    public RecyclerImageHolder(Context context, View v) {
        super(v);
        this.context = context;
        ButterKnife.bind(this, v);

    }

    public void onBind(final int position, VideoModel videoModel) {

        //增加封面
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        picasso.load("https://api.emma-tel.com/upload/" + videoModel.getName()).config(Bitmap.Config.RGB_565).into(imageView);


    }

}

