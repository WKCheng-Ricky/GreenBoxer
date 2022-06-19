package a0611076.qrdoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener{

    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Setting on click listener
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.disconnect_button).setOnClickListener(this);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        updateUI(account);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void updateUI(GoogleSignInAccount account) {
        // to be implemented

//        if (account != null) {
//            String personName = account.getDisplayName();
//            String personGivenName = account.getGivenName();
//            String personFamilyName = account.getFamilyName();
//            String personEmail = account.getEmail();
//            String personId = account.getId();
//            Uri personPhoto = account.getPhotoUrl();
//        }

        if(account != null){
            TextView name = findViewById(R.id.AccName);
            TextView email = findViewById(R.id.AccEmail);
            //TextView id = findViewById(R.id.AccId);
            findViewById(R.id.AccId).setVisibility(View.INVISIBLE);
            name.setText(account.getDisplayName());
            email.setText(account.getEmail());
            //id.setText(account.getId());
        } else {
            //Toast.makeText(this, "Account not exist", Toast.LENGTH_LONG);
            startActivity(new Intent(AccountActivity.this, MainActivity.class));
            finish();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // ...
            case R.id.sign_out_button:
                signOut();
                break;
            case R.id.disconnect_button:
                revokeAccess();
                break;
            // ...
        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, task -> {
                    Toast.makeText(AccountActivity.this,"Account has been signed out.",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AccountActivity.this,MainActivity.class));
                    finish();
                    // ...
                });
    }

    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, task -> {
                    Toast.makeText(AccountActivity.this,"Account has been disconnected.",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AccountActivity.this,MainActivity.class));
                    finish();
                    // ...
                });
    }
}