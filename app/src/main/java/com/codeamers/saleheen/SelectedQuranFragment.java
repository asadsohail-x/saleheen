package com.codeamers.saleheen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class SelectedQuranFragment extends Fragment {
    InputStream inputStream;
    TextView textView;
    String string = "";
    public SelectedQuranFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_selected_quran, container, false);
        textView = root.findViewById(R.id.showText);
        SharedPreferences sp = getActivity().getSharedPreferences("QURAN" , Context.MODE_PRIVATE);
        String fetch = sp.getString("FILENAME" , "");
        try {
            if (fetch.matches("1\t\t Surah Al-Faatiha")){
                inputStream = getActivity().getAssets().open("Al-Fatiha.txt");
            }else if(fetch.matches("2\t\t Surah Al-Baqara")){
                inputStream = getActivity().getAssets().open("Al-Baqara.txt");
            }
            else {return root;}

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            string = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView.setText(string);

        return root;
    }
}