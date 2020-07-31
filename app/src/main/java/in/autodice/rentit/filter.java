package in.autodice.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.example.rentit.R;

import java.util.ArrayList;

public class filter extends AppCompatActivity {
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        String gender=getIntent().getStringExtra("gender");
        if(gender.equals("male")){
            getSupportActionBar().setTitle("Male Products");
            gridView = findViewById(R.id.grid_view_f_image_text);
            ArrayList<Product> products = new ArrayList<Product>();
            HttpHandler4 handler = new HttpHandler4(this,this, products,gridView);
            handler.execute();

        }else {
            getSupportActionBar().setTitle("Female Product");
            gridView = findViewById(R.id.grid_view_f_image_text);
            ArrayList<Product> products = new ArrayList<Product>();
            HttpHandler5 handler = new HttpHandler5(this,this, products,gridView);
            handler.execute();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(this,Home.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
