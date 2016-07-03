package com.example.VKB;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.app.Activity;



public class TransmitterPointsListActivity extends Activity {
	
	TransmitterPointsAdapter transmitterpointsAdapter;
	private ArrayList<TransmitterPointsRecord>transmittersIntent=new ArrayList<TransmitterPointsRecord>();

	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transmitter_points);                
        ListView listView = (ListView)findViewById(R.id.TransmitterPoints_list);
        transmitterpointsAdapter = new TransmitterPointsAdapter();
        
        transmittersIntent = MeasurementPointsListActivity.sayfagecisi;
        for(int i=0;i<transmittersIntent.size();i++){
        	transmitterpointsAdapter.addTransmitterPoints(transmittersIntent.get(i));
        }
        listView.setAdapter(transmitterpointsAdapter);
    }
    
    
    public void onCancel (View view){
    	transmittersIntent.clear();
    	finish();
    }
    
    
    /** Hesaplanan verici koordinatlarý xml dosyasýna kaydedilip wifi üzerinden gönderilmektedir. */
    public void onUpload(View view){
    	xmlLibrary xmlGen = new xmlLibrary();
    	xmlGen.Im_XMLOlustur("TransmitterPoints", "TransmitterPoints", "TransmitterPoints","Transmitters", transmitterpointsAdapter.getObject());
    	ImKAB_WifiKutuphanesi wifideneme = new ImKAB_WifiKutuphanesi();
		wifideneme.Im_WifiVeriGonder("192.168.2.8", 8060, "/mnt/sdcard/TransmitterPoints/TransmitterPoints.xml");
    }
}