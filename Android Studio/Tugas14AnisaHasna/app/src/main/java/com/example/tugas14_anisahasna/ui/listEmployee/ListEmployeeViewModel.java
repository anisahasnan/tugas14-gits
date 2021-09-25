package com.example.tugas14_anisahasna.ui.listEmployee;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tugas14_anisahasna.model.EmployeeModel;
import com.example.tugas14_anisahasna.rest.APIHandler;
import com.example.tugas14_anisahasna.rest.ConfigAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEmployeeViewModel extends ViewModel {

    private String TAG = "retrofit";
    private MutableLiveData<List<EmployeeModel>> listEmployeelMutableLiveData;

    public LiveData<List<EmployeeModel>> getEmployee(){
        if (listEmployeelMutableLiveData == null){
            listEmployeelMutableLiveData = new MutableLiveData<List<EmployeeModel>>();
            loadDataEmployee();
        }
        return listEmployeelMutableLiveData;
    }
    private void loadDataEmployee() {
        APIHandler apiHandler = ConfigAPI.getApiHandler();
        apiHandler.getAllEmployee()
                .enqueue(new Callback<List<EmployeeModel>>() {
                    @Override
                    public void onResponse(Call<List<EmployeeModel>> call,
                                           Response<List<EmployeeModel>> response) {
                        if (response.isSuccessful()) {
                            listEmployeelMutableLiveData.setValue(response.body());

                            Log.d(TAG, "onResponse: " + response.body());

                        }
                    }
                    @Override
                    public void onFailure(Call<List<EmployeeModel>> call, Throwable t)
                    {
                        Log.d(TAG, "onFailure: " + t.getLocalizedMessage() + "|" +
                                t.getMessage());
                    }
                });
    }
}