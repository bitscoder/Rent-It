package in.autodice.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rentit.R;

public class RentItLogIn extends AppCompatActivity {
    EditText email,password;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_it_log_in);
        getSupportActionBar().hide();
        email=findViewById(R.id.input_email);
        password=findViewById(R.id.input_password);


    }

    public void Check(View view) {
        boolean iserror = false;
        String  smail,spassword,errormes="submitted";
        smail = String.valueOf(email.getText()).trim();
        spassword = String.valueOf(password.getText()).trim();

        if (smail == null || smail.equals("")) {
            iserror = true;
            errormes = "Enter your Email";

        } else if (spassword == null || spassword.equals("")) {
            iserror = true;
            errormes = "Enter your password";

        } else {

            HttpHandler httphandler;
            httphandler=new HttpHandler(RentItLogIn.this);
            httphandler.execute("login",smail,spassword);
        }

        if(iserror){
            Toast t = Toast.makeText(getBaseContext(), errormes, Toast.LENGTH_LONG);
            t.show();
        }
    }

    public void Signup(View view) {
        Intent intent=new Intent(getBaseContext(),verifyuser.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStop() {

        finish();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
