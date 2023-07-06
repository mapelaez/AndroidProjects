package com.example.paneladmin.contenidoExtras.sharedPreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.paneladmin.R;

public class SharedPreference extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preferences);

        final TextView etiqueta = (TextView) findViewById(R.id.textView2);
        final EditText cuadronombre =(EditText) findViewById(R.id.CampoNombre);
        final EditText cuadroalias =(EditText) findViewById(R.id.CampoAlias);
        final Button botonguardarnombre =(Button) findViewById(R.id.buttonguardar);
        final Button botonguardaralias =(Button) findViewById(R.id.button1);
        final ToggleButton toggle =(ToggleButton) findViewById(R.id.toggleButton1);
        final Button botonmuestra =(Button) findViewById(R.id.buttonmostrar);

        botonguardarnombre.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String nombre=cuadronombre.getText().toString();
                SharedPreferences preferencias = getSharedPreferences("perfil", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();

                editor.putString("nombre", nombre );
                editor.commit();
                cuadronombre.setText("");
            }//onClick
        });

        botonguardaralias.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String nick=cuadroalias.getText().toString();
                SharedPreferences preferencias = getSharedPreferences("perfil", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString("alias", nick );
                editor.commit();
                cuadroalias.setText("");
            }//onClick
        });

        toggle.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                final String musica;
                if (toggle.isChecked())
                    musica="ON";
                else
                    musica="OFF";

                SharedPreferences preferencias = getSharedPreferences("perfil", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString("music", musica );
                editor.commit();
            }//onClick
        });

        botonmuestra.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                SharedPreferences preferencias = getSharedPreferences("perfil", MODE_PRIVATE);
                String nombre = preferencias.getString("nombre", "no definido");
                String alias = preferencias.getString("alias", "no definido");
                String musiconoff= preferencias.getString("music", "sin definir");
                etiqueta.setText(nombre +", "+ alias +", " + musiconoff);
            }//onClick
        });

    }



}

