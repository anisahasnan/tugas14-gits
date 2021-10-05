package com.example.tugas14_anisahasna.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.tugas14_anisahasna.R;

public class SliderOnBoardAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SliderOnBoardAdapter(Context context) {
        this.context = context;
    }
    int images[] = {
            R.drawable.onboard1, R.drawable.onboard2, R.drawable.onboard3
    };
    int headings[] = {
            R.string.onboard_title1, R.string.onboard_title2, R.string.onboard_title3
    };
    int descriptions[] = {
            R.string.onboard_subtitle1,
            R.string.onboard_subtitle2,
            R.string.onboard_subtitle3
    };
    @Override
    public int getCount() {
        return headings.length;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout,container, false);
        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView heading = view.findViewById(R.id.slider_heading);
        TextView desc = view.findViewById(R.id.slider_desc);
        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(descriptions[position]);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
