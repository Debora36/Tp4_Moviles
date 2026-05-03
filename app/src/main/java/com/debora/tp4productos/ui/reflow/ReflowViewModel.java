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
    private final MutableLiveData<String> mensajeToast = new MutableLiveData<>();
    private final MutableLiveData<HashSet<Producto>> listaProductos = new MutableLiveData<>(new HashSet<>());
    public ReflowViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<String> getMensajeToast() {
        return mensajeToast;
    }
    public LiveData<HashSet<Producto>> getListaProductos() {
        return listaProductos;
    }


    public boolean cargarProducto(String cod, String desc, String precioStr) {
        // Validación de campos vacíos
        if (cod.isEmpty() || desc.isEmpty() || precioStr.isEmpty()) {
            mensajeToast.setValue("Todos los campos son obligatorios");
            return false;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            Producto nuevo = new Producto(cod, desc, precio);

            HashSet<Producto> actual = listaProductos.getValue();
            if (actual != null && actual.add(nuevo)) {
                listaProductos.setValue(actual);
                mensajeToast.setValue("Producto cargado exitosamente");
                return true;
            } else {
                mensajeToast.setValue("El codigo ya existe");
                return false;
            }
        } catch (NumberFormatException e) {
            mensajeToast.setValue("El precio es invalido");
            return false;
        }
    }
}