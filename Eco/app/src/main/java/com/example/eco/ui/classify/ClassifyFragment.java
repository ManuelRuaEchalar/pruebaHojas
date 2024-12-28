package com.example.eco.ui.classify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eco.R;

import java.util.HashMap;
import java.util.Map;

public class ClassifyFragment extends Fragment {

    private EditText editTextWasteName;
    private Spinner spinnerWasteType;
    private Button buttonSubmitSuggestion;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_classify, container, false);

        // Inicializar las vistas
        editTextWasteName = root.findViewById(R.id.editTextWasteName);
        spinnerWasteType = root.findViewById(R.id.spinnerWasteType);
        buttonSubmitSuggestion = root.findViewById(R.id.buttonSubmitSuggestion);

        // Configurar el Spinner con un adaptador personalizado
        String[] wasteTypes = {"Orgánico", "Papel", "Cartón", "Vidrio", "Plástico", "Latas y Metales", "No Aprovechable"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.spinner_item, wasteTypes);
        adapter.setDropDownViewResource(R.layout.spinner_item); // Diseño personalizado del dropdown
        spinnerWasteType.setAdapter(adapter);

        // Manejar clic en el botón de enviar sugerencia
        buttonSubmitSuggestion.setOnClickListener(v -> {
            String wasteName = editTextWasteName.getText().toString().trim();
            String wasteType = spinnerWasteType.getSelectedItem().toString();

            if (wasteName.isEmpty()) {
                Toast.makeText(requireContext(), "Por favor, ingresa el nombre de la basura.", Toast.LENGTH_SHORT).show();
            } else {
                sendSuggestionToServer(wasteName, wasteType);
            }
        });

        return root;
    }

    private void sendSuggestionToServer(String wasteName, String wasteType) {
        String url = "http://192.168.199.5:8000/api/suggestions"; // Cambia esta URL por la de tu API

        // Crear una solicitud POST usando Volley
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> Toast.makeText(requireContext(), "Sugerencia enviada correctamente. Gracias por tu contribución!", Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(requireContext(), "Error al enviar la sugerencia", Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", wasteName);
                params.put("type", wasteType);
                return params;
            }
        };

        // Agregar la solicitud a la cola de Volley
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(stringRequest);
    }
}
