package com.example.anju.login_test_2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;

import static android.app.PendingIntent.getActivity;

public class signupActivity extends AppCompatActivity {
    private int year, month, day;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void ondob(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        EditText dobobj = (EditText) findViewById(R.id.editText7);
        dobobj.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void signup(View view) {
        user_signup userobj = new user_signup();
        EditText nameobj = (EditText) findViewById(R.id.editText);
        EditText emailobj = (EditText) findViewById(R.id.editText2);
        EditText passobj = (EditText) findViewById(R.id.editText5);
        EditText confirmobj = (EditText) findViewById(R.id.editText4);
        EditText dobobj = (EditText) findViewById(R.id.editText7);
        Spinner genderobj = (Spinner) findViewById(R.id.gender_spinner);
        userobj.dob = dobobj.getText().toString();
        userobj.name = nameobj.getText().toString();
        userobj.password = passobj.getText().toString();
        userobj.gender = genderobj.getSelectedItem().toString();
        userobj.email = emailobj.getText().toString();


        if (!userobj.password.equals(confirmobj.getText().toString())) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Passwords dont match!");
            builder1.setCancelable(false);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else {

            Singleton.getMap().put(emailobj.getText().toString(), userobj);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
