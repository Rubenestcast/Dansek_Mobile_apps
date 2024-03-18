package es.uc3m.android.dansek;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import static es.uc3m.android.dansek.Login.displayDialog;

public class Login_nightclubs extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;
    private TextView login_button_drag_nightclubs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_nightclubs_layout);
        TextView login_button = findViewById(R.id.loginButton_nightclubs);

        login_button_drag_nightclubs  = findViewById(R.id.smallRectangle_nightclubs);
        this.gestureDetector = new GestureDetector(this,this);

        login_button.setOnClickListener(this::login);

        TextView backButton = findViewById(R.id.back_button_login_nightclubs);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad MainActivity
                Intent intent = new Intent(Login_nightclubs.this, MainActivity.class);
                startActivity(intent);
                // Cerrar la actividad actual
                finish();
            }
        });

    }
    private void login(View view) {
        EditText userEmail = findViewById(R.id.email_edit_text_nightclubs);
        EditText userPassword = findViewById(R.id.password_edit_text_nightclubs);

        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if (email.isEmpty()) {
            displayDialog(this, R.string.login_error_title, R.string.login_error_empty_email);
        } else if (password.isEmpty()) {
            displayDialog(this, R.string.login_error_title, R.string.login_error_empty_passwd);
        } else {
            // Initialize Firebase Auth
            FirebaseAuth mAuth = FirebaseAuth.getInstance();

            // Login user
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new MyCompleteListener(this, mAuth));

        }

    }




    

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() > e2.getX()) {
            // Swipe from left to right (ignore)
            return false;
        } else if (e1.getX() - e2.getX() < 50 && Math.abs(velocityX) > 200) {
            // Swipe from right to left with enough distance and velocity
            // Perform the transition
            performTransition();
            return true;
        }
        return false;
    }
    private void performTransition() {
        // Obtener las dimensiones del padre (frameLayout)
        int parentWidth = findViewById(R.id.frameLayout_nightclubs).getWidth();

        // Calcular la distancia de desplazamiento (considerando los bordes redondeados)
        int displacement = (parentWidth) - (4 * getResources().getDimensionPixelSize(R.dimen.general_corner_25dp));

        // Implementar el efecto de transición (por ejemplo, animar el botón)
        login_button_drag_nightclubs.animate().translationXBy(displacement).setDuration(100).start();

        // Iniciar LoginActivity después de un breve retraso
        login_button_drag_nightclubs.postDelayed(() -> {
            Intent intent = new Intent(Login_nightclubs.this, SignUp_nightcubs.class);
            startActivity(intent);
            finish();
        }, 50); // El retraso debe coincidir con la duración de la animación
    }
































    // Unused methods below, removed for simplicity
    @Override
    public void onShowPress(MotionEvent e) {}
    @Override
    public boolean onSingleTapUp(MotionEvent e) { return true; }
    @Override
    public void onLongPress(MotionEvent e) {}
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { return true; }
}
