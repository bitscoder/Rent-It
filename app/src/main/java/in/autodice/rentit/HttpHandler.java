package in.autodice.rentit;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
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
public class HttpHandler extends AsyncTask<String, String, String>
{   Context context;
    private  SharedPreferences sharedPreferences;
    private SharedPreferences.Editor mEditor;
    EditText  contact;
    HttpHandler(Context ctx)
    {
        this.context = ctx;
    }
    public String email_id,contact_no;
    @Override
    protected String doInBackground(String... strings)
    {
        String type=strings[0];
        String baseurl="https://rentitapp.000webhostapp.com/";
        URL url;
        HttpURLConnection connection;
        OutputStream outputStream;
        OutputStreamWriter outputStreamWriter;
        BufferedWriter bufferedWriter;
        InputStreamReader inputStreamReader;
        InputStream inputStream;
        BufferedReader bufferedReader;
        try
        {
            if (type.equals("insertuser"))
            {
                String name=strings[1];

                String email_id=strings[2];

                String address = strings[3];
                Log.i("address", address);
                String pincode = strings[4];
                Log.i("pincode", pincode);
                contact_no = strings[5];
                Log.i("contact", contact_no);
                String gender = strings[6];
                Log.i("gender", gender);
                String password=strings[7];
                Log.i("password", password);
                url = new URL(baseurl + "insertuser.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                outputStream = connection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                String postdata = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("email_id", "UTF-8") + "=" + URLEncoder.encode(email_id, "UTF-8") + "&" +
                        URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&" +
                        URLEncoder.encode("pincode", "UTF-8") + "=" + URLEncoder.encode(pincode, "UTF-8") + "&" +
                        URLEncoder.encode("contact", "UTF-8") + "=" + URLEncoder.encode(contact_no, "UTF-8") + "&" +
                        URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")+"&"+
                        URLEncoder.encode("passkey","UTF-8") + "=" + URLEncoder.encode("iopqwertuhygydskasty","UTF-8");
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
            if (type.equals("login"))
            {
                 email_id=strings[1];
                String password=strings[2];
                Log.i("email", email_id);
                Log.i("password", password);
                url = new URL(baseurl + "login.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                outputStream = connection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                String postdata = URLEncoder.encode("email_id", "UTF-8") + "=" + URLEncoder.encode(email_id, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
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
            if (type.equals("productupload"))
            {
                String image = strings[1];
                String email_id = strings[2];
                String pname = strings[3];
                String gender = strings[4];
                String sub_category = strings[5];
                String size = strings[6];
                String description = strings[7];
                String price = strings[8];
                url = new URL(baseurl + "insertprod.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                outputStream = connection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                String postdata = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(image, "UTF-8") + "&" +
                        URLEncoder.encode("email_id", "UTF-8") + "=" + URLEncoder.encode(email_id, "UTF-8") + "&" +
                        URLEncoder.encode("pname", "UTF-8") + "=" + URLEncoder.encode(pname, "UTF-8") + "&" +
                        URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8") + "&" +
                        URLEncoder.encode("sub_category", "UTF-8") + "=" + URLEncoder.encode(sub_category, "UTF-8") + "&" +
                        URLEncoder.encode("size", "UTF-8") + "=" + URLEncoder.encode(size, "UTF-8") + "&" +
                        URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(description, "UTF-8") + "&" +
                        URLEncoder.encode("price", "UTF-8") + "=" + URLEncoder.encode(price, "UTF-8")+"&"+
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
            if (type.equals("updatecontact"))
            {
                String email_id = strings[1];
                String contact = strings[2];
                url = new URL(baseurl + "updatecontact.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                outputStream = connection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                String postdata = URLEncoder.encode("email_id", "UTF-8") + "=" + URLEncoder.encode(email_id, "UTF-8") + "&" + URLEncoder.encode("contact", "UTF-8") + "=" + URLEncoder.encode(contact, "UTF-8")+"&"+
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
            if (type.equals("updatepassword"))
            {
                String email_id=strings[1];
                String password=strings[2];
                Log.i("email", email_id);
                Log.i("password", password);
                url = new URL(baseurl + "updatepassword.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                outputStream = connection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                String postdata = URLEncoder.encode("email_id", "UTF-8") + "=" + URLEncoder.encode(email_id, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")+"&"+
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
            if (type.equals("updateaddress"))
            {
                String email = strings[1];
                String address = strings[2];
                String pincode = strings[3];
                url = new URL(baseurl + "updateaddress.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                outputStream = connection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                String postdata = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&" + URLEncoder.encode("pincode", "UTF-8") + "=" + URLEncoder.encode(pincode, "UTF-8")+"&"+
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
            if (type.equals("buynow"))
            {
                String email_id=strings[1];
                String prod_id=strings[2];
                Log.i("email", email_id);
                Log.i("password", prod_id);
                url = new URL(baseurl + "buynow.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                outputStream = connection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                String postdata = URLEncoder.encode("eid", "UTF-8") + "=" + URLEncoder.encode(email_id, "UTF-8") + "&" + URLEncoder.encode("pid", "UTF-8") + "=" + URLEncoder.encode(prod_id, "UTF-8")+"&"+
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
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (ProtocolException e)
        {
            e.printStackTrace();
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
        if(s.equals("Sign Up Successfull"))
        {
            Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context ,RentItLogIn.class);
            context.startActivity(intent);
        }
        else if(s.equals("Login Successfull"))
        {

            Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
            sharedPreferences= (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(context);
            sharedPreferences.edit().putString("user",email_id).commit();

            Intent intent=new Intent(context ,Home.class);
            context.startActivity(intent);

        }
        else if (s.equals("Product Added"))
        {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, Home.class);
            context.startActivity(intent);
        }
        else if (s.equals("Contact Changed"))
        {
             Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
        else if (s.equals("Password Changed"))
        {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
        else if (s.equals("Contact Changed"))
        {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
        else if (s.equals("Password Changed"))
        {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
        else if (s.equals("Address Updated"))
        {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
        else if (s.equals("Order Confirmed"))
        {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }

    }
}

