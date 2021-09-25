package com.example.tugas14_anisahasna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas14_anisahasna.helper.Config;
import com.example.tugas14_anisahasna.model.ResponseModel;
import com.example.tugas14_anisahasna.rest.APIHandler;
import com.example.tugas14_anisahasna.rest.ConfigAPI;
import com.example.tugas14_anisahasna.ui.addEmployee.AddEmployeeViewModel;
import com.example.tugas14_anisahasna.ui.listEmployee.DetailEmployeeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateEmployeeActivity extends AppCompatActivity {

    private EditText updateEmailEmployee, updatePhoneEmployee, updateAddressEmployee;
    private Button btnSubmitUpdateEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);

        updateEmailEmployee = findViewById(R.id.update_email_employee);
        updatePhoneEmployee = findViewById(R.id.update_phone_employee);
        updateAddressEmployee = findViewById(R.id.update_address_employee);

        btnSubmitUpdateEmployee = findViewById(R.id.btn_submit_update_employee);

        int id = Integer.parseInt(getIntent().getStringExtra(Config.EMPLOYEE_ID));
        String name = getIntent().getStringExtra(Config.EMPLOYEE_NAME);
        String sex = getIntent().getStringExtra(Config.EMPLOYEE_SEX);
        String email = getIntent().getStringExtra(Config.EMPLOYEE_EMAIL);
        String phone = getIntent().getStringExtra(Config.EMPLOYEE_PHONE);
        String address = getIntent().getStringExtra(Config.EMPLOYEE_ADDRESS);
        String division = getIntent().getStringExtra(Config.EMPLOYEE_DIVISION);

        updateEmailEmployee.setText(email);
        updatePhoneEmployee.setText(phone);
        updateAddressEmployee.setText(address);

        btnSubmitUpdateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIHandler apiHandler = ConfigAPI.getApiHandler();
                apiHandler.postUpdateEmployee(
                        id,
                        name,
                        sex,
                        updateEmailEmployee.getText().toString().trim(),
                        updatePhoneEmployee.getText().toString().trim(),
                        updateAddressEmployee.getText().toString().trim(),
                       division)
                        .enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call,
                                                   Response<ResponseModel> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(UpdateEmployeeActivity.this, "Berhasil Update Kontak Employee", Toast.LENGTH_SHORT).show();
                                    finishAffinity();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }
                            }
                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                Toast.makeText(UpdateEmployeeActivity.this, "" + t.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}