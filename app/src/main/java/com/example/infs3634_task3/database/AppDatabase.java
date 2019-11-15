package com.example.infs3634_task3.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.infs3634_task3.Breed;

@Database(entities = {Breed.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BreedDao breedDao();
}