package com.example.android.htc_test_task;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.htc_test_task.jsonentity.Company;
import com.example.android.htc_test_task.jsonentity.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String REQUEST = "http://www.mocky.io/v2/56fa31e0110000f920a72134";
    private final String TAG = "MainActivity";

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL(REQUEST);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            String response = IOUtils.toString(connection.getInputStream(), Charset.forName("UTF-8"));
            Gson gson = new GsonBuilder().create();
            JSONObject json = new JSONObject(response);
            Company htc = gson.fromJson(String.valueOf(json.getJSONObject("company")), Company.class);
            List<Employee> employees = htc.getEmployees();
            Collections.sort(employees);
            for (Employee employee: employees) {
                employee.sortSkills();
            }
            mListView = (ListView)findViewById(R.id.lv);
            ArrayAdapter<Employee> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
            mListView.setAdapter(adapter);
            Log.i(TAG, "OK");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(TAG, "MalformedURLException");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "IOException");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "JSONException");
        }
    }
}
