package jotato.quantumflux.blocks;

public class QFBlocks {
	public static BlockIncinerator incinerator;
	public static BlockMolecularInfuser molecularInfuser;
	
	public static void init(){
		incinerator = new BlockIncinerator();
		molecularInfuser = new BlockMolecularInfuser();
	}
}
