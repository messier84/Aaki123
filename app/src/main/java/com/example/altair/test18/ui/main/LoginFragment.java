package com.example.altair.test18.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.altair.test18.NavigationHost;
import com.example.altair.test18.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.firebase.ui.auth.AuthUI.TAG;

public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;
    TextInputEditText userEditText ;
    TextInputLayout userTextInput ;
    TextInputLayout passwordTextInput;
    TextInputEditText passwordEditText ;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_fragment, container, false);
        userEditText = view.findViewById(R.id.username_login_text);
        userTextInput = view.findViewById(R.id.username_login_input);

        passwordEditText = view.findViewById(R.id.password_login_text);
        passwordTextInput = view.findViewById(R.id.password_login_input);
        MaterialButton cancelB = view.findViewById(R.id.cancels);
        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn(userEditText.getText().toString(),passwordEditText.getText().toString());
            }
        });
        mAuth = FirebaseAuth.getInstance();

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }
    private void updateUI(FirebaseUser currentUser) {
        if(currentUser!=null){
            ((NavigationHost) getActivity()).navigateTo(new OTPFragment(), false);}
    }
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

             // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }
    private boolean validateForm()
    {

        boolean valid = true;

        String email = userEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            userTextInput.setError("Required.");
            valid = false;
        } else {
            userTextInput.setError(null);
        }

        String password = passwordEditText.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordTextInput.setError("Required.");
            valid = false;
        } else {
            passwordTextInput.setError(null);
        }

        return valid;

    }


}
