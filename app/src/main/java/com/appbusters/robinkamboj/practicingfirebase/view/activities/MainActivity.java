package com.appbusters.robinkamboj.practicingfirebase.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.appbusters.robinkamboj.practicingfirebase.R;
import com.appbusters.robinkamboj.practicingfirebase.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private TextView info;

    private HashMap<String, HashMap<String, Boolean>> allInterests;
    private HashMap<String, Boolean> mot, rel, ast, yog, ayu, hea, die;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = (TextView) findViewById(R.id.info);
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mot = new HashMap<>();
        mot.put("Inspiration", Boolean.TRUE);
        mot.put("Stories", Boolean.TRUE);
        mot.put("Life", Boolean.FALSE);
        mot.put("Learning", Boolean.TRUE);
        mot.put("Good Thoughts", Boolean.FALSE);
        mot.put("Thought Of The Day", Boolean.TRUE);
        mot.put("Speeches", Boolean.TRUE);
        mot.put("Moral Stories", Boolean.TRUE);
        mot.put("Real Life Inspiration", Boolean.TRUE);
        mot.put("Quotes", Boolean.TRUE);
        mot.put("Courage", Boolean.TRUE);
        mot.put("Happiness", Boolean.TRUE);
        mot.put("Confidence", Boolean.TRUE);
        mot.put("Psychology", Boolean.TRUE);
        mot.put("Fight Depression", Boolean.TRUE);
        mot.put("Career Motivation", Boolean.TRUE);

        rel= new HashMap<>();
        rel.put("rel1", Boolean.TRUE);
        rel.put("rel2", Boolean.FALSE);
        rel.put("rel3", Boolean.TRUE);
        rel.put("rel4", Boolean.FALSE);
        rel.put("rel5", Boolean.TRUE);
        rel.put("rel6", Boolean.FALSE);
        rel.put("rel7", Boolean.TRUE);
        rel.put("rel8", Boolean.FALSE);

        ast = new HashMap<>();
        ast.put("ast1", Boolean.TRUE);
        ast.put("ast2", Boolean.FALSE);
        ast.put("ast3", Boolean.TRUE);
        ast.put("ast4", Boolean.FALSE);

        yog = new HashMap<>();
        yog.put("yog1", Boolean.TRUE);
        yog.put("yog2", Boolean.FALSE);
        yog.put("yog3", Boolean.TRUE);
        yog.put("yog4", Boolean.FALSE);
        yog.put("yog5", Boolean.TRUE);
        yog.put("yog6", Boolean.FALSE);

        ayu = new HashMap<>();
        ayu.put("ayu1", Boolean.TRUE);
        ayu.put("ayu2", Boolean.FALSE);
        ayu.put("ayu3", Boolean.TRUE);
        ayu.put("ayu4", Boolean.FALSE);
        ayu.put("ayu5", Boolean.TRUE);

        hea = new HashMap<>();
        hea.put("hea1", Boolean.TRUE);
        hea.put("hea2", Boolean.FALSE);
        hea.put("hea3", Boolean.TRUE);
        hea.put("hea4", Boolean.FALSE);
        hea.put("hea5", Boolean.TRUE);
        hea.put("hea6", Boolean.FALSE);
        hea.put("hea7", Boolean.TRUE);
        hea.put("hea8", Boolean.FALSE);

        die = new HashMap<>();
        die.put("die1", Boolean.TRUE);
        die.put("die2", Boolean.FALSE);
        die.put("die3", Boolean.TRUE);
        die.put("die4", Boolean.FALSE);

        allInterests = new HashMap<>();
        allInterests.put("Motivation", mot);
        allInterests.put("Religion", rel);
        allInterests.put("Astrology", ast);
        allInterests.put("Yoga", yog);
        allInterests.put("Ayurveda", ayu);
        allInterests.put("Health", hea);
        allInterests.put("Diet", die);

        User user = new User(mFirebaseUser.getDisplayName(), mFirebaseUser.getEmail(), "I AM ROBIN KAMBOJ, AN ANDORID DEVELOPER!....", "EN", "ROBIN.JPG", "COVER.PNG", allInterests, Boolean.TRUE,
                "12.2.17", "MALE", "7042337414", 20);
        mDatabase.child(mFirebaseUser.getUid()).setValue(user);

        mDatabase.child(mFirebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                String temp = user.getName() + "\n" +  user.getEmail() + "\n";
                info.setText(temp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                info.setText("Cancelled!");
            }
        });
    }
}
