package in.autodice.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.rentit.R;

import java.util.ArrayList;

public class StatusItem extends AppCompatActivity {
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_item);

        String str = getIntent().getStringExtra("status");
        if (str.equals("buying"))
        {
            gridView = findViewById(R.id.grid);
//
            ArrayList<Product> products = new ArrayList<Product>();
            HttpHandler2 handler = new HttpHandler2("viewbuyed",this,this, products,gridView);
            handler.execute("vikrantdharap94@gmail.com");
//        products.add(new Product("aaaaa","aaa"));
//        products.add(new Product("aaaaa","aaa"));
//        products.add(new Product("aaaaa","aaa"));
//        products.add(new Product("aaaaa","aaa"));

            customadapter2 adapter = new customadapter2("prodbuyed", products,this);
            gridView.setAdapter(adapter);
        }else {
            gridView = findViewById(R.id.grid);
//
            ArrayList<Product> products = new ArrayList<Product>();
            HttpHandler2 handler = new HttpHandler2("viewsold",this,this, products,gridView);
            handler.execute("vikrantdharap94@gmail.com");
//        products.add(new Product("aaaaa","aaa"));
//        products.add(new Product("aaaaa","aaa"));
//        products.add(new Product("aaaaa","aaa"));
//        products.add(new Product("aaaaa","aaa"));

            customadapter2 adapter = new customadapter2("prodsold", products,this);
            gridView.setAdapter(adapter);
        }
    }
}
