package in.autodice.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentit.R;

public class XYZ extends AppCompatActivity {
    TextView name, price, gender, category, size, locality, desc, orderid, soldto;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xyz);

        name = findViewById(R.id.name_product);
        price = findViewById(R.id.rate_product);
        gender = findViewById(R.id.gender_product);
        category = findViewById(R.id.category_product);
        size = findViewById(R.id.size_product);
        locality = findViewById(R.id.locality_product);
        desc = findViewById(R.id.desc);
        imageView = findViewById(R.id.image_product);
        orderid = findViewById(R.id.orderid);
        soldto = findViewById(R.id.soldto);

        Intent i =getIntent();
        String id = i.getExtras().getString("value2");
        HttpHandler3 handler = new HttpHandler3("xyz", XYZ.this, name, price, gender, category, size, locality, desc, imageView, orderid, soldto);
        handler.execute(id);
    }
}
