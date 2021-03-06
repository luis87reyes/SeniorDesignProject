package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Adapters.RecommendationRecyclerAdapter;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Recipe;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.R;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.ViewModels.HomeViewModel;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements RecommendationRecyclerAdapter.ItemClickListener {

    private TextView mTextMessage;
    private HomeViewModel model;
    private SuperRecyclerView recommendationRecyclerView;
    private RecommendationRecyclerAdapter recommendationRecyclerAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_search);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_search);
                    intent = new Intent(HomeActivity.this, RecipeSearchActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_pantry);
                    intent = new Intent(HomeActivity.this, PantryManagerActivity.class);
                    startActivity(intent);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        model = new HomeViewModel();

        mTextMessage = (TextView) findViewById(R.id.message);
        recommendationRecyclerView = (SuperRecyclerView) findViewById(R.id.recommendationRecyclerView);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        recommendationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recommendationRecyclerAdapter = new RecommendationRecyclerAdapter(this, model.recommendRecipes());
        recommendationRecyclerAdapter.setOnClickListener(this);
        recommendationRecyclerView.setAdapter(recommendationRecyclerAdapter);
        recommendationRecyclerAdapter.notifyDataSetChanged();
    }


    @Override
    public void onResume()
    {
        super.onResume();

        recommendationRecyclerAdapter = new RecommendationRecyclerAdapter(this, model.recommendRecipes());
        recommendationRecyclerAdapter.setOnClickListener(this);
        recommendationRecyclerView.setAdapter(recommendationRecyclerAdapter);
        recommendationRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
