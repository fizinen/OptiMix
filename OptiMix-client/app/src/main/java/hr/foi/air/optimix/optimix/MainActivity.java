package hr.foi.air.optimix.optimix;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements FragmentIntentInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_main_view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new StorageFragment());
        adapter.addFragment(new AnalysisFragment());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
<<<<<<< HEAD

=======
        
>>>>>>> eed3de0e1773a7ac958aadcfb479337957649e03
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_bubble_chart_white_36dp);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dvr_white_36dp);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_show_chart_white_36dp);
    }

    @OnClick(R.id.floatingSettingsButton)
    public void settingsButtonClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void startIntentFromFragment(Intent intent) {

        startActivity(intent);

    }
}