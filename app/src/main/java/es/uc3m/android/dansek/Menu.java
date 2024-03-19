package es.uc3m.android.dansek;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        Button logOutButton = findViewById(R.id.LogOut);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Notificar al Listener que se ha iniciado el logout
                if (getParent() instanceof LogoutListener) {
                    ((LogoutListener) getParent()).onLogout();
                }
            }
        });
    }
}
