package com.example.paneladmin.ui.eventosReservados;

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
import com.example.paneladmin.databinding.ContenidoListviewEventosReservadosBinding;
import com.example.paneladmin.databinding.FragmentoEventosReservadosBinding;

import java.util.ArrayList;

public class EventosReservadosFragment extends Fragment {

    private FragmentoEventosReservadosBinding binding;
    private ContenidoListviewEventosReservadosBinding binding2;
    private SQLiteDatabase mibbdd = MainActivity.getMibbdd();

    private ListView eventosReservadoslv;
    private TextView nombre;
    private ImageView foto;
    private Button boton;
    private String nomUsuario;
    private ArrayList<String> nomEventos = new ArrayList<String>();
    private ArrayList<String> idEventos = new ArrayList<String>() ;
    private ArrayList<Bitmap>  fotos = new ArrayList<Bitmap>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentoEventosReservadosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        eventosReservadoslv = binding.eventosReservadoslv;


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
                    "WHERE ue.id_usuario = '" + idUsuario + "'AND ue.reserva = 1",null);

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
                AdaptadorCustom2 adaptadorCustom2 = new AdaptadorCustom2(getActivity().getApplicationContext(),fotos,nomEventos,idUsuario,idEventos,nomUsuario);
                eventosReservadoslv.setAdapter(adaptadorCustom2);
            }



        }


    }

}
