package com.example.VKB;

import java.util.ArrayList;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public  class MeasurementPointsAdapter extends BaseAdapter {
    public MeasurementPointsAdapter() {	
    	}
	private ArrayList<MeasurementsPointsRecord>measurements=new ArrayList<MeasurementsPointsRecord>();
	public int getCount() {
		// TODO Auto-generated method stub
		return measurements.size();
	}

	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return getItem(index);
	}

	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	public View getView(int index, View view, ViewGroup parent) {
		if (view == null) {
			LayoutInflater inflater =LayoutInflater.from(parent.getContext());
			view = inflater.inflate(R.layout.measurement_list_item, parent, false);
		}		
		MeasurementsPointsRecord measpointrec = measurements.get(index);
		
		TextView XtextView = (TextView)view.findViewById(R.id.mobileXnumber);
		XtextView.setText(measpointrec.getMobileX());

		TextView YtextView = (TextView)view.findViewById(R.id.mobileYnumber);
		YtextView.setText(measpointrec.getMobileY());
		
		TextView ZtextView = (TextView)view.findViewById(R.id.mobileZnumber);
		ZtextView.setText(measpointrec.getMobileZ());
		
		TextView TransNumber = (TextView)view.findViewById(R.id.TransmitterNumber);
		TransNumber.setText(measpointrec.getTransmitterNumber());
		
		TextView MobileNumber = (TextView)view.findViewById(R.id.MobileNumber);
		MobileNumber.setText(measpointrec.getMobileNumber());
		
		TextView DisttextView = (TextView)view.findViewById(R.id.distance);
		DisttextView.setText(measpointrec.getDistance());
		
		return view;
	}
	
	/** Adds new measurementsPointsRecord object at the end of MeasurementsPoints*/
	public void addMeasurementPoints(MeasurementsPointsRecord measurementsPointsRecord) {
    	measurements.add(measurementsPointsRecord);
		
	}


}
