package com.example.paneladmin.ui.gestionUsuarios;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GestionUsuariosViewModel extends ViewModel {

    private MutableLiveData<String> toast1;
    private MutableLiveData<String> toast2;
    private MutableLiveData<String> toast3;
    private MutableLiveData<String> toast4;
    private MutableLiveData<String> toast5;
    private MutableLiveData<String> toast6;
    private MutableLiveData<String> toast7;
    private MutableLiveData<String> toast8;


    private MutableLiveData<String> mostrar;

    public GestionUsuariosViewModel() {

        toast1 = new MutableLiveData<>();
        toast2 = new MutableLiveData<>();
        toast3 = new MutableLiveData<>();
        toast4 = new MutableLiveData<>();
        toast5 = new MutableLiveData<>();
        toast6 = new MutableLiveData<>();
        toast7 = new MutableLiveData<>();
        toast8 = new MutableLiveData<>();
        mostrar = new MutableLiveData<>();

        toast1.setValue("Se debe de rellenar todos los campos");
        toast2.setValue("Las contraseñas introducidas no coinciden");
        toast3.setValue("Ya existe un usuario con ese nombre");
        toast4.setValue("Se debe de rellenar el campo de usuario");
        toast5.setValue("No existe un usuario con ese nombre");

        toast6.setValue("Usuario Creado");
        toast7.setValue("Usuario Borrado");
        toast8.setValue("Usuario Actualizado");

        mostrar.setValue("");
    }

    public LiveData<String> getToast1(){ return toast1;}
    public LiveData<String> getToast2(){ return toast2;}
    public LiveData<String> getToast3(){ return toast3;}
    public LiveData<String> getToast4(){ return toast4;}
    public LiveData<String> getToast5(){ return toast5;}
    public LiveData<String> getToast6(){ return toast6;}
    public LiveData<String> getToast7(){ return toast7;}
    public LiveData<String> getToast8(){ return toast8;}
    public LiveData<String> getMostrar(){ return mostrar;}





}
