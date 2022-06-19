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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.zxing.Result;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    Button btn_qr, btn_next;
    SignInButton signInButton;

    @Override
    protected void onStart() {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_qr = findViewById(R.id.btn_qr);
        btn_next = findViewById(R.id.btn_next);
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        findViewById(R.id.btn_qr).setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        gsc = GoogleSignIn.getClient(this, gso);

//        if(btn_qr != null){
//            btn_qr.setOnClickListener(v -> {
//                startActivity(new Intent(MainActivity.this, QRActivity.class));
//                //finish();
//            });
//        }

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


    @Override
    public void onClick(View v) {

    }
}

