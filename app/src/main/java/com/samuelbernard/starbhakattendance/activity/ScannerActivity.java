package com.samuelbernard.starbhakattendance.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.samuelbernard.starbhakattendance.R;
import com.samuelbernard.starbhakattendance.helper.DialogHelper;
import com.samuelbernard.starbhakattendance.model.ResponseScan;
import com.samuelbernard.starbhakattendance.pref.LoginPref;
import com.samuelbernard.starbhakattendance.rest.ApiClient;
import com.samuelbernard.starbhakattendance.rest.ApiInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScannerActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.qr_scan)
    DecoratedBarcodeView qrScan;
    @BindView(R.id.coordinator_nav)
    CoordinatorLayout coordinatorNav;

    private DialogHelper helper;
    private LoginPref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        ButterKnife.bind(this);
        helper = new DialogHelper(this);
        pref = new LoginPref(this);

        requestPermission();
        initScanner();
    }

    private void initScanner() {
        qrScan.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                String[] data = result.getText().split("/");
//                Toast.makeText(ScannerActivity.this, data[0]+ data[1]+ data[2]+ data[3], Toast.LENGTH_SHORT).show();
                Log.e(TAG, "barcodeResult: " + result);
                Log.e(TAG, "barcodeResult: " + data);
                Log.e(TAG, "barcodeResult: " + data[0]);
                Log.e(TAG, "barcodeResult: " + data[1]);
                postScan(data[1], "Check in", String.valueOf(pref.getIdUser()), data[0]);
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {}
        });
    }

    private void postScan(String kategoriPresensi, String keterangan, String idSiswa, String idKegiatan) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.dialog_msg_loading));
        dialog.setCancelable(false);
        dialog.show();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseScan> call = apiService.scanQrcode(kategoriPresensi, keterangan, idSiswa, idKegiatan);
        call.enqueue(new Callback<ResponseScan>() {
            @Override
            public void onResponse(@NonNull Call<ResponseScan> call, @NonNull Response<ResponseScan> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus() == 200) {
                            dialog.dismiss();
                            startActivity(new Intent(ScannerActivity.this, MainActivity.class));
                            finish();

                        } else {
                            dialog.dismiss();
                            helper.showDialogSubmit(getString(R.string.dialog_title_fail), getString(R.string.dialog_msg_send_failed), true);
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseScan> call, @NonNull Throwable t) {
                dialog.dismiss();
                helper.showDialogSubmit(getString(R.string.dialog_title_fail), getString(R.string.dialog_msg_send_failed), true);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        qrScan.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrScan.pause();
    }


    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        } else {
            qrScan.resume();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0 && grantResults.length < 1) {
            requestPermission();
        }
    }
}