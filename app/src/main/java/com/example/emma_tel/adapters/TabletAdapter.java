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
import com.example.emma_tel.models.Tablet;
import com.example.emma_tel.utils.Constants;
import com.github.captain_miao.optroundcardview.OptRoundCardView;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class TabletAdapter extends RecyclerView.Adapter<TabletAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    List<Tablet> data = Collections.emptyList();
    Context context;
    private OnItemRecyclerClicked onItemRecyclerClicked;

    public TabletAdapter(List<Tablet> data, Context context ,OnItemRecyclerClicked onItemRecyclerClicked) {
        inflater= LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        this.onItemRecyclerClicked = onItemRecyclerClicked;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= inflater.inflate(R.layout.layout_tablet_item,viewGroup ,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Tablet current = data.get(i);
        myViewHolder.tabletTitle.setText(current.getName());
        myViewHolder.tabletPrice.setText(current.getPrice());
        myViewHolder.tabletName.setText(current.getName());
        Picasso.with(context).load(Constants.IMG_URL + current.getImage()).into(myViewHolder.tabletImage);
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
        ImageView tabletImage;
        TextView tabletTitle;
        TextView tabletDetails;
        TextView tabletPrice;
        TextView tabletName;
        Button button;
        OptRoundCardView roundCardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tabletImage = (ImageView) itemView.findViewById(R.id.image_view_tablet_image);
            tabletTitle= (TextView) itemView.findViewById(R.id.text_tablet_title);
            tabletPrice   =(TextView) itemView.findViewById(R.id.text_view_tablet_price);
            tabletName = (TextView) itemView.findViewById(R.id.tablet_name);
            button =(Button)itemView.findViewById(R.id.details_button);
            roundCardView =(OptRoundCardView)itemView.findViewById(R.id.card_view);


        }
    }
}
