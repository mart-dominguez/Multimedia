package ar.edu.utn.frsf.dam.isi.multimedia;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SensorTemperaturaActivity extends AppCompatActivity {

    private SensorManager sensorMng;
    private Sensor sensorLuz;
    private TextView resultado;
    private TextView titulo;

    private float luzMinimo=Float.MAX_VALUE;
    private float luzMaximo=Float.MIN_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_temperatura);
        resultado = (TextView) findViewById(R.id.resultado);
        titulo   = (TextView) findViewById(R.id.titulo);
        resultado.setLines(5);
        resultado.setMinLines(5);
        sensorMng = (SensorManager) getSystemService(getApplicationContext().SENSOR_SERVICE);
        sensorLuz = sensorMng.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    private SensorEventListener listenerSensorLuz = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float valor = sensorEvent.values[0];
            if(valor >luzMaximo) luzMaximo=valor;
            if(valor <luzMinimo) luzMinimo=valor;
            StringBuilder sb =new StringBuilder();
            sb.append("Presicion:" +sensorEvent.accuracy+"ts ["+sensorEvent.timestamp+"]"+"\r\n");
            sb.append("LUZ actual "+valor+ "\r\n");
            sb.append("Maximo: "+luzMaximo+ "\r\n");
            sb.append("Minimo: "+luzMinimo+ "\r\n");
            calcularColor(valor);
            resultado.setText(sb.toString());
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

        private void calcularColor(float lux){
            int color = Color.BLACK;
            if(lux<3){
                color = ContextCompat.getColor(SensorTemperaturaActivity.this, R.color.color1);
            }
            if(lux>3 && lux<6){
                color = ContextCompat.getColor(SensorTemperaturaActivity.this, R.color.color2);
            }
            if(lux>6 && lux<9){
                color = ContextCompat.getColor(SensorTemperaturaActivity.this, R.color.color3);
            }
            if(lux>9 && lux<11){
                color = ContextCompat.getColor(SensorTemperaturaActivity.this, R.color.color4);
            }
            if(lux>11 && lux<15){
                color = ContextCompat.getColor(SensorTemperaturaActivity.this, R.color.color5);
            }
            if(lux>15 && lux<18){
                color = ContextCompat.getColor(SensorTemperaturaActivity.this, R.color.color6);
            }
            if(lux>18 && lux<22){
                color = ContextCompat.getColor(SensorTemperaturaActivity.this, R.color.color7);
            }
            if(lux>22 && lux<25){
                color = ContextCompat.getColor(SensorTemperaturaActivity.this, R.color.color8);
            }
            if(lux>25 && lux<30){
                color = ContextCompat.getColor(SensorTemperaturaActivity.this, R.color.color9);
            }
            if(lux>20 && lux<35){
                color = ContextCompat.getColor(SensorTemperaturaActivity.this, R.color.color99);
            }
            if(lux>35 && lux<45){
                color = ContextCompat.getColor(SensorTemperaturaActivity.this, R.color.color11);
            }
            if(lux>45 && lux<75){
                color = ContextCompat.getColor(SensorTemperaturaActivity.this, R.color.color12);
            }
            if(lux>75 && lux<95){
                color = ContextCompat.getColor(SensorTemperaturaActivity.this, R.color.color13);
            }
            if(lux>95 && lux<105){
                color = ContextCompat.getColor(SensorTemperaturaActivity.this, R.color.color14);
            }
            if(lux>105 ){
                color = ContextCompat.getColor(SensorTemperaturaActivity.this, R.color.color15);
            }
            titulo.setBackgroundColor(color);
        }
    };

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        sensorMng.registerListener(listenerSensorLuz, sensorLuz, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorMng.unregisterListener(listenerSensorLuz);
    }
}
