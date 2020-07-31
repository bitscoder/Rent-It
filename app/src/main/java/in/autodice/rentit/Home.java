package in.autodice.rentit;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ShareActionProvider;

import com.example.rentit.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ShareActionProvider actionProvider;
    Button give_items,take_items;
    private android.view.View View;
    private MenuItem item;
    SharedPreferences sharedPreferences;
    GridView androidGridView;
    String str_name, str_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        androidGridView = findViewById(R.id.grid_view_image_text);
        ArrayList<Product> products = new ArrayList<Product>();
        HttpHandler2 handler = new HttpHandler2("home",this,this, products,androidGridView);
        handler.execute();


        customadapter2 adapter = new customadapter2("home", products,this);
        androidGridView.setAdapter(adapter);

        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this , drawer , toolbar , R.string.navigation_drawer_open , R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void Give(android.view.View View)
    {
        Intent Give = new Intent(this,productinfo.class);
        startActivity(Give);

    }
    public void Take(android.view.View View)
    {
        Intent Take = new Intent(this,productsonrentactivity.class);
        startActivity(Take);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else  {
            //other stuff...
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        this.item = item;
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.go_home){
            Intent intent=new Intent(this,Home.class);
            startActivity(intent);
        }else if (id == R.id.update__profile) {
            Intent Give = new Intent(this,Update.class);
            startActivity(Give);
        } else if (id == R.id.your__items) {
            Intent Give = new Intent(this,YourItems.class);
            startActivity(Give);
        } else if (id == R.id.faqs) {
            Intent Give = new Intent(this,faq.class);
            startActivity(Give);
        }else if (id == R.id.terms_of_use) {
            Intent Give = new Intent(this,termsofuse.class);
            startActivity(Give);
        }
        else if (id == R.id.contact__us) {
            Intent Give = new Intent(this,contactus.class);
            startActivity(Give);
        }
        else if (id == R.id.log__out) {
            sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
            sharedPreferences.edit().putString("user","none").commit();
            Intent intent=new Intent(Home.this,RentItLogIn.class);
            startActivity(intent);
        }else if (id == R.id.nav_send) {
            Intent Give = new Intent(this,contactus.class);
            startActivity(Give);
        }
        else  if (id == R.id.nav_share) {
            Intent Give = new Intent(this,sharea.class);
            startActivity(Give);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void productlevel(View view) {
        Intent intent=new Intent(getBaseContext(),productinfo.class);
        startActivity(intent);
    }

    public void borrow(View view) {
        Intent intent=new Intent(getBaseContext(),Productdetails.class);
        startActivity(intent);
    }
    @Override
    protected void onStop() {

        finish();
        super.onStop();
    }


    public void productsonrent(android.view.View view) {
        Intent Take = new Intent(this,productsonrentactivity.class);
        startActivity(Take);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, android.view.View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

}
