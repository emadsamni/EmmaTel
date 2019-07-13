package com.example.emma_tel.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allattentionhere.autoplayvideos.AAH_CustomViewHolder;
import com.allattentionhere.autoplayvideos.AAH_VideosAdapter;
import com.example.emma_tel.R;
import com.example.emma_tel.models.EMedia;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;

public class MyVideosAdapter extends AAH_VideosAdapter {

    private List<EMedia> list;
    Picasso picasso;
    private static final int TYPE_VIDEO = 0, TYPE_Image = 1;

    public class MyViewHolder extends AAH_CustomViewHolder {
        final TextView tv;
         TextView des;
        final ImageView img_vol,img_playback;

        boolean isMuted; //to mute/un-mute video (optional)

        public MyViewHolder(View x) {
            super(x);
            tv = ButterKnife.findById(x, R.id.tv);
            des = ButterKnife.findById(x, R.id.description);
            img_vol = ButterKnife.findById(x, R.id.img_vol);
            img_playback = ButterKnife.findById(x, R.id.img_playback);

        }
        @Override
        public void videoStarted() {
            super.videoStarted();
            img_playback.setImageResource(R.drawable.ic_pause);
            if (isMuted) {
                muteVideo();
                img_vol.setImageResource(R.drawable.ic_mute);
            } else {
                unmuteVideo();
                img_vol.setImageResource(R.drawable.ic_unmute);
            }
        }

        @Override
        public void pauseVideo() {
            super.pauseVideo();
            img_playback.setImageResource(R.drawable.ic_play);
        }
    }

    public MyVideosAdapter(List<EMedia> list_urls, Picasso p) {
        this.list = list_urls;
        this.picasso = p;
    }

    public class MyImageViewHolder extends AAH_CustomViewHolder {
        final TextView tv;
        ImageView imageView;

        public MyImageViewHolder(View x) {
            super(x);
            tv = ButterKnife.findById(x, R.id.tv);
            imageView =ButterKnife.findById(x, R.id.image);
        }
    }

    @Override
    public AAH_CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE_Image) {
            View itemView1 = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_image_item, parent, false);
            return new MyImageViewHolder(itemView1);

        }
        else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_vedio_item, parent, false);
            return new MyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(AAH_CustomViewHolder holder, int position) {
        if (list.get(position).getType().equals("1")) {
            ((MyImageViewHolder) holder).tv.setText(list.get(position).getTitle());
            picasso.load("https://api.emma-tel.com/upload/" +list.get(position).getName()).config(Bitmap.Config.RGB_565).into(((MyImageViewHolder) holder).imageView);
        } else {
            ((MyViewHolder) holder).tv.setText(list.get(position).getTitle());
            ((MyViewHolder) holder).des.setText(list.get(position).getDescription());

            //todo
            holder.setImageUrl("https://api.emma-tel.com/upload/hh.png");
            holder.setVideoUrl("https://api.emma-tel.com/upload/" + list.get(position).getName());
            //load image/thumbnail into imageview
            if (list.get(position).getName() != null && !list.get(position).getName().isEmpty())
                picasso.load(holder.getImageUrl()).config(Bitmap.Config.RGB_565).into(holder.getAAH_ImageView());

            holder.setLooping(true); //optional - true by default

            //to play pause videos manually (optional)
            ((MyViewHolder) holder).img_playback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.isPlaying()) {
                        holder.pauseVideo();
                        holder.setPaused(true);
                    } else {
                        holder.playVideo();
                        holder.setPaused(false);
                    }
                }
            });

            holder.setLooping(true);
            holder.muteVideo();
            ((MyViewHolder) holder).isMuted = !((MyViewHolder) holder).isMuted;
            //to mute/un-mute video (optional)
            ((MyViewHolder) holder).img_vol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((MyViewHolder) holder).isMuted) {
                        holder.unmuteVideo();
                        ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_unmute);
                    } else {
                        holder.muteVideo();
                        ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_mute);
                    }
                    ((MyViewHolder) holder).isMuted = !((MyViewHolder) holder).isMuted;
                }
            });

            if (list.get(position).getType().equals("1")) {
                ((MyViewHolder) holder).img_vol.setVisibility(View.GONE);
                ((MyViewHolder) holder).img_playback.setVisibility(View.GONE);
            } else {
                ((MyViewHolder) holder).img_vol.setVisibility(View.VISIBLE);
                ((MyVideosAdapter.MyViewHolder) holder).img_playback.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType().equals("1")) {
            return TYPE_Image;
        } else return TYPE_VIDEO;
    }
}