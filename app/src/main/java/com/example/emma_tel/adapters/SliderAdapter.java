package com.example.emma_tel.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.models.MainSlider;
import com.example.emma_tel.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    List<MainSlider> mainSliderList;

    public  SliderAdapter(Context context , List<MainSlider> mainSliderList)
    {
        this.context =context;
        this.mainSliderList =mainSliderList;
    }
    @Override
    public int getCount() {
        return mainSliderList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (LinearLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view   = layoutInflater.inflate(R.layout.layout_slider_item ,container ,false);
        ImageView imageMainSlider = (ImageView) view.findViewById(R.id.image_main_slider);
        TextView textTitle = (TextView) view.findViewById(R.id.text_title);
        TextView textContent = (TextView) view.findViewById(R.id.text_content);
        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.dots);
        addDotsIndicator(mainSliderList.size(),linearLayout,position);
        textContent.setText(mainSliderList.get(position).getContent());
        textTitle.setText(mainSliderList.get(position).getTitle());
        Picasso.with(context).load(Constants.IMG_URL+mainSliderList.get(position).getImage()).into(imageMainSlider);
        container.addView(view);


        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
    public  void addDotsIndicator(int size , LinearLayout linearLayout , int position)
    {
        TextView[] mDots;
        mDots = new TextView[size];
        for (int i=0; i<size;i++)
        {
            mDots[i] = new TextView(context);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            if (i ==position)
                mDots[i].setTextColor(context.getResources().getColor(R.color.ms_white));
            else
                mDots[i].setTextColor(context.getResources().getColor(R.color.ms_white_tarns));
                mDots[i].setTextSize(35);

            mDots[i].setGravity(Gravity.CENTER);
            linearLayout.addView(mDots[i]);
            linearLayout.setGravity(Gravity.CENTER);

        }
    }
}
