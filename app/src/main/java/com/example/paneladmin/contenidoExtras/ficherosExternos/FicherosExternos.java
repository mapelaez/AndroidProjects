package com.example.paneladmin.contenidoExtras.ficherosExternos;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.example.paneladmin.R;

public class FicherosExternos extends Activity {

    private String estado;
    private TextView etiqueta;
    private Button botonguardar;
    private Button botonleer;
    boolean memok = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fichero_externo);

        botonguardar = (Button) findViewById(R.id.button1);
        botonleer = (Button) findViewById(R.id.button2);
        estado = Environment.getExternalStorageState();
        if (estado.equals(Environment.MEDIA_MOUNTED)) {
            memok = true;
        }
        botonguardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                etiqueta = (TextView) findViewById(R.id.TextView1);
                etiqueta.setText(estado.toString());
                if (memok == true) {
                    try {
                        File ruta = new File(getExternalFilesDir(null), "ficheroexterno.txt");
                        Toast toast1 = Toast.makeText(getApplicationContext(), ruta.toString(), Toast.LENGTH_SHORT);
                        toast1.show();
                        OutputStreamWriter salida = new OutputStreamWriter(new FileOutputStream(ruta));
                        salida.write("Otra vez con ficheros, esta vez externos");
                        salida.close();
                    } catch (Exception ex) {
                        Log.e("Ficherosexternos", "Error al escribir fichero en memoria externa");
                    }
                }
            }
        });
        botonleer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    File ruta = new File(getExternalFilesDir(null), "ficheroexterno.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
                    String cadena = br.readLine();
                    br.close();
                    Toast toast1 = Toast.makeText(getApplicationContext(), cadena.toString(), Toast.LENGTH_SHORT);
                    toast1.show();
                } catch (Exception ex) {
                    Log.e("Ficherosexternos", "Error al leer fichero en memoria externa");
                }

            }
        });
    }
}




