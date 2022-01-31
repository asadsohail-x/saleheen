package com.codeamers.saleheen;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class PrayersFragment extends Fragment {

    private static final int INTERNET_PERMISSION_CODE = 200;
    private static final int COARSE_LOCATION_PERMISSION_CODE = 201;
    private static final int FINE_LOCATION_PERMISSION_CODE = 202;

    double longitude = 0.0, latitude = 0.0;

    public PrayersFragment() {}

    public static PrayersFragment newInstance() {
        return new PrayersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        SharedPreferences sp = getActivity().getSharedPreferences("SALEHEEN_SP", Context.MODE_PRIVATE);
//        if (sp.getBoolean("haveShownLocationAlert", true)) showLocationAlert();
//        else Toast.makeText(getContext(), "Shown", Toast.LENGTH_SHORT).show();



        /*
            check if app has access to the internet
            check if app has access to the location

            if there is no internet
                get last stored prayer times
                show a message on top saying these are the last stored prayer times
                if there are no last stored prayer times
                    show user that app needs to connect to internet for pgetting prayer times

            if location is granted
                send lat-long to the api and get prayer times
                show them in the xml
            else
                tell user that he can enable location anytime he wants by clicking that button

                whatever city the user enters in the input field
                send city name to api and get prayer times
                if city valid
                    show the prayer times in xml
                else
                    show invalid message
         */

    }

    private void showLocationAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Prayer Times");
        alertDialog.setMessage("You can either provide Location access or Enter City Name manually to get Prayer Times\n\n[Note: You can change it Later]\n");
        alertDialog.setPositiveButton("Provide Location", (dialog, which) -> {
            if (getLocationPermission() && getPermission(Manifest.permission.INTERNET, INTERNET_PERMISSION_CODE) ) {
                fetchLocation();
                fetchPrayerTimes();
            } else {
                Toast.makeText(getActivity(), "Understandable, Have a nice day!", Toast.LENGTH_LONG).show();
            }
        });
        alertDialog.setNegativeButton("Provide City", (dialog, which) -> dialog.cancel());
        alertDialog.show();
    }

    private void fetchPrayerTimes() {
        Toast.makeText(getContext(), "Fetching Prayer Times", Toast.LENGTH_SHORT).show();
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

    private void fetchLocation() {
        LocationFinder finder;
        finder = new LocationFinder(getContext());

        if (finder.canGetLocation()) {
            latitude = finder.getLatitude();
            longitude = finder.getLongitude();
            Toast.makeText(getContext(), "lat-lng " + latitude + " — " + longitude, Toast.LENGTH_LONG).show();
        } else finder.showSettingsAlert();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_prayers, container, false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == INTERNET_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Internet Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Internet Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == COARSE_LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Coarse Location Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Coarse Location Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == FINE_LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Fine Location Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Fine Location Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}