package dev.thec0dec8ter.tmdb.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import dev.thec0dec8ter.tmdb.ui.search.FindFragment;
import dev.thec0dec8ter.tmdb.ui.search.RecentFragment;

public class SearchPagerAdapter extends FragmentStateAdapter {

    private final int NUM_PAGES = 2;

    public SearchPagerAdapter(@NonNull @org.jetbrains.annotations.NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public SearchPagerAdapter(@NonNull @org.jetbrains.annotations.NotNull Fragment fragment) {
        super(fragment);
    }

    public SearchPagerAdapter(@NonNull @org.jetbrains.annotations.NotNull FragmentManager fragmentManager, @NonNull @org.jetbrains.annotations.NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new RecentFragment();
            case 1:
                return new FindFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
