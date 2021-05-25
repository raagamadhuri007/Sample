package com.example.sample.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sample.R;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BeginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeginFragment extends Fragment {

    private Handler handler = new Handler();

    private static final String TAG = "hbs";

    public BeginFragment() {
        // Required empty public constructor
    }

    public static BeginFragment newInstance(String param1, String param2) {
        BeginFragment fragment = new BeginFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_begin, container, false);

        handler.postDelayed(OpenNextActicity, 3000);

        return view;
    }

    private Runnable OpenNextActicity = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public void run() {

            Boolean justInstalled = requireActivity().getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getBoolean("justInstalled", true);

            if (justInstalled) {

                Fragment newfragment = new ProfileFragment();
                FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.begin, newfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            } else {
                Fragment newfragment = new DeviceListFragment();
                FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.begin, newfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            requireActivity().getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit().putBoolean("justInstalled", false).apply();
        }
    };
}
