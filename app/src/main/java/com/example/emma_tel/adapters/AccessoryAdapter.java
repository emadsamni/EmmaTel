package com.example.emma_tel.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.interfaces.OnItemRecyclerClicked;
import com.example.emma_tel.models.Accessory;
import com.example.emma_tel.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class AccessoryAdapter extends RecyclerView.Adapter<AccessoryAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Accessory> data = Collections.emptyList();
    Context context;
    private OnItemRecyclerClicked onItemRecyclerClicked;

    public AccessoryAdapter(List<Accessory> data, Context context ,OnItemRecyclerClicked onItemRecyclerClicked) {
        inflater= LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        this.onItemRecyclerClicked = onItemRecyclerClicked;
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
        myViewHolder.accessoryTitle.setText(current.getName());
        myViewHolder.accessoryPrice.setText(current.getPrice());
        myViewHolder.accessoryName.setText(current.getName());
        Picasso.with(context).load(Constants.IMG_URL+current.getImage()).into(myViewHolder.accessoryImage);
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
        ImageView accessoryImage;
        TextView accessoryTitle;;
        TextView accessoryPrice;
        TextView accessoryName;
        Button button;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            accessoryImage = (ImageView) itemView.findViewById(R.id.image_view_accessory_image);
            accessoryTitle= (TextView) itemView.findViewById(R.id.text_Accessory_title);
            accessoryPrice   =(TextView) itemView.findViewById(R.id.text_view_accessory_price);
            accessoryName =(TextView) itemView.findViewById(R.id.accessory_name);
            button =(Button)itemView.findViewById(R.id.details_button);

        }
    }
}
