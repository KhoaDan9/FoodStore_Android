package com.example.foodstorezz.fragment.admin;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodstorezz.R;
import com.example.foodstorezz.adapters.ProductAdapter;
import com.example.foodstorezz.adapters.ProductTypeAdapter;
import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Product;
import com.example.foodstorezz.model.ProductDetail;
import com.example.foodstorezz.model.ProductType;
import com.example.foodstorezz.my_interface.IClickItemProductAdminListener;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    private Button btnAddProduct;
    private EditText edtSearch;
    private ImageView searchIcon;
    private Spinner spnProductType;
    private RecyclerView rcvProduct;
    private ProductAdapter productAdapter;
    private ProductTypeAdapter productTypeAdapter;
    private List<Product> mListProduct;
    private String searchProduct, getType;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        initUi(view);
        productAdapter = new ProductAdapter(new IClickItemProductAdminListener() {
            @Override
            public void onClickItemProductAdmin(Product product) {
                itemProductOnClick(product);
            }
        });

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

        RecyclerView.LayoutManager linearLayoutManager = new GridLayoutManager(requireContext(),2);
        rcvProduct.setLayoutManager(linearLayoutManager);
        rcvProduct.setAdapter(productAdapter);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddProduct();
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

    public void initUi(View view) {
        btnAddProduct = view.findViewById(R.id.btn_add_product);
        rcvProduct = view.findViewById(R.id.rcv_menu);
        edtSearch = view.findViewById(R.id.edt_search_bar);
        searchIcon = view.findViewById(R.id.iv_search_icon);
        spnProductType = view.findViewById(R.id.spn_product_type);

        searchProduct = edtSearch.getText().toString().trim();
    }

    public void loadData() {
        mListProduct = FoodStoreDatabase.getInstance(requireContext()).productDAO().getAllProduct();
        productAdapter.setData(mListProduct);
    }

    public void loadDataBySearchAndType(String search, String type) {
        if(type.equals("Tất cả") )
            mListProduct = FoodStoreDatabase.getInstance(requireContext()).productDAO().searchProduct(search);
        else
            mListProduct = FoodStoreDatabase.getInstance(requireContext()).productDAO().searchProductWithType(search, type);
        productAdapter.setData(mListProduct);
    }
    public void searchProduct(){
        searchProduct = edtSearch.getText().toString().trim();
        loadDataBySearchAndType(searchProduct, getType);
        hideSoftKeyBoard();
    }
    public void hideSoftKeyBoard() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(requireContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }


    public void goToAddProduct() {
        AddProductFragment addProductFragment = new AddProductFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, addProductFragment)
                .addToBackStack(null)
                .commit();
    }

    public void itemProductOnClick(Product product){
        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        ProductDetail.product = product;
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, productDetailFragment)
                .addToBackStack(null)
                .commit();
//        productDetailFragment.setData(product);

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
