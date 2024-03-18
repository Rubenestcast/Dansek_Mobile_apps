package es.uc3m.android.dansek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View login_button = findViewById(R.id.loginButton);
        login_button.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), Login.class);
            startActivity(intent);
        });

        View nightclub_access = findViewById(R.id.nightclub_access);
        nightclub_access.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), Login_nightclubs.class);
            Toast.makeText(getApplicationContext(), "Entra", Toast.LENGTH_SHORT).show();

            startActivity(intent);
        });

    }

}