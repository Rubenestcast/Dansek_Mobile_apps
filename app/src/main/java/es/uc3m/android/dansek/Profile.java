package es.uc3m.android.dansek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import es.uc3m.android.dansek.PrincipalScreen;

public class Profile extends AppCompatActivity {
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        TextView points = findViewById(R.id.points_user);
        TextView logOut = findViewById(R.id.Log_out);
        db = FirebaseFirestore.getInstance();

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

        TextView back_button= findViewById(R.id.BACK);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, PrincipalScreen.class);
                startActivity(intent);
            }
        });

        //Cambiar el nombre
        // Obtener la referencia al TextView User_id
        TextView userIdTextView = findViewById(R.id.User_id);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String userName = currentUser.getEmail();

            if (userName != null && !userName.isEmpty()) {
                // Referencia al documento con el nombre de usuario en la colección "users"
                DocumentReference userRef = db.collection("users").document(userName);

                // Obtener el documento del usuario de Firestore
                userRef.get().addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        // El documento existe, obtener el valor del atributo "field"
                        String name = documentSnapshot.getString("name");
                        if (name != null) {
                            // Actualizar el texto del TextView con el valor del atributo "field"
                            userIdTextView.setText(name);
                        } else {
                            // El atributo "field" no está presente en el documento
                            Log.e("MainActivity", "El documento no contiene el atributo 'name'");
                        }
                    } else {
                        // El documento del usuario no existe en Firestore
                        Log.d("MainActivity", "El documento del usuario no existe en Firestore");
                    }
                }).addOnFailureListener(e -> {
                    // Error al obtener el documento del usuario
                    Log.e("MainActivity", "Error al obtener el documento del usuario", e);
                });
            } else {
                // El nombre de usuario no está disponible
                Log.e("MainActivity", "El nombre de usuario no está disponible");
                Log.d("MainActivity", String.valueOf(currentUser.getEmail()));
            }
        } else {
            // No hay usuario autenticado
            Log.e("MainActivity", "No hay usuario autenticado");
        }







    }
}