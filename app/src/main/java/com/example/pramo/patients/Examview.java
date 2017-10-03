package com.example.pramo.patients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Examview extends AppCompatActivity {
    public ListView examlist;
    DatabaseReference databaseReference;
    TextView date_patients;
    int k=0;
    static String c1,c2,c3,c4,url,answer;
    static ArrayList<Test> radio_array;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examview);
        examlist= (ListView) findViewById(R.id.examlist);
        examlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent x=new Intent(Examview.this,Overview.class);
                startActivity(x);
            }
        });
        radio_array = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://doctor-69aec.firebaseio.com/users/"+MainActivity.em_unique+"/");
        FirebaseListAdapter<Test> firebaseListAdapter=new FirebaseListAdapter<Test>(Examview.this,Test.class,android.R.layout.simple_list_item_1,databaseReference) {
            @Override
            protected void populateView(View v, Test model, int position) {
                date_patients= (TextView)v.findViewById(android.R.id.text1);
                Examview.radio_array.add(model);
                c1=model.getChoice1();
                c2=model.getChoice2();
                c3=model.getChoice3();
                c4=model.getChoice4();
                url=model.getAudio_url();
                answer=model.getAns();
                if(k<1){
                date_patients.setText("Date: "+Examview.radio_array.get(0).getDate());
                k++;
                }
            }
        };
        Toast.makeText(Examview.this,Examview.radio_array.size()+"",Toast.LENGTH_LONG).show();

            examlist.setAdapter(firebaseListAdapter);



    }

}
