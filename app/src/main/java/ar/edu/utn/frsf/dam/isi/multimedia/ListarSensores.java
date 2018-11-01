package ar.edu.utn.frsf.dam.isi.multimedia;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.List;

public class ListarSensores extends AppCompatActivity {

    private ListView listaSensores;
    private SensorArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_sensores);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.barraHerramientas);
        setSupportActionBar(myToolbar);

        SensorManager mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        List<Sensor> sensores = mgr.getSensorList(Sensor.TYPE_ALL);

        listaSensores = (ListView) findViewById(R.id.listaSensores);
        adapter = new SensorArrayAdapter(this,0,sensores);
        listaSensores.setAdapter(adapter);

    }

}
