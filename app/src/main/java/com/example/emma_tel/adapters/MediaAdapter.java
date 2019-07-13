package com.example.emma_tel.adapters;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.emma_tel.R;
import com.example.emma_tel.models.EMedia;

import java.util.Collections;
import java.util.List;

public class MediaAdapter extends  RecyclerView.Adapter<MediaAdapter.MyViewHolder> {


    private LayoutInflater inflater;
    List<EMedia> data = Collections.emptyList();
    Context context;


    public MediaAdapter(List<EMedia> data, Context context) {
        inflater= LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= inflater.inflate(R.layout.layout_vedio_item,viewGroup ,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        EMedia current= data.get(i);

        MediaController mediaController = new MediaController(context);
        String uri = "https://api.emma-tel.com/upload/videoplayback.mp4";
         myViewHolder.videoView.setVideoURI(Uri.parse(uri));
         myViewHolder.videoView.requestFocus();
         myViewHolder.videoView.setMediaController(mediaController);
         mediaController.setAnchorView( myViewHolder.videoView);
         myViewHolder.videoView.start();




    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        VideoView videoView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);







        }
    }
}
