package in.autodice.rentit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rentit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends AppCompatActivity {
    private String vid;
    private FirebaseAuth mAuth;
    private EditText editText;
    public static String contactt=null;
    public String phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);
        getSupportActionBar().hide();
        mAuth=FirebaseAuth.getInstance();
        phonenumber=getIntent().getStringExtra("mobile");
        String mobile="+"+"91"+phonenumber;
        sendVerificationCode(mobile);

        editText=findViewById(R.id.editTextCode);
    }

    public void ntine(View view) {
        String code=editText.getText().toString().trim();
        if(code.length()<6||code.isEmpty()){
            editText.setError("Enter code");
            editText.requestFocus();
            return;
        }

        verifyCode(code);
    }
    private void verifyCode(String code){
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(vid,code);
        signInWithCredential(credential);


    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    contactt=phonenumber;
                    Intent intent=new Intent(VerifyPhoneActivity.this,SignUp.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);


                }else{
                    Toast.makeText(getBaseContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();

                }
            }
        });
    }


    private void sendVerificationCode(String number)
    {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );


    }
    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            vid=s;

        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code=phoneAuthCredential.getSmsCode();
            if(code!=null){
                editText.setText(code);
                verifyCode(code);

            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyPhoneActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();

        }
    };

}
