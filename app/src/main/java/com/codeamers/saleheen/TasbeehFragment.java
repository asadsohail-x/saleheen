package com.codeamers.saleheen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TasbeehFragment extends Fragment {

    public TasbeehFragment() {}

    public static TasbeehFragment newInstance() {
        return new TasbeehFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tasbeeh, container, false);
    }
}