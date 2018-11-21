package ayjw.pe.usernamegen;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
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
    TextView tvResult;
    ListView lv;
    ArrayAdapter<String> ad;

    ArrayList<String> nameY, nameM, nameD;

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
        lv = findViewById(R.id.lv);

        nameY = new ArrayList<String>();
        nameM = new ArrayList<String>();
        nameD = new ArrayList<String>();
        ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameY);
        lv.setAdapter(ad);

        // Set debug value
        // tvResult.setText(inputY + "/" + inputM inputD);

        // Get name list
        getNamePool(nameY, R.raw.pool_year, ad);
        getNamePool(nameM, R.raw.pool_month, null);
        getNamePool(nameD, R.raw.pool_day, null);

        // Update name
        tvResult.setText(genName(inputY, inputM, inputD));
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
