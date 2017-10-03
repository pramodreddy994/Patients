package com.example.pramo.patients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class scores extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView allscores;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        allscores=(ListView) findViewById(R.id.allscores);
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://doctor-69aec.firebaseio.com/score/"+MainActivity.em_unique);
        FirebaseListAdapter<Score> firebaseListAdapter=new FirebaseListAdapter<Score>(scores.this,Score.class,android.R.layout.simple_list_item_1,databaseReference) {
            @Override
            protected void populateView(View v, Score model, int position) {
                tv= (TextView) v.findViewById(android.R.id.text1);
                tv.setText(""+model.getScore());
            }


//
        };
        allscores.setAdapter(firebaseListAdapter);

    }
}
