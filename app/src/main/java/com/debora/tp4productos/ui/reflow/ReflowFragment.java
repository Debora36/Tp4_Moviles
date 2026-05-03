package com.debora.tp4productos.ui.reflow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.debora.tp4productos.databinding.FragmentReflowBinding;

public class ReflowFragment extends Fragment {
    private FragmentReflowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Instancia compartida con toda la Activity
        ReflowViewModel reflowViewModel =
                new ViewModelProvider(requireActivity()).get(ReflowViewModel.class);


        binding = FragmentReflowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //observer del mensaje
        reflowViewModel.getMensajeToast().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null) {
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });
        //listener del boton
        binding.btnCargar.setOnClickListener(v -> {
            String cod = binding.etCodigo.getText().toString();
            String desc = binding.etDescripcion.getText().toString();
            String precio = binding.etPrecio.getText().toString();

            boolean exito = reflowViewModel.cargarProducto(cod, desc, precio);
            if (exito) {
                limpiarCampos();//limpia los campos solo si se cargo correctamente
            }
        });

        return root;
    }
    private void limpiarCampos() {
        binding.etCodigo.setText("");
        binding.etDescripcion.setText("");
        binding.etPrecio.setText("");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}