package com.example.emma_tel.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.models.Event;
import com.example.emma_tel.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class EventAdapter  extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    List<Event> data = Collections.emptyList();
    Context context;

    public EventAdapter(List<Event> data, Context context) {
        inflater= LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= inflater.inflate(R.layout.layout_event_item,viewGroup ,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Event current= data.get(i);
      // myViewHolder.mobileTitle.setText(current.getTitle());
        myViewHolder.mobileContent.setText(current.getContent());
        Picasso.with(context).load(Constants.IMG_URL+current.getImage()).into(myViewHolder.mobileImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        ImageView mobileImage;
        TextView mobileTitle;
        TextView mobileContent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mobileImage = (ImageView) itemView.findViewById(R.id.image_view_event_image);
            mobileTitle= (TextView) itemView.findViewById(R.id.text_event_title);
            mobileContent= (TextView) itemView.findViewById(R.id.text_event_content);


        }
    }
}
