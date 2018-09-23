package net.leocadio.joao.conversor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            //home
//            case R.id.action_home:
//                Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
//                Intent home = new Intent(this, MainActivity.class);
//                startActivity(home);
//                break;

            //Medidas
            case R.id.action_med:
                Toast.makeText(getApplicationContext(), "Medidas", Toast.LENGTH_SHORT).show();
                Intent med = new Intent(this, MedidasActivity.class);
                startActivity(med);
                break;

            //Temperaturas
            case R.id.action_temp:
                Toast.makeText(getApplicationContext(), "Temperaturas", Toast.LENGTH_SHORT).show();
                Intent temp = new Intent(this, TemperaturasActivity.class);
                startActivity(temp);
                break;

            default:

        }

        return super.onOptionsItemSelected(item);
    }

}
