package in.autodice.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentit.R;

public class Update extends AppCompatActivity {
    EditText newcontact,pass,cpass,buil,stree,loc,pin;
    TextView email;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        email=findViewById(R.id.update_email);
        newcontact=findViewById(R.id.update_contact);
        pass=findViewById(R.id.updated_password);
        cpass=findViewById(R.id.updated_cpassword);
        pin=findViewById(R.id.input_s_pincod);
        buil=findViewById(R.id.input_s_flatn);
        stree=findViewById(R.id.input_s_Stree);
        loc=findViewById(R.id.input_s_Landmar);
        sharedPreferences= (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(this);
        final String semail=sharedPreferences.getString("user","none");
        email.setText(semail);
    }

    public void updatecontact(View view) {
        String semail,scontact,errormes="submitted";
        boolean iserror = false;
        semail=String.valueOf(email.getText()).trim();
        scontact=String.valueOf(newcontact.getText()).trim();
        if (semail == null || semail.equals("")) {
            iserror = true;
            errormes = "Enter your email";
        } else if (scontact == null || scontact.equals("")) {
            iserror = true;
            errormes = "Enter your Contact";

        } else{
            HttpHandler httpHandler=new HttpHandler(Update.this);
            httpHandler.execute("updatecontact",semail,scontact);
        }
        if(iserror){
            Toast t = Toast.makeText(getBaseContext(), errormes, Toast.LENGTH_LONG);
            t.show();
        }
    }

    public void updatepass(View view) {
        String semail,spass,scpass,errormes="submitted";
        boolean iserror = false;
        semail=String.valueOf(email.getText()).trim();
        spass=String.valueOf(pass.getText()).trim();

        scpass=String.valueOf(cpass.getText()).trim();
        if (semail == null || semail.equals("")) {
            iserror = true;
            errormes = "Enter your email";
        } else if (spass == null || spass.equals("")) {
            iserror = true;
            errormes = "Enter your password";

        } else if (scpass == null || scpass.equals("")) {
            iserror = true;
            errormes = "Enter your Confirm password";

        }else if (!spass.equals(scpass)) {
            iserror=true;
            errormes="Your confirm password and password differ";

        }else {
            HttpHandler httpHandler=new HttpHandler(Update.this);
            httpHandler.execute("updatepassword",semail,spass);
        }
        if(iserror){
            Toast t = Toast.makeText(getBaseContext(), errormes, Toast.LENGTH_LONG);
            t.show();
        }
    }
    public void updateadress(View view) {
        String semail,sfla,sstree,spin,slan,errormes="submitted";
        boolean iserror = false;
        semail=String.valueOf(email.getText()).trim();
        sfla=String.valueOf(buil.getText()).trim();
        sstree=String.valueOf(stree.getText()).trim();
        slan=String.valueOf(loc.getText()).trim();
        spin=String.valueOf(pin.getText()).trim();
        if (sfla == null || sfla.equals("")) {
            iserror = true;
            errormes = "Enter your flat or building no.";
        } else if (sstree == null || sstree.equals("")) {
            iserror = true;
            errormes = "Enter your Street";

        } else if (slan == null || slan.equals("")) {
            iserror = true;
            errormes = "Enter your nearest landmark";

        }else if (spin == null ||spin.equals("")) {
            iserror = true;
            errormes = "Enter your pincode";

        }else {
            String addreess=sfla+sstree+slan;
            HttpHandler httpHandler=new HttpHandler(Update.this);
            httpHandler.execute("updateaddress",semail,addreess,spin);
        }
        if(iserror){
            Toast t = Toast.makeText(getBaseContext(), errormes, Toast.LENGTH_LONG);
            t.show();
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Update.this,Home.class);
        startActivity(intent);
        super.onBackPressed();
    }


}
