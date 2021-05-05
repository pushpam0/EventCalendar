package com.example.eventcalendar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.view.LayoutInflater;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout cardContainer;
    View addcardlayout;

    private int reqCode;
    private int resCode;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String mobile=null;
    DataSnapshot snapshoref;
    SharedPreferences.Editor editor;
    ActionBar actionBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cardContainer = (LinearLayout) findViewById(R.id.cardContainer);
        Intent intent = new Intent(this,InfoCard.class);
        Intent intent_reg = new Intent(this,RegistrationOfUser.class);
        FloatingActionButton fab = findViewById(R.id.fab);



///*

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
         editor = sharedpreferences.edit();

        mobile =sharedpreferences.getString("mob","");
        if(!mobile.equals("")) {
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference().child(mobile);
            createpriviousevent(snapshoref);
        }
        else
            startActivityForResult(intent_reg,2);



      //*/




       // addcardlayout = getLayoutInflater().inflate(R.layout.card_view,null,false);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             /*   sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                       mobile =sharedpreferences.getString("mob","");
                       System.out.println("what is in the mobile   "+mobile);*/


                if(!mobile.equals("")) {
                   /* firebaseDatabase= FirebaseDatabase.getInstance();
                    databaseReference=firebaseDatabase.getReference().child(mobile);*/
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(mobile).exists()) {

                                snapshoref=snapshot;

                                System.out.println("inside snapshot.child(mobile).exists():  " + snapshot.getChildrenCount());
                                System.out.println("inside snapshot.child(mobile).exists():  " + snapshot.child(""+4).child("name_Of_Name").getValue());
                                System.out.println("inside snapshot.child(mobile).exists():  " + snapshot.child(""+4).child("event_Name").getValue());
                                System.out.println("inside snapshot.child(mobile).exists():  " + snapshot.child(""+4).child("event_Date").getValue());
                                intent.putExtra("TOTAL_VALUE",(int) snapshot.getChildrenCount());

                                startActivityForResult(intent, 1);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            setContentView(R.layout.error_view);
                            System.out.println("DatabaseError");
                        }
                    });
                }
                else
                    startActivityForResult(intent_reg,2);


               // startActivity(intent);




            }
        });

    }

    private void createpriviousevent(DataSnapshot snapshot) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count=(int)snapshot.getChildrenCount();
                while(count!=1){
                    addcardlayout = getLayoutInflater().inflate(R.layout.cardpracticetwo,null,false);
                    cardContainer.addView(addcardlayout);
                    TextView occasion=(TextView) addcardlayout.findViewById(R.id.occasion_id);
                    TextView date=(TextView) addcardlayout.findViewById(R.id.date_id);
                    TextView time=(TextView) addcardlayout.findViewById(R.id.time_id);
                    TextView name=(TextView) addcardlayout.findViewById(R.id.name_id);
                     name.setText(""+snapshot.child(""+(count-1)).child("name_Of_Name").getValue());
                     occasion.setText(""+ snapshot.child(""+(count-1)).child("event_Name").getValue());
                     date.setText(""+ snapshot.child(""+(count-1)).child("event_Date").getValue());
                     time.setText(""+  snapshot.child(""+(count-1)).child("Event_Time").getValue());

                    count--;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    { /*String message=data.getStringExtra("BACK_MESSAGE");
        System.out.println("HAhahahahah "+RESULT_OK);
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==1&&requestCode==RESULT_OK)
        {
            String messaget=data.getStringExtra("EXTRA_MESSAGE");
            System.out.println("HAhahahahahahaahahahahah "+messaget);*/
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null&&requestCode==1){
            String Eoccasion=data.getStringExtra("EXTRA_OC");
            String Ename=data.getStringExtra("EXTRA_NA");
            String Edate=data.getStringExtra("EXTRA_DA");
            String Etime=data.getStringExtra("EXTRA_TI");
            addcardlayout = getLayoutInflater().inflate(R.layout.cardpracticetwo,null,false);
            cardContainer.addView(addcardlayout);

            //View card=addcardlayout.findViewById(R.id.occasion_id);
            TextView occasion=(TextView) addcardlayout.findViewById(R.id.occasion_id);
            TextView date=(TextView) addcardlayout.findViewById(R.id.date_id);
            TextView time=(TextView) addcardlayout.findViewById(R.id.time_id);
            TextView name=(TextView) addcardlayout.findViewById(R.id.name_id);
            occasion.setText(Eoccasion);
            date.setText(Edate);
            time.setText(Etime);
            name.setText(Ename);
          /*  View relati=card.findViewById(R.id.cardrelative);
           TextView EName=(TextView) relati.findViewById(R.id.textView2);
            TextView name=(TextView) relati.findViewById(R.id.textView3);
            TextView date=(TextView) relati.findViewById(R.id.textView4);
            EName.setText(Emessage);
            name.setText(Ename);
            date.setText(Edate);*/
           // System.out.println("HAhahahahahahaahahahahah "+message);
        }

        if(requestCode==2){
            mobile =sharedpreferences.getString("mob","");
            if(!mobile.equals("")) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference().child(mobile);
                createpriviousevent(snapshoref);
            }
        }

        //}
    }



    public void AddCardInList(String name,String Date, String Time){
        System.out.println("inside fun");
        addcardlayout = getLayoutInflater().inflate(R.layout.cardpracticetwo,null,false);
        cardContainer.addView(addcardlayout);
     //   CardView card=(CardView) addcardlayout.findViewById(R.id.eventCard);
     /*  EditText setName=(EditText) card.findViewById(R.id.eventName);
        EditText setDate=(EditText) card.findViewById(R.id.eventDate);
        EditText setTime=(EditText) card.findViewById(R.id.eventTime);*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}