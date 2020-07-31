package in.autodice.rentit;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpHandler3 extends AsyncTask<String, String, String>
{
    Context context;
    TextView tname, tprice, tgender, tcategory, tsize, tlocality, tdesc, torderid, tsoldto;
    ImageView imageView;
   public static String contact, email, pid, str1;
    Button button;

    HttpHandler3(String str, Context ctx, TextView name, TextView price, TextView gender, TextView category, TextView size, TextView locality, TextView desc, ImageView image, Button btn)
    {
        this.context = ctx;
        this.tcategory = name;
        this.tprice = price;
        this.tgender =gender;
        this.tcategory = category;
        this.tsize = size;
        this.tlocality = locality;
        this.tdesc = desc;
        this.imageView = image;
        this.tname = name;
        this.button = btn;
        this.str1 = str;
    }

    HttpHandler3(String str, Context ctx, TextView name, TextView price, TextView gender, TextView category, TextView size, TextView locality, TextView desc, ImageView image, TextView orderid, TextView soldto)
    {
        this.context = ctx;
        this.tcategory = name;
        this.tprice = price;
        this.tgender =gender;
        this.tcategory = category;
        this.tsize = size;
        this.tlocality = locality;
        this.tdesc = desc;
        this.imageView = image;
        this.tname = name;
        this.torderid = orderid;
        this.tsoldto = soldto;
        this.str1 = str;
    }

    @Override
    protected String doInBackground(String... strings)
    {
        String baseurl="https://rentitapp.000webhostapp.com/";
        URL url;
        HttpURLConnection connection;
        OutputStream outputStream;
        OutputStreamWriter outputStreamWriter;
        BufferedWriter bufferedWriter;
        InputStreamReader inputStreamReader;
        InputStream inputStream;
        BufferedReader bufferedReader;
        String id = strings[0];
        pid=id;
        try {
            if (str1.equals("xyz"))
            {
                url = new URL(baseurl + "viewonesold.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                outputStream = connection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                String postdata = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")+"&"+
                        URLEncoder.encode("passkey","UTF-8")+"="+URLEncoder.encode("iopqwertuhygydskasty","UTF-8");
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStreamWriter.close();
                outputStream.close();
                inputStream = connection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                String result = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                return result;
            }
            if (str1.equals("qwe"))
            {
                url = new URL(baseurl + "viewonebuyed.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                outputStream = connection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                String postdata = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")+"&"+
                        URLEncoder.encode("passkey","UTF-8")+"="+URLEncoder.encode("iopqwertuhygydskasty","UTF-8");
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStreamWriter.close();
                outputStream.close();
                inputStream = connection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                String result = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                return result;
            }
            if (str1.equals("product")) {
                url = new URL(baseurl + "prod_details.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                outputStream = connection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                String postdata = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")+"&"+
                        URLEncoder.encode("passkey","UTF-8")+"="+URLEncoder.encode("iopqwertuhygydskasty","UTF-8");
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStreamWriter.close();
                outputStream.close();
                inputStream = connection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                String result = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                return result;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        try
        {
            if (str1.equals("product"))
            {
                Log.i("ressss", s);
                JSONArray jsonArray = new JSONArray(s);
                JSONObject jsonObject;
                String state = "";

                state = jsonArray.getString(0);
                jsonObject = new JSONObject(state);
                pid=jsonObject.getString("prod_id");
                String name = jsonObject.getString("name");
                String image = jsonObject.getString("image_loc");
                String gender = jsonObject.getString("gen");
                String size = jsonObject.getString("size");
                String desc = jsonObject.getString("desc");
                String price = jsonObject.getString("price");
                String locality = jsonObject.getString("local");
                String sub_cat = jsonObject.getString("sub_cat");
                contact = jsonObject.getString("con");
                email = jsonObject.getString("email");
                Log.i("answer", name + image + gender + size + desc + price + locality);

                tname.setText(name);
                tprice.setText(price);
                tgender.setText(gender);
                tsize.setText(size);
                tdesc.setText(desc);
                tlocality.setText(locality);
                tcategory.setText(sub_cat);
                Picasso.get().load(image).into(imageView);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(context, pid, Toast.LENGTH_SHORT).show();
                        HttpHandler handler1= new HttpHandler(context);
                        SharedPreferences sharedPreferences= (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(context);
                        final String semail=sharedPreferences.getString("user","none");
                        handler1.execute("buynow", semail, pid);

                    }
                });
            }
            if (str1.equals("xyz"))
            {
                Log.i("ressss", s);
                JSONArray jsonArray = new JSONArray(s);
                JSONObject jsonObject;
                String state = "";

                state = jsonArray.getString(0);
                jsonObject = new JSONObject(state);
                String prod_id = jsonObject.getString("product_id");
                String name = jsonObject.getString("name");
                String image = jsonObject.getString("image_loc");
                String gender = jsonObject.getString("gen");
                String size = jsonObject.getString("size");
                String desc = jsonObject.getString("desc");
                String price = jsonObject.getString("price");
                String order_id = jsonObject.getString("order_id");
                String uname = jsonObject.getString("uname");
                Log.i("answer", name + image + gender + size + desc + price);

                tlocality.setText(prod_id);
                tname.setText(name);
                tgender.setText(gender);
                tsize.setText(size);
                tdesc.setText(desc);
                tprice.setText(price);
                torderid.setText(order_id);
                tsoldto.setText(uname);
                Picasso.get().load(image).into(imageView);
            }
            if (str1.equals("qwe"))
            {
                Log.i("ressss", s);
                JSONArray jsonArray = new JSONArray(s);
                JSONObject jsonObject;
                String state = "";

                state = jsonArray.getString(0);
                jsonObject = new JSONObject(state);
                String prod_id = jsonObject.getString("product_id");
                String name = jsonObject.getString("name");
                String image = jsonObject.getString("image_loc");
                String gender = jsonObject.getString("gen");
                String size = jsonObject.getString("size");
                String desc = jsonObject.getString("desc");
                String price = jsonObject.getString("price");
                String order_id = jsonObject.getString("order_id");
                String uname = jsonObject.getString("uname");
                Log.i("answer", name + image + gender + size + desc + price);

                tlocality.setText(prod_id);
                tname.setText(name);
                tgender.setText(gender);
                tsize.setText(size);
                tdesc.setText(desc);
                tprice.setText(price);
                torderid.setText(order_id);
                tsoldto.setText(uname);
                Picasso.get().load(image).into(imageView);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}