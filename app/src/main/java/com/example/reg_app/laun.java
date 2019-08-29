package com.example.reg_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class laun extends AppCompatActivity {

    TextInputEditText mail,pass;
    Button but;
    String mail1,pass1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laun);
        mail=findViewById(R.id.name);
        pass=findViewById(R.id.name1);
        but=findViewById(R.id.login);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail1=mail.getText().toString();
                pass1=pass.getText().toString();
                if(mail1.equals("pasc")&&pass1.equals("1234")) {
                    Intent i = new Intent(laun.this, MainActivity.class);
                    i.putExtra("mail",mail1);
                    startActivity(i);
                    finish();
                    
                }
            }
        });

    }
}
