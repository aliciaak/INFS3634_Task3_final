package com.example.infs3634_task3.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.infs3634_task3.adapters.SearchAdapter;
import com.example.infs3634_task3.activities.BreedDetailActivity;
import com.example.infs3634_task3.R;
import com.example.infs3634_task3.Breed;
import com.example.infs3634_task3.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    private View v;
    private RecyclerView recyclerview;
    private List<Breed> list = new ArrayList<>();
    private FavoriteAdapter myAdapter;
    private AppDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        db = Room.databaseBuilder(getActivity(),
                AppDatabase.class, "breed_db").allowMainThreadQueries().build();
        Log.d("All Items", list.size() + " ");
        v = inflater.inflate(R.layout.fragment_fav, container, false);
        recyclerview = v.findViewById(R.id.recyclerview);
        myAdapter = new FavoriteAdapter(getActivity());
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(myAdapter);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        list.addAll(db.breedDao().getAll());
        if (myAdapter != null)
            myAdapter.notifyDataSetChanged();
    }

    public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

        Context mContext;

        public FavoriteAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.row_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            holder.breedName.setText(list.get(position).name);
            holder.breedName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(mContext, BreedDetailActivity.class);
                    i.putExtra("listDetail", list.get(position));
                    mContext.startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView breedName;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                breedName = itemView.findViewById(R.id.breedName);
            }
        }
    }
}