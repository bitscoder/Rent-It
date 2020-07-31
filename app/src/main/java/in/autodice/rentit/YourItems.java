package in.autodice.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rentit.R;

public class YourItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_items);
    }

    public void buying(View view) {
        Intent intent=new Intent(this,StatusItem.class);
        intent.putExtra("status","buying");
        startActivity(intent);
    }

    public void productssold(View view) {
        Intent intent=new Intent(this,StatusItem.class);
        intent.putExtra("status","selling");
        startActivity(intent);
    }
}
