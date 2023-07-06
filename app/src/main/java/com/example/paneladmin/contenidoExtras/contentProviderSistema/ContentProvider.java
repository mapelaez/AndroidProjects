package com.example.paneladmin.contenidoExtras.contentProviderSistema;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.paneladmin.R;

public class ContentProvider extends AppCompatActivity {

    private Button botonConsulta, botonAñadir;
    private TextView etiquetaMostrar;
    private EditText campoNombre, campoTelefono;
    private Toast mostrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_provider_sistema);



        // Fast way
        ActivityCompat.requestPermissions(ContentProvider.this, new String[] {
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
        }, 1);

        botonAñadir = (Button)findViewById(R.id.button);
        botonConsulta = (Button)findViewById(R.id.button2);
        etiquetaMostrar = (TextView)findViewById(R.id.mostrar);
        campoNombre = (EditText)findViewById(R.id.editTextNombre);
        campoTelefono = (EditText)findViewById(R.id.editTextNumero);



        botonAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!campoNombre.getText().toString().isEmpty() && !campoTelefono.getText().toString().isEmpty()){

                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, campoNombre.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, campoTelefono.getText().toString());

                    if(intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }else{
                        mostrar = Toast.makeText(getApplicationContext(),"No existe una aplicación que soporte esta acción", Toast.LENGTH_SHORT);
                        mostrar.show();
                    }

                }
                else{
                    mostrar = Toast.makeText(getApplicationContext(),"Rellena los campos", Toast.LENGTH_SHORT);
                    mostrar.show();
                }
            }
        });

        botonConsulta.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("Range")
            public void onClick(View v){
                etiquetaMostrar.setText("");

                String[] projeccion = new String[] { ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME
                        , ContactsContract.CommonDataKinds.Phone.NUMBER};

                String seleccion = ContactsContract.Data.MIMETYPE + "='" +
                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                        + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

                String orden = ContactsContract.Data.DISPLAY_NAME + " ASC";

                Cursor miCursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI, projeccion, seleccion, null,
                        orden);


                while(miCursor.moveToNext()) {
                    etiquetaMostrar.append("Identificador: " + miCursor.getString(0) + " Nombre: " + miCursor.getString(1)
                            + " Número: " + miCursor.getString(2) +"\n");
                }

                miCursor.close();
            }
        });

    }
}
