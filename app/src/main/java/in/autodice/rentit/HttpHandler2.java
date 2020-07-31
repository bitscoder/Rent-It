package in.autodice.rentit;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class HttpHandler2 extends AsyncTask<String,String,String>
{
    Context context;
    ArrayList<Product> arrayList;
    GridView grid;
    Activity activity;
    String str;

    HttpHandler2(String type,Activity a,Context ctx, ArrayList<Product> productArrayList, GridView gridView)
    {
        context = ctx;
        arrayList = productArrayList;
        grid = gridView;
        activity = a;
        str = type;
    }

    @Override
    protected String doInBackground(String... strings)
    {
        String baseurl="https://rentitapp.000webhostapp.com/";
        URL url;
        OutputStream outputStream;
        OutputStreamWriter outputStreamWriter;
        BufferedWriter bufferedWriter;
        HttpURLConnection connection;
        InputStreamReader inputStreamReader;
        InputStream inputStream;
        BufferedReader bufferedReader;
        try
        {
            if (str.equals("home"))
            {
                url = new URL(baseurl + "viewprod.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                outputStream = connection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                String postdata = URLEncoder.encode("passkey","UTF-8")+"="+URLEncoder.encode("iopqwertuhygydskasty","UTF-8");
                Log.i("login", postdata);
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
                while ((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                Log.i("result", result);
                return result;
            }
            if (str.equals("viewsold"))
            {
                String email_id=strings[0];
                Log.i("email", email_id);
                url = new URL(baseurl + "viewsold.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                outputStream = connection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                String postdata = URLEncoder.encode("eid", "UTF-8") + "=" + URLEncoder.encode(email_id, "UTF-8")+"&"+
                        URLEncoder.encode("passkey","UTF-8")+"="+URLEncoder.encode("iopqwertuhygydskasty","UTF-8");
                Log.i("login", postdata);
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
                while ((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                Log.i("aaaa", result);
                return result;
            }
            if (str.equals("viewbuyed"))
            {
                String email_id=strings[0];
                Log.i("email", email_id);
                url = new URL(baseurl + "viewbuyed.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                outputStream = connection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                String postdata = URLEncoder.encode("eid", "UTF-8") + "=" + URLEncoder.encode(email_id, "UTF-8")+"&"+
                        URLEncoder.encode("passkey","UTF-8")+"="+URLEncoder.encode("iopqwertuhygydskasty","UTF-8");
                Log.i("login", postdata);
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
                while ((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                Log.i("aaaa", result);
                return result;
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
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

            JSONArray jsonArray = new JSONArray(s);
            JSONObject jsonObject;
            String state = "";
            for (int i = 0; i<jsonArray.length(); i++)
            {
                state = jsonArray.getString(i);
                jsonObject = new JSONObject(state);
                String image = jsonObject.getString("image");
                String name = jsonObject.getString("name");
                String id = jsonObject.getString("id");
                Product product = new Product(image, name,id);
                arrayList.add(product);
                Log.i("posttttt", product.getText());
            }

            if (str.equals("home"))
            {
                customadapter2 adapter = new customadapter2("home", arrayList, activity);
                grid.setAdapter(adapter);
            }
            if (str.equals("viewsold"))
            {
                customadapter2 adapter = new customadapter2("prodsold", arrayList, activity);
                grid.setAdapter(adapter);
            }
            if (str.equals("viewbuyed"))
            {
                customadapter2 adapter = new customadapter2("prodbuyed", arrayList, activity);
                grid.setAdapter(adapter);
            }
            Log.i("posttttt",""+ arrayList.size());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
