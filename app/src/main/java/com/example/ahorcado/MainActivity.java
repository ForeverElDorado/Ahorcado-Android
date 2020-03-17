package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String palabraOculta = eligePalabra();
    int numeroDeFallos = 0;
    boolean partidaTerminada = false;
    String palabraConGuiones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.ventanaJuego, new VentanAhorcado()).commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        palabraConGuiones="";
        for (int i = 0; i < palabraOculta.length(); i++) {
            if(palabraOculta.charAt(i)!=' ')palabraConGuiones += "_ ";
            else {
                palabraConGuiones+="  ";
            }
        }
        ((TextView)findViewById(R.id.palabraConGuiones)).setText(palabraConGuiones);

    }
    //Aqui se crea el metodo para elegir una palabra en el ahorcado
    private String eligePalabra() {
        String[] listaPalabras = {"Fraxito", "Papa", "Cajones","Cuarentena","Cursos"};
        Random aleatorio = new Random();
        int pos = aleatorio.nextInt(listaPalabras.length);
        return listaPalabras[pos].toUpperCase();
    }
    public void botonPulsado(View vista){
        Button boton = findViewById(vista.getId());
        boton.setVisibility(View.INVISIBLE);
        chequeaLetra(boton.getText().toString());
    }
    private void  chequeaLetra(String letra){
        //Metodo del ahorcado Netbeans
        if (!partidaTerminada) {
            ImageView imagenAhorcado = ((ImageView) findViewById(R.id.imagenAhorcado));
            TextView textoConGuiones = ((TextView) findViewById(R.id.palabraConGuiones));

            boolean acierto = false;

            for (int i = 0; i < palabraOculta.length(); i++) {
                if (palabraOculta.charAt(i) == letra.charAt(0)) {
                    palabraConGuiones = palabraConGuiones.substring(0, 2 * i) + letra + palabraConGuiones.substring(2 * i + 1);
                    acierto = true;
                }
            }
            if (!palabraConGuiones.contains("_")) {
                imagenAhorcado.setImageResource(R.drawable.acertastetodo);
                partidaTerminada=true;
            }
            textoConGuiones.setText(palabraConGuiones);
            if (!acierto) {
                numeroDeFallos++;
                switch (numeroDeFallos) {
                    case 0:
                        imagenAhorcado.setImageResource(R.drawable.ahorcado_0);
                        break;
                    case 1:
                        imagenAhorcado.setImageResource(R.drawable.ahorcado_1);
                        break;
                    case 2:
                        imagenAhorcado.setImageResource(R.drawable.ahorcado_2);
                        break;
                    case 3:
                        imagenAhorcado.setImageResource(R.drawable.ahorcado_3);
                        break;
                    case 4:
                        imagenAhorcado.setImageResource(R.drawable.ahorcado_4);
                        break;
                    case 5:
                        imagenAhorcado.setImageResource(R.drawable.ahorcado_5);
                        break;
                    case 6:
                        imagenAhorcado.setImageResource(R.drawable.ahorcado_fin);
                        partidaTerminada=true;
                        break;
                }
            }
        }
    }
}
