package com.pintu.neostore.drawer.tabel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;


import com.pintu.neostore.R;
import com.pintu.neostore.adapter.ProductDetailedAdapter;
import com.pintu.neostore.login.Login;
import com.pintu.neostore.model.Cart.Cart_APIMSg;
import com.pintu.neostore.model.ProductDetailed_Model.ProductDetailed_APIMsg;
import com.pintu.neostore.model.ProductDetailed_Model.ProductDetailed_Data;
import com.pintu.neostore.model.ProductDetailed_Model.ProductImage;
import com.pintu.neostore.model.Rate_Model.Rate_APIMsg;
import com.pintu.neostore.viewmodel.BuyVM;
import com.pintu.neostore.viewmodel.BuyVMFactory;
import com.pintu.neostore.viewmodel.ProductDetailedVM;
import com.pintu.neostore.viewmodel.ProductDetailedVMFactory;
import com.pintu.neostore.viewmodel.RateVM;
import com.pintu.neostore.viewmodel.RateVMFactory;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductDetailed extends AppCompatActivity {

    ProductDetailedVM productDetailedVM;
    public static Dialog dialog;


    TextView heading, heading1, heading2, descriptiuon, price, tooltext;
    ImageButton Share, Back_btn;
    RatingBar ratingBar;
    public static ImageView img_main;
    Button Buy, Rate;
    public static Button rateButton;
    public static ProgressBar progressBar;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    SharedPreferences sp;

    ProductDetailedAdapter adapter;
    ProductDetailed_Data list1;

    String ids,token,imgs_1;
    RateVM rateVM;
    BuyVM buyVM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detailed);

        heading = (TextView) findViewById(R.id.head1);
        heading1 = (TextView) findViewById(R.id.head2);
        heading2 = (TextView) findViewById(R.id.head3);
        ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        price = (TextView) findViewById(R.id.pricetxtview);
        tooltext = (TextView) findViewById(R.id.tooltext);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);


        img_main = (ImageView) findViewById(R.id.image_main);

        Back_btn = (ImageButton) findViewById(R.id.imgbtn);
        Share = (ImageButton) findViewById(R.id.btn_share);
        Buy = (Button) findViewById(R.id.btn_buy);
        Rate = (Button) findViewById(R.id.btn_reset);

        descriptiuon = (TextView) findViewById(R.id.tv_description_1);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        Back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDetailed.super.onBackPressed();
            }
        });


        productDetailedVM = new ViewModelProvider(this, new ProductDetailedVMFactory(this)).get(ProductDetailedVM.class);
        productDetailedVM.getProdDetailsObserver().observe(this, new Observer<ProductDetailed_APIMsg>() {
            @Override
            public void onChanged(ProductDetailed_APIMsg apiMsg) {
                System.out.println("---------1-------");
                if (apiMsg != null) {
                    Log.d("saurah", "productDetails Success  " + apiMsg.getData().getProductImages());
                    heading.setText(apiMsg.getData().getName());
                    heading1.setText(apiMsg.getData().getProducer());
                    heading2.setText(apiMsg.getData().getProducer());
                    ratingBar.setRating(apiMsg.getData().getRating());
                    price.setText(String.valueOf(apiMsg.getData().getCost()));
                    tooltext.setText(apiMsg.getData().getName());

                    List<ProductImage> list = apiMsg.getData().getProductImages();
                    list1 = apiMsg.getData();
                    adapter = new ProductDetailedAdapter(ProductDetailed.this, list);
                    recyclerView.setAdapter(adapter);

                    descriptiuon.setText(apiMsg.getData().getDescription());

                } else {
                    System.out.println("---------3-------");

                }
            }

        });

        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Your body here";
                String shareSub = "Your subject here";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });


        Rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRate();
            }
        });

        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBuy();
            }
        });

        rateVM = new ViewModelProvider(this, new RateVMFactory(this)).get(RateVM.class);
        rateVM.getRatingObserver().observe(this, new Observer<Rate_APIMsg>() {
            @Override
            public void onChanged(Rate_APIMsg rate_apiMsg) {
                if(rate_apiMsg != null){
                    Log.d("saurabh","Success rateing");

                }
            }
        });

        buyVM = new ViewModelProvider(this, new BuyVMFactory(this)).get(BuyVM.class);
        buyVM.getBuyObserver().observe(this, new Observer<Cart_APIMSg>() {
            @Override
            public void onChanged(Cart_APIMSg cart_apiMsg) {
                if(cart_apiMsg != null){
                    Log.d("saurabh","Success Buying");
                }
            }

        });


        Intent intent = getIntent();
        imgs_1 = intent.getStringExtra("image");
        ids = intent.getStringExtra(("product id"));
        productDetailedVM.loadProductDetailed(ids);

    }

    private void openRate() {

        dialog = new Dialog(ProductDetailed.this);
        dialog.setContentView(R.layout.rate_popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView text = (TextView) dialog.findViewById(R.id.head1pp);
        text.setText(list1.getName());
        ImageView image = (ImageView) dialog.findViewById(R.id.image_mainpp);
        Picasso.with(getApplicationContext())
                .load(imgs_1)
                .fit()
                .into(image);

        ratingBar = (RatingBar) dialog.findViewById(R.id.ratingbarpp);

        dialog.show();

        rateButton = (Button) dialog.findViewById(R.id.btn_resetpp);
        // if decline button is clicked, close the custom dialog
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float ratingNumber = ratingBar.getRating();
                Log.d("saurabh", "Rating  " + ratingNumber);
                rateVM.loadRating(ids,ratingNumber.toString());


            }
        });
    }

    private void openBuy() {

        dialog = new Dialog(ProductDetailed.this);
        dialog.setContentView(R.layout.buy_popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView text = (TextView) dialog.findViewById(R.id.head1pp);
        text.setText(list1.getName());
        ImageView image = (ImageView) dialog.findViewById(R.id.image_mainpp);
        Picasso.with(getApplicationContext())
                .load(imgs_1)
                .fit()
                .into(image);

        dialog.show();
        sp = getSharedPreferences(Login.PREFS_NAME,MODE_PRIVATE);
        token = sp.getString("Token","");

        rateButton = (Button) dialog.findViewById(R.id.btn_resetpp);
        // if decline button is clicked, close the custom dialog
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) dialog.findViewById(R.id.ed_quantity);
                String quantity  = edit.getText().toString().trim();
                Log.d("saurabh",quantity+"quantity");
                buyVM.loadBuy(token,ids,quantity);
                if(!quantity.equals("")) {
                    dialog.dismiss();
                }
            }
        });
    }
}