package com.example.VKB;

import java.io.Serializable;
import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public  class TransmitterPointsAdapter extends BaseAdapter implements  Serializable {
	
    public TransmitterPointsAdapter() {}
	private ArrayList<TransmitterPointsRecord>transmitters=new ArrayList<TransmitterPointsRecord>();
	
	public int getCount() {
		// TODO Auto-generated method stub
		return transmitters.size();
	}

	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return getItem(index);
	}
	
	public ArrayList<TransmitterPointsRecord> getObject(){
		return transmitters;
	}

	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}
	
	/** Removes all elements from calculatedTransmitterCoordinates*/
	public void clear(){
		 transmitters.clear();
	}

	public View getView(int index, View view, ViewGroup parent) {
		if (view == null) {
			LayoutInflater inflater =LayoutInflater.from(parent.getContext());
			view = inflater.inflate(R.layout.transmitter_list_item, parent, false);
		}		
		TransmitterPointsRecord tranCoord = transmitters.get(index);
		
		TextView XtextView = (TextView)view.findViewById(R.id.transmitterXnumber);
		XtextView.setText(tranCoord.getTransmitterX());

		TextView YtextView = (TextView)view.findViewById(R.id.transmitterYnumber);
		YtextView.setText(tranCoord.getTransmitterY());
		
		TextView ZtextView = (TextView)view.findViewById(R.id.transmitterZnumber);
		ZtextView.setText(tranCoord.getTransmitterZ());
		
		TextView TransNumber = (TextView)view.findViewById(R.id.TransmitterPointsNumber);
		TransNumber.setText(tranCoord.getTransmitterNumber());
		
		return view;
	}
	
	/** Adds new transmitterPointsRecord object at the end of transmitterPoints*/
	public void addTransmitterPoints(TransmitterPointsRecord transmitterPointsRecord) {
		transmitters.add(transmitterPointsRecord);	
	}

}
