package com.crud.db.crudapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        final DbManager manager = new DbManager(LoginScreen.this);

        final EditText User = (EditText) findViewById(R.id.username);
        final EditText Pass = (EditText) findViewById(R.id.password);

        Button LoginBtn = (Button) findViewById(R.id.login_btn);
        Button RegisterBtn = (Button) findViewById(R.id.register_btn);
        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent RegisterPage = new Intent(LoginScreen.this,RegisterScreen.class);
                startActivity(RegisterPage);
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor reader= manager.FetchData(User.getText().toString(),Pass.getText().toString());
                String _id = reader.getString(reader.getColumnIndex("_id"));
                String _users = reader.getString(reader.getColumnIndex("_username"));
                String _passwords = reader.getString(reader.getColumnIndex("_password"));
                String _email = reader.getString(reader.getColumnIndex("_email"));
                String _aemail = reader.getString(reader.getColumnIndex("_alternative_email"));



                Toast.makeText(LoginScreen.this, _id  + " " + _passwords+ _users, Toast.LENGTH_LONG).show();
                SharedPreferences storedSession;
                storedSession = getSharedPreferences("loginSession", Context.MODE_PRIVATE);
                SharedPreferences.Editor editMySessionFile = storedSession.edit();
                editMySessionFile.putString("SessionId", _id);
                editMySessionFile.putString("SessionName", _users);
                editMySessionFile.putString("SessionEmail", _email);
                editMySessionFile.putString("SessionAEmail", _aemail);
                editMySessionFile.apply();

                Intent welcome = new Intent(LoginScreen.this,ProfileView.class);
               /* welcome.putExtra("EditId",_id);

                welcome.putExtra("EditUser",_users);
                welcome.putExtra("EditEmail",_email);
                welcome.putExtra("EditAEmail",_aemail);
                */
                startActivity(welcome);

            }
        });

    }
}
