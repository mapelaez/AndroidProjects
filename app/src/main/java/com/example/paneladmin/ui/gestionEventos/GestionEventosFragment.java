package com.example.paneladmin.ui.gestionEventos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.squareup.picasso.Picasso;
import com.example.paneladmin.MainActivity;
import com.example.paneladmin.R;
import com.example.paneladmin.databinding.FragmentoGestionEventosBinding;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;

public class  GestionEventosFragment extends Fragment {

    private GestionEventosViewModel gestionEventosViewModel;
    private FragmentoGestionEventosBinding binding;

    public static final int RESULTADO_OK = -1;
    private EditText nombre;
    private EditText descripcion;
    private ImageView foto;
    private int pasar = 0;

    private Button boton1;
    private Button boton2;
    private Button boton3;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gestionEventosViewModel =
                new ViewModelProvider(this).get(GestionEventosViewModel.class);

        binding = FragmentoGestionEventosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        nombre = binding.nombre;
        descripcion = binding.descripcion;
        foto = binding.imagen;

        foto.setImageResource(R.mipmap.ic_launcher);

        boton1 = binding.button5;
        boton2 = binding.button6;
        boton3 = binding.button7;

        SQLiteDatabase mibbdd = MainActivity.getMibbdd();
        ContentValues valores = new ContentValues();
        ContentValues valores2 = new ContentValues();


        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elegirGaleria();
            }
        });

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().equals("") && !descripcion.getText().toString().equals("")) {
                    Cursor c = mibbdd.rawQuery("SELECT * FROM Evento WHERE nombre='" + nombre.getText().toString() + "'", null);
                    if(c.getCount() == 0) {
                        c.close();
                            if(pasar == 1) {
                                c.close();
                                valores.put("nombre", nombre.getText().toString());
                                valores.put("descripcion", descripcion.getText().toString());
                                valores.put("foto", imageViewToBytes(foto));

                                mibbdd.insert("Evento", null, valores);

                                String campos1[] = new String[] {"id_usuario"};

                                Cursor c2 = mibbdd.rawQuery("SELECT id_evento FROM Evento WHERE nombre='" + nombre.getText().toString() + "'", null);
                                Cursor cursorbbdd = mibbdd.query("Usuario",campos1, null, null, null,null, null);

                                int id_evento = 0;
                                if(c2.moveToFirst()){
                                    do{
                                        id_evento = c2.getInt(0);
                                    }
                                    while (c2.moveToNext());
                                }
                                c2.close();

                                if(cursorbbdd.getCount() > 0){
                                    if(cursorbbdd.moveToFirst()){
                                        do {
                                            String id_usuario = cursorbbdd.getString(0);
                                            valores2.put("id_usuario", id_usuario);
                                            valores2.put("id_evento",id_evento);
                                            valores2.put("reserva", 0);
                                            mibbdd.insert("UsuarioxEvento",null,valores2);
                                        }
                                        while(cursorbbdd.moveToNext());
                                    }
                                }

                                gestionEventosViewModel.getToast2().observe(getViewLifecycleOwner(), new Observer<String>() {
                                    @Override
                                    public void onChanged(String s) {
                                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                                    }
                                });

                                nombre.setText("");
                                descripcion.setText("");
                            }
                           else{
                                gestionEventosViewModel.getToast8().observe(getViewLifecycleOwner(), new Observer<String>() {
                                    @Override
                                    public void onChanged(String s) {
                                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    else{
                        gestionEventosViewModel.getToast3().observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    gestionEventosViewModel.getToast1().observe(getViewLifecycleOwner(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().equals("")){
                    Cursor c = mibbdd.rawQuery("SELECT * FROM Evento WHERE nombre='" + nombre.getText().toString() + "'", null);
                    if(c.getCount() == 1){
                        c.close();

                        String campos1[] = new String[] {"id_usuario"};

                        Cursor c2 = mibbdd.rawQuery("SELECT id_evento FROM Evento WHERE nombre='" + nombre.getText().toString() + "'", null);

                        int id_evento = 0;
                        if(c2.moveToFirst()) {
                            do {
                                id_evento = c2.getInt(0);
                            }
                            while (c2.moveToNext());
                        }
                        c2.close();

                        Cursor cursorbbdd = mibbdd.query("Usuario",campos1, null, null, null,null, null);

                        if(cursorbbdd.getCount() > 0){
                            mibbdd.delete("UsuarioxEvento", "id_evento='" + id_evento + "'", null);
                        }

                        cursorbbdd.close();

                        mibbdd.delete("Evento", "nombre='" + nombre.getText().toString() + "'", null);

                        gestionEventosViewModel.getToast6().observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                            }
                        });

                        nombre.setText("");
                        descripcion.setText("");
                    }
                    else{
                        gestionEventosViewModel.getToast5().observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                else{
                    gestionEventosViewModel.getToast4().observe(getViewLifecycleOwner(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nombre.getText().toString().equals("") && !descripcion.getText().toString().equals("")){
                    Cursor c = mibbdd.rawQuery("SELECT * FROM Evento WHERE nombre='" + nombre.getText().toString() + "'",null);
                    if(c.getCount() == 1){
                        c.close();
                        if(pasar == 1) {
                            valores.put("nombre", nombre.getText().toString());
                            valores.put("descripcion", descripcion.getText().toString());
                            valores.put("foto", imageViewToBytes(foto));

                            mibbdd.update("Evento", valores, "nombre='" + nombre.getText().toString() + "'", null);

                            gestionEventosViewModel.getToast7().observe(getViewLifecycleOwner(), new Observer<String>() {
                                @Override
                                public void onChanged(String s) {
                                    Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                                }
                            });

                            nombre.setText("");
                            descripcion.setText("");
                        }
                        else{
                            gestionEventosViewModel.getToast8().observe(getViewLifecycleOwner(), new Observer<String>() {
                                @Override
                                public void onChanged(String s) {
                                    Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                    else{
                        gestionEventosViewModel.getToast5().observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
                else{
                    gestionEventosViewModel.getToast1().observe(getViewLifecycleOwner(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
           super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                CropImage.ActivityResult resultado = CropImage.getActivityResult(data);
                if(resultCode == RESULTADO_OK){
                    Uri resultUri = resultado.getUri();
                    Picasso.with(getContext()).load(resultUri).into(foto);
                }else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = resultado.getError();
                }
            }
    }

    public byte[] imageViewToBytes(ImageView foto) {
        Bitmap bitmap = (((BitmapDrawable) foto.getDrawable()).getBitmap());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void elegirGaleria() {
        CropImage.activity().start(getContext(), this);
        pasar = 1;
    }

}