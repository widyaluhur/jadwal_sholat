package com.example.jadwalsholat.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jadwalsholat.R;
import com.example.jadwalsholat.adapter.CommentAdapter;
import com.example.jadwalsholat.entity.AppDatabase;
import com.example.jadwalsholat.entity.DataComment;

import java.util.ArrayList;
import java.util.List;

public class CommentFragment extends Fragment {
    private AppDatabase appDatabase;
    private CommentAdapter commentAdapter;
    private ArrayList<DataComment> listComment = new ArrayList<>();

    private Button btn_submit;
    private RecyclerView rv_comment;
    private EditText etNama, etEmail, etPesan;

//    private int id = 0;

    public CommentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_submit = view.findViewById(R.id.btn_submit);
        etNama = view.findViewById(R.id.et_name);
        etEmail = view.findViewById(R.id.et_email);
        etPesan = view.findViewById(R.id.et_message);

        btn_submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try{
                    DataComment dataComment = new DataComment();
                    dataComment.setName(etNama.getText().toString());
                    dataComment.setEmail(etEmail.getText().toString());
                    dataComment.setComment(etPesan.getText().toString());

                    appDatabase.dao().insertData(dataComment);

                    Log.d("CommentActivity" , "sukses ");
                    Toast.makeText(getContext(),"Tersimpan", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Log.e("CommentActivity" , "gagal menyimpan , msg : "+ex.getMessage());
                    Toast.makeText(getContext(),"Gagal Menyimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rv_comment = view.findViewById(R.id.rv_comment);
        commentAdapter = new CommentAdapter(getContext());
        commentAdapter.notifyDataSetChanged();

        if(appDatabase == null){
            appDatabase = AppDatabase.inidb(getContext());
        }

        listComment.addAll(appDatabase.dao().getData());
        commentAdapter.setData(listComment);

        rv_comment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_comment.setAdapter(commentAdapter);
    }


}