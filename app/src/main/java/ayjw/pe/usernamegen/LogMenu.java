package ayjw.pe.usernamegen;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.GetChars;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-11-19.
 */

public class LogMenu extends AppCompatActivity {
    int selectedItem;

    TextView tvCurrent;
    Button btnReturn, btnDelete;
    String currentName;
    String sdDir;
    File logPath;
    ListView lvLog;

    ArrayList<String> nameList;
    ArrayAdapter<String> ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four);

        selectedItem = -1;

        // Link
        tvCurrent = findViewById(R.id.tvLogCurrent);
        btnReturn = findViewById(R.id.btnLogReturn);
        btnDelete = findViewById(R.id.btnLogDelete);
        lvLog = findViewById(R.id.lvNamesLog);

        btnDelete.setEnabled(false);

        //sdcard
        sdDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        sdDir += "/nameGen/";//내 어플의 디렉토리 이름 설정
        logPath = new File(sdDir);
        if(!logPath.isDirectory())
        {
            logPath.mkdir();
            Toast.makeText(getApplicationContext(), "DIR : " + logPath.getAbsolutePath().toString(), Toast.LENGTH_SHORT);
        }

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

        lvLog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = i;

                Toast.makeText(getApplicationContext(), nameList.get(i).toString() + " 선택함!", Toast.LENGTH_SHORT).show();
                btnDelete.setEnabled(true);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedItem != -1)
                {
                    Toast.makeText(getApplicationContext(), nameList.get(selectedItem).toString() + " 삭제함!", Toast.LENGTH_SHORT).show();

                    nameList.remove(selectedItem);
                    ad.notifyDataSetChanged();

                    saveLog(nameList);
                }
            }
        });

        // Get value
        currentName = getIntent().getStringExtra("IN_RESULT");

        // Set value
        if (currentName == null) // Log only
            tvCurrent.setText("닉네임 내역");
        else
        {
            tvCurrent.setText(currentName);

            // Add to log
            nameList.add(currentName);
            saveLog(nameList);
        }
    }

    void loadLog ()
    {
        nameList.clear();

        File logFile = new File(logPath, "log.txt");

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(logFile));
            String line;

            while ((line = br.readLine()) != null)
            {
                nameList.add(line);
            }

            br.close();
        }
        catch (IOException e)
        {
            // e.printStackTrace();
        }
        /*
        nameList.add("멍청이 1");
        nameList.add("멍청이 2");
        nameList.add("멍청이 3");
        */

        if (nameList.size() <= 0)
            Toast.makeText(getApplicationContext(), "기록이 없습니다!", Toast.LENGTH_SHORT).show();

        ad.notifyDataSetChanged();
    }

    void saveLog (ArrayList<String> list)
    {
        // File logFile = new File(logPath, "log.txt");

        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(logPath.getAbsolutePath() + "/log.txt"));

            if (list  != null)
            {
                for (int i=0; i<list.size(); i++)
                {
                    out.write(list.get(i));
                    out.newLine();
                }

                Toast.makeText(getApplicationContext(), "기록 저장 성공!", Toast.LENGTH_SHORT).show();
            }
            out.close();
        }
        catch (IOException e)
        {
            Toast.makeText(getApplicationContext(), "에러 : 기록 쓰는 중 에러! " + e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    void returnPlz ()
    {
        finish();
    }
}
