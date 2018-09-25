package com.example.wsdp2;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.wsdp2.adapter.ViewPagerAdapter;
import com.example.wsdp2.fragment.BaseFragment;
import com.example.wsdp2.fragment.ProposalFragment;
import com.example.wsdp2.fragment.WeatherFragment;
import com.example.wsdp2.helper.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager ;
    private MenuItem menuItem ;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        bottomNavigationView =(BottomNavigationView)findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_weather:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.item_data:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.item_proposal:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem!=null){
                    menuItem.setChecked(false);
                }else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }

                menuItem =bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new WeatherFragment());
        adapter.addFragment(BaseFragment.newInstance("数据"));
        adapter.addFragment(new ProposalFragment());
        viewPager.setAdapter(adapter);
    }

}
