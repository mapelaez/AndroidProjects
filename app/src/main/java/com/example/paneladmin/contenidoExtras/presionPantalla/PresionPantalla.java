package com.example.paneladmin.contenidoExtras.presionPantalla;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.paneladmin.R;

public class PresionPantalla extends Activity implements View.OnTouchListener {
    private TextView etiquetaarriba;
    private TextView etiquetaabajo;
    private Button botonReset;
    private float x,y,presure,size;
    private long ms,ms2;
    private String presureTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presion_pantalla);
        etiquetaarriba = (TextView)findViewById(R.id.TextView1);
        etiquetaabajo = (TextView)findViewById(R.id.TextView2);
        etiquetaarriba.setOnTouchListener(this);
        botonReset = (Button)findViewById(R.id.button);


        botonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etiquetaabajo.setText("Pulsaciones: ");
            }
        });
    }




    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x=event.getX();
                y=event.getY();
                ms=event.getDownTime();
                presure=event.getPressure();
                size = event.getSize();

                if(presure >= 1.2){
                    presureTexto = "Fuerte";
                }

                if(presure >= 0 && presure <= 0.8){
                    presureTexto = "Flojo";
                }

                if(presure > 0.8 && presure < 1.2){
                    presureTexto = "Normal";
                }

                etiquetaabajo.append("ACTION_DOWN "+x+" "+y+" ms: "+ms+" presure: " + presureTexto + " size: "+ size + "\n");

                break;

            case MotionEvent.ACTION_MOVE:
                etiquetaabajo.append(" ACTION_MOVE ");
                break;

            case MotionEvent.ACTION_UP:
                ms2=event.getEventTime();
                etiquetaabajo.append(" ACTION_UP "+" evento actual: "+ms2+" ");

                break;

        }
        return true;


    }




}


