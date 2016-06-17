package com.example.school.fragment;

import com.example.school.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeFragment extends Fragment {
	
	private EditText inputName, inputEmail, inputPassword;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword;
    private Button btnSignUp;
    
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        
//        inputLayoutName = (TextInputLayout) rootView.findViewById(R.id.input_layout_name);
//        inputLayoutEmail = (TextInputLayout) rootView.findViewById(R.id.input_layout_email);
//        inputLayoutPassword = (TextInputLayout) rootView.findViewById(R.id.input_layout_password);
//        inputName = (EditText) rootView.findViewById(R.id.input_name);
//        inputEmail = (EditText) rootView.findViewById(R.id.input_email);
//        inputPassword = (EditText) rootView.findViewById(R.id.input_password);
//        btnSignUp = (Button) rootView.findViewById(R.id.btn_signup);
// 
//        inputName.addTextChangedListener(new MyTextWatcher(inputName));
//        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
//        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
// 
//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                submitForm();
//            }
//        });

        // Inflate the layout for this fragment
        return rootView;
    }

    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateName()) {
            return;
        }
 
        if (!validateEmail()) {
            return;
        }
 
        if (!validatePassword()) {
            return;
        }
 
        Toast.makeText(getActivity(), "Thank You!", Toast.LENGTH_SHORT).show();
    }
 
    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }
 
        return true;
    }
 
    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();
 
        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }
 
        return true;
    }
 
    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }
 
        return true;
    }
 
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
 
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
 
    private class MyTextWatcher implements TextWatcher {
 
        private View view;
 
        private MyTextWatcher(View view) {
            this.view = view;
        }
 
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
 
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
 
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
//                case R.id.input_name:
//                    validateName();
//                    break;
//                case R.id.input_email:
//                    validateEmail();
//                    break;
//                case R.id.input_password:
//                    validatePassword();
//                    break;
            }
        }
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
