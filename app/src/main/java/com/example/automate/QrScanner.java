package com.example.automate;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.example.automate.models.DriverHistoryClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QrScanner extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {

    QRCodeReaderView qrView;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        qrView = findViewById(R.id.QRCodeReader);

        qrView.setOnQRCodeReadListener(this);
        qrView.setQRDecodingEnabled(true);
        qrView.setAutofocusInterval(1000);
        qrView.setBackCamera();
        qrView.startCamera();
    }

    @Override
    public void onQRCodeRead(final String text, PointF[] points) {
        qrView.setQRDecodingEnabled(false);
        vibrator.vibrate(200);

        Retrofit retrofitAuto = new Retrofit.Builder()
                .baseUrl(AppUtils.AUTO_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DriverHistoryInterface driverHistoryInterface = retrofitAuto.create(DriverHistoryInterface.class);

        Call<List<DriverHistoryClass>> call = driverHistoryInterface.getDriverHistory();
        Log.e("URL : ", driverHistoryInterface.getDriverHistory().request().url().toString());
        call.enqueue(new Callback<List<DriverHistoryClass>>() {
            @Override
            public void onResponse(Call<List<DriverHistoryClass>> call, Response<List<DriverHistoryClass>> response) {
                if (!response.isSuccessful()) {
                    Log.e("Fail", "Server Error.");
                    qrView.setQRDecodingEnabled(true);
                    return;
                }

                try {
                    List<DriverHistoryClass> driverHistoryList = response.body();
                    int f = 0;
                    for (DriverHistoryClass driverHistory : driverHistoryList) {
                        if (driverHistory.getDriverId() == Integer.parseInt(text)) {
                            f=1;
                            Log.e("Success", "\n" + driverHistory.getNoOfPassengers()
                                    + "\n" + driverHistory.getNoOfPassengers()
                                    + "\n" + driverHistory.getSource()
                                    + "\n" + driverHistory.getDestination()
                                    + "\n" + driverHistory.getDate());

                            Toast.makeText(getApplicationContext(), "\n" + driverHistory.getNoOfPassengers()
                                    + "\n" + driverHistory.getNoOfPassengers()
                                    + "\n" + driverHistory.getSource()
                                    + "\n" + driverHistory.getDestination()
                                    + "\n" + driverHistory.getDate(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(f==0)
                        qrView.setQRDecodingEnabled(true);

                } catch (Exception e) {
                    Log.e("Fetching Exception", e.getMessage().toString());
                    Toast.makeText(QrScanner.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    qrView.setQRDecodingEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<List<DriverHistoryClass>> call, Throwable t) {
                Log.e("Failure", "" + t);
                qrView.setQRDecodingEnabled(true);
            }
        });



    }
}
