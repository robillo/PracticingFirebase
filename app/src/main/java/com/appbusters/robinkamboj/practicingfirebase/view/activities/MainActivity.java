package com.appbusters.robinkamboj.practicingfirebase.view.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.appbusters.robinkamboj.practicingfirebase.R;
import com.appbusters.robinkamboj.practicingfirebase.model.User;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private HashMap<String, HashMap<String, Boolean>> allInterests;
    private HashMap<String, Boolean> mot, rel, ast, yog, ayu, hea, die;
    private static final int RESULT_LOAD_IMAGE = 8008, RESULT_LOAD_COVER = 8009;
    private ImageButton userProfilePic, userCoverPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 5);
        }

        userProfilePic = (ImageButton) findViewById(R.id.user_profile_photo);
        userCoverPic = (ImageButton) findViewById(R.id.header_cover_image);

        userCoverPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_COVER);
            }
        });

        userProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Insert Requested", "YES!.");
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

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
        rel.put("God", Boolean.TRUE);
        rel.put("Geeta", Boolean.FALSE);
        rel.put("Mahabharat", Boolean.TRUE);
        rel.put("Quotes", Boolean.FALSE);
        rel.put("Bhajan", Boolean.TRUE);
        rel.put("Prayers", Boolean.FALSE);
        rel.put("Stories", Boolean.TRUE);
        rel.put("Ramayan", Boolean.FALSE);

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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Log.e("REQUEST BEFORE", picturePath);
            Glide.with(this)
                    .load(picturePath)
                    .crossFade()
                    .centerCrop()
                    .into(userProfilePic);

            Log.e("REQUEST AFTER", picturePath);
        }
        else if(requestCode == RESULT_LOAD_COVER && resultCode == RESULT_OK && null != data){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Glide.with(this)
                    .load(picturePath)
                    .crossFade()
                    .centerCrop()
                    .into(userCoverPic);
        }

    }
}
