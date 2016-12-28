package com.crud.db.crudapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Adeel Suleman on 11/1/2016.
 */

public class UpdateProfileScreen extends Activity {
    SharedPreferences getSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile_screen);

        final DbManager manager = new DbManager(UpdateProfileScreen.this);
        getSession = getSharedPreferences("loginSession", Context.MODE_PRIVATE);
        final String ID =getSession.getString("SessionId",null);
        final String EMAIL = getSession.getString("SessionEmail",null);
        final String AEMAIL = getSession.getString("SessionAEmail",null);
        final String USER = getSession.getString("SessionName",null);

        final TextView UserID = (TextView) findViewById(R.id.update_id);
        final EditText UserName = (EditText) findViewById(R.id.update_name);
        final EditText Email = (EditText) findViewById(R.id.update_email);
        final EditText Aemail = (EditText) findViewById(R.id.update_aemail);

        UserID.setText(ID);
        UserName.setText(USER);
        Email.setText(EMAIL);
        Aemail.setText(AEMAIL);



        Button btnUpdateRecord = (Button) findViewById(R.id.updateRecordBtn);
        btnUpdateRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.Updation(ID,"adeel suleman","star12","abc@mail.com","abc1@mail.com");


                Toast.makeText(UpdateProfileScreen.this,"Login User Id is "+ ID+ " " + EMAIL +" "+ AEMAIL , Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = getSession.edit();

                editor.remove("ID");
                editor.remove("EMAIL");
                editor.remove("AEMAIL");
                editor.remove("USER");


            }
        });






    }
}
