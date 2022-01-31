package com.codeamers.saleheen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TasbeehFragment extends Fragment {

    ImageButton addTasbeeh;
    DbService db;
    LinearLayout linearLayout;
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
        View root = inflater.inflate(R.layout.fragment_tasbeeh, container, false);
        db = new DbService(getContext());
        loadTasbeeh(root);

        addTasbeeh = root.findViewById(R.id.addTasbeehId);
        addTasbeeh.setOnClickListener(view -> addTasbeeh());

        return root;
    }

    public void loadTasbeeh(View v){
        linearLayout = v.findViewById(R.id.Tasbeehlayout);
        ArrayList<String> data = db.getTasbeeh();

        for(int i = 0 ; i<data.size() ; i++){

            LinearLayout liner = new LinearLayout(getContext());
            TextView txTitle = new TextView(getContext());
            TextView txCount = new TextView(getContext());

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 350);

            layoutParams.setMargins(15,15,15,15);
            liner.setLayoutParams(layoutParams);
            liner.setOrientation(LinearLayout.VERTICAL);
            liner.setGravity(Gravity.CENTER);
            liner.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.rounded_bg));


            txTitle.setText(data.get(i++));
            txTitle.setLayoutParams(lp);
            txTitle.setTextSize(25);
            txTitle.setGravity(Gravity.CENTER);
            txTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.golden));

            txCount.setTextColor(ContextCompat.getColor(getContext(), R.color.golden));
            txCount.setText(data.get(i));
            txCount.setTextSize(25);
            txCount.setGravity(Gravity.CENTER);
            txCount.setLayoutParams(lp);

            liner.addView(txTitle);
            liner.addView(txCount);
            linearLayout.addView(liner);
        }
    }


    public void addTasbeeh(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Add Tasbeeh");
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT));


        final EditText title = new EditText(getActivity());
        final EditText counter = new EditText(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10,10,10,10);

        title.setLayoutParams(lp);
        counter.setLayoutParams(lp);
        title.setHint("Tasbeeh");
        counter.setHint("Total");
        counter.setInputType(InputType.TYPE_CLASS_NUMBER);
        ll.addView(title);
        ll.addView(counter);
        alertDialog.setView(ll);
        alertDialog.setPositiveButton("Add", (dialog, which) -> {
            String tasbeeh = title.getText().toString();
            String count = counter.getText().toString();
            if(tasbeeh.length()==0 && count.length()==0){
                return;
            }
            boolean check = db.insertTasbeeh(tasbeeh,count);
            if (check)
                Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContent, TasbeehFragment.newInstance())
                    .commit();
        });
        alertDialog.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        alertDialog.show();
    }
}