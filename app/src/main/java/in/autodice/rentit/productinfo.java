package in.autodice.rentit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rentit.R;

import java.io.ByteArrayOutputStream;

public class productinfo extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE =6 ,PICK_IMAGE_GALLERY=1;
    ImageView imageView;
    String[] sfemale = { "Suit", "Lehenga", "Kurtis", "Sarees", "Others"};
    String[] smale ={"Blazer","Suits","Kurtas","Sherwani","Traditional"};

    String[] sizecategory = { "L", "M", "Xl", "XXL", "Others"};
    RadioGroup gender;
    RadioButton male,female,sex;
    EditText nameofproduct,rate,dis;
    String cat, size, imageString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productinfo);
        imageView=findViewById(R.id.imageView);
        nameofproduct=findViewById(R.id.input_product_name);
        gender=findViewById(R.id.gender);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        rate=findViewById(R.id.rate);
        dis=findViewById(R.id.descriptionEditText);
        int id=gender.getCheckedRadioButtonId();
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                sex=findViewById(i);
                if(sex.getText().equals("Male")){
                    Spinner s = (Spinner) findViewById(R.id.spinner2);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(productinfo.this,
                            android.R.layout.simple_spinner_item, smale);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s.setAdapter(adapter);
                    s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            cat=adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            cat="other";
                        }
                    });
                }else {
                    Spinner s = (Spinner) findViewById(R.id.spinner2);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(productinfo.this,
                            android.R.layout.simple_spinner_item, sfemale);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s.setAdapter(adapter);
                    s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            cat=adapterView.getItemAtPosition(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            cat="other";
                        }
                    });
                }
            }
        });

        Spinner s1 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sizecategory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                size=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                size="other";
            }
        });

    }

    public void takephotofromgallary(View view) {

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    },1);
                }}
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);


    }
    public void takephoto(View view) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        android.Manifest.permission.CAMERA
                }, 6);
            }
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String inputStreamImg = null;
        if (requestCode == CAMERA_REQUEST_CODE) {
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        } else if (requestCode == PICK_IMAGE_GALLERY) {
            Uri selectedImage = data.getData();
            try {
               Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                imageView.setImageBitmap(bitmap);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                Log.e("Activity", "Pick from Gallery::>>> ");
                byte[] imageBytes = bytes.toByteArray();
                imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void submit(View view) {
        boolean iserror = false;
        String pname, prate,pdis,errormes="submitted";
        pname = String.valueOf(nameofproduct.getText()).trim();
        pdis = String.valueOf(dis.getText()).trim();
        prate = String.valueOf(rate.getText()).trim();

        if (pname == null || pname.equals("")) {
            iserror = true;
            errormes = "Enter products name";
        } else if (pdis == null || pdis.equals("")) {
            iserror = true;
            errormes = "Enter discription of product";

        } else if (prate == null || prate.equals("")) {
            iserror = true;
            errormes = "Enter rate for product";

        } else{
            String gen = String.valueOf(sex.getText());
            HttpHandler httpHandler = new HttpHandler(productinfo.this);
            SharedPreferences sharedPreferences= (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(this);
            final String semail=sharedPreferences.getString("user","none");
            httpHandler.execute("productupload", imageString, semail, pname, gen, cat, size, pdis, prate);
        }

        if(iserror){
            Toast t = Toast.makeText(getBaseContext(), errormes, Toast.LENGTH_LONG);
            t.show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,Home.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
