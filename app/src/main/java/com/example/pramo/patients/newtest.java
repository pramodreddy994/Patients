package com.example.pramo.patients;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class newtest extends AppCompatActivity {

    RadioGroup rg;
    RadioButton choice1,choice2,choice3,choice4;
    DatabaseReference databaseReference,databaseRefscore,databaseExam;
   // public ArrayList<Test> radio_array;
    TextView number;
    FirebaseDatabase database;
    int index=1;
    int array_index=1;
    SharedPreferences n_text;
    SharedPreferences.Editor ed;
    Button next,submit;
    static int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtest);
        rg = (RadioGroup) findViewById(R.id.radio);
        n_text=getSharedPreferences("n_user",MODE_PRIVATE);
        ed=n_text.edit();
        next= (Button) findViewById(R.id.next);
        choice1 = (RadioButton) findViewById(R.id.choice1);
        choice2 = (RadioButton) findViewById(R.id.choice2);
        choice3 = (RadioButton) findViewById(R.id.choice3);
        choice4 = (RadioButton) findViewById(R.id.choice4);
        submit=(Button) findViewById(R.id.submit);
        number=(TextView) findViewById(R.id.number);
        choice1.setText(Examview.radio_array.get(0).getChoice1());
        choice2.setText(Examview.radio_array.get(0).getChoice2());
        choice3.setText(Examview.radio_array.get(0).getChoice3());
        choice4.setText(Examview.radio_array.get(0).getChoice3());
        database=FirebaseDatabase.getInstance();
//        databaseExam=FirebaseDatabase.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://doctor-69aec.firebaseio.com/users/"+MainActivity.em_unique+"/"+MainActivity.em_unique+index);
        databaseRefscore=FirebaseDatabase.getInstance().getReferenceFromUrl("https://doctor-69aec.firebaseio.com/score/");
        FirebaseListAdapter<Test> firebaseListAdapter = new FirebaseListAdapter<Test>(newtest.this, Test.class, android.R.layout.simple_list_item_1, databaseReference) {
            @Override
            public Test getItem(int position) {
                return super.getItem(position);
            }

            @Override
            public long getItemId(int i) {

                return super.getItemId(i);
            }

            @Override
            protected void populateView(View v, Test model, int position) {
                //radio_array.add(model);

            }
        };
    }

    public void rbclick(View v){
        int radiobuttonid=rg.getCheckedRadioButtonId();

    }
    public void play(View v) throws IOException {
        MediaPlayer mp=new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setDataSource(Examview.radio_array.get(array_index-1).getAudio_url());
        mp.prepare();
        mp.start();


    }

    @Override
    protected void onStop() {

        newtest.score=0;
        Toast.makeText(this,""+newtest.score,Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    public void next(View view) {
        if(array_index<Examview.radio_array.size()){
        index+=1;
        number.setText(""+index);
       // if(array_index<Examview.radio_array.size()){
      //      Toast.makeText(this,Examview.radio_array.get(0).getChoice1()+" array size"+"array index "+array_index,Toast.LENGTH_SHORT).show();
        choice1.setText(""+Examview.radio_array.get(array_index).getChoice1());
        choice2.setText(""+Examview.radio_array.get(array_index).getChoice2());
        choice3.setText(""+Examview.radio_array.get(array_index).getChoice3());
        choice4.setText(""+Examview.radio_array.get(array_index).getChoice4());

            array_index+=1;}
        else{
            index=1;
            array_index=0;
            startActivity(new Intent(newtest.this,Examview.class));
            HashMap<String,Integer> scoremap=new HashMap<>();
            scoremap.put("score",newtest.score);
            databaseRefscore.child(MainActivity.em_unique).child(MainActivity.em_unique+""+n_text.getInt(MainActivity.em_unique,1)).setValue(scoremap);
            ed.putInt(MainActivity.em_unique,(n_text.getInt(MainActivity.em_unique,1)+1));
            ed.commit();
            newtest.score=0;
            finish();
        }
    }
    public void submit(View v){
        if(array_index<Examview.radio_array.size()){
        if(choice1.isChecked()){
           // Toast.makeText(newtest.this,"1 "+choice1.getText()+""+Examview.radio_array.get(array_index-1).getAns(),Toast.LENGTH_SHORT).show();
            if(choice1.getText().toString().equals(Examview.radio_array.get(array_index-1).getAns())){
                Toast.makeText(newtest.this,"Correct answer",Toast.LENGTH_SHORT).show();
            score+=1;}
            else
                Toast.makeText(newtest.this,"wrong answer",Toast.LENGTH_SHORT).show();
        }
        else if(choice2.isChecked()){
           // Toast.makeText(newtest.this,"1 "+choice2.getText()+""+" "+Examview.radio_array.get(Examview.radio_array.size()-(array_index)).getAns(),Toast.LENGTH_SHORT).show();
            if(choice2.getText().toString().equals(Examview.radio_array.get(array_index-1).getAns())){
                Toast.makeText(newtest.this,"Correct answer",Toast.LENGTH_SHORT).show(); score+=1;}
            else
                Toast.makeText(newtest.this,"wrong answer",Toast.LENGTH_SHORT).show();
        }
        else if(choice3.isChecked()){
            if(choice3.getText().toString().equals(Examview.radio_array.get(array_index-1).getAns())){
                Toast.makeText(newtest.this,"Correct answer",Toast.LENGTH_SHORT).show(); score+=1;}
            else
                Toast.makeText(newtest.this,"wrong answer",Toast.LENGTH_SHORT).show();

        }
        else if(choice4.isChecked()){
           // Toast.makeText(newtest.this,"1 "+choice4.getText()+" "+Examview.radio_array.get(Examview.radio_array.size()-(array_index)).getAns(),Toast.LENGTH_SHORT).show();
            if(choice4.getText().toString().equals(Examview.radio_array.get(array_index-1).getAns())){
                score+=1;
                Toast.makeText(newtest.this,"Correct answer",Toast.LENGTH_SHORT).show();}
            else
                Toast.makeText(newtest.this,"wrong answer",Toast.LENGTH_SHORT).show();

        }
          //  databaseRefscore.push().setValue(score);
    }
        Toast.makeText(newtest.this,"Current score"+newtest.score,Toast.LENGTH_SHORT).show();
    }
}