package com.example.jadwalsholat.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jadwalsholat.R;
import com.example.jadwalsholat.entity.AppDatabase;
import com.example.jadwalsholat.entity.DataComment;
import com.example.jadwalsholat.view.CommentContact;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.viewHolder> {
    private Context context;
    private ArrayList<DataComment> commentItems = new ArrayList<>();
    private AppDatabase appDatabase;

    public CommentAdapter(Context context) {
        this.context = context;
        appDatabase = AppDatabase.inidb(this.context);
    }

    public void setData(ArrayList<DataComment> items){
        commentItems.clear();
        commentItems.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.viewHolder holder, final int position) {

        holder.tvNama.setText(commentItems.get(position).getName());
        holder.tvEmail.setText(commentItems.get(position).getEmail());
        holder.tvPesan.setText(commentItems.get(position).getComment());
//        holder.id.setText(commentItems.get(position).getId());
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(context);
                }
                builder.setTitle("Menghapus Data")
                        .setMessage("Anda yakin ingin menghapus data ini?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                DataComment dataComment = new DataComment();

                                dataComment.setId(commentItems.get(position).getId());
                                dataComment.setName(commentItems.get(position).getName());
                                dataComment.setEmail(commentItems.get(position).getEmail());
                                dataComment.setComment(commentItems.get(position).getComment());

                                appDatabase.dao().deleteData(dataComment);

                                Log.d("CommentAdapter", "Sukses Dihapus");
                                Toast.makeText(context, "Data Sukses Dihapus", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentItems.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvNama,tvEmail,tvPesan,id;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_item_name);
            tvEmail = itemView.findViewById(R.id.tv_item_email);
            tvPesan = itemView.findViewById(R.id.tv_item_message);
//            id = itemView.findViewById(R.id.tv_item_id);
            cardView = itemView.findViewById(R.id.cv_item);
        }
    }
}
