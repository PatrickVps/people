package com.people.trombinoscope.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.people.trombinoscope.R;
import com.people.trombinoscope.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by patrickvongpraseuth on 21/09/2017.
 */
public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.ntb_horizontal)
    NavigationTabBar navigationTabBar;

    @BindView(R.id.vp_horizontal_ntb)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        List fragments = new Vector();

        // Ajout des Fragments dans la liste
        fragments.add(Fragment.instantiate(this, TestFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, TestFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, TestFragment.class.getName()));

        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments));

        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.star),
                        getResources().getColor(R.color.colorAccent))
//                        .selectedIcon(getResources().getDrawable(R.drawable.star))
                        .title("Favorites")
//                        .badgeTitle("NTB")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.gobelet),
                        getResources().getColor(R.color.colorAccent))
                        .title("Accueil")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.crown),
                        getResources().getColor(R.color.colorAccent))
                        .title("Profil")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 1);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {
            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }
}
