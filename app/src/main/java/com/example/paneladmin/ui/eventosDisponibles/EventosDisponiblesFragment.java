package com.example.paneladmin.ui.eventosDisponibles;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.paneladmin.MainActivity;
import com.example.paneladmin.databinding.FragmentoEventosDisponiblesBinding;

import java.util.ArrayList;

public class EventosDisponiblesFragment  extends Fragment {

    private FragmentoEventosDisponiblesBinding binding;
    private SQLiteDatabase mibbdd = MainActivity.getMibbdd();

    private ListView eventosDisponibleslv;
    private TextView nombre;
    private ImageView foto;
    private Button boton;
    private String nomUsuario;
    private ArrayList<String> nomEventos = new ArrayList<String>();
    private ArrayList<String> idEventos = new ArrayList<String>() ;
    private ArrayList<Bitmap>  fotos = new ArrayList<Bitmap>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentoEventosDisponiblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        eventosDisponibleslv = binding.eventosDisponibleslv;


        Intent intent =   getActivity().getIntent();
        nomUsuario = intent.getStringExtra("usuario");
        consultarLista(nomUsuario);


        return root;
    }



    public void consultarLista(String nomUsuario) {

        Cursor c = mibbdd.rawQuery("SELECT id_usuario FROM Usuario WHERE usuario = '" + nomUsuario + "'",null);
        int idUsuario = -1;
        if(c.getCount() == 1) {
            if (c.moveToFirst()) {
                do {
                    idUsuario = c.getInt(0);
                }
                while (c.moveToNext());
            }
            c.close();
            Cursor c2 = mibbdd.rawQuery("SELECT  e.nombre,e.foto, ue.id_evento FROM Evento e INNER JOIN  UsuarioxEvento ue ON e.id_evento = ue.id_evento " +
                    "WHERE ue.id_usuario = '" + idUsuario + "'AND ue.reserva = 0",null);

            String nombreEvento;
            byte[] fotoEvento;
            String idEvento;
            BitmapFactory.Options options = new BitmapFactory.Options();

            if (c2.moveToFirst()) {

                do {
                    nombreEvento = c2.getString(0);
                    fotoEvento = c2.getBlob(1);
                    idEvento= c2.getString(2);
                    nomEventos.add(nombreEvento);
                    idEventos.add(idEvento);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(fotoEvento, 0, fotoEvento.length, options);
                    fotos.add(bitmap);
                }
                while(c2.moveToNext());
                AdaptadorCustom adaptadorCustom = new AdaptadorCustom(getActivity().getApplicationContext(),fotos,nomEventos,idUsuario,idEventos, nomUsuario);
                eventosDisponibleslv.setAdapter(adaptadorCustom);

            }


        }


    }


}
