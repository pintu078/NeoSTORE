package com.pintu.neostore.home;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.pintu.neostore.R;

public class Home extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       nav = (NavigationView)findViewById(R.id.navmenu);
       drawerLayout  =(DrawerLayout)findViewById(R.id.drawer);

       toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
       drawerLayout.addDrawerListener(toggle);
       toggle.syncState();

       nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId())
               {
                   case R.id.menu_myAccount:
                       Toast.makeText(getApplicationContext(),"My Account",Toast.LENGTH_SHORT).show();
                       drawerLayout.closeDrawer(GravityCompat.START);
                       break;

                   case R.id.menu_myLocation:
                       Toast.makeText(getApplicationContext(),"My Location",Toast.LENGTH_SHORT).show();
                       drawerLayout.closeDrawer(GravityCompat.START);
                       break;

                   case R.id.menu_myCart:
                       Toast.makeText(getApplicationContext(),"My Cart",Toast.LENGTH_SHORT).show();
                       drawerLayout.closeDrawer(GravityCompat.START);
                       break;
               }
               return true;
           }
       });
    }
}
