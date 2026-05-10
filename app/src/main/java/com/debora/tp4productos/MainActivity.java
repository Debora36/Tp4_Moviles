package com.debora.tp4productos;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.debora.tp4productos.modelo.Producto;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

import com.debora.tp4productos.databinding.ActivityMainBinding;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static HashSet<Producto> listaProductos = new HashSet<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        assert navHostFragment != null;

        //Menu lateral
        NavController navController = navHostFragment.getNavController();//controlador de navegacion

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_transform,//lista de productos
                R.id.nav_reflow//cargar productos
        )
                .setOpenableLayout(binding.drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);//hace que el título cambie según el fragmento

        //configuracion manual del menu lateral
        binding.navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_slideshow) {//si selecciona salir
                muestraDialogo();
                binding.drawerLayout.closeDrawers();
                return true;
            }

            // Para navegar a la lista o a cargar con el fragmento que coincida con el id del item
            boolean handled = NavigationUI.onNavDestinationSelected(item, navController);
            if (handled) {
                binding.drawerLayout.closeDrawers();//cierro el menu lateral para mostrar el fragmento
            }
            return handled;
        });


// Bottom Navigation
        assert binding.appBarMain.contentMain.bottomNavView != null;
        NavigationUI.setupWithNavController(binding.appBarMain.contentMain.bottomNavView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        // Using findViewById because NavigationView exists in different layout files
        // between w600dp and w1240dp
        NavigationView navView = findViewById(R.id.nav_view);
        if (navView == null) {
            // The navigation drawer already has the items including the items in the overflow menu
            // We only inflate the overflow menu if the navigation drawer isn't visible
            getMenuInflater().inflate(R.menu.overflow, menu);
        }
        return result;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void muestraDialogo(){
        new  AlertDialog.Builder(this)
                .setTitle("salida")
                .setMessage("Desea cerrar la aplicacion?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"No sale", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

}