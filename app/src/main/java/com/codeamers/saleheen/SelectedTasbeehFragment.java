package com.codeamers.saleheen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SelectedTasbeehFragment extends Fragment {

    TextView title , count , touchText;
    SharedPreferences sp;
    LinearLayout ll;
    int i = 1;
    String val = "";
    public SelectedTasbeehFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_selected_tasbeeh, container, false);
        sp = getActivity().getSharedPreferences("TasbeehPref", Context.MODE_PRIVATE);
        String TTitle = sp.getString("TITLE", "");
        String TCount = sp.getString("COUNT", "");
        title = root.findViewById(R.id.tasbeehText);
        count = root.findViewById(R.id.tasbeehCount);

        touchText = root.findViewById(R.id.touchTextId);
        ll = root.findViewById(R.id.TouchArea);

        title.setText(TTitle);
        count.setText(TCount);

        ll.setOnClickListener(view -> {
            if (val.equals(TCount)) {
                Toast.makeText(getActivity(), "MAX COUNT REACHED", Toast.LENGTH_SHORT).show();
            }else{
                val = String.valueOf(i++);
                touchText.setText(val);
            }
        });

        return root;
    }
}