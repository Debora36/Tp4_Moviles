package com.debora.tp4productos.ui.transform;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.debora.tp4productos.MainActivity;
import com.debora.tp4productos.modelo.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransformViewModel extends AndroidViewModel {
    private MutableLiveData<List<Producto>> listaFiltrada = new MutableLiveData<>();

    public LiveData<List<Producto>> getListaFiltrada() {
        return listaFiltrada;
    }
    public TransformViewModel(@NonNull Application application) {
        super(application);
    }

    public void cargarYOrdenarProductos() {
        //El ViewModel accede al modelo
        List<Producto> lista = new ArrayList<>(MainActivity.listaProductos);

        // Ordenamiento
        Collections.sort(lista, (p1, p2) ->
                p1.getDescripcion().compareToIgnoreCase(p2.getDescripcion()));

        listaFiltrada.setValue(lista);
    }
}
