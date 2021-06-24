package com.example.jadwalsholat.view;

import android.view.View;

import com.example.jadwalsholat.entity.AppDatabase;
import com.example.jadwalsholat.entity.DataComment;

import java.util.List;

public interface CommentContact {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataComment> list);
        void deleteData(DataComment item);
    }

    interface presenter{
        void insertData(String nama, String email, String pesan, AppDatabase database);
        void readData(AppDatabase database);
        void deleteData(DataComment dataComment, AppDatabase database);
    }
}
