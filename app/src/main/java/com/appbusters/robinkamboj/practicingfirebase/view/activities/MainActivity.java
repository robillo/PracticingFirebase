package com.appbusters.robinkamboj.practicingfirebase.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appbusters.robinkamboj.practicingfirebase.R;
import com.appbusters.robinkamboj.practicingfirebase.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        User user = new User(mFirebaseUser.getDisplayName(), mFirebaseUser.getEmail(), mFirebaseUser.getUid());
        mDatabase.child(user.getUid()).setValue(user);
    }
}
