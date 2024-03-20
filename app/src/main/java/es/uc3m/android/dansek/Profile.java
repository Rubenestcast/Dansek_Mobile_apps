package es.uc3m.android.dansek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import es.uc3m.android.dansek.PrincipalScreen;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        TextView points = findViewById(R.id.points_user);
        TextView logOut = findViewById(R.id.Log_out);

        points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Points.class);
                startActivity(intent);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llama al método onLogout de PrincipalScreen pasando el contexto actual
                PrincipalScreen.onLogout(Profile.this);
            }
        });

        TextView back_arrow = findViewById(R.id.BACK);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, PrincipalScreen.class);
                startActivity(intent);
            }
        });
    }
}