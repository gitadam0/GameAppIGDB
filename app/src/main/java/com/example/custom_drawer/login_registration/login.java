package com.example.custom_drawer.login_registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.custom_drawer.MainActivity;
import com.example.custom_drawer.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthProvider;

public class login extends AppCompatActivity {
    EditText email,password;
    Button login1,reg,google;

    ProgressDialog progressDialog;
     private FirebaseAuth mAuth;

    private static final int RC_SIGN_IN = 123;
    private GoogleApiClient googleApiClient;

     @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        mAuth = FirebaseAuth.getInstance();

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login1=findViewById(R.id.login);
        google=findViewById(R.id.google);




        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OAuthProvider.Builder provider = OAuthProvider.newBuilder("github.com");
                // Target specific email with login hint.
                provider.addCustomParameter("login", "your-email@gmail.com");

                Task<AuthResult> pendingResultTask = mAuth.getPendingAuthResult();
                if (pendingResultTask != null) {
                    // There's something already here! Finish the sign-in for your user.
                    pendingResultTask
                            .addOnSuccessListener(
                                    new OnSuccessListener<AuthResult>() {
                                        @Override
                                        public void onSuccess(AuthResult authResult) {
                                            // User is signed in.
                                            // IdP data available in
                                            // authResult.getAdditionalUserInfo().getProfile().
                                            // The OAuth access token can also be retrieved:
                                            // ((OAuthCredential)authResult.getCredential()).getAccessToken().
                                            // The OAuth secret can be retrieved by calling:
                                            // ((OAuthCredential)authResult.getCredential()).getSecret().
                                        }
                                    })
                            .addOnFailureListener(
                                    new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Handle failure.
                                            Toast.makeText(login.this, "failure", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                } else {
                    // There's no pending result so you need to start the sign-in flow.
                    // See below.
                    Toast.makeText(login.this, "There's no pending result so you need to start the sign-in flow.", Toast.LENGTH_SHORT).show();
                }



                mAuth.startActivityForSignInWithProvider(/* activity= */ login.this, provider.build())
                        .addOnSuccessListener(
                                new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        // User is signed in.
                                        // IdP data available in
                                        // authResult.getAdditionalUserInfo().getProfile().
                                        // The OAuth access token can also be retrieved:
                                        // ((OAuthCredential)authResult.getCredential()).getAccessToken().
                                        // The OAuth secret can be retrieved by calling:
                                        // ((OAuthCredential)authResult.getCredential()).getSecret().
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Handle failure.
                                        Toast.makeText(login.this, "failure2", Toast.LENGTH_SHORT).show();

                                    }
                                });





            }
        });





        reg=findViewById(R.id.register_p);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,registre.class);
                startActivity(intent);
            }
        });

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(login.this);
                progressDialog.setMessage("Loading..."); // Setting Message
                 // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);


                String e=email.getText().toString();
                String p=password.getText().toString();
                if(e.isEmpty() || p.isEmpty()){

                    Toast.makeText(login.this, "email or password cant be empty", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                mAuth.signInWithEmailAndPassword(e, p)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

//                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Intent intent=new Intent(login.this,MainActivity.class);
                                    intent.putExtra("e",e);
                                    startActivity(intent);
                                    progressDialog.dismiss();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(login.this, "wrong email or password",
                                            Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });


//                Toast.makeText(login.this, e+" "+p, Toast.LENGTH_SHORT).show();
            }
        });

    }


}