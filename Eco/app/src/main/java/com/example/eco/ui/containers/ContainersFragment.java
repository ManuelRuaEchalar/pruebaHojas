package com.example.eco.ui.containers;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eco.R;
import com.example.eco.ui.classify.ClassifyFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContainersFragment extends Fragment implements OnMapReadyCallback, OnBasuraClickListener {

    // Constantes
    private static final String TAG = "Proceso"; // Para logs de depuración
    private static final int FINE_PERMISSION_CODE = 1; // Código para el permiso de ubicación

    // Variables para el mapa y ubicación
    private GoogleMap myMap; // Mapa de Google
    private com.google.android.gms.maps.model.Marker ubicacionActualMarker;  // Marcador de la ubicación actual
    private Location currentLocation; // Ubicación actual
    private FusedLocationProviderClient fusedLocationProviderClient; // Cliente para obtener la ubicación

    // Variables para la búsqueda
    private SearchView mapSearchView;
    private RecyclerView resultadosRecyclerView;
    private BasuraAdapter basuraAdapter;

    // Managers para manejar datos
    private ContenedorManager contenedorManager; // Manager para obtener los contenedores
    private BasuraManager basuraManager; // Manager para la lista de basura

    // Usamos onCreateView en lugar de onCreate para inflar la vista del fragmento
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_containers, container, false); // Usamos un layout nuevo para el fragmento

        // Inicialización de vistas y componentes
        mapSearchView = view.findViewById(R.id.mapSearch);

        // Asegurarse de que el campo de texto interno existe
        int searchTextViewId = mapSearchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText searchEditText = mapSearchView.findViewById(searchTextViewId);

        if (searchEditText != null) {
            // Cambiar el tamaño del texto y el color del hint y del texto escrito
            searchEditText.setTextColor(Color.BLACK);  // Color del texto escrito
            searchEditText.setHintTextColor(Color.GRAY);  // Color del hint (texto sugerido)
            searchEditText.setTextSize(18);  // Tamaño del texto
        }

        resultadosRecyclerView = view.findViewById(R.id.resultadosRecyclerView);
        resultadosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));  // Usar getContext() para el contexto en fragmentos

        // Inicializar el manager de ubicación
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        getLastLocation(); // Obtener la última ubicación conocida

        // Inicializar el manager de Basura y su adapter
        basuraManager = new BasuraManager(getContext());
        basuraAdapter = new BasuraAdapter(new ArrayList<>(), getContext(), this::onBasuraClick); // Inicializar con lista vacía
        resultadosRecyclerView.setAdapter(basuraAdapter); // Enlazar el RecyclerView con el adaptador

        // Configuración del SearchView para buscar en la lista de basura
        mapSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                buscarEnListaDeBasura(query); // Buscar basura cuando se envíe una consulta
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    // Si el texto está vacío, ocultar las sugerencias
                    resultadosRecyclerView.setVisibility(View.GONE);
                } else {
                    // Si hay texto, buscar en la lista y mostrar sugerencias
                    buscarEnListaDeBasura(newText);
                }
                return false;
            }
        });

        // Inicializar el manager de contenedores y cargar los contenedores
        contenedorManager = new ContenedorManager(getContext());
        obtenerContenedores(); // Cargar contenedores en el mapa

        // Link para sugerir en caso de no haber resultados
        TextView suggestLink = view.findViewById(R.id.suggestLinkTextView);
        suggestLink.setOnClickListener(v -> {
            // Obtener la referencia al BottomNavigationView
            BottomNavigationView navView = requireActivity().findViewById(R.id.nav_view);

            // Cambiar la selección del BottomNavigationView para navegar a la sección correspondiente
            navView.setSelectedItemId(R.id.navigation_classify); // Cambia al fragmento correspondiente
        });

        return view; // Devolver la vista creada
    }

    @Override
    public void onBasuraClick(Basura basura) {
        Log.d("BasuraClick", "Se hizo clic en: " + basura.getName() + " - " + basura.getSolidWaste());

        // Obtener el código de color según el tipo de basura
        String colorCode = obtenerCodigoColorPorBasura(basura.getSolidWaste());

        // Ocultar el RecyclerView de sugerencias para ver mejor el mapa
        resultadosRecyclerView.setVisibility(View.GONE);

        // Filtrar los contenedores que tengan el código correspondiente
        contenedorManager.obtenerTodosLosContenedores()
                .thenAccept(contenedores -> {
                    // Eliminar solo los marcadores de contenedores sin afectar la ubicación actual
                    if (myMap != null) {
                        myMap.clear();
                        if (ubicacionActualMarker != null && currentLocation != null) {
                            LatLng ubicacionActual = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                            ubicacionActualMarker = myMap.addMarker(new MarkerOptions()
                                    .position(ubicacionActual)
                                    .title("Mi ubicación"));
                        }
                    }

                    for (Contenedor contenedor : contenedores) {
                        if (contenedor.getGarbageCode().contains(colorCode)) {
                            LatLng latLng = new LatLng(contenedor.getLatitude(), contenedor.getLongitude());
                            myMap.addMarker(new MarkerOptions()
                                    .position(latLng)
                                    .title(contenedor.getAddress())
                                    .snippet(contenedor.getGarbageCode())
                                    .icon(getBitmapFromVector(getContext(), R.drawable.ic_trash_icon)));
                        }
                    }
                })
                .exceptionally(throwable -> {
                    Log.e(TAG, "Error al obtener los contenedores: " + throwable.getMessage());
                    return null;
                });
    }

    private String obtenerCodigoColorPorBasura(String solidWaste) {
        Map<String, String> relacionResiduosColor = new HashMap<>();
        relacionResiduosColor.put("organico", "G");  // Verde
        relacionResiduosColor.put("papel", "B");     // Azul
        relacionResiduosColor.put("carton", "B");    // Azul
        relacionResiduosColor.put("vidrio", "P");    // Plomo
        relacionResiduosColor.put("plastico", "Y");  // Amarillo
        relacionResiduosColor.put("latas y metales", "P"); // Plomo
        relacionResiduosColor.put("no aprovechable", "N"); // Negro

        return relacionResiduosColor.getOrDefault(solidWaste.toLowerCase(), "N");
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Pedir permiso si no ha sido otorgado
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }

        // Obtener la última ubicación conocida
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                currentLocation = location; // Almacenar la ubicación actual
                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                if (mapFragment != null) {
                    mapFragment.getMapAsync(ContainersFragment.this); // Inicializar el mapa
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;

        // Configurar y mover la cámara a la ubicación actual
        LatLng ubicacionActual = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacionActual, 15));

        // Añadir un marcador en la ubicación actual
        ubicacionActualMarker = myMap.addMarker(new MarkerOptions().position(ubicacionActual).title("Mi ubicación"));

        // Cargar los contenedores en el mapa
        obtenerContenedores();
    }

    private void buscarEnListaDeBasura(String query) {
        List<Basura> resultados = basuraManager.buscarBasura(query);

        Log.d(TAG, "Número de resultados encontrados: " + resultados.size());

        if (!resultados.isEmpty()) {
            resultadosRecyclerView.setVisibility(View.VISIBLE);
            getView().findViewById(R.id.noResultsLayout).setVisibility(View.GONE); // Ocultar "No Hay Resultados"
            basuraAdapter.updateList(resultados);
        } else {
            resultadosRecyclerView.setVisibility(View.GONE);
            getView().findViewById(R.id.noResultsLayout).setVisibility(View.VISIBLE); // Mostrar "No Hay Resultados"
        }
    }

    private void obtenerContenedores() {
        contenedorManager.obtenerTodosLosContenedores()
                .thenAccept(contenedores -> {
                    for (Contenedor contenedor : contenedores) {
                        LatLng latLng = new LatLng(contenedor.getLatitude(), contenedor.getLongitude());
                        myMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title(contenedor.getAddress())
                                .snippet(contenedor.getGarbageCode())
                                .icon(getBitmapFromVector(getContext(), R.drawable.ic_trash_icon)));
                    }
                })
                .exceptionally(throwable -> {
                    Log.e(TAG, "Error al obtener los contenedores: " + throwable.getMessage());
                    return null;
                });
    }

    private BitmapDescriptor getBitmapFromVector(Context context, @DrawableRes int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        if (vectorDrawable != null) {
            vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
            Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            vectorDrawable.draw(canvas);
            return BitmapDescriptorFactory.fromBitmap(bitmap);
        }
        return null;
    }
}