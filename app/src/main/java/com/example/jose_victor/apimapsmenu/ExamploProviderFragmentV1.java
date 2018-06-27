package com.example.jose_victor.apimapsmenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.nfc.Tag;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//@SuppressLint("ValidFragment")
class ExemploProviderFragmentV1 extends SupportMapFragment implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener{

    private GoogleMap mMap;

    private LocationManager locationManager;

    private static  final String TAG = "ExemploProvFragmentV1";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    try {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();

        String provider = locationManager.getBestProvider(criteria, true);

        Toast.makeText(getActivity(), "provider: " + provider, Toast.LENGTH_LONG);

        mMap = googleMap;

        mMap.setOnMapClickListener(this);

        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.setMyLocationEnabled(true);
    } catch (SecurityException ex) {
        Log.e(TAG,"Errou", ex);
    }


        LatLng torres = new LatLng(-29.3336967,-49.74951531210);

        MarkerOptions marker = new MarkerOptions();
        marker.position(torres);
        marker.title("Está em torres");


        mMap.addMarker(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(torres));
    }

    @Override
    public void onMapClick(LatLng latLng) {

        Toast.makeText(getContext(), "Coordenadas: " + latLng.toString(),
                Toast.LENGTH_SHORT).show();
    }
}