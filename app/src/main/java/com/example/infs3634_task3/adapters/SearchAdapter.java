package com.example.infs3634_task3.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_task3.activities.BreedDetailActivity;
import com.example.infs3634_task3.R;
import com.example.infs3634_task3.Breed;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder>
{
    private Context mContext;
    private List<Breed> mData;

    public SearchAdapter(Context mContext, List<Breed> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.row_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.breedName.setText(mData.get(position).name);
        holder.breedName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent i = new Intent(mContext, BreedDetailActivity.class);
                i.putExtra("listDetail",  mData.get(position));
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView breedName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            breedName = itemView.findViewById(R.id.breedName);
        }
    }
}