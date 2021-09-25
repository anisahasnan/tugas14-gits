package com.example.tugas14_anisahasna.ui.listEmployee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas14_anisahasna.R;
import com.example.tugas14_anisahasna.adapter.EmployeeAdapter;
import com.example.tugas14_anisahasna.model.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

public class ListEmployeeFragment extends Fragment {

    private String TAG = "mvvm";
    private RecyclerView rv;
    private EmployeeAdapter employeeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_list_employee, container, false);

        List<EmployeeModel> listEmployee = new ArrayList<>();
        rv = root.findViewById(R.id.rv);
        rv.setAdapter(employeeAdapter);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        ListEmployeeViewModel listEmployeeViewModel = ViewModelProviders.of(this).get(ListEmployeeViewModel.class);
        listEmployeeViewModel.getEmployee().observe(getViewLifecycleOwner(), new Observer<List<EmployeeModel>>() {
                    @Override
                    public void onChanged(List<EmployeeModel> listEmployee) {
                        employeeAdapter = new EmployeeAdapter(getActivity(), listEmployee);
                        rv.setAdapter(employeeAdapter);
                    }
                });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}