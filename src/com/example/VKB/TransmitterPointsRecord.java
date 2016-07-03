/**
 * 
 */
package com.example.VKB;

/**
 * @author CEM
 *
 */

public class TransmitterPointsRecord {
	
	private String transmitterX;
	private String transmitterY;
	private String transmitterZ;
	private String transmitterNumber;
	/**
	 * @param transmitterX
	 * @param transmitterY
	 * @param transmitterZ
	 * @param transmitterNumber
	 */
	public TransmitterPointsRecord(String transmitterX, String transmitterY,
			String transmitterZ, String transmitterNumber) {
		this.transmitterX = transmitterX;
		this.transmitterY = transmitterY;
		this.transmitterZ = transmitterZ;
		this.transmitterNumber = transmitterNumber;
	}
	/**
	 * @return the transmitterX
	 */
	public String getTransmitterX() {
		return transmitterX;
	}
	/**
	 * @param transmitterX the transmitterX to set
	 */
	public void setTransmitterX(String transmitterX) {
		this.transmitterX = transmitterX;
	}
	/**
	 * @return the transmitterY
	 */
	public String getTransmitterY() {
		return transmitterY;
	}
	/**
	 * @param transmitterY the transmitterY to set
	 */
	public void setTransmitterY(String transmitterY) {
		this.transmitterY = transmitterY;
	}
	/**
	 * @return the transmitterZ
	 */
	public String getTransmitterZ() {
		return transmitterZ;
	}
	/**
	 * @param transmitterZ the transmitterZ to set
	 */
	public void setTransmitterZ(String transmitterZ) {
		this.transmitterZ = transmitterZ;
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
	
}
