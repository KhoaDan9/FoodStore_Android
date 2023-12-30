package com.example.foodstorezz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodstorezz.fragment.admin.AddStaffFragment;
import com.example.foodstorezz.fragment.admin.StaffManagementFragment;
import com.example.foodstorezz.fragment.staff.MenuStaffFragment;
import com.google.android.material.navigation.NavigationView;

public class StaffActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;

    private static final int FRAGMENT_MENU = 0;
    private static final int FRAGMENT_BILL = 1;
    private static final int FRAGMENT_DETAIL = 2;

    private int mCurrentFragment = FRAGMENT_MENU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        Toolbar toolbar = findViewById(R.id.toolbar_staff);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout_staff);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view_staff);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new MenuStaffFragment());
        navigationView.getMenu().findItem(R.id.nav_menu_staff).setChecked(true);
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame_staff, fragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_menu_staff){
            if(mCurrentFragment != FRAGMENT_MENU){
                replaceFragment(new MenuStaffFragment());
                mCurrentFragment = FRAGMENT_MENU;
            }
        }
        else if (id == R.id.nav_bill_staff){
            if(mCurrentFragment != FRAGMENT_BILL){
                replaceFragment(new AddStaffFragment());
                mCurrentFragment = FRAGMENT_BILL;
            }
        }
        else if (id == R.id.nav_detail_staff){
            if(mCurrentFragment != FRAGMENT_DETAIL){
                replaceFragment(new StaffManagementFragment());
                mCurrentFragment = FRAGMENT_DETAIL;
            }
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}