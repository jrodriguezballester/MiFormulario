package com.example.miformulariojoserodriguez5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TerceraActivity extends AppCompatActivity {
    String nombre, edad, sexo, sexo1, texto_correo, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera);

        // mostrar Icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_icono);

        // mostrar Up
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Asociar elementos tipo "Visual" a tipo "codigo"
        final Button button_ver = findViewById(R.id.button_ver);
        final Button button_enviar2 = findViewById(R.id.button_enviar2);
        final EditText editText_correo = findViewById(R.id.editText_correo);
        button_enviar2.setVisibility(View.INVISIBLE);
        // Recoger Datos del bundle de activity2
        Bundle bundle_entrada = this.getIntent().getExtras();
        nombre = null;
        if (bundle_entrada != null) {
            nombre = bundle_entrada.getString("NOMBRE");
            edad = bundle_entrada.getString("EDAD");
            sexo = bundle_entrada.getString("SEXO");
            if (sexo.equals("Hombre")) {
                sexo1 = " hombre";
            } else {
                sexo1 = "a mujer ";
            }
        }


        // Control de campo email
        editText_correo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                correo = editText_correo.getText().toString();
                if (correo.matches("^[A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                    button_enviar2.setVisibility(View.VISIBLE);
                } else {
                    button_enviar2.setVisibility(View.INVISIBLE);
                }
                ;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        texto_correo = "Has introducido los siguientes datos: \n Nombre:" + nombre + "\nEdad: " + edad + "\nSexo: " + sexo;
        // Esperar evento boton enviar correo
        button_enviar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Crear intent para abrir gmail
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{correo});
                email.putExtra(Intent.EXTRA_SUBJECT, "Datos App");
                email.putExtra(Intent.EXTRA_TEXT, texto_correo);

                //need this to prompts email client only
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });


        button_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear el Toast
                CharSequence text = "Hola " + nombre + ", eres un" + sexo1 + " de " + edad + " años";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);

                // quitar el boton y mostrar toast
                button_ver.setVisibility(View.INVISIBLE);
                toast.show();

            }
        });
    }

    public static boolean ValidarMail(String correo) {
        // Patron para validar el email
        // Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        //  Matcher mather = pattern.matcher(correo);
        return correo.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    }
}