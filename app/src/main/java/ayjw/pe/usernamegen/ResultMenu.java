package ayjw.pe.usernamegen;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by user on 2018-11-19.
 */

public class ResultMenu extends AppCompatActivity {
    int inputY, inputM, inputD;
    String resultName;
    ArrayList<String> nameY, nameM, nameD;
    Button btnReset, btnSave, btnLog;

    //    ArrayAdapter<String> ad;
//    ListView lv;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three);

        // Get value
        Intent currentIntent = getIntent();
        inputY = currentIntent.getIntExtra("IN_Y", 0);
        inputM = currentIntent.getIntExtra("IN_M", 0);
        inputD = currentIntent.getIntExtra("IN_D", 0);

        // Setup link
        tvResult = findViewById(R.id.tvResult);
        btnSave = findViewById(R.id.btnResultSave);
        btnLog = findViewById(R.id.btnResultLog);
        btnReset = findViewById(R.id.btnResultReset);
//        lv = findViewById(R.id.lv);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoHell(resultName);
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoHell(null);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoStart();
            }
        });

        nameY = new ArrayList<String>();
        nameM = new ArrayList<String>();
        nameD = new ArrayList<String>();
//        ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameY);
//        lv.setAdapter(ad);

        // Get name list
        getNamePool(nameY, R.raw.pool_year, null);
        getNamePool(nameM, R.raw.pool_month, null);
        getNamePool(nameD, R.raw.pool_day, null);

        // Update name
        // resultName = genName(inputY, inputM, inputD);
//        tvResult.setText(resultName);

        tvResult.setText("SIZE : " + nameY.size() + "/" + nameM.size() + "/" + nameD.size());
    }

    void gotoHell (String name)
    {
        Intent intent = new Intent(getBaseContext(), LogMenu.class);

        // Send value
        intent.putExtra("IN_RESULT", resultName);

        startActivity(intent);
    }

    void gotoStart ()
    {
        Intent intent = new Intent(getBaseContext(), InfoMenu.class);

        startActivity(intent);
        finish();
    }

    String genName (int Y, int M, int D)
    {
        return (nameY.get(Y) + nameM.get(M) + nameD.get(D));
    }

    void getNamePool (ArrayList<String> dest, int resid, ArrayAdapter<String> ad)
    {
        InputStream raw = getResources().openRawResource(resid);

        if (raw != null)
        {
            try {
                InputStreamReader fis = new InputStreamReader(raw, "utf-8");
                BufferedReader bullshit = new BufferedReader(fis);

                // delete this
                dest.clear();

                String tmp = bullshit.readLine();
                while (tmp != null)
                {
                    dest.add(tmp);
                    tmp = bullshit.readLine();
                }

                if (ad != null)
                    ad.notifyDataSetChanged();
                raw.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
