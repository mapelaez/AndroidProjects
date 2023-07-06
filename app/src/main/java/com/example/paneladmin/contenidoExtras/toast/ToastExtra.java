package com.example.paneladmin.contenidoExtras.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paneladmin.R;

public class ToastExtra extends Activity {

    private Button botonToast1, botonToast2, botonToast3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast);

        botonToast1 = (Button) findViewById(R.id.button1);
        botonToast1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),"Toast normal sin modificar nada", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        botonToast2 = (Button) findViewById(R.id.button2);
        botonToast2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast toastg =Toast.makeText(getApplicationContext(),"Toast modificando el gravity", Toast.LENGTH_LONG);
                toastg.setGravity(Gravity.BOTTOM|Gravity.RIGHT,6,6);
                toastg.show();
            }
        });


        botonToast3 = (Button) findViewById(R.id.button3);
        botonToast3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast toastp = new Toast(getApplicationContext());
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_personalizado,(ViewGroup) findViewById(R.id.lytLayout));

                TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
                txtMsg.setText("Toast personalizado con xml propio");

                toastp.setDuration(Toast.LENGTH_LONG);
                toastp.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
                toastp.setView(layout);
                toastp.show();
            }
        });
    }





}
