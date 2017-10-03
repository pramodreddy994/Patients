package com.example.pramo.patients;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;

public class register extends AppCompatActivity {
    private EditText nametxt, emailtxt, passtxt, mobiletxt, hospitaltxt, doctortxt;
    private Button submitbtn;
    public DatabaseReference databaseRef;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    String email,pass,emails;
    StringBuffer unique;
    char ac[];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        unique = new StringBuffer();
        progressDialog = new ProgressDialog(this);
        //databaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://doctor-69aec.firebaseio.com/doctor-69aec");
        nametxt = (EditText) findViewById(R.id.nametxt);
        emailtxt = (EditText) findViewById(R.id.emailtxt);
        passtxt = (EditText) findViewById(R.id.passtxt);
        mobiletxt = (EditText) findViewById(R.id.mobiletxt);
        hospitaltxt = (EditText) findViewById(R.id.hospitaltxt);
        doctortxt = (EditText) findViewById(R.id.doctortxt);
        submitbtn = (Button) findViewById(R.id.submitbtn);


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nametxt.getText().toString().trim();
                email = emailtxt.getText().toString().trim();
                pass = passtxt.getText().toString().trim();
                String mobile = mobiletxt.getText().toString().trim();
                String hospital = hospitaltxt.getText().toString().trim();
                String doctor = doctortxt.getText().toString().trim();
//                 ac=new char[4];
//                ac=email.toCharArray();
//                for(int i=0;i<=3;i++){
//                    unique.append(ac[i]);
//                }


                HashMap<String, String> datamap = new HashMap<String, String>();
                datamap.put("name", name);
                datamap.put("email", email);
                datamap.put("password", pass);
                datamap.put("mobile", mobile);
                datamap.put("hospital", hospital);
                datamap.put("speculation", doctor);
                emails = email.replace(".", "@");
                databaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://doctor-69aec.firebaseio.com/patients_list");
                databaseRef.child(emails).setValue(datamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(register.this, "Stored..", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(register.this, "unable to store...", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                unique = null;

                registeruser();
            }
        });
    }
    private void  registeruser() {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("you are regestering please wait");
        progressDialog.show();
        //firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(this, n)
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(register.this, "Registered Successfully ", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        } else {
                            Toast.makeText(register.this, "Registered failed", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
        emailtxt.getText().clear();nametxt.getText().clear();passtxt.getText().clear();mobiletxt.getText().clear();
        hospitaltxt.getText().clear();doctortxt.getText().clear();
        }
    }

