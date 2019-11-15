package com.example.infs3634_task3;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Breed")
public class Breed implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "breed_id")
    @NonNull
    public String breed_id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "origin")
    public String origin;
    @ColumnInfo(name = "weight")
    public String weight;
    @ColumnInfo(name = "life_span")
    public String life_span;
    @ColumnInfo(name = "dog_friendly")
    public String dog_friendly;

    @ColumnInfo(name = "temperament")
    public String temperament;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "readmore")
    public String readmore;
}
