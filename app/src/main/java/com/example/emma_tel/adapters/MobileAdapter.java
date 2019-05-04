package com.example.emma_tel.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.interfaces.OnItemRecyclerClicked;
import com.example.emma_tel.models.Accessory;
import com.example.emma_tel.models.Mobile;
import com.example.emma_tel.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class MobileAdapter extends RecyclerView.Adapter<MobileAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    List<Mobile> data = Collections.emptyList();
    Context context;
    private OnItemRecyclerClicked onItemRecyclerClicked;

    public MobileAdapter(List<Mobile> data, Context context ,OnItemRecyclerClicked onItemRecyclerClicked) {
        inflater= LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        this.onItemRecyclerClicked = onItemRecyclerClicked;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= inflater.inflate(R.layout.layout_mobile_item,viewGroup ,false);
         MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Mobile current= data.get(i);
        myViewHolder.mobileTitle.setText(current.getName());
        myViewHolder.mobilePrice.setText(current.getPrice());
        myViewHolder.mobileName.setText(current.getName());
        Picasso.with(context).load(Constants.IMG_URL+current.getImage()).into(myViewHolder.mobileImage);
        myViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 onItemRecyclerClicked.onClickedRecyclerItem(current);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        ImageView mobileImage;
        TextView mobileTitle;
        TextView mobileDetails;
        TextView mobilePrice;
        TextView mobileName;
        Button button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mobileImage = (ImageView) itemView.findViewById(R.id.image_view_Mobile_image);
            mobileTitle= (TextView) itemView.findViewById(R.id.text_Mobile_title);
            mobilePrice   =(TextView) itemView.findViewById(R.id.text_view_Mobile_price);
            mobileName = (TextView) itemView.findViewById(R.id.mobile_name);
            button =(Button)itemView.findViewById(R.id.details_button);


        }
    }
}
