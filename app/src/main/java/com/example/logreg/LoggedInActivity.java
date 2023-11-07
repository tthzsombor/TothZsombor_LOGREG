package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoggedInActivity extends AppCompatActivity {

    private TextView loggedtextview;
    private Button loggedkijelentkezesbutton;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        init();
        loggedkijelentkezesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoggedInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void bejelentkezve() {
        Cursor adatok = dbHelper.bejelentkezve();
        if (adatok.getCount() == 0) {
            Toast.makeText(LoggedInActivity.this,
                    "Nincs az adatbázisban bejegyzés",
                    Toast.LENGTH_SHORT).show();
        } else {
            StringBuffer builder = new StringBuffer();
            while(adatok.moveToNext()) {
                builder.append("Teljes név:").append(adatok.getString(4)).append("\n");
            }
             loggedtextview.setText(builder);
        }
    }


    public void init()
    {
        loggedtextview=findViewById(R.id.loggedtextview);
        loggedkijelentkezesbutton=findViewById(R.id.loggedkijelentkezesbutton);
        dbHelper = new DBHelper(LoggedInActivity.this);
        bejelentkezve();

    }
}