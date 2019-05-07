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
    private static final String JSON_URL = "http://api.nytimes.com/svc/topstories/v2/sports.json?api-key=32e97d31e3854521b35d191aaccb86ab";
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


                            JSONArray ModelArray = obj.getJSONArray("results");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < ModelArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject modelObject = ModelArray.getJSONObject(i);
                                JSONArray multimedia=modelObject.getJSONArray("multimedia");
                                String imageUrl1=multimedia.getJSONObject(1).getString("url");
                                String imageUrl4=multimedia.getJSONObject(4).getString("url");

                                String title=modelObject.getString("title");
                                String subSection=modelObject.getString("section");
                                String date=modelObject.getString("published_date");
                                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
                                Date convertedDate = new Date();
                                try {
                                    convertedDate = dateFormat.parse(date);
                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                date=convertedDate.toString();
                                String abstractt=modelObject.getString("abstract");
                                String link=modelObject.getString("url");
                                String byLine=modelObject.getString("byline");
                                DataModel model = new DataModel(imageUrl1,imageUrl4,title,subSection,date,abstractt,byLine,link);
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
