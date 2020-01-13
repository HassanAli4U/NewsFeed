package com.hassan.ali.newsfeed.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.hassan.ali.newsfeed.recyclerview.CustomItemClickListener;
import com.hassan.ali.newsfeed.R;
import com.hassan.ali.newsfeed.recyclerview.RecyclerAdapter;
import com.hassan.ali.newsfeed.retrofit.ArticlesResponse;
import com.hassan.ali.newsfeed.retrofit.NetworkClient;
import com.hassan.ali.newsfeed.retrofit.NewsAPIs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ScienceFragment extends Fragment {
    Context context=MainActivity.context;

    private RecyclerView recyclerView;
    private List<ArticlesResponse.ArticlesBean> data;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    String countrystring=MainActivity.countryString;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_section, container, false);
        recyclerView = (RecyclerView)view. findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data=new ArrayList<ArticlesResponse.ArticlesBean>();

        fetchArticlesDetails(countrystring);
        return view;
    }

    private void fetchArticlesDetails(String country) {

        //Obtain an instance of Retrofit by calling the static method.
        Retrofit retrofit = NetworkClient.getRetrofitClient();
        /*
        The main purpose of Retrofit is to create HTTP calls from the Java interface based on the annotation associated with each method. This is achieved by just passing the interface class as parameter to the create method
        */
        NewsAPIs newsAPIs = retrofit.create(NewsAPIs.class);
        /*
        Invoke the method corresponding to the HTTP request which will return a Call object. This Call object will used to send the actual network request with the specified parameters
        */
        Call<ArticlesResponse> call = newsAPIs.getArticlesByCountry(country,"science","e57fdb1d5b3048519ce529eb977cea7a");
        /*
        This is the line which actually sends a network request. Calling enqueue() executes a call asynchronously. It has two callback listeners which will invoked on the main thread
        */
        call.enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse> response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                if(response.isSuccessful()) {
                    ArticlesResponse articlesResponse = response.body();

                    List<ArticlesResponse.ArticlesBean> articles = response.body().getArticles();

                    data =  articles;


                    adapter = new RecyclerAdapter(getContext(), data, new CustomItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {
                            Log.d("clivk", "clicked position:" + position);

                            Intent intent = new Intent(getContext(), NewsDetails.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("my object", (Serializable) data.get(position));
                            intent.putExtra("data", bundle);
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(adapter);






                    adapter.notifyDataSetChanged();




                }
            }
            @Override
            public void onFailure(Call<ArticlesResponse> call, Throwable t) {
            }
        });
    }

}
