package in.autodice.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.rentit.R;

public class contactus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(contactus.this,Home.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
