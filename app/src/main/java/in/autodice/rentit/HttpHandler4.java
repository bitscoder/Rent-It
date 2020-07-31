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

public class HttpHandler4 extends AsyncTask<String,String,String>
{
    Context context;
    ArrayList<Product> arrayList;
    GridView grid;
    Activity activity;

    HttpHandler4(Activity a,Context ctx, ArrayList<Product> productArrayList, GridView gridView)
    {
        context = ctx;
        arrayList = productArrayList;
        grid = gridView;
        activity = a;
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
        try        {
            url = new URL(baseurl + "viewmprod.php");
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
                String id=jsonObject.getString("id");

                Product product = new Product(image, name,id);
                arrayList.add(product);

            }

            customadapter2 adapter = new customadapter2("home", arrayList, activity);
            grid.setAdapter(adapter);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}