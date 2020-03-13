package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.ventanaJuego, new VentanAhorcado()).commit();
        }
    }

    public void botonPulsado(View vista){
        Button boton = findViewById(vista.getId());
        boton.setVisibility(View.INVISIBLE);
    }
}
