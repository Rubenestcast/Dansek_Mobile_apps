package es.uc3m.android.dansek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
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

    TextView selectYourCity;
    TextView selectYourZone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_main);


        txtCalle = findViewById(R.id.txtcalle);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        TextView back_arrow = findViewById(R.id.BACK);

        selectYourCity = findViewById(R.id.Selectyourcity);
        selectYourZone = findViewById(R.id.Selectyourzone);

        selectYourCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCityMenu(v);
            }
        });

        selectYourZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showZoneMenu(v);
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_main.this, PrincipalScreen.class);
                startActivity(intent);
            }
        });
    }

    private void showCityMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.city1) {
                    selectYourCity.setText(R.string.city1);
                    LatLng city1 = new LatLng(Double.parseDouble(getString(R.string.latcity1)), Double.parseDouble(getString(R.string.longcity1)));
                    String selectedCity = String.valueOf(selectYourCity.getText());


                    if (selectedCity.equals(getString(R.string.city1))) {
                        mMap.addMarker(new MarkerOptions().position(city1).title("Madrid-Atocha"));
                        Log.d("MapMain", "Coordenadas: " + city1);
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(city1));
                    }
                    Log.d("MapMain", String.valueOf(selectYourCity.getText()));
                    return true;
                } else if (id == R.id.city2) {
                    selectYourCity.setText(R.string.city2);

                    LatLng city2 = new LatLng(Double.parseDouble(getString(R.string.latcity2)), Double.parseDouble(getString(R.string.longcity2)));

                    String selectedCity = String.valueOf(selectYourCity.getText());

                    if (selectedCity.equals(getString(R.string.city2))) {
                        mMap.addMarker(new MarkerOptions().position(city2).title("Barcelona"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(city2));
                    }
                    Log.d("MapMain", String.valueOf(selectYourCity.getText()));
                    return true;
                } else if (id == R.id.city3) {
                    selectYourCity.setText(R.string.city3);
                    LatLng city3 = new LatLng(Double.parseDouble(getString(R.string.latcity3)), Double.parseDouble(getString(R.string.longcity3)));
                    String selectedCity = String.valueOf(selectYourCity.getText());

                    if (selectedCity.equals(getString(R.string.city3))) {
                        mMap.addMarker(new MarkerOptions().position(city3).title("Valencia"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(city3));
                    }
                    Log.d("MapMain", String.valueOf(selectYourCity.getText()));
                    return true;
                } else if (id == R.id.city4) {
                    selectYourCity.setText(R.string.city4);
                   LatLng city4 = new LatLng(Double.parseDouble(getString(R.string.latcity4)), Double.parseDouble(getString(R.string.longcity4)));
                    String selectedCity = String.valueOf(selectYourCity.getText());

                    if (selectedCity.equals(getString(R.string.city4))) {
                        mMap.addMarker(new MarkerOptions().position(city4).title("Bilbao"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(city4));
                    }
                    Log.d("MapMain", String.valueOf(selectYourCity.getText()));
                    return true;
                } else if (id == R.id.city5) {
                    selectYourCity.setText(R.string.city5);
                    LatLng city5 = new LatLng(Double.parseDouble(getString(R.string.latcity5)), Double.parseDouble(getString(R.string.longcity5)));

                    String selectedCity = String.valueOf(selectYourCity.getText());

                    if (selectedCity.equals(getString(R.string.city5))) {
                        mMap.addMarker(new MarkerOptions().position(city5).title("Malaga"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(city5));
                    }
                    Log.d("MapMain", String.valueOf(selectYourCity.getText()));
                    return true;
                } else if (id == R.id.city6) {
                    selectYourCity.setText(R.string.city6);
                    LatLng city6 = new LatLng(Double.parseDouble(getString(R.string.latcity6)), Double.parseDouble(getString(R.string.longcity6)));

                    String selectedCity = String.valueOf(selectYourCity.getText());

                    if (selectedCity.equals(getString(R.string.city6))) {
                        mMap.addMarker(new MarkerOptions().position(city6).title("Malaga"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(city6));
                    }
                    Log.d("MapMain", String.valueOf(selectYourCity.getText()));
                    return true;
                } else {
                    return false;
                }
            }
        });

        // Aquí infla el menú antes de mostrarlo
        popupMenu.inflate(R.menu.cities_menu);
        popupMenu.show();
    }

    private void showZoneMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);

        String selectedCity = String.valueOf(selectYourCity.getText());

        int cityGroup = -1; // Inicializar con un valor que no corresponde a ningún grupo de ciudades

        if (selectedCity.equals(getString(R.string.city1))) {
            cityGroup = R.id.city1_group;
        } else if (selectedCity.equals(getString(R.string.city2))) {
            cityGroup = R.id.city2_group;
        } else if (selectedCity.equals(getString(R.string.city3))) {
            cityGroup = R.id.city3_group;
        } else if (selectedCity.equals(getString(R.string.city4))) {
            cityGroup = R.id.city4_group;
        } else if (selectedCity.equals(getString(R.string.city5))) {
            cityGroup = R.id.city5_group;
        } else if (selectedCity.equals(getString(R.string.city6))) {
            cityGroup = R.id.city6_group;
        }

        if (cityGroup != -1) {
            // Ocultar todos los grupos
            popupMenu.getMenu().setGroupVisible(R.id.city1_group, false);
            popupMenu.getMenu().setGroupVisible(R.id.city2_group, false);
            popupMenu.getMenu().setGroupVisible(R.id.city3_group, false);
            popupMenu.getMenu().setGroupVisible(R.id.city4_group, false);
            popupMenu.getMenu().setGroupVisible(R.id.city5_group, false);
            popupMenu.getMenu().setGroupVisible(R.id.city6_group, false);

            // Mostrar solo el grupo de la ciudad seleccionada
            popupMenu.getMenu().setGroupVisible(cityGroup, true);

            // Inflar el menú después de configurar la visibilidad de los grupos
            popupMenu.inflate(R.menu.zones_cities_menu);
        }

        // Manejar los clics en los elementos del menú fuera del método onMenuItemClick
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Manejar la selección de distrito aquí
                handleDistrictSelection(selectedCity, item.getItemId());
                return true;
            }
        });

        popupMenu.show();
    }



    // Método para manejar la selección de distritos
    private void handleDistrictSelection(String selectedCity, int districtId) {
        // Lógica para manejar la selección de distritos
        if (selectedCity.equals(getString(R.string.city1))) {
            if (districtId == R.id.city1_district1) {
                // Manejar la selección del distrito 1 de la ciudad 1
            } else if (districtId == R.id.city1_district2) {
                // Manejar la selección del distrito 2 de la ciudad 1
            } else if (districtId == R.id.city1_district3) {
                // Manejar la selección del distrito 3 de la ciudad 1
            } else if (districtId == R.id.city1_district4) {
                // Manejar la selección del distrito 4 de la ciudad 1
            }
        } else if (selectedCity.equals(getString(R.string.city2))) {
            // Agregar lógica similar para la ciudad 2
        } else if (selectedCity.equals(getString(R.string.city3))) {
            // Agregar lógica similar para la ciudad 3
        } else if (selectedCity.equals(getString(R.string.city4))) {
            // Agregar lógica similar para la ciudad 4
        } else if (selectedCity.equals(getString(R.string.city5))) {
            // Agregar lógica similar para la ciudad 5
        } else if (selectedCity.equals(getString(R.string.city6))) {
            // Agregar lógica similar para la ciudad 6
        }
    }





    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap=googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng city1 = new LatLng(Double.parseDouble(getString(R.string.latcity1)), Double.parseDouble(getString(R.string.longcity1)));

        String selectedCity = String.valueOf(selectYourCity.getText());


        if (selectedCity.equals(getString(R.string.city1))) {
            mMap.addMarker(new MarkerOptions().position(city1).title("Madrid-Atocha"));
            Log.d("MapMain", "Coordenadas: " + city1);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(city1));
        }


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        String calle = getAddressFromLatLng(latLng);

        txtCalle.setText("Calle: " + calle);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        String calle = getAddressFromLatLng(latLng);

        txtCalle.setText("Calle: " + calle);

    }

    private String getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder(Map_main.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                return address.getAddressLine(0); // Devolver la dirección de la calle como una cadena
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}