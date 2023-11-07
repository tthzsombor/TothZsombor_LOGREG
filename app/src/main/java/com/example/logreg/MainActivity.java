package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mainfelhazsnaloedittext;
    private EditText mainjelszoedittext;
    private Button mainbejelentkezesbutton;
    private Button mainregisztraciobutton;
    private DBHelper dbHelper;
    public String bejelentkezve="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mainregisztraciobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mainbejelentkezesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String felhasznalo = mainfelhazsnaloedittext.getText().toString().trim();
                String jelszo = mainjelszoedittext.getText().toString().trim();
                if (felhasznalo.isEmpty() ||
                        jelszo.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Minden mező kitöltése kötölező",
                            Toast.LENGTH_SHORT).show();
                } else {
                    bejelentkezes();
                    bejelentkezve=mainfelhazsnaloedittext.getText().toString();
                }

            }
        });
    }


    public void bejelentkezes() {
        Cursor adatok = dbHelper.bejelentkezve();
        String felhasznalo=mainfelhazsnaloedittext.getText().toString();
        String jelszo=mainjelszoedittext.getText().toString();

        if (adatok.getCount() == 0) {
            Toast.makeText(this,
                    "Nincs az adatbázisban bejegyzés",
                    Toast.LENGTH_SHORT).show();
        }
        else if(felhasznalo != adatok.getString(2) || jelszo != adatok.getString(3))
        {
            Toast.makeText(MainActivity.this, "Nem megfelelő felhasználónév vagy jelszó", Toast.LENGTH_SHORT).show();
        }
        else {
            StringBuffer builder = new StringBuffer();
            while(adatok.moveToNext()) {
                builder.append("ID:").append(adatok.getInt(0)).append("\n");
                builder.append("Email:").append(adatok.getString(1)).append("\n");
                builder.append("Felhasznalonév:").append(adatok.getString(2)).append("\n");
                builder.append("Jelszó:").append(adatok.getString(3)).append("\n");
                builder.append("Teljes név:").append(adatok.getInt(4)).append("\n\n");
            }
        }
    }

    public void init()
    {
        mainfelhazsnaloedittext=findViewById(R.id.mainfelhazsnaloedittext);
        mainjelszoedittext=findViewById(R.id.mainjelszoedittext);
        mainbejelentkezesbutton=findViewById(R.id.mainbejelentkezesbutton);
        mainregisztraciobutton=findViewById(R.id.mainregisztraciobutton);
        dbHelper = new DBHelper(MainActivity.this);
    }
}