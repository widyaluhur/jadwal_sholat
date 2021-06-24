package com.example.jadwalsholat.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jadwalsholat.R;
import com.example.jadwalsholat.adapter.TimeAdapter;
import com.example.jadwalsholat.model.DataItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimeFragment extends Fragment {

    private TimeAdapter jadwalSholatAdapter;
    private RecyclerView rvJadwalSholat;
    private JadwalSholatViewModel jadwalSholatViewModel;

    public TimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_time.
     */
    // TODO: Rename and change types and number of parameters
    public static TimeFragment newInstance(String param1, String param2) {
        TimeFragment fragment = new TimeFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        jadwalSholatAdapter = new TimeAdapter(getContext());
        jadwalSholatAdapter.notifyDataSetChanged();

        rvJadwalSholat = view.findViewById(R.id.RecyclerView);
        rvJadwalSholat.setLayoutManager(new GridLayoutManager(getContext(),1));

        jadwalSholatViewModel = new ViewModelProvider(this).get(JadwalSholatViewModel.class);
        jadwalSholatViewModel.setJadwalSholat();
        jadwalSholatViewModel.getJadwalSholat().observe(this,getJadwal);

        rvJadwalSholat.setAdapter(jadwalSholatAdapter);
    }

    private Observer<ArrayList<DataItem>> getJadwal = new Observer<ArrayList<DataItem>>() {
        @Override
        public void onChanged(ArrayList<DataItem> dataItems) {
            if(dataItems != null){
                jadwalSholatAdapter.setData(dataItems);
            }
        }
    };
}