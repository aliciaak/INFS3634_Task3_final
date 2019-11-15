package com.example.infs3634_task3.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.infs3634_task3.adapters.SearchAdapter;
import com.example.infs3634_task3.R;
import com.example.infs3634_task3.Breed;
import com.example.infs3634_task3.utils.FetchDataListener;
import com.example.infs3634_task3.utils.GetAPIRequest;
import com.example.infs3634_task3.utils.RequestQueueService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private View v;
    private RecyclerView recyclerview_search;
    private EditText editSearch;
    private ImageView imgSearch;

    private List<Breed> list;
    private JSONObject jsonObject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        v = inflater.inflate(R.layout.fragment_search, container, false);

        editSearch = v.findViewById(R.id.editSearch);
        imgSearch = v.findViewById(R.id.imgSearch);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!editSearch.getText().toString().isEmpty())
                    getApiCall();
            }
        });
        list = new ArrayList<>();

        recyclerview_search = v.findViewById(R.id.recyclerview_search);

        return v;
    }

    private void getApiCall() {
        String stringName = editSearch.getText().toString().trim();
        try {
            GetAPIRequest getapiRequest = new GetAPIRequest();
            String url = "https://api.thecatapi.com/v1/breeds/search?q=" + stringName;
            getapiRequest.request(getActivity(), fetchGetResultListener, url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private FetchDataListener fetchGetResultListener = new FetchDataListener() {
        @Override
        public void onFetchComplete(JSONArray response) {
            Log.d("Response", String.valueOf(response));
            RequestQueueService.cancelProgressDialog();
            if (response != null) {
                if (response.length() == 0) {
                    RequestQueueService.showAlert("", "No data available.", getActivity());
                } else {
                    list.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jsonObject = response.getJSONObject(i);

                            Breed breedItem = new Breed();

                            breedItem.name = jsonObject.getString("name");
                            breedItem.origin = jsonObject.getString("origin");

                            breedItem.weight = jsonObject.getJSONObject("weight").getString("imperial");
                            breedItem.life_span = jsonObject.getString("life_span");


                            breedItem.dog_friendly = jsonObject.getString("dog_friendly");
                            breedItem.temperament = jsonObject.getString("temperament");


                            breedItem.description = jsonObject.getString("description");

                            breedItem.breed_id = jsonObject.getString("id");

                            if (jsonObject.has("wikipedia_url"))
                                breedItem.readmore = jsonObject.getString("wikipedia_url");


                            list.add(breedItem);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setuprecyclerview(list);
                    }

                }

            } else {
                RequestQueueService.showAlert("", "Error! No data fetched", getActivity());
            }
        }

        @Override
        public void onFetchFailure(String msg) {
            RequestQueueService.cancelProgressDialog();
            RequestQueueService.showAlert("", msg, getActivity());
        }

        @Override
        public void onFetchStart() {
            RequestQueueService.showProgressDialog(getActivity());
        }
    };

    private void setuprecyclerview(List<Breed> listAnime) {

        SearchAdapter myAdapter = new SearchAdapter(getActivity(), listAnime);
        recyclerview_search.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdapter.notifyDataSetChanged();
        recyclerview_search.setAdapter(myAdapter);
    }
}