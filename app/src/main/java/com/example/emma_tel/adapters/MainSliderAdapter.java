package com.example.emma_tel.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.models.MainSlider;
import com.example.emma_tel.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class MainSliderAdapter extends  RecyclerView.Adapter<MainSliderAdapter.MyViewHolder> {


    private LayoutInflater inflater;
    List<MainSlider> data = Collections.emptyList();
    Context context;


    public MainSliderAdapter(List<MainSlider> data, Context context) {
        inflater= LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= inflater.inflate(R.layout.layout_slider_item2,viewGroup ,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        MainSlider current= data.get(i);


        myViewHolder.title.setText(current.getTitle());
        Picasso.with(context).load(Constants.IMG_URL+current.getImage()).into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title ,content;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.main_slider_image);
            title= (TextView) itemView.findViewById(R.id.title);





        }
    }
}
