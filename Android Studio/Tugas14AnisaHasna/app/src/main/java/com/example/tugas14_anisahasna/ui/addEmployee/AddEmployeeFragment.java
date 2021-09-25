package com.example.tugas14_anisahasna.ui.addEmployee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tugas14_anisahasna.R;
import com.example.tugas14_anisahasna.model.ResponseModel;

public class AddEmployeeFragment extends Fragment {

    private AddEmployeeViewModel addEmployeeViewModel;
    private EditText addNamaEmployee, addEmailEmployee, addPhoneEmployee, addAddressEmployee, addDivisionEmployee;
    private RadioGroup addSexEmployee;
    private String selectedSex;
    private Button btnSubmitAddEmployee;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addEmployeeViewModel =
                new ViewModelProvider(this).get(AddEmployeeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_add_employee, container, false);

        addNamaEmployee = root.findViewById(R.id.add_nama_employee);
        addEmailEmployee = root.findViewById(R.id.add_email_employee);
        addPhoneEmployee = root.findViewById(R.id.add_phone_employee);
        addAddressEmployee = root.findViewById(R.id.add_address_employee);
        addDivisionEmployee = root.findViewById(R.id.add_division_employee);

        addSexEmployee = root.findViewById(R.id.add_sex_employee);

        addSexEmployee.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch(checkedId) {
                    case R.id.add_sex_l_employee:
                        selectedSex = "L";
                        break;
                    case R.id.add_sex_p_employee:
                        selectedSex = "P";
                        break;
                }
            }
        });

        btnSubmitAddEmployee = root.findViewById(R.id.btn_submit_add_employee);
        btnSubmitAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmployeeViewModel.postDataEmployees(
                        addNamaEmployee.getText().toString().trim(),
                        selectedSex,
                        addEmailEmployee.getText().toString().trim(),
                        addPhoneEmployee.getText().toString().trim(),
                        addAddressEmployee.getText().toString().trim(),
                        addDivisionEmployee.getText().toString().trim()
                ).observe(getActivity(), responseModelMutableLiveData -> {
                    Toast.makeText(getActivity(), responseModelMutableLiveData.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}