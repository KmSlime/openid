package com.liem.openid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.makeramen.roundedimageview.RoundedImageView;

public class UserScreen extends AppCompatActivity {

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    Button btnSignOut;
    RoundedImageView imgAvata;
    EditText edtEmail, edtUserFullName;

    String personFullName, personEmail, IdToken, Token;
    GoogleSignInAccount currentAccount;
    public static String GoogleAccount = "Google return values";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        btnSignOut = findViewById(R.id.btnSignOut);
        imgAvata = findViewById(R.id.imgAvata);
        edtEmail = findViewById(R.id.edtEmail);
        edtUserFullName = findViewById(R.id.edtUserFullName);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        currentAccount = GoogleSignIn.getLastSignedInAccount(this);
        InitAccount(); // init value
        InitScreen(); // set value to screen

    }


    @Override
    protected void onStart() {
        super.onStart();

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignOut();
            }
        });
    }

    public void InitAccount(){
        if(currentAccount != null){
            String avtUrl = String.valueOf(currentAccount.getPhotoUrl());

            Glide.with(this)
                    .load(avtUrl)
                    .error(R.drawable.default_avatar)
                    .placeholder(R.drawable.default_avatar)
                    .into(imgAvata);
            personFullName = currentAccount.getDisplayName();
            personEmail = currentAccount.getEmail();
            Log.v(GoogleAccount, "email: " + personEmail);
            Log.v(GoogleAccount, "display name: " + currentAccount.getDisplayName());
            Log.v(GoogleAccount, "fullname: " + personFullName);
            Log.v(GoogleAccount, "zac: " + currentAccount.zac());
            Log.v(GoogleAccount, "avt url: " + avtUrl);
            Log.v(GoogleAccount, "zad: " + currentAccount.zad());
            Log.v(GoogleAccount, "id: " + currentAccount.getId());
            Log.v(GoogleAccount, "family name: " + currentAccount.getFamilyName());
            Log.v(GoogleAccount, "given name: " + currentAccount.getGivenName());
            Log.v(GoogleAccount, "idToken: " + currentAccount.getIdToken());
            Log.v(GoogleAccount, "server auth code: " + currentAccount.getServerAuthCode());
            Log.v(GoogleAccount, "hash code: " + currentAccount.hashCode());

        }

    }

    public void InitScreen(){
        edtEmail.setText(personEmail);
        edtUserFullName.setText(personFullName);

    }

    public void SignOut(){
        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                startActivity(new Intent(UserScreen.this, Login.class));
                finish();
            }
        });

    }


}