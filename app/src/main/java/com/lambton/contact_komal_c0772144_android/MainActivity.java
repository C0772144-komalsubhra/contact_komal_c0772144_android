package com.lambton.contact_komal_c0772144_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText firstName;
    EditText lastName;
    EditText phone;
    EditText email;
    EditText address;
    TextView count;

    DatabaseHelper mDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phone = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        count = findViewById(R.id.count);

        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.phoneList).setOnClickListener(this);
        findViewById(R.id.countButton).setOnClickListener(this);
        mDataBase = new DatabaseHelper(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.add:
                addPerson();
                break;
            case R.id.phoneList:
                //start activity to another activity to use the list of employees
                Intent intent = new Intent(MainActivity.this,PersonActivity.class);
                startActivity(intent);

                break;

            case R.id.countButton:
                long i =   mDataBase.getTaskCount();
                count.setText(String.valueOf(i));
                Toast.makeText(this, "counted", Toast.LENGTH_SHORT).show();
                break;

        }
    }
    private void addPerson() {
        String first_name = firstName.getText().toString().trim();
        String last_name = lastName.getText().toString().trim();
        String phone_number = phone.getText().toString().trim();
        String email_address = email.getText().toString().trim();

        String addr = address.getText().toString().trim();


        //using the calender object to get the current time


        if (first_name.isEmpty()){
            firstName.setError("First Name field is empty");
            firstName.requestFocus();
            return;
        }

        if (last_name.isEmpty()){
            lastName.setError("last Name field is empty");
            lastName.requestFocus();
            return;
        }

        if (phone_number.isEmpty()){
            phone.setError("phone number field is empty");
            phone.requestFocus();
            return;
        }
        if (email_address.isEmpty()){
            email.setError("Email field is empty");
            email.requestFocus();
            return;
        }
        if (addr.isEmpty()){
            address.setError("address field is empty");
            address.requestFocus();
            return;
        }


        if (mDataBase.addPerson(first_name, last_name, phone_number,email_address, addr))
            Toast.makeText(this, "Person added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Person not added", Toast.LENGTH_SHORT).show();


        firstName.setText("");
        lastName.setText("");
        phone.setText("");
        email.setText("");
        address.setText("");
    }
}
