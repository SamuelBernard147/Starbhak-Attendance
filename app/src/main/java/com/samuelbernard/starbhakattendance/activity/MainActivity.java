package com.samuelbernard.starbhakattendance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.samuelbernard.starbhakattendance.R;
import com.samuelbernard.starbhakattendance.adapter.TabFragmentAdapter;
import com.samuelbernard.starbhakattendance.fragment.DashboardFragment;
import com.samuelbernard.starbhakattendance.fragment.ProfileFragment;
import com.samuelbernard.starbhakattendance.helper.CustomViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.vp_order)
    CustomViewPager vpOrder;
    @BindView(R.id.space_nav)
    SpaceNavigationView spaceNav;

    private TabFragmentAdapter adapter;
    private DashboardFragment dashboardFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dashboardFragment = new DashboardFragment();
        profileFragment = new ProfileFragment();

        spaceNav.initWithSaveInstanceState(savedInstanceState);
        initspaceNav();

        setupAdapter();

        spaceNav.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Log.d("onCentreButtonClick ", "onCentreButtonClick");
                spaceNav.shouldShowFullBadgeText(true);
                startActivity(new Intent(MainActivity.this, ScannerActivity.class));
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                vpOrder.setCurrentItem(itemIndex);
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Log.d("onItemReselected ", "" + itemIndex + " " + itemName);
            }
        });
    }

    private void initspaceNav() {
        spaceNav.addSpaceItem(new SpaceItem("Dashboard", R.drawable.ic_dashboard_24dp));
        spaceNav.addSpaceItem(new SpaceItem("Profile", R.drawable.ic_person_black_24dp));
        spaceNav.setCentreButtonIcon(R.drawable.qrcode_scan);
        spaceNav.shouldShowFullBadgeText(true);
        spaceNav.setCentreButtonIconColorFilterEnabled(true);
    }

    // Setup adapter for viewpager
    private void setupAdapter() {
        adapter = new TabFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(dashboardFragment, getResources().getString(R.string.dashboard));
        adapter.addFragment(profileFragment, getResources().getString(R.string.profile));
        vpOrder.setAdapter(adapter);
        vpOrder.disableScroll(true);
        vpOrder.setCurrentItem(0);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        spaceNav.onSaveInstanceState(outState);
    }
}