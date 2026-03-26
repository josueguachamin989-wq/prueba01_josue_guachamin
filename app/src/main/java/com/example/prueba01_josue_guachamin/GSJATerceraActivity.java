package com.example.prueba01_josue_guachamin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GSJATerceraActivity extends AppCompatActivity {

    private EditText gsja_et_nombres;
    private EditText gsja_et_apellidos;
    private EditText gsja_et_dividendo;
    private EditText gsja_et_divisor;
    private Button gsja_btn_cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gsja_activity_tercera);

        gsja_et_nombres = findViewById(R.id.gsja_et_nombres_s3);
        gsja_et_apellidos = findViewById(R.id.gsja_et_apellidos_s3);
        gsja_et_dividendo = findViewById(R.id.gsja_et_dividendo_s3);
        gsja_et_divisor = findViewById(R.id.gsja_et_divisor_s3);
        gsja_btn_cerrar = findViewById(R.id.gsja_btn_cerrar_s3);

        String gsja_nombres = getIntent().getStringExtra("gsja_nombres");
        String gsja_apellidos = getIntent().getStringExtra("gsja_apellidos");

        gsja_et_nombres.setText(gsja_nombres);
        gsja_et_apellidos.setText(gsja_apellidos);

        gsja_btn_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gsja_val1 = gsja_et_dividendo.getText().toString().trim();
                String gsja_val2 = gsja_et_divisor.getText().toString().trim();

                // 1. Validar que no estén vacíos
                if (gsja_val1.isEmpty() || gsja_val2.isEmpty()) {
                    Toast.makeText(GSJATerceraActivity.this, "Error: Ingrese ambos números", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int gsja_n1 = Integer.parseInt(gsja_val1);
                    int gsja_n2 = Integer.parseInt(gsja_val2);

                    // 2. Validar que no sean 0 ni negativos
                    if (gsja_n1 <= 0 || gsja_n2 <= 0) {
                        Toast.makeText(GSJATerceraActivity.this, "Error: Los números deben ser mayores a cero", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Si todo es válido, enviar datos
                    Intent gsja_resultIntent = new Intent();
                    String gsja_data = gsja_val1 + ";" + gsja_val2;
                    gsja_resultIntent.putExtra("gsja_datos_numeros", gsja_data);
                    setResult(RESULT_OK, gsja_resultIntent);
                    finish();

                } catch (NumberFormatException e) {
                    Toast.makeText(GSJATerceraActivity.this, "Error: Ingrese números válidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}