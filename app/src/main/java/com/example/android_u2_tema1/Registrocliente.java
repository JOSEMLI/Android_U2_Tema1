package com.example.android_u2_tema1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Registrocliente extends AppCompatActivity {
    TextView txtcod,txtmensaje,txtape,txtsexo,txtcelular,txtdomicilio;
    String cod,titulo,ape,sexo,celular,domicilio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrocliente);

        Bundle extras = getIntent().getExtras();
        cod=extras.getString("codigo");
        titulo = extras.getString("nombre");
        ape = extras.getString("ape");
        sexo=extras.getString("sexo");
        celular=extras.getString("celular");
        domicilio=extras.getString("Domicilio");

        txtcod=findViewById(R.id.codigocli);
        txtmensaje=findViewById(R.id.titulo);
        txtape=findViewById(R.id.apellidocli);
        txtsexo=findViewById(R.id.sexocli);
        txtcelular=findViewById(R.id.celularcli);
        txtdomicilio=findViewById(R.id.domiciliocli);

        txtcod.setText(cod);
        txtmensaje.setText(titulo);
        txtape.setText(ape);
        txtsexo.setText(sexo);
        txtcelular.setText(celular);
        txtdomicilio.setText(domicilio);
    }
}
