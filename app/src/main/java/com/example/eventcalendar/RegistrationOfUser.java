package com.example.eventcalendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class RegistrationOfUser extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    EditText pno;
    Button reg;
    EditText otp;
    String ootp;
    String phno;
    String veryficationcodebysystem;
    FirebaseAuth mAuth;
    Intent intent,intentotp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
         intent = getIntent();
         pno=(EditText) findViewById(R.id.mobile_rester);
         reg=(Button)findViewById(R.id.registration) ;




        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ;

                 phno = pno.getText().toString().trim();
                if  ( !phno.equals("")) {
                 /*   intentotp = new Intent(RegistrationOfUser.this,PhoneVerification.class);
                    startActivityForResult(intentotp, 3);*/
                    verifyoptandphonenumber();

                   /* SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("mob", phno);
                    editor.commit();

                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference(phno);
                    databaseReference.child(phno).setValue(phno);
                    setResult(Activity.RESULT_OK, intent);*/

                }
                else {
                    pno.setHint("enter valid no");
                   
                }


            }
        });

    }

    private void verifyoptandphonenumber() {
         mAuth=mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("fr");
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + phno)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            System.out.println("onCodeSent  "+s);

            veryficationcodebysystem=s;

           // SaveSomeInformation obj=new SaveSomeInformation(veryficationcodebysystem);


        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code=phoneAuthCredential.getSmsCode();
            if(code!=null){
                System.out.println("VERIFY SUCESSFU"+code);
                signInWithphoneAuthCredential(phoneAuthCredential);
              //  Toast.makeText(getApplicationContext(),"VERIFY SUCESSFUL",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            System.out.println(""+e.getMessage());
           // Toast.makeText(getApplicationContext(),"ERROR "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    };

    public void signInWithphoneAuthCredential(PhoneAuthCredential credential) {
                mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            System.out.println("onComplete if");
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("mob", phno);
                            editor.commit();

                            firebaseDatabase = FirebaseDatabase.getInstance();
                            databaseReference = firebaseDatabase.getReference(phno);
                            databaseReference.child(phno).setValue(phno);
                            setResult(Activity.RESULT_OK, intent);
                            Toast.makeText(getApplicationContext(),"YOU ARE ALL SET",Toast.LENGTH_SHORT).show();
                            finish();
                            // Sign in success, update UI with the signed-in user's information
                          //  Log.d(TAG, "signInWithCredential:success");

                           // FirebaseUser user = task.getResult().getUser();
                            // Update UI
                        } else {
                            System.out.println("onComplete else");
                            // Sign in failed, display a message and update the UI
                           // Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
     //   System.out.println("otp is "+data.getStringExtra("DATA"));
        if(requestCode==3){
           /* SaveSomeInformation objget=new SaveSomeInformation();
            System.out.println("objget.phoneAuthCredentia "+objget.phoneAuthCredential);*/
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(veryficationcodebysystem, data.getStringExtra("OTP"));
            signInWithphoneAuthCredential(credential);
            System.out.println("otp is "+data.getStringExtra("OTP"));
        }
    }
}
