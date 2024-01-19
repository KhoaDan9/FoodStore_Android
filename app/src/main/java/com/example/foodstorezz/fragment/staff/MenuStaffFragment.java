package com.example.foodstorezz.fragment.staff;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodstorezz.R;
import com.example.foodstorezz.adapters.ProductStaffAdapter;
import com.example.foodstorezz.adapters.ProductTypeAdapter;
import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Product;
import com.example.foodstorezz.model.Cart;
import com.example.foodstorezz.model.ProductType;

import java.util.ArrayList;
import java.util.List;

public class MenuStaffFragment extends Fragment {
    private RecyclerView rcvMenuStaff;
    private Button btnGoToCart;
    private EditText edtSearch;
    private ImageView searchIcon;
    private Spinner spnProductType;
    private ProductStaffAdapter productStaffAdapter;
    private ProductTypeAdapter productTypeAdapter;
    private List<Product> mListProduct;
    private String searchProduct, getType;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_staff, container, false);
        initUi(view);

        productStaffAdapter = new ProductStaffAdapter();

        productTypeAdapter = new ProductTypeAdapter(requireContext(), R.layout.item_product_type_selected, getListProductType());
        spnProductType.setAdapter(productTypeAdapter);

        spnProductType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProductType type = productTypeAdapter.getItem(position);
                getType = type.getType();
                loadDataBySearchAndType(searchProduct, getType);
            };

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadData();

        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),2);
        rcvMenuStaff.setLayoutManager(gridLayoutManager);
        rcvMenuStaff.setAdapter(productStaffAdapter);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCart();
            }
        });

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchProduct();
                }
                return false;
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchProduct();
            }
        });

        return view;
    }

    private void loadData(){
        mListProduct = FoodStoreDatabase.getInstance(requireContext()).productDAO().getAllProduct();
        productStaffAdapter.setData(mListProduct);
    }
    private void initUi(View view){
        rcvMenuStaff = view.findViewById(R.id.rcv_menu_staff);
        btnGoToCart = view.findViewById(R.id.btn_go_to_cart);
        edtSearch = view.findViewById(R.id.edt_search_bar_staff);
        searchIcon = view.findViewById(R.id.iv_search_icon_staff);
        spnProductType = view.findViewById(R.id.spn_product_type);

        searchProduct = edtSearch.getText().toString().trim();
    }

    private void goToCart() {
        CartStaffFragment cartStaffFragment = new CartStaffFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame_staff, cartStaffFragment)
                .addToBackStack(null)
                .commit();
    }
    public void searchProduct(){
        searchProduct = edtSearch.getText().toString().trim();
        loadDataBySearchAndType(searchProduct, getType);
        hideSoftKeyBoard();
    }
    public void loadDataBySearchAndType(String search, String type) {
        if(type.equals("Tất cả") )
            mListProduct = FoodStoreDatabase.getInstance(requireContext()).productDAO().searchProduct(search);
        else
            mListProduct = FoodStoreDatabase.getInstance(requireContext()).productDAO().searchProductWithType(search, type);
        productStaffAdapter.setData(mListProduct);
    }
    public void hideSoftKeyBoard() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(requireContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private List<ProductType> getListProductType() {
        List<ProductType> list = new ArrayList<>();
        list.add(new ProductType("Tất cả"));
        List<String> listStr = FoodStoreDatabase.getInstance(requireContext()).productDAO().getAllType();
        for (String type: listStr) {
            list.add(new ProductType(type));
        }
        return list;
    }
}
