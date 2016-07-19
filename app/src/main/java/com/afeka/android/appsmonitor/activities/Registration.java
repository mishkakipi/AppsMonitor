package com.afeka.android.appsmonitor.activities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.afeka.android.appsmonitor.R;
import com.afeka.android.appsmonitor.manager.RegistrationManager;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Registration extends AppCompatActivity {
    private static final String TAG = "RegistrationActivity";
    public static final String PREFS_NAME = "appsmonitor.properties";
    @BindView(R.id.input_name) EditText _nameText;
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_signup) Button _signupButton;
    boolean isParentMode;
    @BindView(R.id.mode) TextView registrationMode;
    private RegistrationManager _registrationManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        _registrationManager = new RegistrationManager();
        loadRegistrationFields(true);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        Switch toggle = (Switch) findViewById(R.id.switchbutton);

        //toggle.setText("Registration Mode: Parent");
        isParentMode = true;
        toggle.setChecked(true);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loadRegistrationFields(isChecked);
            }
        });
    }

    private void loadRegistrationFields(boolean isParent) {
        if (isParent) {
            registrationMode.setText("Registration Mode: Parent");
            ((TextInputLayout)_nameText.getParent()).setHint("Parent Name");
            ((TextInputLayout)_emailText.getParent()).setHint("Parent Email");
            ((TextInputLayout)_passwordText.getParent()).setHint("Password");
        } else {
            registrationMode.setText("Registration Mode: Child");
            ((TextInputLayout)_nameText.getParent()).setHint("Child Name");
            ((TextInputLayout)_emailText.getParent()).setHint("Child Email");
            ((TextInputLayout)_passwordText.getParent()).setHint("Parent Passphrase");
        }
        isParentMode = isParent;
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Registration.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Registering...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.
        String regPassphrase = _registrationManager.register(name, email, password, isParentMode);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_APPEND);
        SharedPreferences.Editor settingsEditor = settings.edit();
        settingsEditor.putString("mode", isParentMode?"parent":"child");
        settingsEditor.commit();
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Registration failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
