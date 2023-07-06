package com.example.paneladmin.ui.extras;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.paneladmin.MainActivity;
import com.example.paneladmin.PanelUsuario;
import com.example.paneladmin.contenidoExtras.contentProviderSistema.ContentProvider;
import com.example.paneladmin.contenidoExtras.ficherosExternos.FicherosExternos;
import com.example.paneladmin.contenidoExtras.ficherosInternos.FicherosInternos;
import com.example.paneladmin.contenidoExtras.ficherosRaw.FicherosRaw;

import com.example.paneladmin.contenidoExtras.notificaciones.Notificaciones;
import com.example.paneladmin.contenidoExtras.presionPantalla.PresionPantalla;
import com.example.paneladmin.contenidoExtras.sensores.Sensores;
import com.example.paneladmin.contenidoExtras.sharedPreferences.SharedPreference;
import com.example.paneladmin.contenidoExtras.toast.ToastExtra;
import com.example.paneladmin.contenidoExtras.youtube.Youtube;
import com.example.paneladmin.databinding.FragmentoExtrasBinding;
import com.example.paneladmin.ui.gestionEventos.GestionEventosViewModel;

public class ExtrasFragment extends Fragment {

    private ExtrasViewModel gestionExtras;
    private FragmentoExtrasBinding binding;


    private Button boton1;
    private Button boton2;
    private Button boton3;
    private Button boton4;
    private Button boton5;
    private Button boton6;
    private Button boton7;
    private Button boton8;
    private Button boton9;
    private Button boton10;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gestionExtras =
                new ViewModelProvider(this).get(ExtrasViewModel.class);

        binding = FragmentoExtrasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        boton1 = binding.button10;
        boton2 = binding.button9;
        boton3 = binding.button11;
        boton4 = binding.button12;
        boton5 = binding.button13;
        boton6= binding.button14;
        boton7 = binding.button15;
        boton8 = binding.button16;
        boton9 = binding.button17;
        boton10 = binding.button18;

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), ContentProvider.class);
                startActivity(intent1);
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getActivity(), FicherosExternos.class);
                startActivity(intent2);
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getActivity(), FicherosInternos.class);
                startActivity(intent3);
            }
        });

        boton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent4 = new Intent(getActivity(), FicherosRaw.class);
                startActivity(intent4);
            }
        });

        boton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent5 = new Intent(getActivity(), PresionPantalla.class);
                startActivity(intent5);
            }
        });

        boton6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent6 = new Intent(getActivity(), Sensores.class);
                startActivity(intent6);
            }
        });


        boton7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent7 = new Intent(getActivity(), SharedPreference.class);
                startActivity(intent7);
            }
        });

        boton8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent8 = new Intent(getActivity(), Notificaciones.class);
                startActivity(intent8);
            }
        });

        boton9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent9 = new Intent(getActivity(), Youtube.class);
                startActivity(intent9);
            }
        });

        boton10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent10 = new Intent(getActivity(), ToastExtra.class);
                startActivity(intent10);
            }
        });




        return root;
    }





}
