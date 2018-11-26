package ayjw.pe.usernamegen;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by user on 2018-11-19.
 */

public class InfoMenu extends AppCompatActivity
{
    static int MAX_YEAR = 100;
    static int MAX_MONTH = 12;
    static int MAX_DAY = 31;

    DatePicker dp;
    Button btnMake, btnRandom;
    static int currentY, currentM, currentD;

    int getLastYear (int Y)
    {
        return (Y % 10) + ((int)(Y / 10) % 10) * 10;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two);

        dp = findViewById(R.id.datePicker);
        btnMake = findViewById(R.id.btnMake);
        btnRandom = findViewById(R.id.btnMakeRandom);

        Calendar calendar = Calendar.getInstance();

        int ynow = calendar.get(Calendar.YEAR), mnow = calendar.get(Calendar.MONTH), dnow = calendar.get(Calendar.DAY_OF_MONTH);

        currentY = getLastYear(ynow);
        currentM = mnow - 1;
        currentD = dnow - 1;

        dp.init(ynow, mnow, dnow, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int Y, int M, int D) {
                currentY = getLastYear(Y);
                currentM = M;
                currentD = D - 1;
            }
        });



        btnMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoHell();
            }
        });

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Make random
                Random rd = new Random();

                currentY = rd.nextInt(MAX_YEAR);
                currentM = rd.nextInt(MAX_MONTH);
                currentD = rd.nextInt(MAX_DAY);

                gotoHell();
            }
        });
    }

    void gotoHell ()
    {
        Intent intent = new Intent(getBaseContext(), ResultMenu.class);

        // Send value
        intent.putExtra("IN_Y", currentY);
        intent.putExtra("IN_M", currentM);
        intent.putExtra("IN_D", currentD);

        startActivity(intent);
        finish();
    }
}
