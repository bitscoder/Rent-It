package in.autodice.rentit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.rentit.R;

public class Productdetails extends AppCompatActivity {

    TextView name, price, gender, category, size, locality, desc;
    ImageView imageView;
    PopupMenu pop;
    Button contact;
    Button button;
    public Productdetails() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);
        name = findViewById(R.id.name_product);
        price = findViewById(R.id.rate_product);
        gender = findViewById(R.id.gender_product);
        category = findViewById(R.id.category_product);
        size = findViewById(R.id.size_product);
        locality = findViewById(R.id.locality_product);
        desc = findViewById(R.id.des);
        imageView = findViewById(R.id.image_product);
        contact=findViewById(R.id.con);
        pop=new PopupMenu(getBaseContext(),contact);
        MenuInflater inflater= pop.getMenuInflater();
        button=findViewById(R.id.b1);

        inflater.inflate(R.menu.contacts,pop.getMenu());

        Intent i =getIntent();
        String id = i.getExtras().getString("value1");
        HttpHandler3 handler = new HttpHandler3("product",Productdetails.this, name, price, gender, category, size, locality, desc, imageView,button);
        handler.execute(id);
    }





    public void contactuser(View view) {
        pop.show();
        pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.option1:
                        if(ActivityCompat.checkSelfPermission(Productdetails.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                            String[] permission = {Manifest.permission.CALL_PHONE};
                            ActivityCompat.requestPermissions(Productdetails.this,permission,1);
                        }
                        String str=HttpHandler3.contact;
                        Intent intent=new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel: +91"+str));
                        startActivity(intent);
                        return true;
                    case R.id.option2:
                        String no=HttpHandler3.contact;
                        String msg="hii maa";
                        if(ActivityCompat.checkSelfPermission(Productdetails.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
                            String[] permission = {Manifest.permission.SEND_SMS,Manifest.permission.RECEIVE_SMS};
                            ActivityCompat.requestPermissions(Productdetails.this,permission,1);
                        }
                        Intent mintent=new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:+91"+no));
                        mintent.putExtra("sms_body",msg);
                        startActivity(mintent);
                    case R.id.option3:
                        String emaila=HttpHandler3.email;
                        String sub="About your product on rent it";
                        String msg1="hii";
                        Intent eintent=new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:"+emaila));
                        eintent.putExtra(eintent.EXTRA_SUBJECT,sub);
                        eintent.putExtra(eintent.EXTRA_TEXT,msg1);
                        startActivity(eintent);
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getBaseContext(),Home.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
