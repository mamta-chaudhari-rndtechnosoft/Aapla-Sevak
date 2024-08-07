package com.vinodmapari.aaplasevak.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.VolleySingleton;
import com.wang.avi.AVLoadingIndicatorView;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    Button button_login;
    EditText edit_user,edit_password;
    AVLoadingIndicatorView progress_login;
    private Vibrator vibrator;
    private String username="";
    private String password="";
    private RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_login = (Button)findViewById(R.id.btnLogin);
        edit_user = (EditText)findViewById(R.id.tvusername);
        edit_password = (EditText)findViewById(R.id.tvpassword);

        progress_login = findViewById(R.id.progress_login);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        button_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                validateUserData();

            }
        });

    }

    private void validateUserData()
    {
        final String user = edit_user.getText().toString();
        final String password = edit_password.getText().toString();

        //checking if email is empty
        if (TextUtils.isEmpty(user)) {
            edit_user.setError("Please enter user name");
            edit_user.requestFocus();
            // Vibrate for 100 milliseconds
            vibrator.vibrate(100);
            //    button_login.setEnabled(true);
            return;
        }
        //checking if password is empty
        if (TextUtils.isEmpty(password)) {
            edit_password.setError("Please enter your password");
            edit_password.requestFocus();
            //Vibrate for 100 milliseconds
            vibrator.vibrate(100);
            //      buttonLogin.setEnabled(true);
            return;
        }

        loginUser();


    }

    private void loginUser() {
        progress_login.setVisibility(View.VISIBLE);

        final String userName = edit_user.getText().toString().trim();
        final String password = edit_password.getText().toString().trim();
        String params = "&username="+userName+"&password="+password;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.loginURL+params,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        progress_login.setVisibility(View.GONE);

                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("LOGGED_IN_USER");
                            if (jsonObject1.getString("error").equals("false"))
                            {
                                final String id = jsonObject1.getString("id");
                                final String name = jsonObject1.getString("username");
                                final String password = jsonObject1.getString("password");

                                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsUser" , Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("id",id);
                                editor.putString("username",name);
                                editor.putString("password",password);

                                editor.commit();

                                Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "user name or password is incorrect", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progress_login.setVisibility(View.GONE);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //          Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                        progress_login.setVisibility(View.GONE);
                    }
                });


        VolleySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
    }
}