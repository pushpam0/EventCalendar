package com.example.eventcalendar;

import com.google.firebase.auth.PhoneAuthCredential;

public class SaveSomeInformation {
   public static String phoneAuthCredential;

    public SaveSomeInformation() {
    }

    public SaveSomeInformation(String phoneAuthCredential) {
        this.phoneAuthCredential = phoneAuthCredential;
    }
}
