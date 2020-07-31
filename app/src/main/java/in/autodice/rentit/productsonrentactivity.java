package in.autodice.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rentit.R;

public class productsonrentactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productsonrentactivity);
        getSupportActionBar().hide();
    }

    public void men_products(View view) {
        Intent intent=new Intent(productsonrentactivity.this,filter.class);
        intent.putExtra("gender","male");
        startActivity(intent);
    }

    public void Womens_product(View view) {
        Intent intent=new Intent(productsonrentactivity.this,filter.class);
        intent.putExtra("gender","female");
        finish();
        startActivity(intent);
    }
}
