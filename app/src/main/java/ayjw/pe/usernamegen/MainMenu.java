package ayjw.pe.usernamegen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by user on 2018-11-19.
 */

public class MainMenu extends AppCompatActivity
{
    Button btnBegin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one);

        btnBegin = findViewById(R.id.btnMainBegin);

        btnBegin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                gotoHell();
            }
        });
    }

    void gotoHell ()
    {
        // InfoMenu.class = any destination
        Intent intent = new Intent(this, InfoMenu.class);

        startActivity(intent);
    }
}
