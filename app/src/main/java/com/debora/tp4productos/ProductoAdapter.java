package com.debora.tp4productos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.debora.tp4productos.modelo.Producto;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolderProducto> {
    private List<Producto> productos;
    private LayoutInflater li;

    public ProductoAdapter() {
    }

    public ProductoAdapter(List<Producto> productos, LayoutInflater li) {
        this.productos = productos;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=li.inflate(R.layout.item, parent, false);
        return new ViewHolderProducto(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        Producto prodActual=productos.get(position);
        holder.codigo.setText("Codigo: "+prodActual.getCodigo());
        holder.descripcion.setText(prodActual.getDescripcion());
        holder.precio.setText("Precio $: "+prodActual.getPrecio());

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public void actualizarLista(List<Producto> nuevaLista) {
        this.productos.clear();
        this.productos.addAll(nuevaLista);
        notifyDataSetChanged();
    }

    public class ViewHolderProducto extends RecyclerView.ViewHolder{
        TextView codigo, descripcion, precio;

        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);
            codigo=itemView.findViewById(R.id.codigoP);
            descripcion=itemView.findViewById(R.id.descripcionP);
            precio=itemView.findViewById(R.id.precioP);

        }
    }
}
