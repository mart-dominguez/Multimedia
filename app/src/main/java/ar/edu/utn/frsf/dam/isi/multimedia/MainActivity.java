package ar.edu.utn.frsf.dam.isi.multimedia;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.barraHerramientas);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mi_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.foto:
                Intent i1 = new Intent(this,SacarFotoActivity.class);
                startActivity(i1);
                return true;
            case R.id.audio:
                return true;
            default:
                Toast.makeText(this, "ESTA OPCION NO TIENE ACCION ASOCIADA", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }

}
