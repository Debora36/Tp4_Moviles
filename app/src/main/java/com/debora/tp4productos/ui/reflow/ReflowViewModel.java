package com.debora.tp4productos.ui.reflow;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.debora.tp4productos.MainActivity;
import com.debora.tp4productos.modelo.Producto;

import java.util.HashSet;

public class ReflowViewModel extends AndroidViewModel {
    private MutableLiveData<String> mensajeToast;
    public ReflowViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<String> getMensajeToast() {
        if(mensajeToast==null){
            mensajeToast=new MutableLiveData<>();
        }
        return mensajeToast;
    }


    public void cargarProducto(String cod, String desc, String precioStr) {
        // Validación de campos vacíos
        if (cod.isEmpty() || desc.isEmpty() || precioStr.isEmpty()) {
            mensajeToast.setValue("Todos los campos son obligatorios");
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            Producto nuevo = new Producto(cod, desc, precio);

            boolean agregado = MainActivity.listaProductos.add(nuevo);
            if (agregado) {
                mensajeToast.setValue("Producto cargado exitosamente");
                return;
            } else {
                mensajeToast.setValue("El codigo ya existe");
                return;
            }
        } catch (NumberFormatException e) {
            mensajeToast.setValue("El precio es invalido");
            return;
        }
    }
}