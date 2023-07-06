package com.example.paneladmin.contenidoExtras.ficherosRaw;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.paneladmin.R;

public class FicherosRaw extends Activity {

    private TextView etiqueta;
    private Button botonleer;
    private BufferedReader br;
    private InputStream ficheroraw;
    private String todo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fichero_raw);


        botonleer=(Button)findViewById(R.id.button1);
        etiqueta=(TextView)findViewById(R.id.textView1);
        botonleer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try
                {
                    String cadena="";
                    ficheroraw = getResources().openRawResource(R.raw.ficheroucam);
                    br =new BufferedReader(new InputStreamReader(ficheroraw));
                    while ((cadena = br.readLine()) != null){
                        Log.i("Aplicacion Ficheros raw", cadena);
                        //todo.concat(todo + cadena);
                    }
                    //etiqueta.setText(todo);
                    ficheroraw.close();
                }
                catch (IOException e){
                    e.printStackTrace();}
            }
        });
    }



}

