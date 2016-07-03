/*******************************************************************************
 * xmlLibrary.java
 *
 * AMA�:
 *
 * Bu s�n�f ile xmlLibrary i�lemleri ger�eklenmektedir. 
 * Bu s�n�f Im_YeniDosyaAcmaAyari, Im_VarolanDosyayiAcmaAyari, Im_BelgedenStringYarat, Im_XMLOlustur, Im_XMLVeriOku,
 * Im_XMLEtiketEkle, Im_XMLEtiketIsmiGuncelle, Im_XMLEtiketSil, Im_XMLVeriYaz, Im_XMLVeriGuncelle, Im_XMLVeriSil
 * fonksiyonlar�n� i�ermektedir. 
 *
 * ER���M: Public
 *
 * GLOBAL DE���KENKER:
 *
 * FONKS�YON PROTOT�PLER�:
 *
 * public Document Im_YeniDosyaAcmaAyari()
 * 
 * public Document Im_VarolanDosyayiAcmaAyari( String str_dosya_klasoru,
 *		   									   String str_XML_dosyasi_ismi)
 * 
 * public String Im_BelgedenStringYarat(Document belge)
 * 
 * public void Im_DosyaKaydet( String str_klasor_adi,
 *		   					   	String str_dosya_adi,
 *		   					   	String str_kaydedilecek_dosya )
 * 
 * public void Im_XMLOlustur( String str_dosya_adi, 
 * 							  String str_klasor_adi, 
 * 							  String str_kok_etiket,
 *							  String [] str_alt_eleman )
 *
 * public void Im_XMLOlustur()
 * 
 * public String Im_XMLVeriOku( String str_dosya_klasoru, 
 * 								String str_XML_dosyasi_ismi, 
 *			   					String str_veri_okunacak_etiket )
 *
 * public void Im_XMLEtiketEkle( String str_dosya_klasoru,
 * 							     String str_XML_dosyasi_ismi, 
 * 							     String str_belirtec_etiket,
 * 							     String str_eklenecek_etiket_ismi,
 * 							     String str_icerisine_yazilacak_deger )
 * 
 * public void Im_XMLEtiketIsmiGuncelle( String str_dosya_klasoru,
 * 									     String str_XML_dosyasi_ismi, 
 *		 						   	  	 String str_ismi_degistirilecek_olan_etiket, 
 *									  	 String str_yeni_isim )
 *
 * public void Im_XMLEtiketSil( String str_dosya_klasoru,
 * 							    String str_XML_dosyasi_ismi,
 * 							    String str_silinecek_etiket )
 * 
 * public void Im_XMLVeriYaz( String str_dosya_klasoru, 
 * 							  String str_acilacak_XML_dosyasi_ismi, 
 * 							  String str_icinde_degisiklik_yapilacak_etiket, 
 * 							  String str_veri_girilecek_etiket_elemani, 
 *							  String str_girilecek_veri, 
 *							  String str_yeni_dosya_ismi, 
 *							  String str_yeni_XMLin_kaydedilecegi_dosya )
 *
 * public void Im_XMLVeriGuncelle( String str_dosya_klasoru, 
 *                                 String str_gulcellencek_XML_dosyasi_ismi, 
 * 								   String str_icerisinde_guncelleme_yapilacak_etiket,
 * 								   String str_guncelleme_yap�lacak_etiket_elemani, 
 *								   String str_yeni_veri	)
 *
 * public void Im_XMLVeriSil( String str_dosya_klasoru,
 * 							  String str_XML_dosyasi_ismi, 
 * 							  String str_icinde_degisiklik_yapilacak_etiket,
 *							  String str_verisi_silinecek_etiket_eleman� )
 * 
 * NOTLAR: Fonksiyonlar i�erisinde t�rk�e karakter i�eren etiket ismi, klas�r ismi, dosya ad� gibi 
 * parametreler girilmesi durumunda fonksiyonlar herhangi bir hata vermemektedir. Fakat i�levlerini
 * ger�ekle�tirememektedir.	
 * 
 * GEL��T�RME GE�M���:
 *
 * Yazar: Cem YEN��ER�
 * Tarih: 25.04.2013
 * G�ncelleme Tarihi: 
 * Versiyon: v_1.0
 *
 */
package com.example.VKB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class xmlLibrary
{
	 /********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				Im_YeniDosyaAcmaAyari
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon, di�er fonksiyonlarda kullan�lmak i�in yaz�lm�� olup,
	 * herhangi bir xmlLibrary dosya i�leminin yap�labilmesi i�in gerekli d�zenlemeleri yapan fonksiyondur.
	 *
	 * ER���M: Public
	 * 
	 * PARAMETRELER: YOK
	 * 			ADI				T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI				T�P�				A�IKLAMASI
	 *			belge			Document			Gerekli d�zenlemeler yap�larak belge de�i�keni haz�rlan�r ve d�nd�r�l�r.			
	 *
	 * GEREKL�L�K: 	Herhangi bir xmlLibrary dosyas� i�lemi yapabilabilmesi i�in �ncelikle bu fonksiyonun 
	 * �a�r�lmas� gerekmektedir.
	 *
	*********************************************************************************************/	
    public Document Im_YeniDosyaAcmaAyari()
    {
        try
        {
        	/**
	  		 * xmlLibrary belgesi olu�turmak i�in ilk olarak belge_ureteci nesnesi olu�turuldu.
	  		 * Normal JAVA dan farkl� olarak Android i�in newInstance metodu kullan�lmas� gerekmektedir.
	  		 * 
	  		 * belge_yapici nesnesi bir xmlLibrary belgesinden DOM belgesi elde edebilmek i�in olu�turuldu.
	  		 * 
	  		 * belge isimli bir DOM �rne�i yarat�ld�. DOM ile bir xmlLibrary belgesinin i�eri�i,stili ve yap�s� g�r�l�p de�i�tirilebilir.
	  		 */ 	
        	DocumentBuilderFactory belge_ureteci = DocumentBuilderFactory.newInstance();
	   		DocumentBuilder belge_yapici = belge_ureteci.newDocumentBuilder();
	   		Document belge = belge_yapici.newDocument();
	   		return belge;
	    } 
        catch (ParserConfigurationException e) 
        {
			e.printStackTrace();
			return null;
	    }		
    }
    
    
	 /********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				Im_VarolanDosyayiAcmaAyari
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon di�er fonksiyonlarda kullan�lmak i�in yaz�lm�� olup
	 * herhangi varolan xmlLibrary dosyas� �zerinde i�lem yap�labilmmek i�in gerekli d�zenlemeleri yapan fonk
	 * siyondur.
	 *
	 * ER���M: Public
	 * 
	 * PARAMETRELER: YOK
	 * 			ADI						T�P�				A�IKLAMASI
	 *			str_dosya_klasoru		String				A��lacak dosyan�n bulundu�u klas�r� tutan de�i�kendir.
	 *			str_XML_dosyasi_ismi	String				A��lacak olan dosyan�n ismini tutan de�i�kendir.
	 * D�N��:	
	 * 			ADI						T�P�				A�IKLAMASI
	 *			belge					Document			Gerekli d�zenlemeler yap�larak belge de�i�keni haz�rlan�r ve d�nd�r�l�r.			
	 *
	 * GEREKL�L�K: 	Herhangi bir xmlLibrary dosyas� i�lemi yapabilabilmesi i�in �ncelikle bu fonksiyonun 
	 * �a�r�lmas� gerekmektedir.
	 *
	*********************************************************************************************/	
   public Document Im_VarolanDosyayiAcmaAyari( String str_dosya_klasoru,
		   									   String str_XML_dosyasi_ismi )
   {
       try
       {	
    	   /**
    	    * ??
    	    */
    	   	DocumentBuilderFactory dosya_uretici = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dosya_yapici = dosya_uretici.newDocumentBuilder();
		    Document dosya = null;
			try {
				dosya = dosya_yapici.parse(new File("/sdcard/"+ str_dosya_klasoru +"/"+ str_XML_dosyasi_ismi + ".xml"));
				dosya.normalizeDocument(); 
		   		return dosya;
			} catch (SAXException e) {
				e.printStackTrace();
				return dosya;
			} catch (IOException e) {
				e.printStackTrace();
				return dosya;
			}
	    } 
       catch (ParserConfigurationException e) 
       {
			e.printStackTrace();
			return null;
	    }		
   }
    
     /********************************************************************************************
	 *
	 * FONKS�YON ADI: 				Im_BelgedenStringYarat
	 * FONKS�YON A�IKLAMASI: 		Belgeden String yarat�lmas� i�lemini ger�ekle�tirir.
	 * 
	 * ER���M: Public
	 * 
	 * PARAMETRELER:
	 * 			ADI				T�P�				A�IKLAMASI
	 *		   	belge		   	Document			D�n��t�r�lmesi gereken belge parametre olarak verilir.
	 *
	 * D�N��:
	 * 			ADI				T�P�				A�IKLAMASI
	 *			str_yazici		String				Belgenin kullan�lmak �zere String olarak d�nd�r�lece�i de�i�kendir.
	 *
	 * GEREKL�L�K: 	Bu fonksiyon ile yarat�lmas� planlanan xmlLibrary dosya yap�s� String e d�n��t�r�l�p
	 * dosyan�n yarat�lmas� i�leminin ger�ekle�ti�i k�s�ma aktar�lmas�nda kullan�l�r. 
	 *
	*********************************************************************************************/
    public String Im_BelgedenStringYarat(Document belge)
    {
        try
        {
        	/**
        	 * ??
        	 */
           DOMSource belge_kaynagi = new DOMSource(belge);
           StringWriter yazici = new StringWriter();
           StreamResult sonuc = new StreamResult(yazici);
           TransformerFactory donusum_ureteci = TransformerFactory.newInstance();
           Transformer donusturucu = donusum_ureteci.newTransformer();
           donusturucu.transform(belge_kaynagi, sonuc);
           String str_yazici = yazici.toString();;
           return str_yazici;
        }
        catch(TransformerException ex)
        {
           ex.printStackTrace();
           return null;
        }
    }
    
    
     /********************************************************************************************
	 *
	 * FONKS�YON ADI: 				Im_DosyaKaydet
	 * FONKS�YON A�IKLAMASI: 		��lemi tamamlanan bir xmlLibrary doyas�n� belirtilen klas�r alt�nda belir
	 * tilen isim ile kaydeden fonksiyondur.
	 * 
	 * ER���M: Public
	 * 
	 * PARAMETRELER:
	 * 			ADI						T�P�				A�IKLAMASI
	 *		  	str_klasor_adi			String				��erisine kaydedilecek olan klas�re ait ismi tutan de�i�kendir.
	 *			str_dosya_adi			String				Kaydedilecek olan dosyaya ait ismi tutan de�i�kendir.
	 *			str_kaydedilecek_dosya	String				Kaydedilecek olan dosyan�n tutuldu�u de�i�kendir.
	 *
	 * D�N��: YOK
	 * 			ADI						T�P�				A�IKLAMASI
	 *		
	 * GEREKL�L�K: 	�stenildi�i �ekilde d�zenlenen xmlLibrary dosyalar�n�n kaydedilmesi i�leminin yap�lmas�
	 * i�in gereklidir.
	 *
	*********************************************************************************************/
    public void Im_DosyaKaydet( String str_klasor_adi,
		   					   	String str_dosya_adi,
		   					   	String str_kaydedilecek_dosya
		   					  )
    {
		try 
		{
			/*Dosyan�n kaydedilece�i dizin olu�turulmaktad�r.*/
			String str_dizin = "/mnt/sdcard/" + str_klasor_adi + "/" + str_dosya_adi + ".xml";
			
			/*Belirtilen dizinde dosya yap�land�r�r.*/
			File yeni_dosya = new File(str_dizin);
			
			/**
			 * E�er b�yle bir klas�r belirtilen dizinde yoksa,�ncelikle klas�r yarat�l�r.  
			 */
			if(!yeni_dosya.exists())
			{
				yeni_dosya.getParentFile().mkdir();	    				
			}
			
			/**
			 * �zellikleri belirtilen dosya yarat�l�r
			 * ve xmlLibrary den String e d�n��t�r�len yap� dosya i�erisine byte byte at�l�r.
			 * Daha sonra olu�turulan dosya kapan�r. 
			 */
			yeni_dosya.createNewFile();
			
			FileOutputStream dosya_cikti = new FileOutputStream(yeni_dosya);
			
			OutputStreamWriter cikti_yazici = new OutputStreamWriter(dosya_cikti);
			cikti_yazici.append(str_kaydedilecek_dosya);
			cikti_yazici.close();
			
			dosya_cikti.close();
		} 
		catch (Exception e)
		{
		}
    }
    
	
     /********************************************************************************************
	 *
	 * FONKS�YON ADI: 				Im_XMLOlustur
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon istenilen klas�rde ve dosya isminde, istenilen yap�da 
	 * xmlLibrary belgesi olu�turabilen fonksiyondur.
	 *
	 * ER���M: Public
	 * 
	 * PARAMETRELER:
	 * 			ADI					T�P�							A�IKLAMASI
	 *			str_klas�r_adi		String							Dosyan�n /sdcard/ yolu i�erisinde kaydedilece�i klas�r�n ad�n� tutan de�i�kendir.
	 *			str_dosya_adi		String							Dosyan�n /sdcard/klas�r_adi/ yolu i�inde hangi isimle kaydedilece�ini tutan de�i�kendir.
	 *			str_kok_etiket		String							xmlLibrary dosyas�n�n sahip olaca�� k�k eleman�n tutuld�u de�i�kendir.	
	 *			str_alt_etiket		String							xmlLibrary dosyas�n�n sahip olaca�� alt eleman�n tutuld�u de�i�kendir.
	 *			str_alt_eleman		String Array					xmlLibrary dosyas�nda k�k eleman alt�nda bulunacak alt elemanlar� tutan dizi de�i�kenidir.
	 *			TransmitterPoints	ArrayList<TransmitterPointsRecord>  Transmitter     Koordinat de�erlerini tutacak de�i�ken
	 *
	 * D�N��: YOK
	 * 			ADI					T�P�				A�IKLAMASI
	 *
	 * GEREKL�L�K: 	Bu fonksiyon daha �nce olu�turulmam�� bir xmlLibrary belgesinin istenilen �zelliklerde
	 * olu�turulmas�na esnek bir �ekilde olanak sa�lamaktad�r.
	 *
	*********************************************************************************************/
	public void Im_XMLOlustur ( String str_klasor_adi,
								String str_dosya_adi,
							    String str_kok_etiket,
							    String str_alt_etiket,
							    ArrayList<TransmitterPointsRecord> TransmitterPoints)
	{
		try
		{	 
			Document belge= Im_YeniDosyaAcmaAyari(); /*Dosya a�mak i�in ayar fonksiyonu �a�r�lmaktad�r.*/
		  	
	   		Element kok_eleman = belge.createElement(str_kok_etiket);	 /* kok_eleman isimli xmlLibrary k�k etiketi yarat�ld�*/
	   		belge.appendChild(kok_eleman);								 /* Olu�turulan kok_eleman belgeye eklendi.*/
		   		
	   		/**
	   		 * Bu d�ng� ile kok_eleman etiketinin alt�nda yer alacak alt elemanlar yarat�lm��t�r.
	   		 * alt1_eleman ile temsil edileceklerdir.
	   		 */
		   	for(int i = 0; i < TransmitterPoints.size(); i++)
		   	{	
	   	  		
	   			/*Parametre olarak tan�ml� alt1 eleman� yarat�l�p kok_eleman �n alt�na eklenmi�tir.*/
	   	  		Element alt1_eleman = belge.createElement(str_alt_etiket);	
	   	  		kok_eleman.appendChild(alt1_eleman);
	   	  		
      	  		Attr attr = belge.createAttribute("id");
      	  		attr.setValue("Transmitter" + ":" + String.valueOf(i+1));
      	  		alt1_eleman.setAttributeNode(attr);
	   	      	   
	   	  		/*alt2_1_eleman alt1_eleman �n alt�nda yer alacak alt elemanlardan birisidir.*/
	   	  		Element alt2_1_eleman = belge.createElement("X_Coordinate");
	   	  		alt2_1_eleman.appendChild(belge.createTextNode(String.valueOf(TransmitterPoints.get(i).getTransmitterX())));
	   	  		alt1_eleman.appendChild(alt2_1_eleman);
	   	  		
	   	  		/*alt2_2_eleman alt1_eleman �n alt�nda yer alacak alt elemanlardan birisidir.*/
	   	  		Element alt2_2_eleman = belge.createElement("Y_Coordinate");
	   	  		alt2_2_eleman.appendChild(belge.createTextNode(String.valueOf(TransmitterPoints.get(i).getTransmitterY())));
	   	  		alt1_eleman.appendChild(alt2_2_eleman);
	   	  		
	   	  		/*alt2_3_eleman alt1_eleman �n alt�nda yer alacak alt elemanlardan birisidir.*/	
	   	  		Element alt2_3_eleman = belge.createElement("Z_Coordinate");
	   	  		alt2_3_eleman.appendChild(belge.createTextNode(String.valueOf(TransmitterPoints.get(i).getTransmitterZ())));
	   	  		alt1_eleman.appendChild(alt2_3_eleman);
	   		} 	
		   		
	   		/* belgeden String e d�n���m yapabilmek i�in str_string_belgeden nesnesi yarat�lm��t�r.*/
	   		String str_string_belgeden = Im_BelgedenStringYarat(belge);
	   		/*Dosya kaydet fonksiyonu �a�r�larak yap�lan i�lemler belirtilen konuma, belirtilen isim ile kaydedildi.*/
	   		Im_DosyaKaydet(str_klasor_adi, str_dosya_adi, str_string_belgeden);
		}
		catch(Exception e)
		{
			
		}
	}
	
	/********************************************************************************************
	*
	* FONKS�YON ADI: 				Im_XMLVeriOku
	* FONKS�YON A�IKLAMASI: 		Bu fonksiyon belirtilen xmlLibrary belgesi i�erisinde belirtilen etikete ait
	* de�eri okuma i�lemini yapmaktad�r.
	* 
	* ER���M: public
	* 
	* PARAMETRELER:	
	* 		ADI							T�P�			A�IKLAMASI
	*		str_dosya_klasoru			String			��erisinden veri okunacak olan xmlLibrary belgesinin bulundu�u klas�r�n tutuldu�u de�i�kendir.
	*		str_XML_dosyasi_ismi		String			��erisinden etiket de�eri okunacak olan xmlLibrary belgesinin isminin tutuldu�u de�i�kendir.	
	*		str_veri_okunacak_etiket	String			Hangi etikete ait de�erin okunaca��n�n tutuldu�u de�i�kendir.
	* 
	* D�N��:
	* 		ADI							T�P�			A�IKLAMASI
	* 		str_okunan_veri					String
	*	
	* GEREKL�L�K: 	Bir xmlLibrary belgesi i�erisinde herhangi bir etiket alt�ndaki de�erin okunmas� gerekti�i
	* durumlar i�in kullan�lmaktad�r.
	* 
	*********************************************************************************************/
	public String Im_XMLVeriOku( String str_dosya_klasoru, 
   						 		 String str_XML_dosyasi_ismi, 
   						 	     String str_veri_okunacak_etiket
		  					   )
	{
		String str_okunan_veri = "Veri Yok!"; /*Belirtilen yolda herhangi bir veri bulunamazsa d�necek de�erdir.*/
		try
		{
			Document belge = Im_VarolanDosyayiAcmaAyari(str_dosya_klasoru, str_XML_dosyasi_ismi); /*Dosya a�mak i�in ayar fonksiyonu �a�r�lmaktad�r.*/
	    
		    /**
		     * belge.getElementsByTagName ile i�erisinde de�i�iklik yap�lmak istenen etiketler, NodeList tipinde
		     * etiketlere atand� ve olu�turulan NodeList etiketlerinden istenen etikete ait i�erik getTextContent() ile �a�r�ld�.
		     * Okunmak istenen de�er str_okunan_veri de�i�kenine atand�.
		     */
		    NodeList etiketler = belge.getElementsByTagName(str_veri_okunacak_etiket);
			for (int i =0; i<etiketler.getLength();i++)
			{
			    Node eleman = etiketler.item(i);
			    
			    if(str_veri_okunacak_etiket.equals(eleman.getNodeName()))
			    {
			        str_okunan_veri=eleman.getTextContent(); 	/*Verinin d�n�� de�erine y�klenme i�lemi*/
			    }
		    }
	 	}
		catch (Exception e) 
		{	
			e.printStackTrace();
		}
		return str_okunan_veri;
	}	
	
	
	
	
	/********************************************************************************************************************************
	 ******************************************************************************************************************************** 
	 *******************************************************************************************************************************
	 * Buradaki System.Out.println() ler kalkacak ve nesneye atanacak......
	 *
	 **/
	
	public ArrayList<MeasurementsPointsRecord> xmlgen( String str_XML_dosyasi_ismi,ArrayList<MeasurementsPointsRecord>measurements) {
		    try {		    	
		    	
				File fXmlFile = new File("/sdcard/"+ str_XML_dosyasi_ismi + ".xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
			 
				doc.getDocumentElement().normalize();
				
				NodeList listOfTransmitter = doc.getElementsByTagName("Transmitter");

	            for(int s=0; s<listOfTransmitter.getLength() ; s++){


	                Node firstTransmitterNode = listOfTransmitter.item(s);
	                if(firstTransmitterNode .getNodeType() == Node.ELEMENT_NODE){


	                    Element TransmitterElement = (Element)firstTransmitterNode ;

	                    //-------
	                    NodeList TransmitterNameList = TransmitterElement.getElementsByTagName("TransmitterName");
	                    Element TransmitterNameElement = (Element)TransmitterNameList .item(0);

	                    NodeList textFNList = TransmitterNameElement.getChildNodes();
	                        //----

	                   NodeList listOfTransmitterNames = TransmitterElement.getElementsByTagName("MobilePoint");
	                    for(int i=0; i<listOfTransmitterNames.getLength() ; i++){


	                Node firstMobilePointNode = listOfTransmitterNames.item(i);
	                 if(firstMobilePointNode .getNodeType() == Node.ELEMENT_NODE){

	                    Element classElement = (Element)firstMobilePointNode;
	                    //----

	                        NodeList MobilePointNameList = classElement .getElementsByTagName("MobilePointName");
	                        Element MobilePointNameElement = (Element)MobilePointNameList.item(0);
	                        
	                        NodeList MobilePointXList = classElement .getElementsByTagName("XKoordinat");
	                        Element MobilePointXElement = (Element)MobilePointXList.item(0);
	                        
	                        NodeList MobilePointYList = classElement .getElementsByTagName("YKoordinat");
	                        Element MobilePointYElement = (Element)MobilePointYList.item(0);
	                        
	                        NodeList MobilePointZList = classElement .getElementsByTagName("ZKoordinat");
	                        Element MobilePointZElement = (Element)MobilePointZList.item(0);
	                        
	                        NodeList DistanceList = classElement .getElementsByTagName("Distance");
	                        Element DistanceElement = (Element)DistanceList.item(0);

	                        NodeList textCLSNMList = MobilePointNameElement.getChildNodes();
	                        NodeList textCLSXList = MobilePointXElement.getChildNodes();
	                        NodeList textCLSYList = MobilePointYElement.getChildNodes();
	                        NodeList textCLSZList = MobilePointZElement.getChildNodes();
	                        NodeList textDistanceList = DistanceElement.getChildNodes();
	                           
	                           measurements.add(new MeasurementsPointsRecord(
	                           ((Node)textCLSXList .item(0)).getNodeValue().trim(),
	                           ((Node)textCLSYList .item(0)).getNodeValue().trim(),
	                           ((Node)textCLSZList .item(0)).getNodeValue().trim(),
	                           ((Node)textCLSNMList .item(0)).getNodeValue().trim(),
	                           ((Node)textFNList.item(0)).getNodeValue().trim(),
	                           ((Node)textDistanceList .item(0)).getNodeValue().trim()));
	                           }}}}
					    } catch (Exception e) {
			e.printStackTrace();
		    }
		    return measurements;
		    }

	
}
