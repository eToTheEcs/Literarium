package com.example.com.literarium;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private Button loginButton;
    private EditText userName;
    private EditText password;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        //getActionBar().hide();

        //if user had already logged in, skip to the main activity
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);

        if(sharedPreferences.getInt(getString(R.string.user_id_setting), -1) != -1 &&
            sharedPreferences.getString(getString(R.string.user_token_setting), "") != "") {

            Intent skipToMain = new Intent(this, MainActivity.class);
            startActivity(skipToMain);
        }


        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetUserInput();
    }

    public void login(View v) {

        LoginTask loginTask = new LoginTask(this, userName.getText().toString().trim(), password.getText().toString().trim());
        loginTask.execute();
    }

    private void resetUserInput() {
        userName.setText("");
        password.setText("");
    }

    /**
     * redirects the to the mainActivity
     */
    public void handleLoginSuccess() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * incorrect credentials
     */
    public void handleLoginFailure() {

        Toast.makeText(this, "wrong credentials!", Toast.LENGTH_SHORT).show();
        resetUserInput();
    }
}
