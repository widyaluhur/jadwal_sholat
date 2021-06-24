package com.example.jadwalsholat.view;

import android.os.AsyncTask;

import com.example.jadwalsholat.entity.AppDatabase;
import com.example.jadwalsholat.entity.DataComment;

import java.util.List;

public class CommentPresenter implements CommentContact.presenter {
    private CommentContact.view view;

    public CommentPresenter(CommentContact.view view) {
        this.view = view;
    }

    class InsertData extends AsyncTask<Void,Void,Long> {
        private AppDatabase appDatabase;
        private DataComment dataComment;

        InsertData(AppDatabase appDatabase, DataComment dataComment) {
            this.appDatabase = appDatabase;
            this.dataComment = dataComment;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.dao().insertData(dataComment);
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successAdd();
        }
    }

    @Override
    public void insertData(String nama, String email, String pesan, AppDatabase database) {
        final DataComment dataComment = new DataComment();
        dataComment.setName(nama);
        dataComment.setEmail(email);
        dataComment.setComment(pesan);
        new InsertData(database,dataComment).execute();
    }

    @Override
    public void readData(AppDatabase database) {
        List<DataComment> list;
        list = database.dao().getData();
        view.getData(list);
    }

    class DeleteData extends AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataComment dataComment;

        public DeleteData(AppDatabase appDatabase, DataComment dataComment) {
            this.appDatabase = appDatabase;
            this.dataComment = dataComment;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            appDatabase.dao().deleteData(dataComment);
            return null;
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successDelete();
        }
    }

    @Override
    public void deleteData(DataComment dataComment, AppDatabase database) {
        new DeleteData(database,dataComment).execute();
    }
}
