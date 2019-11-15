package com.example.infs3634_task3.utils;

import org.json.JSONArray;

public interface FetchDataListener
{
    void onFetchComplete(JSONArray response);

    void onFetchFailure(String msg);

    void onFetchStart();
}
