package com.hassan.ali.newsfeed.recyclerview;

/**
 * Created by I Love Allah on 06/10/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hassan.ali.newsfeed.R;
import com.hassan.ali.newsfeed.retrofit.ArticlesResponse;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<ArticlesResponse.ArticlesBean> data;

    Context mContext;
    CustomItemClickListener listener;

    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        final ViewHolder mViewHolder = new ViewHolder(mView);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView textViewTitle = holder.textViewTitle;
        TextView textViewDate = holder.textViewDate;
        ImageView imageView = holder.imageView;

            Picasso.with(mContext).load(data.get(position).getUrlToImage()).into(imageView);



//        imageView.setImageResource(data.get(position).g));
        textViewTitle.setText(data.get(position).getTitle());
        textViewDate.setText(data.get(position).getPublishedAt());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public RecyclerAdapter(Context mContext, List<ArticlesResponse.ArticlesBean> data, CustomItemClickListener listener) {
        this.data = data;
        this.mContext = mContext;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewDate;

        ViewHolder(View v) {
            super(v);
            textViewTitle = (TextView) v
                    .findViewById(R.id.textViewTitle);

            textViewDate = (TextView) v
                    .findViewById(R.id.textViewDate);



            imageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }
//    public static String encodUrl(String url){
//        String[] splitUrl = url.split("/");
//        String imageName = splitUrl[splitUrl.length-1];//get name of file
//        String mainUrl = url.replaceAll(imageName , "");//get url without file name bacause dont need to encode
//        return (mainUrl + Uri.encode(imageName));
//    }
}
