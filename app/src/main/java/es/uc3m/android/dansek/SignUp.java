package es.uc3m.android.dansek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class SignUp extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;
    private TextView signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        signUpButton = findViewById(R.id.smallRectangle);
        this.gestureDetector = new GestureDetector(this, this);

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
        int parentWidth = findViewById(R.id.frameLayout).getWidth();

        // Calcular la distancia de desplazamiento (considerando los bordes redondeados)
        int displacement = (parentWidth) - (4 * getResources().getDimensionPixelSize(R.dimen.frame_layout_corner_radius));

        // Implementar el efecto de transición (por ejemplo, animar el botón)
        signUpButton.animate().translationXBy(-displacement).setDuration(100).start();

        // Iniciar LoginActivity después de un breve retraso
        signUpButton.postDelayed(() -> {
            Intent intent = new Intent(SignUp.this, Login.class);
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
