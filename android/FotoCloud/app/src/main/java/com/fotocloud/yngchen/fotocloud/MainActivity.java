package com.fotocloud.yngchen.fotocloud;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class MainActivity extends ActionBarActivity {

    private Firebase myFirebaseRef;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://fotocloud.firebaseio.com/");
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick_Register(View v)
    {
        CharSequence c_email = ((TextView)findViewById(R.id.email)).getText();
        CharSequence c_password = ((TextView)findViewById(R.id.password)).getText();
        CharSequence c_password_confirm = ((TextView)findViewById(R.id.password_confirm)).getText();
        //Validate email pattern
        if(!Pattern.compile(EMAIL_PATTERN).matcher(c_email).matches())
        {
            //Email does not match regexp!
            new AlertDialog.Builder(this)
                    .setTitle("Invalid Email")
                    .setMessage("Please re-enter your email address")
                    .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Reset email field
                            ((TextView)findViewById(R.id.email)).setText("");
                        }
                    })
                    .show();

            return;
        }
        //Validate password == password_confirm
        if(c_password.equals(c_password_confirm))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Passwords do not match")
                    .setMessage("Please re-enter your password")
                    .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Reset password fields
                            ((TextView) findViewById(R.id.password)).setText("");
                            ((TextView) findViewById(R.id.password_confirm)).setText("");
                        }
                    })
                    .show();

            return;
        }
        //Everything is valid. Register this user with firebase.
        Firebase.ResultHandler createResultHandler = new Firebase.ResultHandler(){
            @Override
            public void onError(FirebaseError error)
            {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Error processing your request")
                        .setMessage("There's nothing you can do.")
                        .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Reset email field
                                ((TextView)findViewById(R.id.email)).setText("");
                                // Reset password fields
                                ((TextView) findViewById(R.id.password)).setText("");
                                ((TextView) findViewById(R.id.password_confirm)).setText("");
                            }
                        })
                        .show();
            }

            @Override
            public void onSuccess()
            {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Welcome to FotoCloud!")
                        .setMessage("There's nothing you can do.")
                        .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Reset email field
                                ((TextView)findViewById(R.id.email)).setText("");
                                // Reset password fields
                                ((TextView) findViewById(R.id.password)).setText("");
                                ((TextView) findViewById(R.id.password_confirm)).setText("");
                            }
                        })
                        .show();
            }
        };
        myFirebaseRef.createUser(c_email.toString(), c_password.toString(), createResultHandler);

    }
}
