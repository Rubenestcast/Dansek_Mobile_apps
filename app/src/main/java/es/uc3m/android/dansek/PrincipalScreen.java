package es.uc3m.android.dansek;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class PrincipalScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_screen_layout);

        // Obtener la referencia al TextView del menú (burguerMenu)
        View burguerMenu = findViewById(R.id.burguerMenu);
        View mapMenu = findViewById(R.id.mapMenu);

        // Configurar el OnClickListener para el burguerMenu
        burguerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar el menú emergente cuando se hace clic en el burguerMenu
                showPopupMenu(v);
            }
        });
        mapMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar el menú emergente cuando se hace clic en el burguerMenu
                showPopupMapMenu(v);
            }
        });


    }

    // Método para mostrar el menú emergente
    private void showPopupMenu(View view) {
        // Inflar el layout del menú emergente
        View popupView = LayoutInflater.from(this).inflate(R.layout.menu_layout, null);

        // Obtener el ancho de la pantalla
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;

        float menuWidthFloat = (float) screenWidth;
        int menuWidth = (int) menuWidthFloat;


        // Crear el PopupWindow con el ancho calculado
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        PopupWindow popupWindow = new PopupWindow(popupView, menuWidth, height, true);

        // Mostrar el PopupWindow en la posición deseada (izquierda de la pantalla)
        popupWindow.showAtLocation(view, Gravity.START, 0, 0);
    }

    private void showPopupMapMenu(View view) {
        // Inflar el layout del menú emergente
        View popupView = LayoutInflater.from(this).inflate(R.layout.map_layout, null);

        // Obtener el ancho de la pantalla
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;

        float menuWidthFloat = (float) screenWidth / 1.5f;
        int menuWidth = (int) menuWidthFloat;


        // Crear el PopupWindow con el ancho calculado
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        PopupWindow popupWindow = new PopupWindow(popupView, menuWidth, height, true);

        // Mostrar el PopupWindow en la posición deseada (izquierda de la pantalla)
        popupWindow.showAtLocation(view, Gravity.END, 0, 0);
    }
}
