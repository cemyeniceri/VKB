/*******************************************************************************
 * ImKAB_WifiKutuphanesi.java
 *
 * AMAÇ:
 *
 * Bu sýnýf ile ImKAB_WifiKutuphanesi iþlemleri gerçeklenmektedir. ImKAB_WifiKutuphanesi
 * Wifi üzerinden dosya alma ve dosya gönderme iþlemlerini yapýlabilmektedir. 
 * Bu sýnýf Im_WifiVeriGonder ve Im_WifiVeriAl fonksiyonlarýna sahiptir.
 *
 * ERÝÞÝM: Public
 *
 * GLOBAL DEÐÝÞKENKER:
 * 
 * Socket soket
 *
 * FONKSÝYON PROTOTÝPLERÝ:
 *
 * public void Im_WifiVeriGonder( final String str_ip_adres, 
 *								  final int i_port_numarasi, 
 *								  final String str_gonderilecek_veri )
 *
 * public void Im_WifiVeriAl( final int i_port_numarasi,
 *							  final String str_dosya_yolu,
 *							  final String str_dosya_adi )
 * 
 * GELÝÞTÝRME GEÇMÝÞÝ:
 *
 * Yazar: Cem YENÝÇERÝ
 * Tarih: 09.05.2013
 * Güncelleme Tarihi: 
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
	 * FONKSÝYON ADI: 				Im_WifiVeriGonder
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile belirtilen dosya yolu üzerinde bulunan bir XML
	 * dosyasý  belirtilen IP adresi ve port numarasýna gönderilebilmektedir.
	 *
	 * ERÝÞÝM: Public
	 * 
	 * PARAMETRELER: 
	 * 			ADI						TÝPÝ				AÇIKLAMASI
	 *			str_ip_adres			String				Gönderilecek olan IP adresine ait bilgilerin tutulduðu parametredir.
	 *			i_port_numarasi			int					Gönderme iþlemi için kullanýlacak olan Port Numarasý bilgisinin tutulduðu parametredir.
	 *			str_gonderilecek_veri	String				Gönderilecek olan dosyaya ait yolun tutulduðu parametredir.
	 *
	 * DÖNÜÞ:	
	 * 			ADI						TÝPÝ				AÇIKLAMASI		
	 * 
	 * GEREKLÝLÝK:	
	 *
	 ********************************************************************************************/	
	public void Im_WifiVeriGonder( final String str_ip_adres, 
								   final int i_port_numarasi, 
								   final String str_gonderilecek_veri
								  )
	{
		/**
		 * Api level 11'den sonraki sürümlerde soket iþlemlerinin yapýlabilmesi için bir thread yaratýlmasý
		 * gerekmektedir. Bu nedenle soket ile yapýlacak iþlemler bir thread ve run fonksiyonu altýnda
		 * yazýlmýþtýr.
		 */
		new Thread( new Runnable() 
		{
			/*Thread in çalýþtýrdýðý run() fonksiyonu oluþturulmuþtur.*/
			public void run() 
			{    
				try 
				{
					/* Belirtilen Ip Adresi ve Port Numarasý için soket oluþturulmuþtur. */
	                soket = new Socket( str_ip_adres, i_port_numarasi ); 
	                
	                	/** 
	                	 * Verilen dosya yolu üzerindeki dosya, File tipinde dosya oluþturularak
	                	 * okunmuþtur.
	                	 */
		                File dosya = new File ( str_gonderilecek_veri );
		                
		                /*Dosyanýn gönderilebilmesi için dosya boyutunda bir byte dizisi açýlmýþtýr.*/
		                byte [] byte_dizi = new byte [ (int) dosya.length() ];
		                
		                /*Açýlan FileInputStream yardýmý ile açýlan dosya byte olarak okunmuþtur.*/
		                FileInputStream fis_dosyadan_byte_oku = new FileInputStream( dosya );
		                
		                /*FileInputStream ile okunan bytelar diziye yazýlmak için  BufferedInputStream'de tutulmuþtur.*/
						BufferedInputStream okunan_bytelari_buffara_yaz = new BufferedInputStream( fis_dosyadan_byte_oku );
		                
		                /**
		                 * Dosyadan okunan stream þeklindeki veriler read methodu ile 0. indexten baþlanarak
		                 * uzunluðu kadar okunup ByteDizi deðiþkenine kaydedilmiþtir.
		                 */
		                okunan_bytelari_buffara_yaz.read( byte_dizi, 0, byte_dizi.length);
		                
		                /*OutputStream yardýmý ile soket gönderme iþlemi için tanýmlanmýþtýr.*/
		                OutputStream byte_olarak_ciktisini_gonder = soket.getOutputStream();
		                
		                /** 
		                 * Gönderme iþlemi write metodu ile yapýlmýþ ve flush metodu ile kapatýlmýþtýr.
		                 * ilk parametre olarak ByteDizi deðiþkeni yazýlýp, dizinin 0. parametresinden baþlanarak
		                 * dizinin boyu kadar veri yazýlmýþtýr. 
		                 */
		                byte_olarak_ciktisini_gonder.write( byte_dizi, 0, byte_dizi.length );
		                byte_olarak_ciktisini_gonder.flush();
		                
		                /*Ýþlemi bitiren soket kapatýlmýþtýr.*/
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
	 * FONKSÝYON ADI: 				Im_WifiVeriAl
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon belirtilen port numarasýndan, istemci tarafýndan gönderilen
	 * dosyayý alýp belirtilen dosya yoluna kaydeden fonksiyondur.
	 *  
	 *
	 * ERÝÞÝM: Public
	 * 
	 * PARAMETRELER: 
	 * 			ADI						TÝPÝ				AÇIKLAMASI
	 *			i_PortNumarasi			int					Alým iþlemi için kullanýlacak olan Port Numarasý bilgisinin tutulduðu parametredir.
	 *			str_dosya_yolu			String				Alýnan dosyanýn kaydedileceði yolun tutulduðu parametredir.(/sdcard/ þeklinde olmalýdýr.)
	 *			str_dosya_adi			String				Alýnan dosyanýn kaydedileceði ismin tutulduðu deðiþkendir.
	 * 
	 * DÖNÜÞ:	
	 * 			ADI						TÝPÝ				AÇIKLAMASI			
	 *
	 * GEREKLÝLÝK: 					Cihazýn Wi-Fi özelliðinin açýk olup, IP numarasýnýn  ve port adresinin 
	 * gönderici cihaz tarafýndan bilinmesi gereklidir.  
	 *
	 *********************************************************************************************/	
	public void Im_WifiVeriAl( final int i_port_numarasi,
							   final String str_dosya_yolu,
							   final String str_dosya_adi
							 )
	{
		/**
		 * Api level 11'den sonraki sürümlerde soket iþlemlerinin yapýlabilmesi için bir thread yaratýlmasý
		 * gerekmektedir. Bu nedenle soket ile yapýlacak iþlemler bir thread ve run fonksiyonu altýnda
		 * yazýlmýþtýr.
		 */
		new Thread(new Runnable() 
		{
			public void run()
			{
				/** 
				 * Veri alýmý yapabilmek için ServerSocket sýnýfýndan nesnemize referans olacak server_soket
				 * örneðimiz yaratýldý.Henüz bir nesneye referans olmadýðýndan null olarak ilk deðeri verildi. 
				 */
				ServerSocket sunucu_soket = null;
				
				/**
				 * Veri göndermek isteyen istemciye ait soketi tutan deðiþkendir.
				 * Baþlangýç deðeri henüz bir istemci olmadýðý için null olarak atanmýþtýr.
				 */
	    	    Socket istemci_soket = null;
	    	    
	    	    /** 
	    	     * Ýstemci soketinden gelen girdi akýþýný tutabilmek için oluþturulmuþtur.
	    	     * Baþlangýç deðeri henüz bir girdi akýþý olmadýðý için null olarak atanmýþtýr
	    	     */
	    	    InputStream girdi_akisi = null;
	    	    
	    	    /* Ýstenilen yerde dosya oluþturabilmek için oluþturulmuþtur. */
	    	    FileOutputStream dosya_cikti_akisi = null;
	    	    
	    	    /*Çýktý akýþýnýn saðlanabilmesi için tamponlanmis_cikti_akisi referans deðiþkeni yaratýldý.*/
	    	    BufferedOutputStream tamponlanmis_cikti_akisi;
	    	    
	    	    int i_dosya_boyutu = 10000000;		/*Baþlangýçta dosya boyutuna ilk deðer olarak 10000000 verildi.*/
	    	    int i_okunan_byte_sayisi = 0;		/*Baþlangýçta okunan byte deðeri 0 olarak verildi. */
	    	    int i_durum = 0;					/*Daha sonra kullanýlmak üzere i_durum deðiþkeni yaratýldý. */

			    try 
			    {
			    	
			    	/* Ýstenilen port numarasýnda soket açýlmýþtýr. Ýnternet üzerinde iletiþim soketler aracýlýðýyla yapýlmaktadýr. */
					sunucu_soket = new ServerSocket( i_port_numarasi );
					
					/** 
					 * Ýstemci tarafýndan gelcek istekler accept() metodu ile beklenir. 
					 * Bekelenen istek geldiðinde istemci_soket tipinde Socket nesnesi döndürür. 
					 */
					istemci_soket = sunucu_soket.accept();
					
					/* dosyayý tutabilmek için byte dizi oluþturulmuþtur. */
					byte[] bytedizi = new byte[ i_dosya_boyutu ];   
					
					/** 
					 * Ýstemci soket tarafýndan gönderilmek istenen veriyi, okunmak üzere girdi_akisi olarak
					 * getInputStream() metodu ile döndürdük.
					 */
					girdi_akisi = istemci_soket.getInputStream();
					
				    try 
				    {
				    	/* oluþturulacak dosyanýn yolu verilmiþtir. */
						dosya_cikti_akisi = new FileOutputStream( str_dosya_yolu + str_dosya_adi + ".xml" );
						
					} 
				    
				    catch (FileNotFoundException e)
				    {
						e.printStackTrace();
					}
			    
				    /* yolu belirlenen dosya için tampon oluþturulur.*/
				    tamponlanmis_cikti_akisi = new BufferedOutputStream( dosya_cikti_akisi );
				    
				    /**
				     * Girdi akisina döndürülen veri 0. indeksten baþlanarak sonuna kadar byte byte okunup bytedizi ye kaydedilmiþtir.
				     * Okunan byte sayýsý i_okunan_byte deðiþkenine atanmýþtýr. 
				     */
					i_okunan_byte_sayisi = girdi_akisi.read( bytedizi, 0, bytedizi.length );
				    i_durum = i_okunan_byte_sayisi;
		
			    
				    /**
				     * Bu kod bloðu dosya okuma iþlemi tamamlanana kadar çalýþacaktýr.
				     */
				    do
				    {
				        try
				        {
				        	/* Eðer okunmayan veriler varsa kaldýðý yerden okumaya devam edecektir.*/
							i_okunan_byte_sayisi = girdi_akisi.read( bytedizi, i_durum, ( bytedizi.length - i_durum ) );
						} 
				        
				        catch (IOException e)
				        {
							e.printStackTrace();
						}
			        
				        /*eðer bu blok içerisinde veri okunabilmiþ ise i_durum deðiþkenine okunana byte miktarý eklenecektir.*/
				        if ( i_okunan_byte_sayisi >= 0 )
				        {
				            i_durum += i_okunan_byte_sayisi;
				        }
				        
				    } while ( i_okunan_byte_sayisi > -1 );  /* girdi_akisi.read() komutu okunacak bir veri kalmadýðýnda -1 deðerini döndürür.*/
			    
			    	/*yolu belirlenen dosya için oluþturulan tampona bytedizi de bulunan veriler 0. indexten baþlanarak yazýlýr. */
					tamponlanmis_cikti_akisi.write( bytedizi, 0, i_durum );
					
					/* tamponlamis_cikti_akisi temizlenir.*/
					tamponlanmis_cikti_akisi.flush();
					
					/*tamponlanmis_cikti_akisi kapatýlýr.*/
					tamponlanmis_cikti_akisi.close();
					
					/*girdi_akisi kapatýlýr.*/
					girdi_akisi.close();
					
					/*istemci_soket kapatýlýr.*/
					istemci_soket.close();
					
					/*sunucu_soket kapatýlýr.*/
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