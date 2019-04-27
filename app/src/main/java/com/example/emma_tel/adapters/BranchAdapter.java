package com.example.emma_tel.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.models.Branch;
import com.example.emma_tel.models.Page;

import java.util.Collections;
import java.util.List;

public class BranchAdapter   extends RecyclerView.Adapter<BranchAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    List<Branch> data = Collections.emptyList();
    Context context;

    public BranchAdapter(List<Branch> data, Context context) {
        inflater= LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= inflater.inflate(R.layout.layout_branch_item,viewGroup ,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Branch current= data.get(i);
        myViewHolder.branchPhone.setText(current.getPhone());
        myViewHolder.branchaddress.setText(current.getAddress());
        myViewHolder.branchfixedPhone.setText(current.getFixed_phone());
        myViewHolder.branchtitle.setText(current.getTitle());
        myViewHolder.branchwhatsapp.setText(current.getWhatsapp());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView branchPhone;
        TextView branchfixedPhone;
        TextView branchwhatsapp;
        TextView branchtitle;
        TextView branchaddress;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            branchPhone= (TextView) itemView.findViewById(R.id.text_view_branch_phone);
            branchfixedPhone= (TextView) itemView.findViewById(R.id.text_view_branch_fixed_phone);
            branchwhatsapp= (TextView) itemView.findViewById(R.id.text_view_branch_whatsapp);
            branchtitle= (TextView) itemView.findViewById(R.id.text_view_branch_title);
            branchaddress= (TextView) itemView.findViewById(R.id.text_view_branch_address);


        }
    }
}
