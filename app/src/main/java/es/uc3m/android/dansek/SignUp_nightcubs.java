package es.uc3m.android.dansek;

import static es.uc3m.android.dansek.Login.displayDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SignUp_nightcubs extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;
    private TextView signUpButton_drag_nightclubs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_nightclubs_up_layout);

        signUpButton_drag_nightclubs = findViewById(R.id.smallRectangle_nightclubs);
        TextView sign_upButton = findViewById(R.id.signUpButton_nightclubs);
        this.gestureDetector = new GestureDetector(this, this);


        findViewById(R.id.signUpButton_nightclubs).setOnClickListener(this::signUp_nightclubs);

        TextView backButton = findViewById(R.id.back_button_signup_nightclubs);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad MainActivity
                Intent intent = new Intent(SignUp_nightcubs.this, MainActivity.class);
                startActivity(intent);
                // Cerrar la actividad actual
                finish();
            }
        });

    }

    private void signUp_nightclubs(View view) {

        EditText userEmail = findViewById(R.id.email_edit_text_nightclubs);
        EditText nightclubDirection = findViewById(R.id.direcion_facturacion_edit_text);
        EditText userPassword = findViewById(R.id.password_edit_text_nightclubs);
        EditText userPasswordConfirm = findViewById(R.id.password_edit_text2_nightclubs);

        String email = userEmail.getText().toString();
        String direccion_facturación = nightclubDirection.getText().toString();
        String password = userPassword.getText().toString();
        String passwordConfirm = userPasswordConfirm.getText().toString();

        if (!isValidEmailAddress(email)) {
            displayDialog(this, R.string.sing_up_error_title, R.string.sing_up_error_invalid_email);
        } else if (!password.equals(passwordConfirm)) {
            displayDialog(this, R.string.sing_up_error_title, R.string.sing_up_error_passwd_diff);
        } else if (password.length() < 6) {
            displayDialog(this, R.string.sing_up_error_title, R.string.sing_up_error_passwd_diff);
        } else {
            // Initialize Firebase Auth
            FirebaseAuth mAuth = FirebaseAuth.getInstance();

            // Create user
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new MyCompleteListener(this, mAuth));


        }


    }



    // Source: https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
    public boolean isValidEmailAddress(String email) {
        String ePattern =
                "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    // These below are the methods we ose for the drag movement
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
        if (e1.getX() < e2.getX()) {
            // Swipe from left to right (ignore)
            return false;
        } else if (e1.getX() - e2.getX() > 50 && Math.abs(velocityX) > 200) {
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
        signUpButton_drag_nightclubs.animate().translationXBy(-displacement).setDuration(100).start();

        // Iniciar LoginActivity después de un breve retraso
        signUpButton_drag_nightclubs.postDelayed(() -> {
            Intent intent = new Intent(SignUp_nightcubs.this, Login_nightclubs.class);
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
