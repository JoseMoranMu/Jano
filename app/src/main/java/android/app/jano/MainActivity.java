package android.app.jano;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static Context contextOfApplication;
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
            Fragment container = new Fragment();

            switch (item.getItemId()){
                case R.id.navigation_earning:
                    container = new EarningFragment();
                    break;
                case R.id.navigation_expense:
                    container = new ExpenseFragment();
                    break;
                case R.id.navigation_config:
                    container = new ConfigFragment();
                    break;
            }
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.replace(R.id.container, container);
            transaction.addToBackStack(null); //Para que al pulsar back vuelva atrás el Fragment y no la Activity
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getFragmentManager().getBackStackEntryCount() == 0) initFragment();
            }
        });

        contextOfApplication = getApplicationContext();
        initFragment();
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void getNavigationVisible(boolean flag){
        if(flag){
            navigation.setVisibility(View.VISIBLE);
        }else{
            navigation.setVisibility(View.GONE);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(flag);
    }

    //From Retrofit files
    public static Context getContextOfApplication(){
        return contextOfApplication;
    }
    //Go to ConfigFragment
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        Fragment container;
        switch (item.getItemId()) {
            case R.id.action_config:
                container = new ConfigFragment();
                break;
            case android.R.id.home:
                container = new HomeFragment();
                break;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.container, container);
        transaction.addToBackStack(null);
        transaction.commit();
        return true;
    }

    private void initFragment() {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        Fragment container = new HomeFragment();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.container, container);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //Change title depends to fragment active
    public void setActionBarCenterTitle(String title) {
        getSupportActionBar().setTitle(title);

    }
}