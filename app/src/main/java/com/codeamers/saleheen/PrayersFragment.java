package com.codeamers.saleheen;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class PrayersFragment extends Fragment {

    private static final int INTERNET_PERMISSION_CODE = 200;
    private static final int COARSE_LOCATION_PERMISSION_CODE = 201;
    private static final int FINE_LOCATION_PERMISSION_CODE = 202;

    double longitude = 0.0, latitude = 0.0;

    ImageButton locationBtn;
    TextView todayDate, fajrTime, dhuhrTime, asrTime, maghribTime, ishaTime;
    SharedPreferences sp;

    public PrayersFragment() {}
    public static PrayersFragment newInstance() {
        return new PrayersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void fetchPrayerTimes() {
        Toast.makeText(getContext(), "Fetching Prayer Times", Toast.LENGTH_SHORT).show();
        String url = "https://api.pray.zone/v2/times/today.json?longitude=" + longitude + "&latitude=" + latitude;
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        if (response.getInt("code") == 200) {
                            JSONObject datetime = response.getJSONObject("results").getJSONArray("datetime").getJSONObject(0);
                            JSONObject times = datetime.getJSONObject("times");
                            JSONObject date = datetime.getJSONObject("date");

                            SharedPreferences.Editor ed = sp.edit();

                            ed.putString("Fajr", times.getString("Fajr"));
                            ed.putString("Dhuhr", times.getString("Dhuhr"));
                            ed.putString("Asr", times.getString("Asr"));
                            ed.putString("Maghrib", times.getString("Maghrib"));
                            ed.putString("Isha", times.getString("Isha"));
                            ed.putString("Date", date.getString("hijri"));
                            ed.apply();

                            refreshUI();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, error -> {
            // TODO: Handle Error
            Log.d("SAD", error.toString());
        });
        APIHandler.getInstance(getContext()).addToRequestQueue(req);
    }

    private void refreshUI() {
        if (!sp.getString("Date", "-").equals("-")) {
            todayDate.setText( sp.getString("Date", "-") );
            fajrTime.setText( sp.getString("Fajr", "-") );
            dhuhrTime.setText( sp.getString("Dhuhr", "-") );
            asrTime.setText( sp.getString("Asr", "-") );
            maghribTime.setText( sp.getString("Maghrib", "-") );
            ishaTime.setText( sp.getString("Isha", "-") );
        }
    }

    private boolean getPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(getActivity(), new String[] { permission }, requestCode);
        else return true;
        return false;
    }

    private boolean getLocationPermission() {
        return getPermission(Manifest.permission.ACCESS_COARSE_LOCATION, COARSE_LOCATION_PERMISSION_CODE) ||
                getPermission(Manifest.permission.ACCESS_FINE_LOCATION, FINE_LOCATION_PERMISSION_CODE);
    }

    private void fetchLocation(View v) {
        LocationFinder finder;
        finder = new LocationFinder(getContext());

        if (!getLocationPermission()) {
            Toast.makeText(getContext(), "Please Grant Location Permissions", Toast.LENGTH_LONG).show();
        }

        if (finder.canGetLocation()) {
            latitude = finder.getLatitude();
            longitude = finder.getLongitude();
            Toast.makeText(getContext(), "Fetching Location", Toast.LENGTH_SHORT).show();
            fetchPrayerTimes();
        } else {
            finder.showSettingsAlert();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_prayers, container, false);

        locationBtn = root.findViewById(R.id.locationBtn);
        locationBtn.setOnClickListener(this::fetchLocation);

        todayDate = root.findViewById(R.id.todayDate);
        fajrTime = root.findViewById(R.id.fajrTime);
        dhuhrTime = root.findViewById(R.id.dhuhrTime);
        asrTime = root.findViewById(R.id.asrTime);
        maghribTime = root.findViewById(R.id.maghribTime);
        ishaTime = root.findViewById(R.id.ishaTime);

        sp = root.getContext().getSharedPreferences("SALEHEEN_SP", Context.MODE_PRIVATE);

        refreshUI();

        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == INTERNET_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(getContext(), "Internet Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Internet Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == COARSE_LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(getContext(), "Coarse Location Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Coarse Location Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == FINE_LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(getContext(), "Fine Location Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Fine Location Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}