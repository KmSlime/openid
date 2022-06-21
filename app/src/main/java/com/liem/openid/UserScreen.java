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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class UserScreen extends AppCompatActivity {

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    Button btnSignOut;
    ImageView imgAvata;
    EditText edtToken, edtEmail, edtUserFullName;

    String personFullName, personEmail, IdToken, Token;
    GoogleSignInAccount currentAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        btnSignOut = findViewById(R.id.btnSignOut);
        imgAvata = findViewById(R.id.imgAvata);
        edtToken = findViewById(R.id.edtToken);
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
            personFullName = currentAccount.getDisplayName();
            personEmail = currentAccount.getEmail();
            IdToken = currentAccount.getIdToken();
            Log.v("Return value", personFullName + "" + personEmail + IdToken);
        }
    }

    public void InitScreen(){
        edtEmail.setText(personEmail);
        edtUserFullName.setText(personFullName);
        edtToken.setText(IdToken);

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