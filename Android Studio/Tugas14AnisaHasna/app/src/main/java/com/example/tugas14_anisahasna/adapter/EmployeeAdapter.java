package com.example.tugas14_anisahasna.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tugas14_anisahasna.R;
import com.example.tugas14_anisahasna.helper.Config;
import com.example.tugas14_anisahasna.model.EmployeeModel;
import com.example.tugas14_anisahasna.ui.listEmployee.DetailEmployeeActivity;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private Context context;
    private List<EmployeeModel> listEmployee;

    public EmployeeAdapter(Context context, List<EmployeeModel> listEmployee) {
        this.context = context;
        this.listEmployee = listEmployee;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        holder.namaEmployee.setText(listEmployee.get(position).getName());
        holder.emailEmployee.setText(listEmployee.get(position).getEmail());
        holder.divisionEmployee.setText(listEmployee.get(position).getDivision());

        holder.cvEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailEmployeeActivity.class);
                intent.putExtra(Config.EMPLOYEE_ID,
                        String.valueOf(listEmployee.get(position).getId()));
                intent.putExtra(Config.EMPLOYEE_NAME,
                        listEmployee.get(position).getName());
                intent.putExtra(Config.EMPLOYEE_SEX,
                        listEmployee.get(position).getSex());
                intent.putExtra(Config.EMPLOYEE_EMAIL,
                        listEmployee.get(position).getEmail());
                intent.putExtra(Config.EMPLOYEE_PHONE,
                        listEmployee.get(position).getPhone());
                intent.putExtra(Config.EMPLOYEE_ADDRESS,
                        listEmployee.get(position).getAddress());
                intent.putExtra(Config.EMPLOYEE_DIVISION,
                        listEmployee.get(position).getDivision());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listEmployee.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private TextView namaEmployee, sexEmployee, emailEmployee, phoneEmployee, addressEmployee, divisionEmployee;
        private ImageView imageEmployee;
        private CardView cvEmployee;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            namaEmployee = itemView.findViewById(R.id.nama_employee);
            emailEmployee = itemView.findViewById(R.id.email_employee);
            divisionEmployee = itemView.findViewById(R.id.division_employee);
            imageEmployee = itemView.findViewById(R.id.image_employee);
            cvEmployee = itemView.findViewById(R.id.cv_employee);
        }
    }
}
