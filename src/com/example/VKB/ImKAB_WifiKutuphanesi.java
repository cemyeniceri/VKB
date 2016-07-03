/*******************************************************************************
 * ImKAB_WifiKutuphanesi.java
 *
 * AMA�:
 *
 * Bu s�n�f ile ImKAB_WifiKutuphanesi i�lemleri ger�eklenmektedir. ImKAB_WifiKutuphanesi
 * Wifi �zerinden dosya alma ve dosya g�nderme i�lemlerini yap�labilmektedir. 
 * Bu s�n�f Im_WifiVeriGonder ve Im_WifiVeriAl fonksiyonlar�na sahiptir.
 *
 * ER���M: Public
 *
 * GLOBAL DE���KENKER:
 * 
 * Socket soket
 *
 * FONKS�YON PROTOT�PLER�:
 *
 * public void Im_WifiVeriGonder( final String str_ip_adres, 
 *								  final int i_port_numarasi, 
 *								  final String str_gonderilecek_veri )
 *
 * public void Im_WifiVeriAl( final int i_port_numarasi,
 *							  final String str_dosya_yolu,
 *							  final String str_dosya_adi )
 * 
 * GEL��T�RME GE�M���:
 *
 * Yazar: Cem YEN��ER�
 * Tarih: 09.05.2013
 * G�ncelleme Tarihi: 
 * Versiyon: v_1.0
 *
 */

package com.example.VKB;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ImKAB_WifiKutuphanesi 
{
	Socket soket;
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				Im_WifiVeriGonder
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile belirtilen dosya yolu �zerinde bulunan bir XML
	 * dosyas�  belirtilen IP adresi ve port numaras�na g�nderilebilmektedir.
	 *
	 * ER���M: Public
	 * 
	 * PARAMETRELER: 
	 * 			ADI						T�P�				A�IKLAMASI
	 *			str_ip_adres			String				G�nderilecek olan IP adresine ait bilgilerin tutuldu�u parametredir.
	 *			i_port_numarasi			int					G�nderme i�lemi i�in kullan�lacak olan Port Numaras� bilgisinin tutuldu�u parametredir.
	 *			str_gonderilecek_veri	String				G�nderilecek olan dosyaya ait yolun tutuldu�u parametredir.
	 *
	 * D�N��:	
	 * 			ADI						T�P�				A�IKLAMASI		
	 * 
	 * GEREKL�L�K:	
	 *
	 ********************************************************************************************/	
	public void Im_WifiVeriGonder( final String str_ip_adres, 
								   final int i_port_numarasi, 
								   final String str_gonderilecek_veri
								  )
	{
		/**
		 * Api level 11'den sonraki s�r�mlerde soket i�lemlerinin yap�labilmesi i�in bir thread yarat�lmas�
		 * gerekmektedir. Bu nedenle soket ile yap�lacak i�lemler bir thread ve run fonksiyonu alt�nda
		 * yaz�lm��t�r.
		 */
		new Thread( new Runnable() 
		{
			/*Thread in �al��t�rd��� run() fonksiyonu olu�turulmu�tur.*/
			public void run() 
			{    
				try 
				{
					/* Belirtilen Ip Adresi ve Port Numaras� i�in soket olu�turulmu�tur. */
	                soket = new Socket( str_ip_adres, i_port_numarasi ); 
	                
	                	/** 
	                	 * Verilen dosya yolu �zerindeki dosya, File tipinde dosya olu�turularak
	                	 * okunmu�tur.
	                	 */
		                File dosya = new File ( str_gonderilecek_veri );
		                
		                /*Dosyan�n g�nderilebilmesi i�in dosya boyutunda bir byte dizisi a��lm��t�r.*/
		                byte [] byte_dizi = new byte [ (int) dosya.length() ];
		                
		                /*A��lan FileInputStream yard�m� ile a��lan dosya byte olarak okunmu�tur.*/
		                FileInputStream fis_dosyadan_byte_oku = new FileInputStream( dosya );
		                
		                /*FileInputStream ile okunan bytelar diziye yaz�lmak i�in  BufferedInputStream'de tutulmu�tur.*/
						BufferedInputStream okunan_bytelari_buffara_yaz = new BufferedInputStream( fis_dosyadan_byte_oku );
		                
		                /**
		                 * Dosyadan okunan stream �eklindeki veriler read methodu ile 0. indexten ba�lanarak
		                 * uzunlu�u kadar okunup ByteDizi de�i�kenine kaydedilmi�tir.
		                 */
		                okunan_bytelari_buffara_yaz.read( byte_dizi, 0, byte_dizi.length);
		                
		                /*OutputStream yard�m� ile soket g�nderme i�lemi i�in tan�mlanm��t�r.*/
		                OutputStream byte_olarak_ciktisini_gonder = soket.getOutputStream();
		                
		                /** 
		                 * G�nderme i�lemi write metodu ile yap�lm�� ve flush metodu ile kapat�lm��t�r.
		                 * ilk parametre olarak ByteDizi de�i�keni yaz�l�p, dizinin 0. parametresinden ba�lanarak
		                 * dizinin boyu kadar veri yaz�lm��t�r. 
		                 */
		                byte_olarak_ciktisini_gonder.write( byte_dizi, 0, byte_dizi.length );
		                byte_olarak_ciktisini_gonder.flush();
		                
		                /*��lemi bitiren soket kapat�lm��t�r.*/
		                soket.close();
				}
				catch (IOException e) 
				{
				} 
			}
		}).start();
	}

	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				Im_WifiVeriAl
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon belirtilen port numaras�ndan, istemci taraf�ndan g�nderilen
	 * dosyay� al�p belirtilen dosya yoluna kaydeden fonksiyondur.
	 *  
	 *
	 * ER���M: Public
	 * 
	 * PARAMETRELER: 
	 * 			ADI						T�P�				A�IKLAMASI
	 *			i_PortNumarasi			int					Al�m i�lemi i�in kullan�lacak olan Port Numaras� bilgisinin tutuldu�u parametredir.
	 *			str_dosya_yolu			String				Al�nan dosyan�n kaydedilece�i yolun tutuldu�u parametredir.(/sdcard/ �eklinde olmal�d�r.)
	 *			str_dosya_adi			String				Al�nan dosyan�n kaydedilece�i ismin tutuldu�u de�i�kendir.
	 * 
	 * D�N��:	
	 * 			ADI						T�P�				A�IKLAMASI			
	 *
	 * GEREKL�L�K: 					Cihaz�n Wi-Fi �zelli�inin a��k olup, IP numaras�n�n  ve port adresinin 
	 * g�nderici cihaz taraf�ndan bilinmesi gereklidir.  
	 *
	 *********************************************************************************************/	
	public void Im_WifiVeriAl( final int i_port_numarasi,
							   final String str_dosya_yolu,
							   final String str_dosya_adi
							 )
	{
		/**
		 * Api level 11'den sonraki s�r�mlerde soket i�lemlerinin yap�labilmesi i�in bir thread yarat�lmas�
		 * gerekmektedir. Bu nedenle soket ile yap�lacak i�lemler bir thread ve run fonksiyonu alt�nda
		 * yaz�lm��t�r.
		 */
		new Thread(new Runnable() 
		{
			public void run()
			{
				/** 
				 * Veri al�m� yapabilmek i�in ServerSocket s�n�f�ndan nesnemize referans olacak server_soket
				 * �rne�imiz yarat�ld�.Hen�z bir nesneye referans olmad���ndan null olarak ilk de�eri verildi. 
				 */
				ServerSocket sunucu_soket = null;
				
				/**
				 * Veri g�ndermek isteyen istemciye ait soketi tutan de�i�kendir.
				 * Ba�lang�� de�eri hen�z bir istemci olmad��� i�in null olarak atanm��t�r.
				 */
	    	    Socket istemci_soket = null;
	    	    
	    	    /** 
	    	     * �stemci soketinden gelen girdi ak���n� tutabilmek i�in olu�turulmu�tur.
	    	     * Ba�lang�� de�eri hen�z bir girdi ak��� olmad��� i�in null olarak atanm��t�r
	    	     */
	    	    InputStream girdi_akisi = null;
	    	    
	    	    /* �stenilen yerde dosya olu�turabilmek i�in olu�turulmu�tur. */
	    	    FileOutputStream dosya_cikti_akisi = null;
	    	    
	    	    /*��kt� ak���n�n sa�lanabilmesi i�in tamponlanmis_cikti_akisi referans de�i�keni yarat�ld�.*/
	    	    BufferedOutputStream tamponlanmis_cikti_akisi;
	    	    
	    	    int i_dosya_boyutu = 10000000;		/*Ba�lang��ta dosya boyutuna ilk de�er olarak 10000000 verildi.*/
	    	    int i_okunan_byte_sayisi = 0;		/*Ba�lang��ta okunan byte de�eri 0 olarak verildi. */
	    	    int i_durum = 0;					/*Daha sonra kullan�lmak �zere i_durum de�i�keni yarat�ld�. */

			    try 
			    {
			    	
			    	/* �stenilen port numaras�nda soket a��lm��t�r. �nternet �zerinde ileti�im soketler arac�l���yla yap�lmaktad�r. */
					sunucu_soket = new ServerSocket( i_port_numarasi );
					
					/** 
					 * �stemci taraf�ndan gelcek istekler accept() metodu ile beklenir. 
					 * Bekelenen istek geldi�inde istemci_soket tipinde Socket nesnesi d�nd�r�r. 
					 */
					istemci_soket = sunucu_soket.accept();
					
					/* dosyay� tutabilmek i�in byte dizi olu�turulmu�tur. */
					byte[] bytedizi = new byte[ i_dosya_boyutu ];   
					
					/** 
					 * �stemci soket taraf�ndan g�nderilmek istenen veriyi, okunmak �zere girdi_akisi olarak
					 * getInputStream() metodu ile d�nd�rd�k.
					 */
					girdi_akisi = istemci_soket.getInputStream();
					
				    try 
				    {
				    	/* olu�turulacak dosyan�n yolu verilmi�tir. */
						dosya_cikti_akisi = new FileOutputStream( str_dosya_yolu + str_dosya_adi + ".xml" );
						
					} 
				    
				    catch (FileNotFoundException e)
				    {
						e.printStackTrace();
					}
			    
				    /* yolu belirlenen dosya i�in tampon olu�turulur.*/
				    tamponlanmis_cikti_akisi = new BufferedOutputStream( dosya_cikti_akisi );
				    
				    /**
				     * Girdi akisina d�nd�r�len veri 0. indeksten ba�lanarak sonuna kadar byte byte okunup bytedizi ye kaydedilmi�tir.
				     * Okunan byte say�s� i_okunan_byte de�i�kenine atanm��t�r. 
				     */
					i_okunan_byte_sayisi = girdi_akisi.read( bytedizi, 0, bytedizi.length );
				    i_durum = i_okunan_byte_sayisi;
		
			    
				    /**
				     * Bu kod blo�u dosya okuma i�lemi tamamlanana kadar �al��acakt�r.
				     */
				    do
				    {
				        try
				        {
				        	/* E�er okunmayan veriler varsa kald��� yerden okumaya devam edecektir.*/
							i_okunan_byte_sayisi = girdi_akisi.read( bytedizi, i_durum, ( bytedizi.length - i_durum ) );
						} 
				        
				        catch (IOException e)
				        {
							e.printStackTrace();
						}
			        
				        /*e�er bu blok i�erisinde veri okunabilmi� ise i_durum de�i�kenine okunana byte miktar� eklenecektir.*/
				        if ( i_okunan_byte_sayisi >= 0 )
				        {
				            i_durum += i_okunan_byte_sayisi;
				        }
				        
				    } while ( i_okunan_byte_sayisi > -1 );  /* girdi_akisi.read() komutu okunacak bir veri kalmad���nda -1 de�erini d�nd�r�r.*/
			    
			    	/*yolu belirlenen dosya i�in olu�turulan tampona bytedizi de bulunan veriler 0. indexten ba�lanarak yaz�l�r. */
					tamponlanmis_cikti_akisi.write( bytedizi, 0, i_durum );
					
					/* tamponlamis_cikti_akisi temizlenir.*/
					tamponlanmis_cikti_akisi.flush();
					
					/*tamponlanmis_cikti_akisi kapat�l�r.*/
					tamponlanmis_cikti_akisi.close();
					
					/*girdi_akisi kapat�l�r.*/
					girdi_akisi.close();
					
					/*istemci_soket kapat�l�r.*/
					istemci_soket.close();
					
					/*sunucu_soket kapat�l�r.*/
					sunucu_soket.close();
					
				}
			    catch (IOException e)
			    {
					e.printStackTrace();
				}
			}
	   }).start();
	}
}