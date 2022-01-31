package com.codeamers.saleheen;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    CardView prayersFragBtn, tasbeehFragBtn, quranFragBtn;

    public HomeFragment() {}

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        prayersFragBtn = root.findViewById(R.id.prayersFragBtn);
        tasbeehFragBtn = root.findViewById(R.id.tasbeehFragBtn);
        quranFragBtn = root.findViewById(R.id.quranFragBtn);

        prayersFragBtn.setOnClickListener(view ->
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flContent, PrayersFragment.newInstance())
                        .commit()
        );
        tasbeehFragBtn.setOnClickListener(view ->
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flContent, TasbeehFragment.newInstance())
                        .commit()
        );
        quranFragBtn.setOnClickListener(view ->
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flContent, QuranFragment.newInstance())
                        .commit()
        );
        return root;
    }
}