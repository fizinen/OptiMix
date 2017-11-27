package hr.foi.air.optimix.optimix;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity implements FragmentIntentInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_settings_view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new UserListActivity());
        adapter.addFragment(new ReceiptListAndCreationActivity());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_account_circle_white_36dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_toc_white_36dp);
    }

    @Override
    public void startIntentFromFragment(Intent i) {
        startActivity(i);
    }


}
