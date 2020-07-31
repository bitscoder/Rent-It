package in.autodice.rentit;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.Toast;

        import com.example.rentit.R;

        import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    EditText name,email,password;

    EditText flatno,street,landmark,pincode,confirm;
    RadioButton sex;
    RadioGroup gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        name=findViewById(R.id.input_s_username);
        email=findViewById(R.id.input_s_email);
        password=findViewById(R.id.input_password);
        flatno=findViewById(R.id.input_s_flatno);
        street=findViewById(R.id.input_s_Street);
        landmark=findViewById(R.id.input_s_Landmark);
        pincode=findViewById(R.id.input_s_pincode);
        confirm=findViewById(R.id.input_cpassword);
        gender=findViewById(R.id.gender);
    }

    public void CheckSignup(View view) {
        boolean iserror = false;
        String sname, smail,spassword,errormes="submitted",sflatno,sstreet,slandmark,spincode,sconfirm,address,ssex;
        int id=gender.getCheckedRadioButtonId();
        sex=findViewById(id);
        ssex=String.valueOf(sex.getText());
        sname = String.valueOf(name.getText()).trim();
        smail = String.valueOf(email.getText()).trim();
        sflatno = String.valueOf(flatno.getText()).trim();
        sstreet = String.valueOf(street.getText()).trim();
        slandmark = String.valueOf(landmark.getText()).trim();
        spincode=String.valueOf(pincode.getText()).trim();
        spassword = String.valueOf(password.getText()).trim();
        sconfirm = String.valueOf(confirm.getText()).trim();
        address=sflatno+", "+sstreet+", "+slandmark;
        final Pattern emailpattern= Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        if (sname == null || sname.equals("")) {
            iserror = true;
            errormes = "Enter your Name";
        } else if (smail == null || smail.equals("")) {
            iserror = true;
            errormes = "Enter your Email";

        } else if (sflatno == null || sflatno.equals("")) {
            iserror = true;
            errormes = "Enter your flatno.";

        } else if (sstreet == null || sstreet.equals("")) {
            iserror = true;
            errormes = "Enter your street";

        } else if (spincode== null || spincode.equals("")||spincode.length()!=6) {
            iserror = true;
            errormes = "Invalid Pincode";

        } else if (slandmark == null || slandmark.equals("")) {
            iserror = true;
            errormes = "Enter your Landmark";

        } else if (spassword == null || spassword.equals("")) {
            iserror = true;
            errormes = "Enter your password";

        } else if (spassword.length() < 8) {
            iserror=true;
            errormes="password length is weak";

        }else if (!spassword.equals(sconfirm)) {
            iserror=true;
            errormes="Your confirm password and password differ";

        }else if(!emailpattern.matcher(smail).matches()){
            iserror=true;
            errormes="email is invalid";
        }else{
            HttpHandler httphandler=new HttpHandler(SignUp.this);
            httphandler.execute("insertuser",sname,smail,address,spincode,VerifyPhoneActivity.contactt,ssex,spassword);
        }

        if(iserror){
            Toast t = Toast.makeText(getBaseContext(), errormes, Toast.LENGTH_LONG);
            t.show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,RentItLogIn.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
