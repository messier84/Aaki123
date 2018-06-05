package com.example.altair.test18;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public interface NavigationHost {
    void navigateTo(Fragment fragment, boolean addToBackstack);
    void popping(String name, int callwe);
}

