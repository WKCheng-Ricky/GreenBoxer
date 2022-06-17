package a0611076.qrdoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_qr = findViewById(R.id.btn_qr);
        Button btn_next = findViewById(R.id.btn_next);

        if(btn_qr != null){
            btn_qr.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, QRActivity.class));
                //finish();
            });
        }

        if(btn_next != null){
            btn_next.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this,SheetActivity.class));
               // finish();
            });

        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}


//
//
//public class MainActivity extends AppCompatActivity {
//    private static final int MY_CAMERA_REQUEST_CODE = 100;
//
//
//    private CodeScanner mCodeScanner;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
//        }
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        CodeScannerView scannerView = findViewById(R.id.scanner_view);
//        mCodeScanner = new CodeScanner(this, scannerView);
//        mCodeScanner.setDecodeCallback(new DecodeCallback() {
//            @Override
//            public void onDecoded(@NonNull final Result result) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(MainActivity.this, result.getText(), Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        });
//        scannerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mCodeScanner.startPreview();
//            }
//        });
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mCodeScanner.startPreview();
//    }
//
//    @Override
//    protected void onPause() {
//        mCodeScanner.releaseResources();
//        super.onPause();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == MY_CAMERA_REQUEST_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//}