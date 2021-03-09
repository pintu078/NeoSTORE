package com.pintu.neostore.home;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.pintu.neostore.R;
import com.pintu.neostore.adapter.ViewPagerAdapter;
import com.pintu.neostore.drawer.MyAccount;
import com.pintu.neostore.drawer.MyCart;
import com.pintu.neostore.drawer.Tables;
import com.pintu.neostore.login.Login;
import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.register.AppConstant;
import com.pintu.neostore.register.MyData;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Response;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ViewPager mViewPager;
    TabLayout indicator;
    Intent intent;
    // images array
    int[] images = {R.drawable.beds,R.drawable.sofas,R.drawable.cupboards,R.drawable.tabels,R.drawable.chairs};


    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);


        /*----------------------Hooks-------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View headerContainer = navigationView.getHeaderView(0);
        toolbar = findViewById(R.id.toolbar);
        indicator=(TabLayout)findViewById(R.id.indicator);
        ImageView img = (ImageView) headerContainer.findViewById(R.id.header_img);
        TextView per = (TextView) headerContainer.findViewById(R.id.header_name);
        TextView ema = (TextView) headerContainer.findViewById(R.id.header_email);


        /*----------------------Tool BAr-------------------------*/
         setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
    //    getSupportActionBar().setTitle("NeoSTORE");


        mViewPager = (ViewPager)findViewById(R.id.viewpager);

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(Home.this, images);

        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
        indicator.setupWithViewPager(mViewPager, true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 2000, 4000);


//        MyData myData = AppConstant.mydatas.get(0);
//        System.out.println("------home----"+myData.FnameD);
//        per.setText(myData.FnameD+" "+myData.LnameD);
//        ema.setText(myData.EmailD);

        SharedPreferences sp = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        per.setText(sp.getString("FName","")+" "+sp.getString("LName",""));
        ema.setText(sp.getString("Email",""));



        /*----------------------Navigation Drawer Menu-------------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_myCart);


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_myCart:
                intent = new Intent(Home.this, MyCart.class);
                startActivity(intent);
                break;
            case R.id.nav_myAccount:
                intent = new Intent(Home.this, MyAccount.class);
                startActivity(intent);
                break;
            case R.id.nav_Table:
                intent = new Intent(Home.this, Tables.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                SharedPreferences sp = getSharedPreferences(Login.PREFS_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
               // editor.remove("hasLoggedIn");
                editor.clear();
                editor.commit();
                intent = new Intent(Home.this, Login.class);
                startActivity(intent);
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            Home.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mViewPager.getCurrentItem() < images.length - 1) {
                        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                    } else {
                        mViewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

}
