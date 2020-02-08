package com.example.automate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.automate.models.DriverClass;
import com.example.automate.models.PassengerClass;
import com.example.automate.models.RideHistoryDriver;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText id;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.et_login);
        pass = findViewById(R.id.et_pass);
        login = findViewById(R.id.btn_login);

        insertDummyDriver();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

    }

    private void insertDummyDriver() {

        AppUtils.drivers.add(new DriverClass("driver1","111",4,new RideHistoryDriver(4f,2,"Ojas","Opal","12/02/2019")));
        AppUtils.drivers.add(new DriverClass("driver2","222",5,new RideHistoryDriver(5f,4,"Agate","Orion","12/11/2019")));
        AppUtils.drivers.add(new DriverClass("driver3","222",5,new RideHistoryDriver(5f,4,"Agate","Orion","12/02/2019")));

    //    AppUtils.passengers.add(new PassengerClass())
    }

    private void doLogin() {
        if(id.getText().toString().equals("") || pass.getText().toString().equals("")) {
            Toast.makeText(this,"Invalid",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent;
            System.out.println("id is "+id.getText().toString());
            if(id.getText().toString().equals("driver1") ||id.getText().toString().equals("driver2") || id.getText().toString().equals("driver3"))
            {
                intent = new Intent(this,DriverMapActivity.class);
                intent.putExtra("mode","driver");
            }
            else {
                intent = new Intent(this,MapActivity.class);
                intent.putExtra("mode", "rider");
            }

            startActivity(intent);
        }
    }
}
