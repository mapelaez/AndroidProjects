package com.example.paneladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paneladmin.BBDD;

public class MainActivity extends AppCompatActivity {

    private EditText nombre, contraseña;
    private Button btnlogin, btnregistrarse;
    private static SQLiteDatabase mibbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BBDD laBBDD = new BBDD(this, "BBDD", null, 1);
        this.mibbdd = laBBDD.getWritableDatabase();
        ContentValues valores = new ContentValues();

        Cursor c = mibbdd.rawQuery("SELECT id_usuario FROM Usuario WHERE usuario='admin'", null);
        if(c.getCount() == 0 ){
            valores.put("usuario", "admin");
            valores.put("contraseña","admin");
            valores.put("permisos",1);
            mibbdd.insert("Usuario",null,valores);
        }
        c.close();

        nombre = (EditText) findViewById(R.id.nombre1);
        contraseña = (EditText) findViewById(R.id.contraseña1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        btnregistrarse = (Button) findViewById(R.id.btnregistro);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuario = nombre.getText().toString();
                String pass = contraseña.getText().toString();

                if(usuario.equals("")|| pass.equals(""))
                    Toast.makeText(MainActivity.this, "No deje campos vacios", Toast.LENGTH_SHORT).show();
                else{
                    Boolean revisarUser = BBDD.revisarUser(usuario, pass);
                    if(revisarUser == true){
                        Boolean revisarPermiso= BBDD.revisarPermiso(usuario);
                        if(revisarPermiso == true) {
                            Toast.makeText(MainActivity.this, "Login correcto", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), PanelAdmin.class);
                            startActivity(intent);
                            nombre.setText("");
                            contraseña.setText("");
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Login correcto", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, PanelUsuario.class);
                            intent.putExtra("usuario",nombre.getText().toString());
                            startActivity(intent);
                            nombre.setText("");
                            contraseña.setText("");
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Contraseña o usuario incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnregistrarse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrarseActivity.class);
                startActivity(intent);
            }
        });
    }

    public static SQLiteDatabase getMibbdd() {
        return mibbdd;
    }

}
