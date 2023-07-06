package com.example.paneladmin.ui.gestionEventos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GestionEventosViewModel extends ViewModel {

    private MutableLiveData<String> toast1;
    private MutableLiveData<String> toast2;
    private MutableLiveData<String> toast3;
    private MutableLiveData<String> toast4;
    private MutableLiveData<String> toast5;
    private MutableLiveData<String> toast6;
    private MutableLiveData<String> toast7;
    private MutableLiveData<String> toast8;

    public GestionEventosViewModel() {

        toast1 = new MutableLiveData<>();
        toast2 = new MutableLiveData<>();
        toast3 = new MutableLiveData<>();
        toast4 = new MutableLiveData<>();
        toast5 = new MutableLiveData<>();
        toast6 = new MutableLiveData<>();
        toast7 = new MutableLiveData<>();
        toast8 = new MutableLiveData<>();

        toast1.setValue("Rellena los campos");
        toast2.setValue("Evento agregado correctamente");
        toast3.setValue("Ya se encuentra un evento con ese nombre");
        toast4.setValue("Rellena el campo del nombre del evento");
        toast5.setValue("No se encuentra un evento con ese nombre");
        toast6.setValue("Evento borrado correctamente");
        toast7.setValue("Evento actualizado correctamente");
        toast8.setValue("Selecciona una foto de la galeria");


    }

    public LiveData<String> getToast1(){ return toast1;}
    public LiveData<String> getToast2(){ return toast2;}
    public LiveData<String> getToast3(){ return toast3;}
    public LiveData<String> getToast4(){ return toast4;}
    public LiveData<String> getToast5(){ return toast5;}
    public LiveData<String> getToast6(){ return toast6;}
    public LiveData<String> getToast7(){ return toast7;}
    public LiveData<String> getToast8(){ return toast8;}
}