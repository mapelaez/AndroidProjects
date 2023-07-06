package com.example.paneladmin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrarseActivity extends  AppCompatActivity {

    EditText nombre, contraseña, recontraseña;
    Button registrarse, loguearse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        nombre = (EditText) findViewById(R.id.usuario);
        contraseña = (EditText) findViewById(R.id.contraseña1);
        recontraseña = (EditText) findViewById(R.id.contraseña2);
        registrarse = (Button) findViewById(R.id.btnregistro);
        loguearse = (Button) findViewById(R.id.btnlogin);

        SQLiteDatabase mibbdd = MainActivity.getMibbdd();
        ContentValues valores = new ContentValues();
        ContentValues valores2 = new ContentValues();


        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = nombre.getText().toString();
                String pass = contraseña.getText().toString();
                String pass2 = recontraseña.getText().toString();

                if(user.equals("")||pass.equals("")||pass2.equals(""))
                    Toast.makeText(RegistrarseActivity.this, "Por favor rellenar todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(pass2)){
                            Boolean revisarSiExiste= BBDD.revisarSiExiste(user);

                        if(revisarSiExiste == false){
                            valores.put("usuario", nombre.getText().toString());
                            valores.put("contraseña", contraseña.getText().toString());
                            valores.put("permisos", 0);

                            mibbdd.insert("Usuario",null,valores);

                            String campos1[]= new String[] {"id_evento"};

                            Cursor c2 = mibbdd.rawQuery("SELECT id_usuario FROM Usuario WHERE usuario='" + nombre.getText().toString() + "'", null);
                            Cursor cursorbbdd = mibbdd.query("Evento", campos1, null, null, null, null, null);

                            int id_usuario = 0;
                            if(c2.moveToFirst()) {
                                do {
                                    id_usuario = c2.getInt(0);
                                }
                                while (c2.moveToNext());
                            }
                            c2.close();

                            if(cursorbbdd.getCount() > 0){
                                if(cursorbbdd.moveToFirst()){
                                    do {
                                        String id_evento = cursorbbdd.getString(0);
                                        valores2.put("id_usuario", id_usuario);
                                        valores2.put("id_evento",id_evento);
                                        valores2.put("reserva", 0);
                                        mibbdd.insert("UsuarioxEvento",null,valores2);
                                    }
                                    while(cursorbbdd.moveToNext());
                                }
                            }

                            Toast.makeText(RegistrarseActivity.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(RegistrarseActivity.this, "Ya se encuentra un usuario con ese nombre", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegistrarseActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

        loguearse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

