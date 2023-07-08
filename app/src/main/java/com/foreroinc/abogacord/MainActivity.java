package com.foreroinc.abogacord;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.foreroinc.abogacord.databinding.ActivityMainBinding;
import com.foreroinc.abogacord.db.DbUsuarios;
import com.foreroinc.abogacord.recycler.objetoUsuario;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private SharedPreferences sharedPreferences;
    private int aa;
    ArrayList<objetoUsuario> us;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbUsuarios user = new DbUsuarios(MainActivity.this);
        us = new ArrayList<objetoUsuario>();

        aa = user.obtenerValorAA();
        us = user.informacionlog(String.valueOf(aa));
        String b = "el roll es: " + us.get(0).getIDRoll();
        Toast.makeText(MainActivity.this, b, Toast.LENGTH_LONG).show();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawerLayout = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home, R.id.clientes, R.id.consultarcita, R.id.agendarcita, R.id.crearproceso, R.id.crearusuario)
                .setOpenableLayout(drawerLayout)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        createMenuItems(us.get(0).getIDRoll());

        // Agregar un listener al icono de la barra de navegación para abrir la barra lateral
        binding.appBarMain.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.openDrawer(GravityCompat.START);
                } else {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void createMenuItems(int rr) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();

        // Agregar elementos de menú dinámicamente
        menu.setGroupCheckable(R.id.grupo, true, true);

        if (rr == 1) {

            // Home
            MenuItem item1 = menu.add(R.id.grupo, R.id.home, Menu.NONE, R.string.menu_home);
            item1.setIcon(R.drawable.ic_menu_home);


            // Crear usuario
            MenuItem item6 = menu.add(R.id.grupo, R.id.crearusuario, Menu.NONE, R.string.menu_crearusuario);
            item6.setIcon(R.drawable.ic_menu_crearusuario);

            // Clientes
            MenuItem item2 = menu.add(R.id.grupo, R.id.modificar_usuario, Menu.NONE, R.string.menu_modificar_usuario);
            item2.setIcon(R.drawable.ic_menu_clientes);

            MenuItem item3 = menu.add(R.id.grupo, R.id.crearproceso, Menu.NONE, R.string.menu_crearproceso);
            item3.setIcon(R.drawable.ic_menu_crearproceso);


            MenuItem item0 = menu.add(R.id.grupo, R.id.lista_administrador, Menu.NONE, R.string.menu_vercasos);
            item0.setIcon(R.drawable.ic_menu_agendarcitas);

            MenuItem item11 = menu.add(R.id.grupo, R.id.fichero, Menu.NONE, R.string.menu_fichero);
            item11.setIcon(R.drawable.ic_menu_agendarcitas);

            MenuItem item12 = menu.add(R.id.grupo, R.id.registro, Menu.NONE, "Agregar registro");
            item12.setIcon(R.drawable.ic_menu_home);

            MenuItem item13 = menu.add(R.id.grupo, R.id.registro2, Menu.NONE, "Eliminar registro");
            item12.setIcon(R.drawable.ic_menu_home);




        } else if (rr == 2) {
            // Home
            MenuItem item1 = menu.add(R.id.grupo, R.id.home, Menu.NONE, R.string.menu_home);
            item1.setIcon(R.drawable.ic_menu_home);

            // Crear proceso
            MenuItem item2 = menu.add(R.id.grupo, R.id.crearproceso, Menu.NONE, R.string.menu_crearproceso);
            item2.setIcon(R.drawable.ic_menu_crearproceso);

            // Lista abogado
            MenuItem item3 = menu.add(R.id.grupo, R.id.lista_abogado, Menu.NONE, R.string.menu_vercasos);
            item3.setIcon(R.drawable.ic_menu_clientes);
        } else if (rr == 3) {
            // Home
            MenuItem item1 = menu.add(R.id.grupo, R.id.home, Menu.NONE, R.string.menu_home);
            item1.setIcon(R.drawable.ic_menu_home);

            // Lista cliente
            MenuItem item2 = menu.add(R.id.grupo, R.id.lista_cliente, Menu.NONE, R.string.menu_vercasos);
            item2.setIcon(R.drawable.ic_menu_agendarcitas);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                // Ejemplo de cómo manejar la selección de un elemento
                if (itemId == R.id.home) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    home fragment = new home();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle(R.string.menu_home);
                } else if (itemId == R.id.crearusuario) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    crearusuario fragment = new crearusuario();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle(R.string.menu_crearusuario);
                } else if (itemId == R.id.modificar_usuario) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    modificar_usuario fragment = new modificar_usuario();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle(R.string.menu_modificar_usuario);
                } else if (itemId == R.id.crearproceso) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    crearproceso fragment = new crearproceso();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle(R.string.menu_crearproceso);
                }else if (itemId == R.id.lista_abogado) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    lista_abogado fragment = new lista_abogado();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle(R.string.menu_vercasos);
                }  else if (itemId == R.id.lista_cliente) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    lista_cliente fragment = new lista_cliente();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle(R.string.menu_vercasos);
                } else if (itemId == R.id.lista_administrador) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    lista_administrador fragment = new lista_administrador();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle(R.string.menu_vercasos);
                }else if (itemId == R.id.fichero) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fichero fragment = new fichero();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle(R.string.menu_fichero);

                }
                else if (itemId == R.id.registro) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    registro fragment = new registro();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle("registro");

                }else if (itemId == R.id.registro2) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Registro2 fragment = new Registro2();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle("registro");

                }



                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}