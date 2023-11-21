package com.backcoffer.androidphpmysql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    TextView tv_username, tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        if(!SharedPrefManager.getInstance(this).isLoggedIn())
        {
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
        }

        tv_username = findViewById(R.id.profile_username);
        tv_email = findViewById(R.id.profile_email);

        tv_email.setText(SharedPrefManager.getInstance(this).getUserEmail());
        tv_username.setText(SharedPrefManager.getInstance(this).getUsername());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.menuSettings:
                Toast.makeText(getApplicationContext(), "You clicked settings", Toast.LENGTH_LONG).show();
                break;
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        }
        return true;
    }
}