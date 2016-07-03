package com.example.VKB;

import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onDownloadData(View v){

    	/**MKB den wifi üzerinden dosya alýnýp MeasurementPoints.xml adýnda kaydedilir.*/
    	ImKAB_WifiKutuphanesi wifideneme = new ImKAB_WifiKutuphanesi();
		wifideneme.Im_WifiVeriAl(8060, "/sdcard/", "MeasurementPoints");
		
		/**Dosya inene kadar sayfa geçiþine izin vermez.Ancak uygulama çalþtýrýlmadan
		 *  önce o yoldaki dosyanýn silinmesi gereklidir.*/
		File belge_kontrol = new File( "/sdcard/MeasurementPoints.xml");
		while ( true ){
		if(belge_kontrol.exists()){
			Toast.makeText(getApplicationContext(),
                    "File Received", Toast.LENGTH_SHORT).show();
			break;
		}}
		Intent intent1 = new Intent(this, MeasurementPointsListActivity.class);
		startActivity(intent1);
    }
}