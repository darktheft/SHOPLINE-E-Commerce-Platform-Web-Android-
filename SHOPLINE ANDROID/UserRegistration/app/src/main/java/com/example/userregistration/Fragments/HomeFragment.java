package com.example.userregistration.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.userregistration.Adapters.ProductAdapter;
import com.example.userregistration.Model.Item;
import com.example.userregistration.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerview;
    private ProductAdapter mProductAdapter;
    private ArrayList<Item> mProductlist;
    private RequestQueue mRequestQueue;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerview = rootview.findViewById(R.id.recyclerView);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mProductlist = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this.getContext());
        parseJson();


        return rootview;
    }





    private void parseJson() {
        String url = "https://api.myjson.com/bins/vm3jy";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("products");
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String creatorName = hit.getString("name");
                                String imageURl = hit.getString("image");
                                String price = hit.getString("price");
                                mProductlist.add(new Item(imageURl,creatorName,price));
                            }
                            mProductAdapter = new ProductAdapter(HomeFragment.this.getContext(),mProductlist);
                            mRecyclerview.setAdapter(mProductAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
            mRequestQueue.add(request);

    }





}