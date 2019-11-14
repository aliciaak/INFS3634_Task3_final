package com.example.infs3634_task3;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Breed {
    @PrimaryKey
    @SerializedName("breed_id")

    private String breed_id;
    private String name;
    private String catImage;
    private String origin;
    private String weight;
    private String life_span;
    private int dog_friendliness;

    private String temperament;
    private String description;
    private String readmore;

}
