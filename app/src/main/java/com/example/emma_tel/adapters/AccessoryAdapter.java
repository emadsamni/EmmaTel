package com.example.emma_tel.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.models.Accessory;
import com.example.emma_tel.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class AccessoryAdapter extends RecyclerView.Adapter<AccessoryAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Accessory> data = Collections.emptyList();
    Context context;

    public AccessoryAdapter(List<Accessory> data, Context context) {
        inflater= LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= inflater.inflate(R.layout.layout_accressory_item,viewGroup ,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Accessory current= data.get(i);
        myViewHolder.accessoryColor.setText(current.getColors());
        myViewHolder.accessoryTitle.setText(current.getName());
        myViewHolder.accessoryPrice.setText(current.getPrice());
        myViewHolder.accessoryDetails.setText(current.getDetails());
        Picasso.with(context).load(Constants.IMG_URL+current.getImage()).into(myViewHolder.accessoryImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        ImageView accessoryImage;
        TextView accessoryTitle;
        TextView accessoryDetails;
        TextView accessoryPrice;
        TextView accessoryColor;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            accessoryImage = (ImageView) itemView.findViewById(R.id.image_view_Accessory_image);
            accessoryTitle= (TextView) itemView.findViewById(R.id.text_view_Accessory_title);
            accessoryDetails= (TextView) itemView.findViewById(R.id.text_view_Accessory_details);
            accessoryPrice   =(TextView) itemView.findViewById(R.id.text_view_Accessory_price);
            accessoryColor   =(TextView) itemView.findViewById(R.id.text_view_Accessory_color);

        }
    }
}
