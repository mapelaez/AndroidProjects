package com.example.paneladmin.contenidoExtras.ficherosInternos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.paneladmin.R;

public class FicherosInternos extends Activity {

        private String nombrefichero = "Ucam.txt";
        private String cadena;
        private FileOutputStream fichero;
        private InputStreamReader ficherolectura;
        private BufferedReader br;
        private Button botonguardar;
        private Button botonleer;
        private EditText campotexto;
        private TextView etiqueta;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fichero_interno);

            campotexto = (EditText)findViewById(R.id.editText1);
            botonguardar = (Button)findViewById(R.id.button1);
            botonleer = (Button)findViewById(R.id.button2);
            etiqueta = (TextView)findViewById(R.id.textView1);
            botonguardar.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    try {
                        fichero = openFileOutput(nombrefichero, Context.MODE_APPEND);
                        fichero.write(campotexto.getText().toString().getBytes());
                        fichero.close();
                    } catch (FileNotFoundException e) {
                        Log.e("Aplicacion Ficheros", e.getMessage(), e);
                    } catch (IOException e) {
                        Log.e("Aplicacion Ficheros", e.getMessage(), e);
                    }
                }
            });


            botonleer.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    try {
                        ficherolectura = new InputStreamReader(openFileInput("Ucam.txt"));
                        br = new BufferedReader(ficherolectura);
                        cadena = br.readLine();
                        while (cadena != null){
                            etiqueta.setText(cadena);
                            cadena=br.readLine();
                        }
                    } catch (Exception ex) {
                        Log.e("Aplicacion ficheros", "Error leyendo de fichero");
                    }
                    finally{
                        try{
                            if (ficherolectura!=null)
                                ficherolectura.close();
                        }catch (IOException ioe){
                            ioe.printStackTrace();
                        }
                    }
                }
            });



        }


    }
