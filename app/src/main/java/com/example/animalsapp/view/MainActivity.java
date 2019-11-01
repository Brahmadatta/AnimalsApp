package com.example.animalsapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.animalsapp.R;

public class MainActivity extends AppCompatActivity {

    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavController = Navigation.findNavController(this,R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this,mNavController);

    }

    //having the back icon with this code.

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(mNavController,(DrawerLayout) null);
    }
}
