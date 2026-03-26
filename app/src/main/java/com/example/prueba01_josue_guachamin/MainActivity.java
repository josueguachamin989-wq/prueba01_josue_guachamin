package com.example.prueba01_josue_guachamin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText gsja_et_nombres;
    private EditText gsja_et_apellidos;
    private EditText gsja_et_dividendo;
    private EditText gsja_et_divisor;
    private EditText gsja_et_multiplicacion;
    private EditText gsja_et_potencia;
    private EditText gsja_et_factorial;
    private Button gsja_btn_siguiente;
    private Button gsja_btn_mostrar_resultados;

    private String gsja_nombre_recibido = "";
    private String gsja_apellido_recibido = "";
    private int gsja_num1_recibido = 0;
    private int gsja_num2_recibido = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gsja_activity_main);

        gsja_et_nombres = findViewById(R.id.gsja_et_nombres);
        gsja_et_apellidos = findViewById(R.id.gsja_et_apellidos);
        gsja_et_dividendo = findViewById(R.id.gsja_et_dividendo);
        gsja_et_divisor = findViewById(R.id.gsja_et_divisor);
        gsja_et_multiplicacion = findViewById(R.id.gsja_et_parte_entera);
        gsja_et_potencia = findViewById(R.id.gsja_et_residuo);
        gsja_et_factorial = findViewById(R.id.gsja_et_num_invertido);
        gsja_btn_siguiente = findViewById(R.id.gsja_btn_siguiente);
        gsja_btn_mostrar_resultados = findViewById(R.id.gsja_btn_mostrar_resultados);

        gsja_btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gsja_intent = new Intent(MainActivity.this, GSJASegundaActivity.class);
                startActivityForResult(gsja_intent, 200);
            }
        });

        gsja_btn_mostrar_resultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gsja_mostrar_resultados_metodo();
            }
        });
    }

    private void gsja_mostrar_resultados_metodo() {
        gsja_et_nombres.setText(gsja_nombre_recibido);
        gsja_et_apellidos.setText(gsja_apellido_recibido);
        gsja_et_dividendo.setText(String.valueOf(gsja_num1_recibido));
        gsja_et_divisor.setText(String.valueOf(gsja_num2_recibido));

        // Multiplicación usando solo sumas
        int gsja_mult = 0;
        for (int gsja_i = 0; gsja_i < gsja_num2_recibido; gsja_i++) {
            gsja_mult = gsja_mult + gsja_num1_recibido;
        }
        gsja_et_multiplicacion.setText(String.valueOf(gsja_mult));

        // Potencia (num1 ^ num2) usando solo sumas/restas
        // num1^num2 = num1 * num1 * ... * num1 (num2 veces)
        int gsja_pot = 1;
        for (int gsja_i = 0; gsja_i < gsja_num2_recibido; gsja_i++) {
            // gsja_pot = gsja_pot * gsja_num1_recibido (reemplazando por sumas)
            int gsja_temp_mult = 0;
            for (int gsja_j = 0; gsja_j < gsja_num1_recibido; gsja_j++) {
                gsja_temp_mult = gsja_temp_mult + gsja_pot;
            }
            gsja_pot = gsja_temp_mult;
        }
        gsja_et_potencia.setText(String.valueOf(gsja_pot));

        // Factorial del primer numero usando solo sumas/restas
        int gsja_fact = 1;
        for (int gsja_i = 1; gsja_i <= gsja_num1_recibido; gsja_i++) {
            // gsja_fact = gsja_fact * gsja_i (reemplazando por sumas)
            int gsja_temp_mult_fact = 0;
            for (int gsja_j = 0; gsja_j < gsja_i; gsja_j++) {
                gsja_temp_mult_fact = gsja_temp_mult_fact + gsja_fact;
            }
            gsja_fact = gsja_temp_mult_fact;
        }
        gsja_et_factorial.setText(String.valueOf(gsja_fact));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK && data != null) {
            String gsja_full = data.getStringExtra("gsja_datos_completos");
            if (gsja_full != null) {
                String[] gsja_parts = gsja_full.split("\\|");
                if (gsja_parts.length == 4) {
                    gsja_nombre_recibido = gsja_parts[0];
                    gsja_apellido_recibido = gsja_parts[1];
                    gsja_num1_recibido = Integer.parseInt(gsja_parts[2]);
                    gsja_num2_recibido = Integer.parseInt(gsja_parts[3]);
                }
            }
        }
    }
}