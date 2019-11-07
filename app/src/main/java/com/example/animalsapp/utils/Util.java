package com.example.animalsapp.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.animalsapp.R;

public class Util {

    public static void loadImage(ImageView imageView, String url, CircularProgressDrawable circularProgressDrawable){

        RequestOptions options = new RequestOptions()
                .placeholder(circularProgressDrawable)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(imageView)
                .setDefaultRequestOptions(options)
                .load(url)
                .into(imageView);
    }

    public static CircularProgressDrawable getProgressDrawable(Context context){
        CircularProgressDrawable cpd = new CircularProgressDrawable(context);
        cpd.setStrokeWidth(10f);
        cpd.setCenterRadius(50f);
        cpd.start();
        return cpd;
    }

    @BindingAdapter("android:imageurl")
    public static void loadImage(ImageView view,String url){

        loadImage(view,url,getProgressDrawable(view.getContext()));
    }
}
