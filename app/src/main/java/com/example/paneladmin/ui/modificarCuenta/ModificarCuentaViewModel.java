package com.example.paneladmin.ui.modificarCuenta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ModificarCuentaViewModel extends ViewModel {

    private MutableLiveData<String> toast1;
    private MutableLiveData<String> toast2;
    private MutableLiveData<String> toast3;


    public ModificarCuentaViewModel() {
        toast1 = new MutableLiveData<>();
        toast2 = new MutableLiveData<>();
        toast3 = new MutableLiveData<>();

        toast1.setValue("Introduce todos los campos");
        toast2.setValue("La contraseña introducida es incorrecta");
        toast3.setValue("Datos modificados correctamente");
    }

    public LiveData<String> getToast1(){ return toast1;}
    public LiveData<String> getToast2(){ return toast2;}
    public LiveData<String> getToast3(){ return toast3;}
}
