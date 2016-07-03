package com.example.VKB;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ListView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;


public class MeasurementPointsListActivity extends Activity {
	
    ArrayList<MeasurementsPointsRecord>data = new ArrayList<MeasurementsPointsRecord>();
	MeasurementPointsAdapter measurementpointsAdapter;
	TransmitterPointsAdapter transmitterPointsAdapter;
	
    public static ArrayList<TransmitterPointsRecord>sayfagecisi = new ArrayList<TransmitterPointsRecord>();


	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_points);
        
        ListView listView = (ListView)findViewById(R.id.MobilePoints_list);
        measurementpointsAdapter = new MeasurementPointsAdapter();
        listView.setAdapter(measurementpointsAdapter);
        

        xmlLibrary xml = new xmlLibrary();
        data=xml.xmlgen("MeasurementPoints",data);
        
        /**measurement points ekranýna parse edilen deðerler basýlmýþtýr.*/
        for(int i=0; i<data.size(); i++){
        	measurementpointsAdapter.addMeasurementPoints(new MeasurementsPointsRecord(data.get(i).getMobileX(), data.get(i).getMobileY(), data.get(i).getMobileZ(),data.get(i).getMobileNumber(),data.get(i).getTransmitterNumber(),data.get(i).getDistance()));
        	}
        }
   
    public void onCancel (View view){
    	finish();
    }
    
    @SuppressWarnings("unchecked")
	public void onCalculate (View view){
    	
        ArrayList<MeasurementsPointsRecord>temp = new ArrayList<MeasurementsPointsRecord>();

        transmitterPointsAdapter = new TransmitterPointsAdapter();
		double [][] sonucHesaplananDouble = new double[3][1];
		 
		double distance [];
		double mobilePointDouble[][];
		
		int x=1;
		 
		 /** Deletes all elements of transmitterPointsAdapter */
		 transmitterPointsAdapter.clear();
		
		//tüm döngüyü tarar ve her bir transmitter i gruplar.	
		
		while(true){
			int counter =0;
			
			for(int i=0; i< data.size(); i++){
				if(data.get(i).getTransmitterNumber().equals(String.valueOf(x))){
					temp.add(counter,data.get(i));
					counter++;
					}
				}
			x++;
			if(temp.isEmpty()){
				break;
			}
			
	    	/**Length of distance matrix depends on Mobile Points*/
		     distance = new double[temp.size()];
		     
		     /** [3] corresponds to (x,y,z) coordinates of Mobile Point */
			 mobilePointDouble = new double[temp.size()][3];
			 
			for(int i=0; i<temp.size(); i++){
				MeasurementsPointsRecord measurementsPointsRecord = temp.get(i);
				mobilePointDouble[i][0] = Double.valueOf(measurementsPointsRecord.getMobileX());
				mobilePointDouble[i][1] = Double.valueOf(measurementsPointsRecord.getMobileY());
				mobilePointDouble[i][2] = Double.valueOf(measurementsPointsRecord.getMobileZ());
				distance[i] = Double.valueOf(measurementsPointsRecord.getDistance());
			}
			
			sonucHesaplananDouble = IcKonFunc.enAzKareler(mobilePointDouble,distance);
			transmitterPointsAdapter.addTransmitterPoints(new TransmitterPointsRecord(Double.toString(sonucHesaplananDouble[0][0]), Double.toString(sonucHesaplananDouble[1][0]), Double.toString(sonucHesaplananDouble[2][0]),Integer.toString(x-1)));
			transmitterPointsAdapter.notifyDataSetChanged();
			//dfsfsdf
			temp.clear();
		}
		
    	Intent intent2 = new Intent(this, TransmitterPointsListActivity.class);
    	for(int i = 0;i<transmitterPointsAdapter.getObject().size();i++){    
    		sayfagecisi.add(i, transmitterPointsAdapter.getObject().get(i)); 
    	}
		startActivity(intent2);
    }
}
