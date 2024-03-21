package es.uc3m.android.dansek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Map_main extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    EditText txtLatitudLongitud, txtCalle;
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_main);

        txtLatitudLongitud = findViewById(R.id.txtlatitudlongitud);
        txtCalle = findViewById(R.id.txtcalle);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        TextView back_arrow = findViewById(R.id.BACK);



        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_main.this, PrincipalScreen.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap=googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng madrid = new LatLng(40.408666,-3.6911323);

        mMap.addMarker(new MarkerOptions().position(madrid).title("Madrid-Atocha"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(madrid));

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        String calle = getAddressFromLatLng(latLng);
        txtLatitudLongitud.setText("Lat: " + latLng.latitude + " Long: " + latLng.longitude);
        txtCalle.setText("Calle: " + calle);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        String calle = getAddressFromLatLng(latLng);
        txtLatitudLongitud.setText("Lat: " + latLng.latitude + " Long: " + latLng.longitude);
        txtCalle.setText("Calle: " + calle);
    }

    private String getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder(Map_main.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                String streetAddress = address.getAddressLine(0); // Obtener la primera línea de la dirección
                return streetAddress; // Devolver la dirección de la calle como una cadena
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}