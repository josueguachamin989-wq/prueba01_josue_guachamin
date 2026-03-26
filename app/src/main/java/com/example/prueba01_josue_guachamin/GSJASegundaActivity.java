package com.example.prueba01_josue_guachamin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GSJASegundaActivity extends AppCompatActivity {

    private EditText gsja_et_nombres;
    private EditText gsja_et_apellidos;
    private EditText gsja_et_dividendo;
    private EditText gsja_et_divisor;
    private Button gsja_btn_siguiente;
    private Button gsja_btn_cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gsja_activity_segunda);

        gsja_et_nombres = findViewById(R.id.gsja_et_nombres_s2);
        gsja_et_apellidos = findViewById(R.id.gsja_et_apellidos_s2);
        gsja_et_dividendo = findViewById(R.id.gsja_et_dividendo_s2);
        gsja_et_divisor = findViewById(R.id.gsja_et_divisor_s2);
        gsja_btn_siguiente = findViewById(R.id.gsja_btn_siguiente_s2);
        gsja_btn_cerrar = findViewById(R.id.gsja_btn_cerrar_s2);

        // Estado Inicial: Bloqueado
        gsja_et_nombres.setEnabled(false);
        gsja_et_apellidos.setEnabled(false);
        gsja_et_dividendo.setEnabled(false);
        gsja_et_divisor.setEnabled(false);
        gsja_btn_cerrar.setEnabled(false);

        gsja_btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gsja_intent = new Intent(GSJASegundaActivity.this, GSJATerceraActivity.class);
                // Pasar parámetros en una sola variable
                String gsja_envio = gsja_et_nombres.getText().toString() + "|" + gsja_et_apellidos.getText().toString();
                gsja_intent.putExtra("gsja_datos_pasados", gsja_envio);
                startActivityForResult(gsja_intent, 100);
            }
        });

        gsja_btn_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gsja_nom = gsja_et_nombres.getText().toString().trim();
                String gsja_ape = gsja_et_apellidos.getText().toString().trim();

                // Validación: No pueden estar vacíos
                if (gsja_nom.isEmpty() || gsja_ape.isEmpty()) {
                    Toast.makeText(GSJASegundaActivity.this, "Error: Nombres y Apellidos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Enviar datos a la Actividad 1 en una sola variable
                Intent gsja_resultIntent = new Intent();
                String gsja_concatenado = gsja_nom + "|" + gsja_ape + "|" + gsja_et_dividendo.getText().toString() + "|" + gsja_et_divisor.getText().toString();
                gsja_resultIntent.putExtra("gsja_datos_completos", gsja_concatenado);
                setResult(RESULT_OK, gsja_resultIntent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            // Recibir parámetros en una sola variable
            String gsja_numeros_raw = data.getStringExtra("gsja_datos_numeros");
            if (gsja_numeros_raw != null) {
                String[] gsja_parts = gsja_numeros_raw.split(";");
                if (gsja_parts.length == 2) {
                    gsja_et_dividendo.setText(gsja_parts[0]);
                    gsja_et_divisor.setText(gsja_parts[1]);
                }
            }
            
            // Habilitar campos solo al regresar de la ventana 3
            gsja_et_nombres.setEnabled(true);
            gsja_et_apellidos.setEnabled(true);
            gsja_btn_cerrar.setEnabled(true);
        }
    }
}