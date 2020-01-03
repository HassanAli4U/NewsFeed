package com.hassan.ali.newsfeed;

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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SportsFragment extends Fragment {
    Context context=MainActivity.context;

    private RecyclerView recyclerView;
    private ArrayList<DataModel> data;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private static final String JSON_URL = "https://newsapi.org/v2/top-headlines?country=eg&category=sports&apiKey=e57fdb1d5b3048519ce529eb977cea7a";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_section, container, false);
        recyclerView = (RecyclerView)view. findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<DataModel>();
//        data.add(new DataModel("https://static01.nyt.com/images/2018/07/17/sports/17stein_web1/17stein_web1-mediumThreeByTwo210.jpg","76ers Foiled in Their Pursuit of Houston\\u2019s Daryl Morey","Sport","2018-07-16T19:20:27-04:00"));
//
//
//        adapter = new RecyclerAdapter(getContext(), data, new CustomItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                Log.d("clivk", "clicked position:" + position);
//                Toast.makeText(getContext(), "clicked position:" + position, Toast.LENGTH_SHORT).show();            }
//        });
//        recyclerView.setAdapter(adapter);

//        loadData();
        adapter = new RecyclerAdapter(getContext(), data, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d("clivk", "clicked position:" + position);
                Toast.makeText(getContext(), "clicked position:" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), NewsDetails.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("my object", (Serializable) data.get(position));
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        loadData();
        return view;
    }
    //    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser && !_areLecturesLoaded ) {
//            loadData();
//            _areLecturesLoaded = true;
//        }
//    }
    public void loadData() {
        //getting the progressbar
//        final ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
//
//        //making the progressbar visible
//        progressBar.setVisibility(View.VISIBLE);
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);


                            JSONArray ModelArray = obj.getJSONArray("articles");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < ModelArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject modelObject = ModelArray.getJSONObject(i);
                                String imageUrl1= (String) modelObject.getString("urlToImage").trim();
                                if(imageUrl1.equals(""))
                                {
                                    imageUrl1="data:image/png;base64,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";
                                }

                                String sourceUrl= (String) modelObject.getString("url");

                                String title=modelObject.getString("title");
                                String description=modelObject.getString("description");
                                String date=modelObject.getString("publishedAt");
                                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
                                Date convertedDate = new Date();
                                try {
                                    convertedDate = dateFormat.parse(date);
                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                date=convertedDate.toString();
                                String abstractt=modelObject.getString("description");
                                JSONObject sourceObject=modelObject.getJSONObject("source");
                                String author=sourceObject.getString("name");
                                DataModel model = new DataModel(imageUrl1,title,date,abstractt,sourceUrl,author);
                                data.add(model);
                                if(i%5==0){
                                    adapter.notifyDataSetChanged();
                                }
                            }
//                            Toast.makeText(getContext(), "First date:" + data.get(0).getDate(), Toast.LENGTH_SHORT).show();

                            adapter.notifyDataSetChanged();
                            Log.d("clivk", data.get(0).getDate());

//                            progressBar.setVisibility(View.INVISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}
