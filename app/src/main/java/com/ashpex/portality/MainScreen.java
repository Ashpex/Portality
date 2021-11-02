package com.ashpex.portality;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ashpex.portality.fragment.ForumFragment;
import com.ashpex.portality.fragment.SignUpCourseFragment;
import com.ashpex.portality.fragment.TaskFragment;
import com.ashpex.portality.fragment.ProfileFragment;
import com.google.android.material.navigation.NavigationView;


public class MainScreen extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private androidx.appcompat.widget.Toolbar toolBarUser;
    private NavigationView navigationView;
    private FrameLayout frMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        mappingControls();
        eventToolBar();


    }


    private void eventToolBar() {
        setSupportActionBar(toolBarUser);
        ActionBar actionBar = getSupportActionBar();


        toolBarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                eventClickNav(item);
                return true;
            }
        });

    }

    private void eventClickNav(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()){
            case R.id.menuHome:{
                FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frMain, new ForumFragment());
                transaction.commit();
                drawerLayout.closeDrawers();
                return;
            }
            case R.id.menuTask:{
                FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frMain, new TaskFragment());
                transaction.commit();
                drawerLayout.closeDrawers();
                return;
            }
            case R.id.menuCourseSignUp:{
                FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frMain, new SignUpCourseFragment());
                transaction.commit();
                drawerLayout.closeDrawers();
                return;
            }

            case R.id.menuBotCourse:{
                FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frMain, new ProfileFragment());
                transaction.commit();
                drawerLayout.closeDrawers();
                return;
            }

            case R.id.menuExit:{
                drawerLayout.closeDrawers();
                setContentView(R.layout.activity_login);
            }

            default: {
                drawerLayout.closeDrawers();
                return;
            }
        }

    }

    private void mappingControls() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolBarUser = findViewById(R.id.toolBarUser);
        navigationView = findViewById(R.id.nav_view_main);
        frMain = findViewById(R.id.frMain);

    }
}