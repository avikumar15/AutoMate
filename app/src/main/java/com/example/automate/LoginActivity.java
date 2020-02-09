package com.example.automate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.automate.models.DriverClass;
import com.example.automate.models.DriverHistoryClass;
import com.example.automate.models.PassengerClass;

import java.util.ArrayList;
import java.util.List;

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

        DriverClass d1=new DriverClass(),d2= new DriverClass(),d3= new DriverClass();
        List<DriverClass.History> lh1=new ArrayList<>(),lh2=new ArrayList<>(),lh3=new ArrayList<>();
        // todo
        /*d1.setHistory(new DriverClass.History());
        lh1.add(d1.History(4f,2,"Ojas","Opal","12/02/2019"))*/

        AppUtils.drivers.add(new DriverClass(1,"111",4,"","",lh1));
        AppUtils.drivers.add(new DriverClass(2,"222",5,"","",lh2));
        AppUtils.drivers.add(new DriverClass(3,"222",5,"","",lh3));

        //    AppUtils.passengers.add(new PassengerClass())
    }

    private void doLogin() {
        if(id.getText().toString().equals("") || pass.getText().toString().equals("")) {
            Toast.makeText(this,"Invalid",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent;
            System.out.println("id is "+id);
            if(id.getText().toString().equals("1") ||id.getText().toString().equals("2") || id.getText().toString().equals("3"))
            {
                intent = new Intent(this,DriverMapActivity.class);
                intent.putExtra("mode",id.getText().toString());
            }
            else {
                intent = new Intent(this,MapActivity.class);
                intent.putExtra("mode", "rider");
            }

            startActivity(intent);
        }
    }
}
