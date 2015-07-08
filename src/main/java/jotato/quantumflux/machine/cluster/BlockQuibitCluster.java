package jotato.quantumflux.machine.cluster;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jotato.quantumflux.QuantumFlux;
import jotato.quantumflux.blocks.BlockContainerBase;
import jotato.quantumflux.blocks.ModBlocks;
import jotato.quantumflux.gui.QFGuiHandler.GUI;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

//todo: feel free to refactor this. It is UGLY and I just want to get it working right now
public class BlockQuibitCluster extends BlockContainerBase
{

	@SideOnly(Side.CLIENT)
	protected IIcon icon_top;

	@SideOnly(Side.CLIENT)
	protected IIcon icon_bottom;
	@SideOnly(Side.CLIENT)
	protected IIcon[] icon_side =new IIcon[5];


    public BlockQuibitCluster()
    {
        super(Material.iron, "quibitCluster", 3, "pickaxe", 0,ItemBlockQuibitCluster.class);
        setStepSound(soundTypeMetal);
    }
    
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		this.icon_top = ir.registerIcon(getTexture("cluster/quibitCluster_top"));
		this.icon_bottom = ir.registerIcon(getTexture("cluster/quibitCluster_bottom"));
		
		
		for(int i=0;i<5;i++){
			this.icon_side[i]=ir.registerIcon(getTexture("cluster/quibitCluster_"+(i+1)+"_side"));
		}
		
	}
	
	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int metadata = 0; metadata < 5; metadata++) {
			par3List.add(new ItemStack(ModBlocks.quibitCluster, 1, metadata));
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{


		if (side == 1)
		{
			return this.icon_top;
		}
		if (side == 0)
		{
			return this.icon_bottom;
		}

		 return icon_side[meta];
	}


//    @Override
//    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p6, float p7, float p8, float p9)
//    {
//        switch (this.level) {
//        case 1:
//            player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER_1.ordinal, world, x, y, z);
//            break;
//        case 2:
//            player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER_2.ordinal, world, x, y, z);
//            break;
//        case 3:
//            player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER_3.ordinal, world, x, y, z);
//            break;
//        case 4:
//            player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER_4.ordinal, world, x, y, z);
//            break;
//        case 5:
//            player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER_5.ordinal, world, x, y, z);
//            break;
//        }
//        return true;
//    }

    @Override
    public TileEntity createNewTileEntity(World world, int p1)
    {
        
        return new TileEntityQuibitCluster();

    }

}
