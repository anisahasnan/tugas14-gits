package com.example.tugas14_anisahasna.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas14_anisahasna.MainActivity;
import com.example.tugas14_anisahasna.R;
import com.example.tugas14_anisahasna.helper.SharedPref;
import com.example.tugas14_anisahasna.model.ResponseModel;
import com.example.tugas14_anisahasna.rest.APIHandler;
import com.example.tugas14_anisahasna.rest.ConfigAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText username_input,password_input;
    TextView register;
    Button btnLogin;
    Vibrator v;
    private String TAG = "retrofit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_input = findViewById(R.id.username);
        password_input = findViewById(R.id.password);
        register = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btn_login);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUserData();
            }
        });

        //when someone clicks on login
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void validateUserData() {

        //first getting the values
        final String username = username_input.getText().toString();
        final String password = password_input.getText().toString();

        //checking if username is empty
        if (TextUtils.isEmpty(username)) {
            username_input.setError("Please enter your username");
            username_input.requestFocus();
            // Vibrate for 100 milliseconds
            v.vibrate(100);
            btnLogin.setEnabled(true);
            return;
        }
        //checking if password is empty
        if (TextUtils.isEmpty(password)) {
            password_input.setError("Please enter your password");
            password_input.requestFocus();
            //Vibrate for 100 milliseconds
            v.vibrate(100);
            btnLogin.setEnabled(true);
            return;
        }

        //Login User if everything is fine
        loginUser(username, password);
    }

    private void loginUser(String username, String password) {

        //making api call
        APIHandler apiHandler = ConfigAPI.getApiHandler();
        apiHandler.login(username, password)
                .enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call,
                                           Response<ResponseModel> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: SUKSES > " + response.body());
//                            ResponseModel responseModel = new ResponseModel();
//                            responseModel.setMessage("Berhasil Login");
//                            responseModel.setError(false);
                            Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            if(!response.body().isError()){
                                SharedPref.getInstance(LoginActivity.this).storeUserName(username);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                        Toast.makeText(LoginActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
}