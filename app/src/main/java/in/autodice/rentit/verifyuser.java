package in.autodice.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.rentit.R;
import com.google.firebase.auth.FirebaseAuth;

public class verifyuser extends AppCompatActivity {
    private EditText editTextMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyuser);
        getSupportActionBar().hide();
        editTextMobile = findViewById(R.id.input_v_number);
    }

    public void ontinue(View view) {
        String mobile = editTextMobile.getText().toString().trim();

        if(mobile.isEmpty() || mobile.length() < 10){
            editTextMobile.setError("Enter a valid mobile");
            editTextMobile.requestFocus();
            return;
        }
        Intent intent = new Intent(verifyuser.this, VerifyPhoneActivity.class);
        intent.putExtra("mobile", mobile);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent=new Intent(verifyuser.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(verifyuser.this,RentItLogIn.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
