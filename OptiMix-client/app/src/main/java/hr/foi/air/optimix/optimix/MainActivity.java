package hr.foi.air.optimix.optimix;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import hr.foi.air.optimix.core.SessionManager;

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
        tabLayout.getTabAt(0).setText("Skladište");
        tabLayout.getTabAt(1).setText("Analiza");

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

    @Override
    public void onBackPressed() {
        showPopUp();

    }

    private void showPopUp() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Log out");
        helpBuilder.setMessage("Da li se uistinu želite odjaviti?");
        helpBuilder.setPositiveButton("Da",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        signOut();
                    }
                });

        helpBuilder.setNegativeButton("Ne", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Zatvori dijalog
            }
        });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

    }

    private void signOut() {
                SessionManager.getInstance(getApplicationContext()).destroyAll();
                for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); ++i) {
                    getFragmentManager().popBackStack();
                }
                MainActivity.super.onBackPressed();
            }


    }
