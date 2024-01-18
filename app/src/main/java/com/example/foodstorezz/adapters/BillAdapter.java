package com.example.foodstorezz.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodstorezz.R;
import com.example.foodstorezz.database.Bill;
import com.example.foodstorezz.fragment.staff.PayStaffFragment;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> {
    private List<Bill> mListBill;
    private IClickItemBill iClickItemBill;

    public interface IClickItemBill{
        void getDetailBill(Bill bill);
    }

    public BillAdapter(IClickItemBill iClickItemBill){
        this.iClickItemBill = iClickItemBill;
    }

    public void setData(List<Bill> list){
        this.mListBill = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill_id,parent,false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        Bill bill = mListBill.get(position);
        if (bill == null)
            return;
        holder.tvBillId.setText(String.valueOf(bill.getId()));
        holder.tvStaffName.setText(bill.getStaffName());
        holder.tvDate.setText(bill.getDate());
        holder.tvAllPrice.setText(String.valueOf(bill.getTotalBill()));
        holder.btnBillDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemBill.getDetailBill(bill);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (mListBill != null)
            return mListBill.size();
        return 0;
    }

    public class BillViewHolder extends RecyclerView.ViewHolder{
        private TextView tvBillId, tvStaffName, tvDate, tvAllPrice;
        private Button btnBillDetail;
        public BillViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBillId = itemView.findViewById(R.id.tv_bill_id2);
            tvStaffName = itemView.findViewById(R.id.tv_staff_name2);
            tvDate = itemView.findViewById(R.id.tv_date2);
            tvAllPrice = itemView.findViewById(R.id.tv_all_price2);
            btnBillDetail = itemView.findViewById(R.id.btn_bill_detail);
        }
    }

}
