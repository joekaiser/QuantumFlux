package jotato.quantumflux.blocks;

import net.minecraft.block.material.Material;

public class BlockQuibitCluster extends BlockBase
{

    protected BlockQuibitCluster()
    {
        super(Material.iron, "quibitCluster", 1, "pickaxe", 0);
        setStepSound(soundTypeMetal);
    }

}
