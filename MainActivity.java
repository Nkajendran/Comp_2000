package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText User;
EditText Password;
String[] EmployeeArray = new String[10];
JSONObject Employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
    }

    public void GoToDetails(View view)
    {
        setContentView(R.layout.userdetails);
    }
    public void GoToBooking(View view)
    {
        setContentView(R.layout.bookingsystem);
    }
    public void GoToEdit(View view)
    {
        setContentView(R.layout.editdetails);
    }
    public void GoToNotification(View view)
    {
        setContentView(R.layout.bookingapplication);
    }
    public void GoToBack(View view) {setContentView(R.layout.activity_main);}
    public void GoToStart(View view) {setContentView(R.layout.admin);}
    public void GoToMenu(View view) {setContentView(R.layout.admin);}
    public void GoToBookingSuccess(View view) {setContentView(R.layout.bookingsystem_success);}
    public void GoToAccept(View view) {
        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();}
    public void GoToMain(View view){setContentView(R.layout.admin);}
    public void GoToAdd(View View) {
        setContentView(R.layout.add_details);
        JSONObject Details = new JSONObject();
        try{
            Details.put("id",47);
            Details.put("surname","Test1");
            Details.put("forename","TestTwo");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }   RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/employees";
        JsonObjectRequest DetailsAdd = new JsonObjectRequest (Request.Method.POST, url, Details, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject Response) {
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        requestQueue.add(DetailsAdd);
    }
    public void GoToAdmin(View View) {setContentView(R.layout.admindetails);}
    public void GoToSuccess(View view) {setContentView(R.layout.bookingaccepted);}
    public void GoTOLists(View view) {setContentView(R.layout.employees);
       ListView Employeelist = findViewById(R.id.list);
        ArrayList<String> Employees = new ArrayList<>();
        for (int i = 0; i < EmployeeArray.length; i++)
        { Employees.add(EmployeeArray[i]);}
        ArrayAdapter<String> Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Employees);
        Employeelist.setAdapter(Adapter);
       }

    public void GoToLogin(View view) {



                RequestQueue EmployeeQueue = Volley.newRequestQueue(MainActivity.this);
                User = findViewById(R.id.Username);
                Password = findViewById(R.id.Password);
                if (User.getText().toString().equals("User") && Password.getText().toString().equals("Password")) {
                    setContentView(R.layout.activity_main);
                }
                if (User.getText().toString().equals("admin") && Password.getText().toString().equals("Password")) {
                    setContentView(R.layout.admin);
                    String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/employees";
                    JsonArrayRequest EmployeeRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                for (int i = 0; i < EmployeeArray.length; i++) {
                                    Employee = response.getJSONObject(i);
                                    String UserString = Employee.getString("forename");
                                    EmployeeArray[i] = UserString;

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }

                    });
                    EmployeeQueue.add(EmployeeRequest);
                }

        }
    public void PutDataToAPI(View view) {
         EditText Fortext = findViewById(R.id.editTextTextPersonName6);
         EditText Lasttext = findViewById(R.id.editTextTextPersonName7);
         EditText idtext        = findViewById(R.id.editTextNumber);

       String forname = Fortext.getText().toString();
       String surname = Lasttext.getText().toString();
       int id = Integer.parseInt(idtext.getText().toString());
        String postUrl = "http://web.socem.plymouth.ac.uk/COMP2000/api/employees/" + id;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject postData = new JSONObject();
        try {
            postData.put("id",  id);
            postData.put("surname", surname);
            postData.put("forename", forname);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    public void DeleteDataFromAPI(View view) {
        EditText Fortext = findViewById(R.id.editTextTextPersonName6);
        EditText Lasttext = findViewById(R.id.editTextTextPersonName7);
        EditText idtext        = findViewById(R.id.editTextNumber);

        String forname = Fortext.getText().toString();
        String surname = Lasttext.getText().toString();
        int id = Integer.parseInt(idtext.getText().toString());
        String postUrl = "http://web.socem.plymouth.ac.uk/COMP2000/api/employees/" + id;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject postData = new JSONObject();
        try {
            postData.put("id",  id);
            postData.put("surname", surname);
            postData.put("forename", forname);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}







