package com.example.jose_victor.apimapsmenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
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
class ExemploProviderFragmentV2GPS extends SupportMapFragment implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener, LocationListener{

    private GoogleMap mMap;

    private LocationManager locationManager;

    private static  final String TAG = "ExProvFragmentV2GPS";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onPause() {
        super.onPause();

        locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.removeUpdates(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);



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

    @Override
    public void onLocationChanged(Location location) {
//        Toast.makeText(getActivity(), "Localização atualizada", Toast.LENGTH_SHORT).show();
        LatLng novaLocalizacao = new LatLng(location.getLatitude(),location.getAltitude());

        MarkerOptions marker = new MarkerOptions();
        marker.position(novaLocalizacao);
        marker.title("Novo marcador");


        mMap.addMarker(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(novaLocalizacao));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(getActivity(), "status prov alterado", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getActivity(), "prov ativado", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getActivity(), "prov desativado", Toast.LENGTH_LONG).show();

    }
}