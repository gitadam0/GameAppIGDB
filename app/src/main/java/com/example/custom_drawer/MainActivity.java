package com.example.custom_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.custom_drawer.login_registration.login;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
TextView gmail_text;
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

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        toggle.syncState();




        FirebaseUser currentUser = mAuth.getCurrentUser();
        Toast.makeText(this, currentUser.getEmail()+"", Toast.LENGTH_SHORT).show();


        NavigationView navigationView=findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        TextView t= headerView.findViewById(R.id.mygmail_head);
        t.setText(currentUser.getEmail());

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
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                 new MapsFragment()).commit();
                Intent i=new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.nav3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StoreFragment()).commit();
                break;
            case R.id.nav4:
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