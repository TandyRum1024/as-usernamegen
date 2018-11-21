package ayjw.pe.usernamegen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-11-19.
 */

public class LogMenu extends AppCompatActivity {
    TextView tvCurrent;
    Button btnReturn, btnDelete;
    String currentName;
    ListView lvLog;

    ArrayList<String> nameList;
    ArrayAdapter<String> ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four);

        // Link
        tvCurrent = findViewById(R.id.tvLogCurrent);
        btnReturn = findViewById(R.id.btnLogReturn);
        btnDelete = findViewById(R.id.btnLogDelete);
        lvLog = findViewById(R.id.lvNamesLog);

        nameList = new ArrayList<String>();
        ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);
        lvLog.setAdapter(ad);

        loadLog();

        // Listeners
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnPlz();
            }
        });

        // Get value
        currentName = getIntent().getStringExtra("IN_RESULT");

        // Set value
        if (currentName == null) // Log only
            tvCurrent.setText("닉네임 내역");
        else
            tvCurrent.setText(currentName);
    }

    void loadLog ()
    {
        nameList.clear();
        nameList.add("멍청이 1");
        nameList.add("멍청이 2");
        nameList.add("멍청이 3");

        ad.notifyDataSetChanged();
    }

    void returnPlz ()
    {
        finish();
    }
}
