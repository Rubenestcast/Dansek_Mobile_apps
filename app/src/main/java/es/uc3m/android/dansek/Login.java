package es.uc3m.android.dansek;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;


public class Login extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;
    private TextView login_button_drag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        login_button_drag  = findViewById(R.id.smallRectangle);
        TextView login_button = findViewById(R.id.loginButton);
        this.gestureDetector = new GestureDetector(this,this);



        login_button.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), PrincipalScreen.class);
            startActivity(intent);
        });


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
        int parentWidth = findViewById(R.id.frameLayout).getWidth();

        // Calcular la distancia de desplazamiento (considerando los bordes redondeados)
        int displacement = (parentWidth) - (4 * getResources().getDimensionPixelSize(R.dimen.general_corner_25dp));

        // Implementar el efecto de transición (por ejemplo, animar el botón)
        login_button_drag.animate().translationXBy(displacement).setDuration(100).start();

        // Iniciar LoginActivity después de un breve retraso
        login_button_drag.postDelayed(() -> {
            Intent intent = new Intent(Login.this, SignUp.class);
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
