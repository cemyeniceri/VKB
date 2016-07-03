/**
 * 
 */
package com.example.VKB;

/**
 * @author CEM
 *
 */

public class MeasurementsPointsRecord {
	
	private String mobileX;
	private String mobileY;
	private String mobileZ;
	private String mobileNumber;
	private String transmitterNumber;
	private String distance;
	
	/**
	 * @param mobileX
	 * @param mobileY
	 * @param mobileZ
	 * @param mobileNumber
	 * @param transmitterNumber
	 * @param distance
	 */
	public MeasurementsPointsRecord(String mobileX, String mobileY,
			String mobileZ, String mobileNumber, String transmitterNumber,
			String distance) {
		this.mobileX = mobileX;
		this.mobileY = mobileY;
		this.mobileZ = mobileZ;
		this.mobileNumber = mobileNumber;
		this.transmitterNumber = transmitterNumber;
		this.distance = distance;
	}
	
	/**
	 * @return the mobileX
	 */
	public String getMobileX() {
		return mobileX;
	}
	/**
	 * @param mobileX the mobileX to set
	 */
	public void setMobileX(String mobileX) {
		this.mobileX = mobileX;
	}
	/**
	 * @return the mobileY
	 */
	public String getMobileY() {
		return mobileY;
	}
	/**
	 * @param mobileY the mobileY to set
	 */
	public void setMobileY(String mobileY) {
		this.mobileY = mobileY;
	}
	/**
	 * @return the mobileZ
	 */
	public String getMobileZ() {
		return mobileZ;
	}
	/**
	 * @param mobileZ the mobileZ to set
	 */
	public void setMobileZ(String mobileZ) {
		this.mobileZ = mobileZ;
	}
	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	/**
	 * @return the transmitterNumber
	 */
	public String getTransmitterNumber() {
		return transmitterNumber;
	}
	/**
	 * @param transmitterNumber the transmitterNumber to set
	 */
	public void setTransmitterNumber(String transmitterNumber) {
		this.transmitterNumber = transmitterNumber;
	}
	/**
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
}
