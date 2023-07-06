package com.example.paneladmin.ui.eventosReservados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paneladmin.MainActivity;
import com.example.paneladmin.R;

import java.util.ArrayList;

public class AdaptadorCustom2 extends BaseAdapter {

    private Context context;
    private int idUsuario;
    private LayoutInflater inflater;
    private ArrayList<String> nomEventos;
    private ArrayList<String> idEventos;
    private ArrayList<Bitmap>  fotos;
    private Button button;
    private SQLiteDatabase mibbdd = MainActivity.getMibbdd();

    public AdaptadorCustom2(Context ctx, ArrayList<Bitmap> fotos, ArrayList<String> nomEventos,  int idUsuario, ArrayList<String> idEventos, String nomUsuario){
        this.context = ctx;
        this.fotos = fotos;
        this.nomEventos = nomEventos;
        this.idUsuario = idUsuario;
        this.idEventos = idEventos;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return nomEventos.size();
    }

    @Override
    public Object getItem(int i) {
        return nomEventos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int posicion, View convertirView, ViewGroup viewGroup) {
        convertirView = inflater.inflate(R.layout.contenido_listview_eventos_reservados,null);
        TextView nombreEvento = convertirView.findViewById(R.id.nombreEvento2);
        ImageView imagen = convertirView.findViewById(R.id.imagenDisponible2);
        Button boton = convertirView.findViewById(R.id.botonReservado);
        boton.setTag(posicion);
        boton.setText("Quitar Reserva");
        nombreEvento.setText(nomEventos.get(posicion));
        imagen.setImageBitmap(fotos.get(posicion));
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String idEvento, idUsuarioEvento;
                int index = (int)v.getTag();
                idEvento = idEventos.get(index);
                Cursor c = mibbdd.rawQuery("SELECT ue.id_usuario_evento FROM Evento e INNER JOIN  UsuarioxEvento ue ON e.id_evento = ue.id_evento " +
                        "WHERE ue.id_usuario = '" + idUsuario +  "'AND ue.id_evento = '" + idEvento + "'",null);
                if(c.getCount() == 1) {
                    int reserva;
                    if (c.moveToFirst()) {
                        do {
                            idUsuarioEvento = c.getString(0);
                            reserva = 0;
                            ContentValues valores = new ContentValues();
                            valores.put("reserva",reserva);
                            mibbdd.update("UsuarioxEvento", valores, "" + "id_evento='" + idEvento + "'AND id_usuario_evento = '" + idUsuarioEvento + "'", null);
                            nomEventos.remove(index);
                            idEventos.remove(index);
                            fotos.remove(index);
                            notifyDataSetChanged();
                        }
                        while (c.moveToNext());
                    }
                }
            }
        });
        return convertirView;
    };
}



