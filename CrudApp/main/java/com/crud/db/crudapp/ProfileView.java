package com.crud.db.crudapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Adeel Suleman on 11/1/2016.
 */

public class ProfileView extends Activity {
    SharedPreferences getSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_profile_screen);

        //final Intent loginData = getIntent();
        //final String ID = loginData.getStringExtra("EditId");


        getSession = getSharedPreferences("loginSession", Context.MODE_PRIVATE);
       final String ID =getSession.getString("SessionId",null);
       final String EMAIL = getSession.getString("SessionEmail",null);
       final String AEMAIL = getSession.getString("SessionAEmail",null);
       final String USER = getSession.getString("SessionName",null);

        TextView UserName = (TextView) findViewById(R.id.username_txt);
        TextView Email = (TextView) findViewById(R.id.email_txt);
        TextView Aemail = (TextView) findViewById(R.id.aemail_txt);

        UserName.setText(USER);
        Email.setText(EMAIL);
        Aemail.setText(AEMAIL);

        Button btnEdit = (Button) findViewById(R.id.edit_btn);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent editProfile = new Intent(ProfileView.this,UpdateProfileScreen.class);
                startActivity(editProfile);


                Toast.makeText(ProfileView.this,"Login User Id is "+ ID+ " " + EMAIL +" "+ AEMAIL , Toast.LENGTH_LONG).show();
            }
        });



    }
}
