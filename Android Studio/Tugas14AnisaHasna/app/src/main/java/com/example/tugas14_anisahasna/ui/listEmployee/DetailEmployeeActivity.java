package com.example.tugas14_anisahasna.ui.listEmployee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tugas14_anisahasna.MainActivity;
import com.example.tugas14_anisahasna.R;
import com.example.tugas14_anisahasna.UpdateEmployeeActivity;
import com.example.tugas14_anisahasna.helper.Config;
import com.example.tugas14_anisahasna.model.ResponseModel;
import com.example.tugas14_anisahasna.rest.APIHandler;
import com.example.tugas14_anisahasna.rest.ConfigAPI;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_employee);
        Objects.requireNonNull(getSupportActionBar()).hide();

        ImageView imageEmployee = findViewById(R.id.image_employee);
        TextView namaEmployee = findViewById(R.id.nama_employee);
        TextView sexEmployee = findViewById(R.id.sex_employee);
        TextView emailEmployee = findViewById(R.id.email_employee);
        TextView phoneEmployee = findViewById(R.id.phone_employee);
        TextView addressEmployee = findViewById(R.id.address_employee);
        TextView divisionEmployee = findViewById(R.id.division_employee);
        ImageView btnDeleteEmployee = findViewById(R.id.btn_delete_employee);
        ImageView btnUpdateEmployee = findViewById(R.id.btn_update_employee);

        String name = getIntent().getStringExtra(Config.EMPLOYEE_NAME);
        String sex = getIntent().getStringExtra(Config.EMPLOYEE_SEX);
        String email = getIntent().getStringExtra(Config.EMPLOYEE_EMAIL);
        String phone = getIntent().getStringExtra(Config.EMPLOYEE_PHONE);
        String address = getIntent().getStringExtra(Config.EMPLOYEE_ADDRESS);
        String division = getIntent().getStringExtra(Config.EMPLOYEE_DIVISION);

        namaEmployee.setText(name);
        sexEmployee.setText(sex);
        emailEmployee.setText(email);
        phoneEmployee.setText(phone);
        addressEmployee.setText(address);
        divisionEmployee.setText(division);

        String id = getIntent().getStringExtra(Config.EMPLOYEE_ID);
        btnDeleteEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIHandler apiHandler = ConfigAPI.getApiHandler();
                apiHandler.postDeleteEmployee(id)
                        .enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(DetailEmployeeActivity.this, "Berhasil Menghapus Employee", Toast.LENGTH_SHORT).show();
                                    finishAffinity();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }
                            }
                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                Toast.makeText(DetailEmployeeActivity.this, "Gagal Delete"
                                        + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btnUpdateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailEmployeeActivity.this, UpdateEmployeeActivity.class);
                intent.putExtra(Config.EMPLOYEE_ID, id);
                intent.putExtra(Config.EMPLOYEE_NAME, name);
                intent.putExtra(Config.EMPLOYEE_SEX, sex);
                intent.putExtra(Config.EMPLOYEE_EMAIL, email);
                intent.putExtra(Config.EMPLOYEE_PHONE, phone);
                intent.putExtra(Config.EMPLOYEE_ADDRESS, address);
                intent.putExtra(Config.EMPLOYEE_DIVISION, division);
                startActivity(intent);
            }
        });
    }
}