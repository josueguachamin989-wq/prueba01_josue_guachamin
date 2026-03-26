package com.example.prueba01_josue_guachamin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GJMASegundaActivity extends AppCompatActivity {

    private EditText gjma_et_nombres;
    private EditText gjma_et_apellidos;
    private EditText gjma_et_dividendo;
    private EditText gjma_et_divisor;
    private EditText gjma_et_num;
    private Button gjma_btn_siguiente;
    private Button gjma_btn_cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gjma_activity_segunda);

        gjma_et_nombres = findViewById(R.id.gjma_et_nombres_s2);
        gjma_et_apellidos = findViewById(R.id.gjma_et_apellidos_s2);
        gjma_et_dividendo = findViewById(R.id.gjma_et_dividendo_s2);
        gjma_et_divisor = findViewById(R.id.gjma_et_divisor_s2);
        gjma_et_num = findViewById(R.id.gjma_et_num_s2);
        gjma_btn_siguiente = findViewById(R.id.gjma_btn_siguiente_s2);
        gjma_btn_cerrar = findViewById(R.id.gjma_btn_cerrar_s2);

        gjma_btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GJMASegundaActivity.this, GJMATerceraActivity.class);
                intent.putExtra("gjma_nombres", gjma_et_nombres.getText().toString());
                intent.putExtra("gjma_apellidos", gjma_et_apellidos.getText().toString());
                startActivityForResult(intent, 100);
            }
        });

        gjma_btn_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("gjma_nombres", gjma_et_nombres.getText().toString());
                resultIntent.putExtra("gjma_apellidos", gjma_et_apellidos.getText().toString());
                resultIntent.putExtra("gjma_dividendo", gjma_et_dividendo.getText().toString());
                resultIntent.putExtra("gjma_divisor", gjma_et_divisor.getText().toString());
                resultIntent.putExtra("gjma_num", gjma_et_num.getText().toString());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            gjma_et_dividendo.setText(data.getStringExtra("gjma_dividendo"));
            gjma_et_divisor.setText(data.getStringExtra("gjma_divisor"));
            gjma_et_num.setText(data.getStringExtra("gjma_num"));
            gjma_btn_cerrar.setEnabled(true);
        }
    }
}