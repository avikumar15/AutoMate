package com.example.automate;

import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

public class QrScanner extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener{

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
    public void onQRCodeRead(String text, PointF[] points) {

        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();

    }
}
