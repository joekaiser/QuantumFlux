package jotato.quantumflux.blocks;

public class QFBlocks {
	public static BlockEntropyAccelerator entropyAccelerator;
	
	public static void init(){
		entropyAccelerator = new BlockEntropyAccelerator();
	}
}
