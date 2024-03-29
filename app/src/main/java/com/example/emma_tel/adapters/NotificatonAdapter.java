package com.example.emma_tel.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.models.Notification;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class NotificatonAdapter extends RecyclerView.Adapter<NotificatonAdapter.MyViewHolder> {


    private LayoutInflater inflater;
    List<Notification> data = Collections.emptyList();
    Context context;

    public NotificatonAdapter(List<Notification> data, Context context) {
        inflater= LayoutInflater.from(context);
        this.data = data;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= inflater.inflate(R.layout.layout_notification_item,viewGroup ,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Notification current= data.get(i);
        myViewHolder.content.setText(current.getContent());
        if (current.getCreated_at()!= null) {
            myViewHolder.date.setText(current.getCreated_at()+"");
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder  {
        TextView content;
        TextView date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.text_content);
            date = (TextView) itemView.findViewById(R.id.text_date);


        }
    }
}
