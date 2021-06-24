package com.example.jadwalsholat.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataCommentDAO {

    @Insert
    Long insertData(DataComment dataComment);

    @Query("Select * from comment_db")
    List<DataComment> getData();

    @Delete
    void deleteData(DataComment dataComment);

}
