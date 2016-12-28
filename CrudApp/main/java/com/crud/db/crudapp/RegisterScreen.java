package com.crud.db.crudapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Adeel Suleman on 11/1/2016.
 */
public class RegisterScreen extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        final DbManager manager = new DbManager(RegisterScreen.this);

        final EditText Username = (EditText) findViewById(R.id._username);
        final EditText Password = (EditText) findViewById(R.id._password);
        final EditText Email = (EditText) findViewById(R.id._email);
        final EditText AlternativeEmail = (EditText) findViewById(R.id._alternativeemail);
        final EditText ConfirmPassword = (EditText) findViewById(R.id._confirmpassword);


        Button CreateAccount = (Button) findViewById(R.id.create_accountbtn);
        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String P = Password.getText().toString();
                String CP = ConfirmPassword.getText().toString();
                String AE = AlternativeEmail.getText().toString();
                String E = Email.getText().toString();
                if( !P.equals(CP) && E.equals(AE)) {
                    Toast.makeText(RegisterScreen.this,"Password should be Matched",Toast.LENGTH_LONG).show();
                }
                else if(P.equals(CP) && !E.equals(AE)) {
                    manager.Insertion(Username.getText().toString(), Password.getText().toString(), Email.getText().toString(), AlternativeEmail.getText().toString());
                    Toast.makeText(RegisterScreen.this,"Registeration Success",Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}
