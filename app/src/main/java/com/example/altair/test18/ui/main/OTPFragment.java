package com.example.altair.test18.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.altair.test18.NavigationHost;
import com.example.altair.test18.R;

public class OTPFragment extends Fragment {
    public static OTPFragment newInstance() {
        return new OTPFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.otp_fragment, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Check if user is signed in (non-null) and update UI accordingly.


        ((NavigationHost) getActivity()).popping(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
