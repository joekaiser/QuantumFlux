package jotato.quantumflux.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockQuibitCluster extends BlockBase
{
    public BlockQuibitCluster(int level)
    {
        super(Material.iron, "quibitCluster_" + level, 1, "pickaxe", 0);
        setStepSound(soundTypeMetal);
    }
    

}
