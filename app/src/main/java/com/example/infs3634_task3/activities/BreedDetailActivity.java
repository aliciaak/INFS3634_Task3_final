package com.example.infs3634_task3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infs3634_task3.R;
import com.example.infs3634_task3.Breed;
import com.example.infs3634_task3.database.AppDatabase;
import com.example.infs3634_task3.utils.FetchDataListener;
import com.example.infs3634_task3.utils.GetAPIRequest;
import com.example.infs3634_task3.utils.RequestQueueService;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.room.Room;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

public class BreedDetailActivity extends AppCompatActivity {

    TextView breed_origin, breed_weight, breed_life_span, dog_friendliness_level, breed_description, readmore, breed_temperament;
    Toolbar toolbar;
    Breed listOfBreedItem;
    FloatingActionButton favoriteBtn;
    AppDatabase db;
    ImageView imageview;
    boolean isFavourite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_detail_layout);
        db = Room.databaseBuilder(BreedDetailActivity.this,
                AppDatabase.class, "breed_db").allowMainThreadQueries().build();
        imageview = findViewById(R.id.imageview);
        listOfBreedItem = (Breed) getIntent().getExtras().getSerializable("listDetail");

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        breed_origin = findViewById(R.id.breed_origin);
        breed_weight = findViewById(R.id.breed_weight);
        breed_life_span = findViewById(R.id.breed_life_span);
        breed_temperament = findViewById(R.id.breed_temperament);
        breed_description = findViewById(R.id.breed_description);
        dog_friendliness_level = findViewById(R.id.dog_friendliness_level);
        readmore = findViewById(R.id.readmore);

        CollapsingToolbarLayout ctl = findViewById(R.id.collapsing);
        ctl.setTitle(listOfBreedItem.name);

        breed_life_span.setText(listOfBreedItem.life_span + " years");
        breed_origin.setText(listOfBreedItem.origin);
        breed_temperament.setText(listOfBreedItem.temperament);
        breed_weight.setText(listOfBreedItem.weight + " kgs");
        dog_friendliness_level.setText(listOfBreedItem.dog_friendly);
        breed_description.setText(listOfBreedItem.description);
        readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(listOfBreedItem.readmore);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        favoriteBtn = findViewById(R.id.btnFav);

        Breed breedItem = db.breedDao().getBreedById(listOfBreedItem.breed_id);

        if (breedItem != null) {
            isFavourite = true;
            favoriteBtn.setImageResource(R.drawable.ic_star_full_white);
        } else {
            isFavourite = false;
            favoriteBtn.setImageResource(R.drawable.ic_star_normal);
        }
//        if (database.isAvailableInFavorite(listOfBreedItem.breed_id)) {
//            isFavourite = true;
//            favoriteBtn.setImageResource(R.drawable.ic_star_full_white);
//        } else {
//            isFavourite = false;
//            favoriteBtn.setImageResource(R.drawable.ic_star_normal);
//        }
        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isFavourite) {
                    isFavourite = false;
                    favoriteBtn.setImageResource(R.drawable.ic_star_normal);
                    db.breedDao().delete(listOfBreedItem);
                } else {
                    isFavourite = true;
                    favoriteBtn.setImageResource(R.drawable.ic_star_full_white);
                    db.breedDao().insert(listOfBreedItem);
                }
            }
        });

        getApiCall();
    }

    private void getApiCall() {
        try {
            GetAPIRequest getapiRequest = new GetAPIRequest();
            String url = "https://api.thecatapi.com/v1/images/search?size=&mime_types=&order=&limit=1&page=0&category_ids=&format=&breed_id=" + listOfBreedItem.breed_id;
//            String url = "https://api.thecatapi.com/v1/images/" + listOfBreedItem.breed_id;
            getapiRequest.request(BreedDetailActivity.this, fetchGetResultListener, url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    FetchDataListener fetchGetResultListener = new FetchDataListener() {
        @Override
        public void onFetchComplete(JSONArray response) {
            Log.d("Response", String.valueOf(response));
            RequestQueueService.cancelProgressDialog();
            if (response != null) {
                if (response.length() == 0) {
                    RequestQueueService.showAlert("", "Note: may have some missing data.", BreedDetailActivity.this);
                } else {
                    if (response.length() > 0) {
                        try {
                            Picasso.get()
                                    .load(response.getJSONObject(0).getString("url").replace("\\", "").replaceAll("\"", "").trim())
                                    .placeholder(new ColorDrawable(Color.parseColor("#DFDFDF")))
                                    .error(new ColorDrawable(Color.parseColor("#DFDFDF")))
                                    .into(imageview);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                RequestQueueService.showAlert("", "Error! No data fetched", BreedDetailActivity.this);
            }
        }

        @Override
        public void onFetchFailure(String msg) {
            RequestQueueService.cancelProgressDialog();
            RequestQueueService.showAlert("", msg, BreedDetailActivity.this);
        }

        @Override
        public void onFetchStart() {
            RequestQueueService.showProgressDialog(BreedDetailActivity.this);
        }
    };
}