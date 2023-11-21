package com.backcoffer.androidphpmysql;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText et_username, et_password;
    AppCompatButton btn_login;
    TextView tv_register;
    ProgressDialog pd_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
            return;
        }

        et_username = findViewById(R.id.login_username);
        et_password = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);
        pd_progress = new ProgressDialog(this);
        pd_progress.setMessage("Please wait...");



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void userLogin(){
        final String username = et_username.getText().toString().trim();
        final String password = et_password.getText().toString().trim();

        pd_progress.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd_progress.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(!jsonObject.getBoolean("error"))
                            {
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("username"),
                                        jsonObject.getString("email")
                                );
                                startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd_progress.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

}