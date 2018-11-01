package ar.edu.utn.frsf.dam.isi.multimedia;

import android.content.Context;
import android.hardware.Sensor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SensorArrayAdapter extends ArrayAdapter<Sensor> {

    public SensorArrayAdapter(@NonNull Context context, int resource,List<Sensor> datos) {
        super(context,resource,datos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.fila_sensor,null);
        }
        TextView tv1 = v.findViewById(R.id.fila_dato);
        TextView tv2 = v.findViewById(R.id.fila_detalle);
        Sensor s1 = getItem(position);
        tv1.setText(s1.getName());
        tv2.setMinLines(5);
        tv2.setLines(7);
        tv2.setMaxLines(10);
        StringBuilder sb = new StringBuilder();
        sb.append("TIPO: "+s1.getStringType()+"\r\n");
        sb.append("Vendor: "+s1.getVendor()+"\r\n");
        sb.append("Delay: ("+s1.getMinDelay()+","+s1.getMaxDelay()+")"+"\r\n");
        sb.append("Energia: "+s1.getPower()+"\r\n");
        sb.append("Version: "+s1.getVersion()+"\r\n");
        sb.append("Resolucion: "+s1.getResolution()+"\r\n");
        tv2.setText(sb.toString());
        return v;
    }
}
