

package es.uc3m.android.dansek;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class PrincipalScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private androidx.drawerlayout.widget.DrawerLayout drawer;
    private ActionBarDrawerToggle toogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_popup);

        // Obtener la referencia al TextView del menú (burguerMenu)

        View mapMenu = findViewById(R.id.mapMenu);
        View Menu = findViewById(R.id.burguerMenu);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        toogle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toogle);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Configurar el clic del icono del menú
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        mapMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalScreen.this, Map_main.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Obtener el ID del elemento del menú seleccionado
        int id = item.getItemId();

        // Verificar el ID del elemento del menú seleccionado y mostrar el Toast correspondiente
        if (id == R.id.menu_home) {
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_profile) {
            Toast.makeText(this, "Open My Profile", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        } else if (id == R.id.menu_points) {
            Toast.makeText(this, "Open My Points", Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(PrincipalScreen.this, Points.class);
            startActivity(intent2);
        }else if (id == R.id.menu_tickets) {
            Toast.makeText(this, "Open my tickets", Toast.LENGTH_SHORT).show();
            Intent intent3 = new Intent(PrincipalScreen.this, Tickets.class);
            startActivity(intent3);
        }else if (id == R.id.menu_settings) {
            Toast.makeText(this, "Open Settings", Toast.LENGTH_SHORT).show();
            Intent intent4 = new Intent(PrincipalScreen.this, Settings.class);
            startActivity(intent4);
        }else if (id == R.id.logout) {
            Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show();
            onLogout(PrincipalScreen.this);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toogle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        toogle.onConfigurationChanged(newConfig);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toogle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void onLogout(Context context) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            mAuth.signOut();
            // Redirigir al usuario a la actividad de inicio de sesión
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            // Si context es una actividad, llama a finish
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        }
    }





}


