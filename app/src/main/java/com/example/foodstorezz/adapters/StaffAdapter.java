package com.example.foodstorezz.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodstorezz.R;
import com.example.foodstorezz.database.Staff;

import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder> {


    private List<Staff> mListStaff;
    private IClickItemStaff iClickItemStaff;
    public interface IClickItemStaff {
        void getDetailStaff(Staff staff);
    }

    public StaffAdapter(IClickItemStaff iClickItemStaff) {
        this.iClickItemStaff = iClickItemStaff;
    }
    public void setData(List<Staff> staff) {
        this.mListStaff = staff;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staff, parent, false);
        return new StaffViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {
        Staff staff = mListStaff.get(position);
        if (staff == null)
            return;
        holder.tvStaffFullName.setText(staff.getFullname());
        holder.tvStaffPhoneNumber.setText(staff.getPhonenumber());

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemStaff.getDetailStaff(staff);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListStaff != null){
            return mListStaff.size();
        }
        return 0;
    }

    public class StaffViewHolder extends RecyclerView.ViewHolder{
        private TextView tvStaffFullName, tvStaffPhoneNumber;
        private Button btnDetail;
        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStaffFullName = itemView.findViewById(R.id.tv_staff_full_name);
            tvStaffPhoneNumber = itemView.findViewById(R.id.tv_staff_phone_number);
            btnDetail = itemView.findViewById(R.id.btn_staff_detail);
        }
    }
}