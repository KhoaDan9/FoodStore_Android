//package com.example.foodstorezz.adapters;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.foodstorezz.database.User;
//
//import java.util.List;
//
//public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
//    private List<User> mListUser;
//
//    public void setData(List<User> list) {
//        this.mListUser = list;
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
//        return new UserViewHolder(view);
//    }
//
//    public class UserViewHolder extends RecyclerView.ViewHolder {
//        private TextView tvUsername;
//        private TextView tvAddress;
//        private Button btnUpdate;
//        public UserViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            tvUsername = itemView.findViewById(R.id.tv_username);
//            tvAddress = itemView.findViewById(R.id.tv_address);
//            btnUpdate = itemView.findViewById(R.id.btn_update);
//        }
//    }
//}
