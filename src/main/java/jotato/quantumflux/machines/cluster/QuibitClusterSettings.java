package jotato.quantumflux.machines.cluster;

import java.text.NumberFormat;

import jotato.quantumflux.ConfigMan;

public class QuibitClusterSettings{
	public int transferRate;
	public int capacity;
	public int level;
	
	public QuibitClusterSettings(int level){
		this.level = level;
		transferRate = ConfigMan.quibitCluster_baseTransferRate;
		capacity = ConfigMan.quibitCluster_baseStorage;
		for (int i = 1; i < level; i++) {
			this.transferRate *= ConfigMan.quibitCluster_multiplier;
			this.capacity *= ConfigMan.quibitCluster_multiplier;
		}
		if (level == 5) {
			this.transferRate = Integer.MAX_VALUE;
		}
	}
	
	public String getTransferRateFormatted(){
		if(level==5){
			return "Infinite";
		}
		return NumberFormat.getIntegerInstance().format(transferRate);
	}
	
	public String getCapacityFormatted(){
		
		return NumberFormat.getIntegerInstance().format(capacity);
	}
}