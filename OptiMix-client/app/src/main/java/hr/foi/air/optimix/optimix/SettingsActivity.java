package hr.foi.air.optimix.optimix;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


/**
 * Settings activity that extends AppCompatActivity and implements View.FragmentIntentInterface
 * Adding recipes and user activity. This activity makes two fragments in tabs from which user can and recipe.
 * Admin can add user from this activity.
 */
public class SettingsActivity extends AppCompatActivity implements FragmentIntentInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_settings_view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new ReceiptListAndCreationActivity());
        adapter.addFragment(new UserListFragment());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.getTabAt(0).setText("Recepti");
        tabLayout.getTabAt(1).setText("Zaposlenici");
    }

    @Override
    public void startIntentFromFragment(Intent i) {
        startActivity(i);
    }


}
