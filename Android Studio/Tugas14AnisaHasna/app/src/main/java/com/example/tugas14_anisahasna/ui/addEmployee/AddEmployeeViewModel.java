package com.example.tugas14_anisahasna.ui.addEmployee;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tugas14_anisahasna.MainActivity;
import com.example.tugas14_anisahasna.model.ResponseModel;
import com.example.tugas14_anisahasna.rest.APIHandler;
import com.example.tugas14_anisahasna.rest.ConfigAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEmployeeViewModel extends ViewModel {

    private String TAG = "retrofit";
    private MutableLiveData<ResponseModel> responseModelMutableLiveData;
    public LiveData<ResponseModel> postDataEmployees(
            String nama, String sex, String email, String phone, String address, String division) {
        if (responseModelMutableLiveData == null) {
            responseModelMutableLiveData = new MutableLiveData<>();
            postDataEmployee(nama, sex, email, phone, address, division);
        }
        return responseModelMutableLiveData;
    }
    private void postDataEmployee(String nama, String sex, String email, String phone, String address, String division) {
        APIHandler apiHandler = ConfigAPI.getApiHandler();
        apiHandler.postAddEmployee(nama, sex, email, phone, address, division)
                .enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call,
                                           Response<ResponseModel> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: SUKSES > " + response.body());
                            ResponseModel responseModel = new ResponseModel();
                            responseModel.setMessage("Berhasil Menambah Employee");
                            responseModelMutableLiveData.setValue(responseModel);
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }
}