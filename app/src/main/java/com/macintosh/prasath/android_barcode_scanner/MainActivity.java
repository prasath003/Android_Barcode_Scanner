package com.macintosh.prasath.android_barcode_scanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_Scan = 1;
    TextView ResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ResultTextView = findViewById(R.id.id_scanResult);
        Button ScanButton = findViewById(R.id.id_scanBt);

        //scan button

        ScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //capture activity is used for portrait scan
                IntentIntegrator.REQUEST_CODE = REQUEST_CODE_Scan;
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setPrompt("Scan Some Barcode").setBeepEnabled(true).
                        setCaptureActivity(Orientation_Activity.class).setCameraId(0).initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                IntentResult result_scan = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                ResultTextView.append("\n"+result_scan.getContents());
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
