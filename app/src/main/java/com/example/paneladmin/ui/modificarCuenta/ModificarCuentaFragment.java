package com.example.paneladmin.ui.modificarCuenta;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.paneladmin.MainActivity;
import com.example.paneladmin.databinding.FragmentoPerfilBinding;
import com.example.paneladmin.ui.gestionUsuarios.GestionUsuariosViewModel;

public class ModificarCuentaFragment extends Fragment {


    private ModificarCuentaViewModel modificarCuentaViewModel;
    private FragmentoPerfilBinding binding;
    private EditText contraseñaAntigua;
    private EditText contraseñaNueva;
    private String nomUsuario;
    private Button boton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        modificarCuentaViewModel =
                new ViewModelProvider(this).get(ModificarCuentaViewModel.class);

        binding = FragmentoPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        contraseñaAntigua = binding.editTextTextPassword;
        contraseñaNueva = binding.editTextTextPassword2;
        boton = binding.button8;

        Intent intent =   getActivity().getIntent();
        nomUsuario = intent.getStringExtra("usuario");
        SQLiteDatabase mibbdd = MainActivity.getMibbdd();
        ContentValues valores = new ContentValues();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!contraseñaAntigua.getText().toString().equals("") && !contraseñaNueva.getText().toString().equals("")){
                    Cursor c1 = mibbdd.rawQuery("SELECT id_usuario FROM Usuario WHERE usuario='" + nomUsuario + "' AND contraseña='" + contraseñaAntigua.getText().toString() + "'" , null);
                    if(c1.getCount() == 1){
                        valores.put("contraseña",contraseñaNueva.getText().toString());
                        mibbdd.update("Usuario",valores,"usuario='" + nomUsuario + "'",null );
                        contraseñaAntigua.setText("");
                        contraseñaNueva.setText("");
                        modificarCuentaViewModel.getToast3().observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    else{
                        modificarCuentaViewModel.getToast2().observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                else{
                    modificarCuentaViewModel.getToast1().observe(getViewLifecycleOwner(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return root;
    }



}
