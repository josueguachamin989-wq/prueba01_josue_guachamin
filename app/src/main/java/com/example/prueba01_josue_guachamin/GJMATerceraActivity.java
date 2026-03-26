package com.example.prueba01_josue_guachamin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class GJMATerceraActivity extends AppCompatActivity {

    private EditText gjma_et_nombres;
    private EditText gjma_et_apellidos;
    private EditText gjma_et_dividendo;
    private EditText gjma_et_divisor;
    private EditText gjma_et_num;
    private Button gjma_btn_cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gjma_activity_tercera);

        gjma_et_nombres = findViewById(R.id.gjma_et_nombres_s3);
        gjma_et_apellidos = findViewById(R.id.gjma_et_apellidos_s3);
        gjma_et_dividendo = findViewById(R.id.gjma_et_dividendo_s3);
        gjma_et_divisor = findViewById(R.id.gjma_et_divisor_s3);
        gjma_et_num = findViewById(R.id.gjma_et_num_s3);
        gjma_btn_cerrar = findViewById(R.id.gjma_btn_cerrar_s3);

        String nombres = getIntent().getStringExtra("gjma_nombres");
        String apellidos = getIntent().getStringExtra("gjma_apellidos");

        gjma_et_nombres.setText(nombres);
        gjma_et_apellidos.setText(apellidos);

        gjma_btn_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("gjma_dividendo", gjma_et_dividendo.getText().toString());
                resultIntent.putExtra("gjma_divisor", gjma_et_divisor.getText().toString());
                resultIntent.putExtra("gjma_num", gjma_et_num.getText().toString());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}