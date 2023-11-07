package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

private EditText registeremailedittext;
private EditText registerfelhasznaloedittext;
private EditText registerjelszoedittext;
private EditText registernevedittext;
private DBHelper dbHelper;

private Button registerregisztraciobutton;
private  Button registervisszabutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        registerregisztraciobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = registeremailedittext.getText().toString().trim();
                String jelszo = registerjelszoedittext.getText().toString().trim();
                String felhasznalo = registerfelhasznaloedittext.getText().toString().trim();
                String teljesnev = registernevedittext.getText().toString().trim();

                if (email.isEmpty() ||
                        jelszo.isEmpty() ||
                        felhasznalo.isEmpty() ||
                        teljesnev.isEmpty()) {
                    Toast.makeText(RegisterActivity.this,
                            "Minden mező kitöltése kötölező",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Sikeres regisztráció", Toast.LENGTH_SHORT).show();
                    adatHozzaadas();
                }
            }
        });

        registervisszabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void adatHozzaadas() {
        String email = registeremailedittext.getText().toString();
        String felhasznalonev = registerfelhasznaloedittext.getText().toString();
        String jelszo = registerjelszoedittext.getText().toString();
        String teljesnev = registernevedittext.getText().toString();

        if (dbHelper.rogzites(felhasznalonev,jelszo, email, teljesnev)) {
            Toast.makeText(RegisterActivity.this,"Sikeres adatfelvétel", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(RegisterActivity.this, "Sikertelen adatfelvétel", Toast.LENGTH_SHORT).show();
        }
    }

    public void init()
    {
        registeremailedittext=findViewById(R.id.registeremailedittext);
        registerfelhasznaloedittext=findViewById(R.id.registerfelhasznaloedittext);
        registerjelszoedittext=findViewById(R.id.registerjelszoedittext);
        registernevedittext=findViewById(R.id.registernevedittext);
        registerregisztraciobutton=findViewById(R.id.registerregisztraciobutton);
        registervisszabutton=findViewById(R.id.registervisszabutton);
        dbHelper = new DBHelper(RegisterActivity.this);


    }
}