//package com.example.infs3634_task3.database;
//
//import androidx.room.Dao;
//import androidx.room.Insert;
//import androidx.room.Delete;
//import androidx.room.Query;
//
//import androidx.room.OnConflictStrategy;
//
//import com.example.infs3634_task3.Breed;
//
//import java.util.List;
//
//@Dao
//public interface BreedDao {
//    //DAO for cat breeds
//
//    @Insert
//    void insert(Breed entities);
//
//    @Delete
//    void delete(Breed entity);
//
//    @Query("SELECT * FROM breed")
//    List<Breed> getAll();
//
//    @Query("DELETE FROM breed")
//    public void deleteAll();
//
//    @Query("SELECT * FROM breed where breed_id =:breed_id")
//    Breed findBreedbyId(long breed_id);
//}
