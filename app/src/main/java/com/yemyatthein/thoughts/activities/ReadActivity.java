package com.yemyatthein.thoughts.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.yemyatthein.thoughts.R;
import com.yemyatthein.thoughts.fragments.SearchFormFragment;
import com.yemyatthein.thoughts.fragments.SearchResultFragment;
import com.yemyatthein.thoughts.fragments.ViewFragment;


public class ReadActivity extends AppCompatActivity
        implements SearchFormFragment.SearchFormFragmentInteractionListener {

    public static final String FRAGMENT_TARGET_KEY = "fragment_target";
    public static final int READ_FRAGMENT_TARGET_VIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        if (savedInstanceState == null) {
            int fragmentTarget = 0;

            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                fragmentTarget = bundle.getInt(FRAGMENT_TARGET_KEY);
            }

            switch (fragmentTarget) {
                case READ_FRAGMENT_TARGET_VIEW:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.read_container, new ViewFragment())
                            .commit();
                    break;
                default:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.read_container, new SearchFormFragment())
                            .commit();
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, ReadActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public void keywordSubmitted() {
        getFragmentManager().beginTransaction()
                .replace(R.id.read_container, new SearchResultFragment())
                .commit();
    }
}
