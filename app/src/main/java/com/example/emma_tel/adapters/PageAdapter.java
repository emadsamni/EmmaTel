package com.example.emma_tel.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.models.Page;

import java.util.Collections;
import java.util.List;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.MyViewHolder>  {
    private LayoutInflater inflater;
    List<Page> data = Collections.emptyList();
    Context context;

    public PageAdapter(List<Page> data, Context context) {
        inflater= LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= inflater.inflate(R.layout.layout_page_item,viewGroup ,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Page current= data.get(i);
        myViewHolder.pageTitle.setText(current.getTitle());
        myViewHolder.pageContent.setText(current.getContent());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView pageTitle;
        TextView pageContent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pageTitle= (TextView) itemView.findViewById(R.id.text_view_page_title);
            pageContent= (TextView) itemView.findViewById(R.id.text_view_page_content);


        }
    }


}
