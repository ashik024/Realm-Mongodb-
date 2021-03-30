package com.example.realmpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {


//    tuitorial= https://www.thecrazyprogrammer.com/2016/12/android-realm-database-tutorial.html

    EditText editText1;
    EditText editText2;
    EditText editText3;

    Button button;
    Button button2;

    Realm realm;

    TextView textView;

    String FName;
    String FClub;
    String FGoals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Realm.init(this);
        realm = Realm.getDefaultInstance();

        editText1=findViewById(R.id.name);
        editText2=findViewById(R.id.club);
        editText3=findViewById(R.id.goals);

        textView= findViewById(R.id.result);

        button= findViewById(R.id.save);
        button2= findViewById(R.id.load);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FName= editText1.getText().toString().trim();
                FClub= editText2.getText().toString().trim();
                FGoals= editText3.getText().toString().trim();

                if (TextUtils.isEmpty(FName)){
                    Toast.makeText(MainActivity.this, "Complete All Sections", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(FClub)){
                    Toast.makeText(MainActivity.this, "Complete All Sections", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(FGoals)){
                    Toast.makeText(MainActivity.this, "Complete All Sections", Toast.LENGTH_SHORT).show();
                }else {
                    savedata();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });


    }



    private void savedata() {

        realm.beginTransaction();
        PlayersDetails playersDetails = realm.createObject(PlayersDetails.class);
        playersDetails.setName(FName);
        playersDetails.setClub(FClub);
        playersDetails.setGoals(Integer.parseInt(FGoals));

        realm.commitTransaction();
        editText1.getText().clear();
        editText2.getText().clear();
        editText3.getText().clear();
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();

    }

    private void loadData() {

        RealmResults<PlayersDetails> realmResults =realm.where(PlayersDetails.class).findAll();

        if (realmResults.isEmpty()){
            Toast.makeText(this, "No data to load", Toast.LENGTH_SHORT).show();

        }else {
            textView.setText("");

            for (PlayersDetails playersDetails : realmResults){

                textView.append("Name: " + playersDetails.getName()+ "\n"+
                        "Club: " + playersDetails.getClub()+ "\n"+
                        "Goals: " + playersDetails.getGoals()+ "\n"+"\n"
                );

            }


        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        RealmResults<PlayersDetails> realmResults =realm.where(PlayersDetails.class).findAll();

        realm.beginTransaction();

        // remove single match
//        realmResults.deleteFirstFromRealm();
//        realmResults.deleteLastFromRealm();

// remove a single object
//        PlayersDetails playersDetails = realmResults.get(5);
//        playersDetails.deleteFromRealm();

// Delete all matches
        realm.deleteAll();
        realm.commitTransaction();
    }
}
