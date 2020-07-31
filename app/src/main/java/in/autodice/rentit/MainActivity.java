package in.autodice.rentit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.example.rentit.R;

public class MainActivity extends Activity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        final String loginstatus=sharedPreferences.getString("user","none");
        if(!loginstatus.equals("none")){
            Intent intent=new Intent(this,Home.class);
            startActivity(intent);
        }
        if(loginstatus.equals("none")) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getBaseContext(), RentItLogIn.class);
                    startActivity(intent);
                }
            }, 2000);
        }

    }
    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}
