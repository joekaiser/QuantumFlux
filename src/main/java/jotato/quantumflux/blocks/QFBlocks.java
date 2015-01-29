package jotato.quantumflux.blocks;

public class QFBlocks {
	public static BlockIncinerator incinerator;
	public static BlockEfficientIncinerator efficientIncinerator;
	
	public static void init(){
		incinerator = new BlockIncinerator();
		efficientIncinerator = new BlockEfficientIncinerator();
	}
}
