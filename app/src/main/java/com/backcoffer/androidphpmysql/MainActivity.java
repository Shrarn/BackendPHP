package com.backcoffer.androidphpmysql;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    AppCompatButton btn_register;
    EditText et_username, et_password, et_email;
    TextView tv_signin;
    ProgressDialog pd_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            return;
        }

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_email = findViewById(R.id.et_email);

        btn_register = findViewById(R.id.btn_register);

        tv_signin = findViewById(R.id.tv_signin);



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        }); //onClickListener for the "Register Button".

        tv_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

    }

    /*Function to register users*/
    private void registerUser() {
        pd_progress = new ProgressDialog(this);
        final String email = et_email.getText().toString().trim();
        final String username = et_username.getText().toString().trim();
        final String password = et_password.getText().toString().trim();

        pd_progress.setMessage("Registering user...");
        pd_progress.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd_progress.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd_progress.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}