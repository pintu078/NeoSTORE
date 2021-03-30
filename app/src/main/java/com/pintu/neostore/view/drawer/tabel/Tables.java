package com.pintu.neostore.view.drawer.tabel;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pintu.neostore.R;
import com.pintu.neostore.adapter.ProductListAdapter;
import com.pintu.neostore.model.ProductList_Data;
import com.pintu.neostore.viewmodel.TabelsVM;

import java.util.ArrayList;
import java.util.List;

public class Tables extends AppCompatActivity {

    NestedScrollView nestedScrollView;
    public static ProgressBar progressBar;
    RecyclerView recyclerView;
    private TabelsVM tabelsVM;
    ProductListAdapter productListAdapter;
    EditText inputSearch;
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    ArrayList<ProductList_Data> list1 = new ArrayList<>();
    String id = "1";
    Integer li = 10;
    public Integer page = 1;

//    List<Item> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
        ImageButton imgbtn = findViewById(R.id.imgbtn);
        inputSearch = findViewById(R.id.input_search);

        nestedScrollView = findViewById(R.id.scroll_view);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tables.super.onBackPressed();
            }
        });


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // tabelsVM.loadProductLists("1",10,1);

        tabelsVM = ViewModelProviders.of(this).get(TabelsVM.class);
        tabelsVM.getTableListObserver().observe(this, new Observer<List<ProductList_Data>>() {

            @Override
            public void onChanged(@Nullable List<ProductList_Data> productList_data) {
                System.out.println("-------onchanged");
                System.out.println(productList_data.size());

                list1.addAll(productList_data);

                productListAdapter = new ProductListAdapter(Tables.this, list1);
                productListAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(productListAdapter);
                progressBar.setVisibility(View.GONE);

            }
        });
        System.out.println("---------Tabels");
        // tabelsVM.loadProductLists();

        /*
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            //when we do scrolling start
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("saurabh","onScrollStateChanged");
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    Log.d("saurabh","OnScrollListener---if");
                    isScrolling = true;
                }
            }

            @Override
            // when scroll done
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = linearLayoutManager.getChildCount();//current visible item in list
                totalItems = linearLayoutManager.getItemCount();//total item
                scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();//scroll out of screen
                Log.d("saurabh","onScrolled");

                if(isScrolling && (currentItems + scrollOutItems == 3)){
                    isScrolling = false;
                    Log.d("saurabh","onScrolled--if");
                    page++;
                   tabelsVM.loadProductLists(id,li,page);
                }
                //tabelsVM.loadProductLists("2",10,1);
            }
        });

         */
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {

                    page++;
                    progressBar.setVisibility(View.VISIBLE);
                    tabelsVM.loadProductLists(id, li, page);
                    // progressBar.setVisibility(View.GONE);
                    Log.d("saurabh", "onscrollchange executed");


                }
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                productListAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}



