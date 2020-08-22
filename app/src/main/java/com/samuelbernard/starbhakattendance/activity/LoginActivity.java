package com.samuelbernard.starbhakattendance.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.samuelbernard.starbhakattendance.R;
import com.samuelbernard.starbhakattendance.helper.DialogHelper;
import com.samuelbernard.starbhakattendance.model.ResponseLogin;
import com.samuelbernard.starbhakattendance.pref.LoginPref;
import com.samuelbernard.starbhakattendance.rest.ApiClient;
import com.samuelbernard.starbhakattendance.rest.ApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.taruna_bhakti_logo)
    ImageView tarunaBhaktiLogo;
    @BindView(R.id.tv_title_login)
    MaterialTextView tvTitleLogin;
    @BindView(R.id.input_email)
    TextInputEditText inputEmail;
    @BindView(R.id.input_password)
    TextInputEditText inputPassword;
    @BindView(R.id.cb_showPass)
    MaterialCheckBox cbShowPass;
    @BindView(R.id.tv_forget_password)
    MaterialTextView tvForgetPassword;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.btn_sign_in)
    MaterialButton btnSignIn;
    @BindView(R.id.coordinator_nav)
    CoordinatorLayout coordinatorNav;

    private Boolean isEmptyField;
    private LoginPref loginPref;
    private DialogHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPref = new LoginPref(this);
        helper = new DialogHelper(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sign_in)
    public void onViewClicked() {
        validateForm();
    }

    // Form validation
    private void validateForm() {
        isEmptyField = false;
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            isEmptyField = true;
            showSnackbar(getString(R.string.eror_form_null));
        } else if (TextUtils.isEmpty(password)) {
            isEmptyField = true;
            showSnackbar(getString(R.string.eror_form_null));
        }
        if (!isEmptyField) {
            submitData(email, password);
        }
    }

    // Send data to web service
    private void submitData(String email, String password) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.dialog_msg_loading));
        dialog.setCancelable(false);
        dialog.show();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLogin> call = apiService.loginUser(email, password);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(@NonNull Call<ResponseLogin> call, @NonNull Response<ResponseLogin> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus() == 200) {
                            ResponseLogin.Data user = response.body().getData();
                            loginPref.setUserData(user);
                            dialog.dismiss();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            dialog.dismiss();
                            helper.showDialogSubmit(getString(R.string.dialog_title_fail), getString(R.string.dialog_msg_invalid_login), true);
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseLogin> call, @NonNull Throwable t) {
                dialog.dismiss();
                helper.showDialogSubmit(getString(R.string.dialog_title_fail), getString(R.string.dialog_msg_invalid_login), true);
            }
        });
    }

    // Show snackbar
    private void showSnackbar(String message) {
        helper.showMessageSnackBar(coordinatorNav, message, Snackbar.LENGTH_LONG);
    }
}
