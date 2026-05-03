package com.debora.tp4productos.ui.transform;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.debora.tp4productos.MainActivity;
import com.debora.tp4productos.ProductoAdapter;
import com.debora.tp4productos.databinding.FragmentTransformBinding;

import com.debora.tp4productos.modelo.Producto;
import com.debora.tp4productos.ui.reflow.ReflowViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class TransformFragment extends Fragment {

    private FragmentTransformBinding binding;
    private ProductoAdapter adapter;
    private ReflowViewModel sharedViewModel; // Referencia al viewModel

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(ReflowViewModel.class);
        binding = FragmentTransformBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        adapter = new ProductoAdapter(new ArrayList<>(), getLayoutInflater());//Arranca con una lista vacia

        sharedViewModel.getListaProductos().observe(getViewLifecycleOwner(), productos -> {
            if (productos != null) {
                List<Producto> lista = new ArrayList<>(productos);
                Collections.sort(lista, (p1, p2) ->
                        p1.getDescripcion().compareToIgnoreCase(p2.getDescripcion()));
                adapter.actualizarLista(lista);
            }
        });

        binding.recyclerviewTransform.setAdapter(adapter);
        binding.recyclerviewTransform.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}