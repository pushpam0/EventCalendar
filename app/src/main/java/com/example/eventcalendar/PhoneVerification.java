package com.example.eventcalendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class PhoneVerification extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication_view);

        intent=getIntent();
        EditText otp=findViewById(R.id.otp_verify_id);
        Button verify=findViewById(R.id.verify_button_id);
       // String ootp=otp.getText().toString();

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ootp=otp.getText().toString();
                if(!ootp.equals("")){
                    intent.putExtra("OTP",ootp);
                   /* SaveSomeInformation objget=new SaveSomeInformation();
                    System.out.println("objget.phoneAuthCredentia "+objget.phoneAuthCredential);
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(objget.phoneAuthCredential,ootp);
                    RegistrationOfUser obj=new RegistrationOfUser() ;
                    obj.signInWithphoneAuthCredential(credential);*/
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"ENTER VALID OTP",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
