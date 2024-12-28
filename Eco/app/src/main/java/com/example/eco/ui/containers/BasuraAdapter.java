package com.example.eco.ui.containers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eco.R;

import java.util.List;

public class BasuraAdapter extends RecyclerView.Adapter<BasuraAdapter.BasuraViewHolder> {
    private List<Basura> basuraList;
    private Context context;
    private OnBasuraClickListener listener;

    public BasuraAdapter(List<Basura> basuraList, Context context, OnBasuraClickListener listener) {
        this.basuraList = basuraList;
        this.context = context;
        this.listener = listener;  // Asignar el listener
    }

    @NonNull
    @Override
    public BasuraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el nuevo layout personalizado
        View view = LayoutInflater.from(context).inflate(R.layout.item_basura, parent, false);
        return new BasuraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasuraViewHolder holder, int position) {
        // Obtener el objeto Basura en la posición actual
        Basura basura = basuraList.get(position);

        // Establecer los valores en los TextView
        holder.textViewName.setText(basura.getName());
        holder.textViewSolidWaste.setText(basura.getSolidWaste());

        // Configurar el clic del ítem
        holder.itemView.setOnClickListener(v -> listener.onBasuraClick(basura));
    }


    @Override
    public int getItemCount() {
        return basuraList.size();
    }

    public void updateList(List<Basura> newList) {
        basuraList.clear(); // Limpiar la lista actual

        // Si la nueva lista tiene más de 8 elementos, solo agregar los primeros 8
        if (newList.size() > 8) {
            basuraList.addAll(newList.subList(0, 8)); // Solo tomar hasta 8 elementos
        } else {
            basuraList.addAll(newList); // Si son menos de 8, agregar todo
        }

        notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
    }

    // ViewHolder personalizado para el nuevo layout
    static class BasuraViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewSolidWaste;

        public BasuraViewHolder(@NonNull View itemView) {
            super(itemView);
            // Conectar los TextView con los del layout item_basura.xml
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewSolidWaste = itemView.findViewById(R.id.textViewSolidWaste);
        }
    }
}
