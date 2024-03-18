package es.uc3m.android.dansek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

        View nightclub_acces = findViewById(R.id.nightclub_acces);
        nightclub_acces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad MainActivity
                Intent intent = new Intent(MainActivity.this, Login_nightclubs.class);
                startActivity(intent);
                // Cerrar la actividad actual
                finish();
            }
        });
    }

}