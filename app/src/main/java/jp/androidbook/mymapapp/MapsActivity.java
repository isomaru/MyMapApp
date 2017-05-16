package jp.androidbook.mymapapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Context mContext;

    static final String DB = "sqlite.db";
    static final int DB_VERSION = 1;
    static final String CREATE_TABLE = "create table mapContents ( _id integer primary key autoincrement, latitude numeric not null, longitude numeric not null, _name varchar(255) );";
    static final String DROP_TABLE = "drop table mapCOntents;";

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mContext = getApplicationContext();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Hiratsuka and move the camera
        LatLng hiratsuka = new LatLng(35.32735, 139.349041);
        mMap.addMarker(new MarkerOptions().position(hiratsuka).title("平塚駅"));

        LatLng namihei = new LatLng(35.327813, 139.348006);
        Marker n = mMap.addMarker(new MarkerOptions().position(namihei).title("波平"));
        n.setTag("1");

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hiratsuka, 14));

        mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.getTag().toString() == "1") {
                    Intent intent = new Intent(mContext, ScrollingActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

    }
}
