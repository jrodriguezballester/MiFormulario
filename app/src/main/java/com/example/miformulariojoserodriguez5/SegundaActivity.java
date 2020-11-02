package com.example.miformulariojoserodriguez5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SegundaActivity extends AppCompatActivity {
    String nombre, edad, sexo;
    RadioGroup radioGroup_sexo;
    TextView textView_edad;
    RadioButton radioButton_hombre;
    Button button_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        // Asociar elementos tipo "Visual" a tipo "codigo"
        textView_edad = findViewById(R.id.textView_edad);
        radioGroup_sexo = findViewById(R.id.radioGroup_sexo);
        radioButton_hombre = findViewById(R.id.radioButton_hombre);
        button_next = findViewById(R.id.button_next2);

        SeekBar seekBar_edad = findViewById(R.id.seekBar);


        // mostrar Icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_icono);
        // mostrar Up
        getSupportActionBar().setHomeButtonEnabled(true);


        // Datos del bundle de activity1
        Bundle bundle_entrada = this.getIntent().getExtras();
        nombre = null;
        if (bundle_entrada != null) {
            nombre = bundle_entrada.getString("NOMBRE");
        }

        // Esperar Evento del boton
        button_next.setOnClickListener(new View.OnClickListener() {
            /**
             * Recoger valores y abrir la siguiente Activity
             */
            @Override
            public void onClick(View v) {

                // identificar el radiobutton seleccionado
                int radioButtonID_select = radioGroup_sexo.getCheckedRadioButtonId();
                RadioButton radioButton_selected = findViewById(radioGroup_sexo.getCheckedRadioButtonId());
                sexo = radioButton_selected.getText().toString();


                // Creamos el Intent
                Intent intent = new Intent(SegundaActivity.this, TerceraActivity.class);

                // Creamos el Bundle
                Bundle bundle = new Bundle();

                //Asociamos Clave-valor
                bundle.putString("NOMBRE", nombre);
                bundle.putString("EDAD", edad);
                bundle.putString("SEXO", sexo);

                // Añadimos el bundle al intent
                intent.putExtras(bundle);

                // iniciar la nueva actividad
                startActivity(intent);
            }
        });

        // seekbar
        // Crear el Toast
        CharSequence text = "La edad debe ser mayor de 16 y menor de 55 años";
        int duration = Toast.LENGTH_LONG;
        final Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);

        seekBar_edad.setMax(100);
        seekBar_edad.setProgress(50);
        textView_edad.setText(String.valueOf(seekBar_edad.getProgress()));
        //     seekBar_edad.setMin(0);
        //Esperar cambio en seekbar
        seekBar_edad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            // Cuando cambia seekbar
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_edad.setText(String.valueOf(progress));
                edad = textView_edad.getText().toString();
                if (progress < 16 || progress > 55) {
                    button_next.setVisibility(View.GONE);
                    toast.show();
                } else {
                    button_next.setVisibility(View.VISIBLE);
                }
            }

            // Cuando empieza
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            // Cuando acaba
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}