package com.example.custom_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.custom_drawer.login_registration.login;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;

    DrawerLayout drawerLayout;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        Fade fade=new Fade();
//        View decor= getWindow().getDecorView();
//        fade.excludeTarget(decor.findViewById(R.id.toolbar),true);
////        fade.excludeTarget(decor.findViewById(com.google.android.gms.auth.api.R.id.status_bar_latest_event_content),true);
////        fade.excludeTarget(decor.findViewById(R.id.navigationBarBackground),true);
//        getWindow().setEnterTransition(fade);
//        getWindow().setExitTransition(fade);

        mAuth = FirebaseAuth.getInstance();

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,
                drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Fragment1()).commit();
            navigationView.setCheckedItem(R.id.nav1);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Fragment1()).commit();
                break;
            case R.id.nav2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                 new Fragment2()).commit();
                break;
            case R.id.nav3:
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(MainActivity.this, login.class);
                startActivity(intent);
                finish();
                break;
        }
        drawerLayout.close();
        return true;
    }
}