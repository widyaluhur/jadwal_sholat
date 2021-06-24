package com.example.jadwalsholat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.jadwalsholat.view.CommentFragment;
import com.example.jadwalsholat.view.TimeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private Fragment selectedFragment = new TimeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.main_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        loadFragment(selectedFragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.ic_time:
                selectedFragment = new TimeFragment();
                loadFragment(selectedFragment);
                break;

            case R.id.ic_comment:
                selectedFragment = new CommentFragment();
                loadFragment(selectedFragment);
                break;
        }

        return loadFragment(selectedFragment);
    }

    private boolean loadFragment(Fragment selectedFragment) {
        if (selectedFragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame,selectedFragment)
                    .commit();
            return true;
        }
        return false;
    }

}
