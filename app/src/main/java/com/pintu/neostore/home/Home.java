package com.pintu.neostore.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.pintu.neostore.R;
import com.pintu.neostore.adapter.ViewPagerAdapter;
import com.pintu.neostore.drawer.MyAccount.MyAccount;
import com.pintu.neostore.drawer.mycart.MyCart;
import com.pintu.neostore.drawer.order.OrderList;
import com.pintu.neostore.drawer.store_locator.StoreLocator;
import com.pintu.neostore.drawer.tabel.Tables;
import com.pintu.neostore.login.Login;
import com.pintu.neostore.model.fetch.Data;
import com.pintu.neostore.viewmodel.FetchVM;
import com.pintu.neostore.viewmodel.FetchVMFactory;
import com.squareup.picasso.Picasso;


import java.util.Timer;
import java.util.TimerTask;




public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView tabel_CardView;
    ViewPager mViewPager;
    TabLayout indicator;
    Intent intent;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    FetchVM fetchVM;
    // images array
    int[] images = {R.drawable.beds, R.drawable.sofas, R.drawable.cupboards, R.drawable.tabels, R.drawable.chairs};

    ImageView headerImg;
    TextView header_Name, header_email,notification;
    ViewPagerAdapter mViewPagerAdapter;

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);


        /*----------------------Hooks-------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View headerContainer = navigationView.getHeaderView(0);
        toolbar = findViewById(R.id.toolbar);
        indicator = (TabLayout) findViewById(R.id.indicator);
        headerImg = (ImageView) headerContainer.findViewById(R.id.header_img);
        header_Name = (TextView) headerContainer.findViewById(R.id.header_name);
        header_email = (TextView) headerContainer.findViewById(R.id.header_email);
        //Menu counter = navigationView.getMenu();
        //notification = (TextView) counter.findItem(R.id.tv_notification);
        notification = (TextView) navigationView.getMenu().findItem(R.id.nav_myCart).getActionView();

//        private void setMenuCounter(@IdRes int itemId, int count) {
//            TextView view = (TextView) navigationView.getMenu().findItem(itemId).getActionView();
//            view.setText(count > 0 ? String.valueOf(count) : null);
//        }

        tabel_CardView = (CardView) findViewById(R.id.tabel_cardView);

        /*----------------------Tool BAr-------------------------*/
        setSupportActionBar(toolbar);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(Home.this, images);

        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
        indicator.setupWithViewPager(mViewPager, true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 2000, 4000);

        sp = getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
        header_Name.setText(sp.getString("FName", "") + " " + sp.getString("LName", ""));
        header_email.setText(sp.getString("Email", ""));
        token = sp.getString("Token", "");
        Log.d("saurabh", "token " + token);

        final String image = sp.getString("Profile", "");
        Log.d("saurabh", "image " + image);

//        TextDrawable drawable = TextDrawable.builder().buildRect("A", Color.RED);
//        headerImg.setImageDrawable(drawable);
        if (!image.equals("")) {
            Picasso.with(getApplicationContext())
                    .load(image)
                    .noFade()
                    .fit()
                    .into(headerImg);
        }


        /*----------------------Navigation Drawer Menu-------------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_myCart);


        tabel_CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Home.this, Tables.class);
                startActivity(intent);
            }
        });



        fetchVM = new ViewModelProvider(this, new FetchVMFactory(this)).get(FetchVM.class);
        fetchVM.getFetchListObserver().observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                if(data != null){
                    Log.d("saurabh","succes home");
                    sp = getSharedPreferences(Login.PREFS_NAME,MODE_PRIVATE);
                    editor = sp.edit();
                    editor.putString("cart", data.getTotalCarts().toString());
                    editor.commit();
                    String quantity = sp.getString("cart","");

                    if(quantity.equals("0")){
                        notification.setVisibility(View.GONE);
                    }else{
                        notification.setVisibility(View.VISIBLE);
                        notification.setText(quantity);
                    }
                }
            }
        });
        fetchVM.loadFetchList(token);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
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
            case R.id.nav_myOrders:
                intent = new Intent(Home.this, OrderList.class);
                startActivity(intent);
                break;
            case R.id.nav_location:
                intent = new Intent(Home.this, StoreLocator.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                sp = getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
                editor = sp.edit();
                editor.putString("FName", "");
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

    @Override
    protected void onResume() {
        super.onResume();
        sp = getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
        header_Name.setText(sp.getString("FName", "") + " " + sp.getString("LName", ""));
        header_email.setText(sp.getString("Email", ""));
        String image = sp.getString("Profile", "");
        if (!image.equals("")) {
            Picasso.with(getApplicationContext())
                    .load(image)
                    .fit()
                    .into(headerImg);
            fetchVM.loadFetchList(token);
        }
        Log.d("saurabh","token-- "+token);
    }
}

