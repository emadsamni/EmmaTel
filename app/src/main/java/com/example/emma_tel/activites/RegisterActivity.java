package com.example.emma_tel.activites;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.emma_tel.R;
import com.example.emma_tel.fragments.FinishFragment;
import com.example.emma_tel.fragments.RegisterFragment;
import com.example.emma_tel.fragments.VerificationFragment;

public class RegisterActivity extends AppCompatActivity {
    ImageButton mNextBtn;
    Button mSkipBtn, mFinishBtn;
    ImageView zero, one, two;
    ImageView[] indicators;
    int lastLeftValue = 0;
    CoordinatorLayout mCoordinator;
    static final String TAG = "PagerActivity";
    int page = 0;   //  to track page position

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         assignUIReference();
        assignAction();
    }
    private void assignUIReference() {
        mNextBtn = (ImageButton) findViewById(R.id.intro_btn_next);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP)
            mNextBtn.setImageDrawable(
                    tintMyDrawable(ContextCompat.getDrawable(this, R.drawable.ic_chevron_right_24dp), Color.WHITE)
            );
        mSkipBtn = (Button) findViewById(R.id.intro_btn_skip);
        mFinishBtn = (Button) findViewById(R.id.intro_btn_finish);
        zero = (ImageView) findViewById(R.id.intro_indicator_0);
        one = (ImageView) findViewById(R.id.intro_indicator_1);
        two = (ImageView) findViewById(R.id.intro_indicator_2);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.main_content);
        indicators = new ImageView[]{zero, one, two};
        RegisterFragment fragment = new RegisterFragment();
        loadFragment(fragment);
        updateIndicators(page);

    }
    private  void assignAction()
    {
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page += 1;
                updateIndicators(page);
                switch (page)
                {
                    case 0:
                        RegisterFragment fragment1 = new RegisterFragment();
                        loadFragment(fragment1);
                        updateIndicators(page);
                        return;
                    case 1:
                        VerificationFragment fragment2 = new VerificationFragment();
                        loadFragment(fragment2);
                        updateIndicators(page);
                        return;
                    case 2:
                        FinishFragment fragment3 = new FinishFragment();
                        loadFragment(fragment3);
                        updateIndicators(page);
                        return;

                }
            }
        });

        mSkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page -= 1;
                switch (page)
                {
                    case 0:
                        RegisterFragment fragment1 = new RegisterFragment();
                        loadFragment(fragment1);
                        updateIndicators(page);
                    case 1:
                        VerificationFragment fragment2 = new VerificationFragment();
                        loadFragment(fragment2);
                        updateIndicators(page);
                    case 2:
                        FinishFragment fragment3 = new FinishFragment();
                        loadFragment(fragment3);
                        updateIndicators(page);

                }
                updateIndicators(page);
            }
        });

        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMain = new Intent(RegisterActivity.this, MainActivity.class);
                toMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(toMain);
                finish();

            }
        });
    }
    public static Drawable tintMyDrawable(Drawable drawable, int color) {
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, color);
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        return drawable;
    }
    void updateIndicators(int position) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackgroundResource(
                    i == position ? R.drawable.indicator_selected : R.drawable.indicator_unselected
            );
        }
        mNextBtn.setVisibility(position == 2 ? View.GONE : View.VISIBLE);
        mFinishBtn.setVisibility(position == 2 ? View.VISIBLE : View.GONE);
        mSkipBtn.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
    }
    private  boolean loadFragment(Fragment fragment)
    {
        if (fragment  != null )
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,fragment).commitNowAllowingStateLoss();
            return true;
        }
        return false;
    }

}
