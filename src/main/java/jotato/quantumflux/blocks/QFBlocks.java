package jotato.quantumflux.blocks;

public class QFBlocks {
	public static BlockEntropyAccelerator entropyAccelerator;
	public static BlockQuibitCluster quibitCluster;
	
	public static void init(){
		entropyAccelerator = new BlockEntropyAccelerator();
		quibitCluster = new BlockQuibitCluster();
	}
}
