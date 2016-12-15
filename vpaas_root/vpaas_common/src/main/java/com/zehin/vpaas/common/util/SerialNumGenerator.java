package com.zehin.vpaas.common.util;
/**
 * 
 * 流水号生成
 *
 */
public class SerialNumGenerator {
	private int sn;
	public SerialNumGenerator(){
		sn = 0;
	}
	
	public synchronized String getNum() {
		if (sn >= 99999) {
			sn = 0;
		}
		sn++;
		String serialNum = String.format("%05d", sn);
		return serialNum;
	}	
}
