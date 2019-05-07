package com.hassan.ali.newsfeed;

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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<DataModel> data;

    Context mContext;
    CustomItemClickListener listener;



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
        TextView textViewSubsection = holder.textViewSubsection;
        ImageView imageView = holder.imageView;

        Picasso.with(mContext).load(data.get(position).getImageUrl1()).into(imageView);

//        imageView.setImageResource(data.get(position).g));
        textViewTitle.setText(data.get(position).getTitle());
        textViewSubsection.setText(data.get(position).getSubsection());
        textViewDate.setText(data.get(position).getDate());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public RecyclerAdapter(Context mContext, ArrayList<DataModel> data, CustomItemClickListener listener) {
        this.data = data;
        this.mContext = mContext;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewSubsection;
        TextView textViewDate;

        ViewHolder(View v) {
            super(v);
            textViewTitle = (TextView) v
                    .findViewById(R.id.textViewTitle);

            textViewDate = (TextView) v
                    .findViewById(R.id.textViewDate);

            textViewSubsection = (TextView) v
                    .findViewById(R.id.textViewSubsection);

            imageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }

}
