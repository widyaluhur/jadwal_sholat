package com.example.jadwalsholat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jadwalsholat.R;
import com.example.jadwalsholat.model.DataItem;

import java.util.ArrayList;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {

    private ArrayList<DataItem> dataItems = new ArrayList<>();
    private Context context;

    public TimeAdapter(Context context){
        this.context = context;
    }

    public void setData(ArrayList<DataItem> items){
        dataItems.clear();
        dataItems.addAll(items);
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public TimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeAdapter.ViewHolder holder, int position) {

        holder.tvDate.setText(dataItems.get(position).getDate().getReadable());
        holder.tvHijriDay.setText(dataItems.get(position).getDate().getHijri().getDay());
        holder.tvHijriMonth.setText(dataItems.get(position).getDate().getHijri().getMonth().getEn());
        holder.tvHijriYear.setText(dataItems.get(position).getDate().getHijri().getYear());
        holder.tvTime.setText(dataItems.get(position).getTimings().getImsak());
        holder.tvSubuh.setText(dataItems.get(position).getTimings().getFajr());
        holder.tvTerbit.setText(dataItems.get(position).getTimings().getSunrise());
        holder.tvDzuhur.setText(dataItems.get(position).getTimings().getDhuhr());
        holder.tvAshar.setText(dataItems.get(position).getTimings().getAsr());
        holder.tvMaghrib.setText(dataItems.get(position).getTimings().getMaghrib());
        holder.tvIsya.setText(dataItems.get(position).getTimings().getIsha());

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvDate, tvTime, tvSubuh, tvTerbit, tvDzuhur, tvAshar, tvMaghrib, tvIsya;
        TextView tvHijriDay, tvHijriMonth, tvHijriYear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            tvName = itemView.findViewById(R.id.tv_name);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvHijriDay = itemView.findViewById(R.id.tv_date_hijri_day);
            tvHijriMonth = itemView.findViewById(R.id.tv_date_hijri_month);
            tvHijriYear = itemView.findViewById(R.id.tv_date_hijri_year);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvSubuh = itemView.findViewById(R.id.tv_time_subuh);
            tvTerbit = itemView.findViewById(R.id.tv_time_terbit);
            tvDzuhur = itemView.findViewById(R.id.tv_time_dzuhur);
            tvAshar = itemView.findViewById(R.id.tv_time_ashar);
            tvMaghrib = itemView.findViewById(R.id.tv_time_maghrib);
            tvIsya = itemView.findViewById(R.id.tv_time_isya);
        }
    }
}
