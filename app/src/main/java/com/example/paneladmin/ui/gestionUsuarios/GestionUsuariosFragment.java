package com.example.paneladmin.ui.gestionUsuarios;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paneladmin.BBDD;
import com.example.paneladmin.MainActivity;
import com.example.paneladmin.PanelAdmin;
import com.example.paneladmin.databinding.FragmentoGestionUsuariosBinding;

import org.w3c.dom.Text;


public class GestionUsuariosFragment extends Fragment {

    private GestionUsuariosViewModel gestionUsuariosViewModel;
    private FragmentoGestionUsuariosBinding binding;

    private EditText usuario;
    private EditText contraseña;
    private EditText contraseña2;
    private TextView mostrar;
    private String mostrarString = "";
    private Switch switch1;

    private Button boton1;
    private Button boton2;
    private Button boton3;
    private Button boton4;

    public int establecerPermiso(Switch switch1) {
        int permisos;
        if (switch1.isChecked()) {
            permisos = 1;
            return permisos;
        } else {
            permisos = 0;
            return permisos;            }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gestionUsuariosViewModel =
                new ViewModelProvider(this).get(GestionUsuariosViewModel.class);

        binding = FragmentoGestionUsuariosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        usuario = binding.usuario;
        contraseña = binding.pass;
        contraseña2 = binding.pass2;
        switch1 = binding.switch1;

        boton1 = binding.button;
        boton2 = binding.button2;
        boton3 = binding.button3;
        boton4 = binding.button4;
        mostrar = binding.textView;

        Toast toast;
        SQLiteDatabase mibbdd = MainActivity.getMibbdd();
        ContentValues valores = new ContentValues();
        ContentValues valores2 = new ContentValues();
        ContentValues valores3 = new ContentValues();

        int permisos;

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!usuario.getText().toString().equals("") && !contraseña.getText().toString().equals("") && !contraseña2.getText().toString().equals("")){
                   if(contraseña.getText().toString().equals(contraseña2.getText().toString())) {
                       Cursor c = mibbdd.rawQuery("SELECT * FROM Usuario WHERE usuario='" + usuario.getText().toString() + "'", null);
                       if (c.getCount() == 0) {
                           c.close();
                           valores.put("usuario", usuario.getText().toString());
                           valores.put("contraseña", contraseña.getText().toString());
                           valores.put("permisos", establecerPermiso(switch1));

                           mibbdd.insert("Usuario",null,valores);

                           String campos1[]= new String[] {"id_evento"};

                           Cursor c2 = mibbdd.rawQuery("SELECT id_usuario FROM Usuario WHERE usuario='" + usuario.getText().toString() + "'", null);
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


                           cursorbbdd.close();

                           usuario.setText("");
                           contraseña.setText("");
                           contraseña2.setText("");

                           gestionUsuariosViewModel.getToast6().observe(getViewLifecycleOwner(), new Observer<String>() {
                               @Override
                               public void onChanged(String s) {
                                       Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                               }
                           });

                       }
                       else {
                           gestionUsuariosViewModel.getToast3().observe(getViewLifecycleOwner(), new Observer<String>() {
                               @Override
                               public void onChanged(String s) {
                                   Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                               }
                            });
                       }
                   }
                   else{
                        gestionUsuariosViewModel.getToast2().observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                            }
                        });
                   }
                }
                else{
                    gestionUsuariosViewModel.getToast1().observe(getViewLifecycleOwner(), new Observer<String>(){
                        public void onChanged(String s) {
                            Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!usuario.getText().toString().equals("")){
                    Cursor c = mibbdd.rawQuery("SELECT * FROM Usuario WHERE usuario='" + usuario.getText().toString() + "'", null);
                    if (c.getCount() == 1){
                        c.close();

                        Cursor c2 = mibbdd.rawQuery("SELECT id_usuario FROM Usuario WHERE usuario='" + usuario.getText().toString() + "'", null);

                        int id_usuario = 0;
                        if(c2.moveToFirst()) {
                            do {
                                id_usuario = c2.getInt(0);
                            }
                            while (c2.moveToNext());
                        }
                        c2.close();

                        Cursor cursorbbdd = mibbdd.rawQuery("SELECT id_usuario_evento FROM UsuarioxEvento WHERE id_usuario='" + id_usuario + "'", null);

                        if(cursorbbdd.getCount() > 0){
                            mibbdd.delete("UsuarioxEvento", "id_usuario='" + id_usuario + "'", null);
                        }

                        cursorbbdd.close();

                        mibbdd.delete("Usuario", "usuario='" + usuario.getText().toString() + "'", null);

                        usuario.setText("");
                        contraseña.setText("");
                        contraseña2.setText("");

                        gestionUsuariosViewModel.getToast7().observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else{
                        gestionUsuariosViewModel.getToast5().observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
                else{
                    gestionUsuariosViewModel.getToast4().observe(getViewLifecycleOwner(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!usuario.getText().toString().equals("") && !contraseña.getText().toString().equals("") && !contraseña2.getText().toString().equals("")) {
                    if (contraseña.getText().toString().equals(contraseña2.getText().toString())) {
                        Cursor c = mibbdd.rawQuery("SELECT * FROM Usuario WHERE usuario='" + usuario.getText().toString() + "'", null);
                        if (c.getCount() == 1) {
                            c.close();
                            valores3.put("contraseña", contraseña.getText().toString());
                            valores3.put("permisos", establecerPermiso(switch1));
                            mibbdd.update("Usuario",valores3,"usuario='" + usuario.getText().toString() + "'",null );
                            usuario.setText("");
                            contraseña.setText("");
                            contraseña2.setText("");

                            gestionUsuariosViewModel.getToast8().observe(getViewLifecycleOwner(), new Observer<String>(){
                                public void onChanged(String s) {
                                    Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else{
                            gestionUsuariosViewModel.getToast5().observe(getViewLifecycleOwner(), new Observer<String>() {
                                @Override
                                public void onChanged(String s) {
                                    Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                    else{
                        gestionUsuariosViewModel.getToast2().observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                else{
                    gestionUsuariosViewModel.getToast1().observe(getViewLifecycleOwner(), new Observer<String>(){
                        public void onChanged(String s) {
                            Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrar.setText("");
                String campos1[]= new String[] {"id_usuario","usuario","contraseña","permisos"};


                Cursor cursorbbdd = mibbdd.query("Usuario", campos1, null, null, null, null, null);

                String mostrarPer;
                if (cursorbbdd.moveToFirst()){
                    do{
                        String id = cursorbbdd.getString(0);
                        String usuario = cursorbbdd.getString(1);
                        String contraseña = cursorbbdd.getString(2);
                        int permiso = cursorbbdd.getInt(3);

                        if(permiso == 1){
                            mostrarPer = "Con permisos";
                        }
                        else{
                            mostrarPer = "Sin permisos";
                        }

                        mostrarString = mostrarString + "id: " + id + "\n" +"usuario: "+ usuario +"\n"+ "contraseña: " + contraseña + "\n" +
                                "permisos: " + mostrarPer + "\n" + "\n";

                    }while (cursorbbdd.moveToNext());
                }

                /*if(cursorbbdd2.moveToFirst()){
                    do{
                         String id_usuario_evento = cursorbbdd2.getString(0);
                         String id_usuario = cursorbbdd2.getString(1);
                         String id_evento = cursorbbdd2.getString(2);
                         int reserva = cursorbbdd2.getInt(3);

                         mostrarString = mostrarString + "id_usuario_evento: " + id_usuario_evento + "\n" + "id_usuario: " + id_usuario + "\n" +
                                         "id_evento: " + id_evento + "\n" + "reserva: " + reserva + "\n" + "\n";
                    }
                    while(cursorbbdd2.moveToNext());
                }*/



                gestionUsuariosViewModel.getMostrar().observe(getViewLifecycleOwner(), new Observer<String>(){
                    public void onChanged(String s) {
                        mostrar.setText(mostrarString);
                    }
                });

                mostrarString = "";

            }

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}