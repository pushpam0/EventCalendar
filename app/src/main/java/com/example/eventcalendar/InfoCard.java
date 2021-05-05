package com.example.eventcalendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InfoCard extends AppCompatActivity {

    //public String EXTRA_MESSAGE ;
    String eventoccasionname;
    String eventnameofname;
    String eventeventdate;
    String eventeventtime;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_card);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Intent intent = getIntent();
        EditText occasion = (EditText) findViewById(R.id.occasionname);
        EditText nameofname = (EditText) findViewById(R.id.nameofname);
        EditText eventdate = (EditText) findViewById(R.id.eventdate);
        EditText eventtime = (EditText) findViewById(R.id.edittexttime);
        Button button = (Button) findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventoccasionname = occasion.getText().toString().trim();
                eventnameofname = nameofname.getText().toString().trim();
                eventeventdate = eventdate.getText().toString().trim();
                eventeventtime=eventtime.getText().toString().trim();



                FirebaseApp.initializeApp(InfoCard.this);
              //  FirebaseApp.initializeApp("https://eventcalendar-efb2a-default-rtdb.firebaseio.com/");
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                String mobile = sharedpreferences.getString("mob", "");
                firebaseDatabase =FirebaseDatabase.getInstance();
                databaseReference=firebaseDatabase.getReference(mobile);
               int a= (int)intent.getIntExtra("TOTAL_VALUE",-1);
                DataBundel dataBundel=new DataBundel(eventoccasionname,eventnameofname,eventeventdate,eventeventtime);
                databaseReference.child(""+((--a)+1)).setValue(dataBundel);


               // Toast.makeText(getApplicationContext(),"Event Added ",Toast.LENGTH_SHORT).show();
               MainActivity obj=new MainActivity();
                //obj.AddCardInList(name,date,time);
                System.out.println("After fun call  "+eventoccasionname);
                intent.putExtra("EXTRA_OC",eventoccasionname);
                intent.putExtra("EXTRA_NA",eventnameofname);
                intent.putExtra("EXTRA_DA",eventeventdate);
                intent.putExtra("EXTRA_TI",eventeventtime);

                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==1)
        {
            String message=data.getStringExtra("BACK_MESSAGE");
            System.out.println("HAhahahahahahaahahahahah"+message);

        }
        else
            System.out.println("NOBNONONOO");
    }*/

}
