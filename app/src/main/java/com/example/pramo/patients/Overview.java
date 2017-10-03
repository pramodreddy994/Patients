package com.example.pramo.patients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Overview extends AppCompatActivity {
    public Button scorebtn,taketestbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        scorebtn=(Button) findViewById(R.id.scorebtn);
        taketestbtn=(Button) findViewById(R.id.taketestbtn);
        scorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Overview.this,scores.class);
                startActivity(i);
            }
        });
        taketestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("error","executed");
                Intent i=new Intent(Overview.this,newtest.class);
                startActivity(i);
            }
        });
    }
}
