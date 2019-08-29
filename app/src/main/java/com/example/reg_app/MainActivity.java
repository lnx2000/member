package com.example.reg_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout text_name, text_contact, text_mail;
    private TextInputEditText name, contact, mail;
    private CheckBox FE, SE, TE, BE;
    private String name1, contact1, mail1, year,branch,current_date;
    private Button register;
    private TextView acm_mail, tV_year,date;
    private Spinner mSpinner;
String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s=getIntent().getExtras().getString("mail");

        text_name = findViewById(R.id.layout_name);
        text_contact = findViewById(R.id.layout_contact);
        text_mail = findViewById(R.id.layout_mail);
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        mail = findViewById(R.id.mail);
        FE = findViewById(R.id.FE);
        SE = findViewById(R.id.SE);
        TE = findViewById(R.id.TE);
        BE = findViewById(R.id.BE);
        register = findViewById(R.id.register);
        acm_mail = findViewById(R.id.acm_mail);
        tV_year = findViewById(R.id.tV_year);
        date=findViewById(R.id.date);

        current_date= DateFormat.getDateInstance().format(new Date());
        date.append(current_date);
        acm_mail.setText("Mail-ID : "+s);

        mSpinner=findViewById(R.id.branch);
        ArrayAdapter<String> mAdapter=new ArrayAdapter<>(MainActivity.this,
                R.layout.spinner_item,
                getResources().getStringArray(R.array.Branches));
        mAdapter.setDropDownViewResource(R.layout.spinner_item);
        mSpinner.setAdapter(mAdapter);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckbox();
                text_name.setErrorEnabled(false);
                text_contact.setErrorEnabled(false);
                text_mail.setErrorEnabled(false);
                tV_year.setError(null);
                name1 = name.getText().toString().trim();
                contact1 = contact.getText().toString().trim();
                mail1 = mail.getText().toString().trim();
                branch=mSpinner.getSelectedItem().toString();
                if (!TextUtils.isEmpty(name1) && !TextUtils.isEmpty(contact1) && !TextUtils.isEmpty(mail1) && isEmailValid(mail1) && isContactValid(contact1) && !TextUtils.isEmpty(year)) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setMessage("To pay:- 1500Rs")
                            .setCancelable(false)
                            .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(MainActivity.this, "User registered to database!", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).show();
                }
                if (TextUtils.isEmpty(name1)) {
                    text_name.setError("This field cannot be empty.");
                }
                if (TextUtils.isEmpty(contact1)) {
                    text_contact.setError("This field cannot be empty.");
                } else if (!isContactValid(contact1)) {
                    text_contact.setError("Format is incorrect.");
                }
                if (TextUtils.isEmpty(mail1)) {
                    text_mail.setError("This field cannot be empty.");
                } else if (!isEmailValid(mail1)) {
                    text_mail.setError("Format is incorrect.");
                }
                if (TextUtils.isEmpty(year)) {
                    tV_year.setError("Mandatory");
                }
            }
        });

    }

    boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    boolean isContactValid(String contact) {
        if (contact.length() == 10)
            return true;
        else
            return false;
    }

    void onCheckbox(){
        if(FE.isChecked())
            year = "FE";
        else if(SE.isChecked())
            year = "SE";
        else if(TE.isChecked())
            year = "TE";
        else if(BE.isChecked())
            year = "BE";
        else
            year = null;
    }

    public void onCheckBoxClicked(View view) {
        switch (view.getId()) {
            case R.id.FE:
                SE.setChecked(false);
                TE.setChecked(false);
                BE.setChecked(false);
                break;
            case R.id.SE:
                FE.setChecked(false);
                TE.setChecked(false);
                BE.setChecked(false);
                break;
            case R.id.TE:
                FE.setChecked(false);
                SE.setChecked(false);
                BE.setChecked(false);
                break;
            case R.id.BE:
                FE.setChecked(false);
                SE.setChecked(false);
                TE.setChecked(false);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                finish();
                startActivity(getIntent());
                break;
            case R.id.menu_logout:
                break;
        }
        return true;
    }
}
