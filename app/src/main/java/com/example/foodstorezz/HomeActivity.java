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

import com.example.foodstorezz.fragment.AddStaffFragment;
import com.example.foodstorezz.fragment.HomeFragment;
import com.example.foodstorezz.fragment.StaffManagementFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_ADD_STAFF = 1;
    private static final int FRAGMENT_STAFF_MANAGEMENT = 2;

    private int mCurrentFragment = FRAGMENT_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            if(mCurrentFragment != FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                mCurrentFragment = FRAGMENT_HOME;
            }
        }
        else if (id == R.id.nav_add_staff){
            if(mCurrentFragment != FRAGMENT_ADD_STAFF){
                replaceFragment(new AddStaffFragment());
                mCurrentFragment = FRAGMENT_ADD_STAFF;
            }
        }
        else if (id == R.id.nav_staff_management){
            if(mCurrentFragment != FRAGMENT_STAFF_MANAGEMENT){
                replaceFragment(new StaffManagementFragment());
                mCurrentFragment = FRAGMENT_STAFF_MANAGEMENT;
            }
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

//    @Override
//    public void onBackPressed(){
//        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
//            mDrawerLayout.closeDrawer(GravityCompat.START);
//        }
//        else {
//            super.onBackPressed();
//        }
//    }
}