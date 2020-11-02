package com.example.miformulariojoserodriguez5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String nombre;
    EditText editText_nombre;
    Button button_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mostrar Icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_icono);


        // Asociar elementos tipo "Visual" a tipo "codigo"
        editText_nombre = findViewById(R.id.editText_nombre);
        button_next = findViewById(R.id.button_next);
        button_next.setEnabled(false);

        // Control de campo nombre no vacio
        editText_nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                button_next.setEnabled(true);
                if (editText_nombre.getText().toString().equals("")) {
                    button_next.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Esperar Evento del boton
        button_next.setOnClickListener(new View.OnClickListener() {
            /**
             * Recoger el valor del editText y abrir la siguiente Activity
             */
            @Override
            public void onClick(View v) {
                nombre = editText_nombre.getText().toString();

                // Creamos el Intent
                Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
                // Creamos el Bundle
                Bundle bundle = new Bundle();
                //Asociamos Clave-valor
                bundle.putString("NOMBRE", nombre);

                // Añadimos el bundle al intent
                intent.putExtras(bundle);
                // iniciar la nueva actividad
                startActivity(intent);
            }
        });


    }
}