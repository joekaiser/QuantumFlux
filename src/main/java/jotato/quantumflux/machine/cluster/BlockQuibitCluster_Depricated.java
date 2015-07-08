package jotato.quantumflux.machine.cluster;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jotato.quantumflux.QuantumFlux;
import jotato.quantumflux.blocks.BlockContainerBase;
import jotato.quantumflux.gui.QFGuiHandler.GUI;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

//todo: feel free to refactor this. It is UGLY and I just want to get it working right now
public class BlockQuibitCluster_Depricated extends BlockContainerBase
{
    private int level;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_top;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_front;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_bottom;
	@SideOnly(Side.CLIENT)
	protected IIcon icon_side;


    public BlockQuibitCluster_Depricated(int level)
    {
        super(Material.iron, "quibitCluster_" + level, 3, "pickaxe", 0,ItemBlockQuibitCluster.class);
        setStepSound(soundTypeMetal);
        this.level = level;
    }
    
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		this.icon_top = ir.registerIcon(getTexture("cluster/quibitCluster_top"));
		this.icon_bottom = ir.registerIcon(getTexture("cluster/quibitCluster_bottom"));
		this.icon_front = ir.registerIcon(getTexture("cluster/quibitCluster_"+this.level+"_front"));
		this.icon_side = ir.registerIcon(getTexture("cluster/quibitCluster_"+this.level+"_side"));
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		int frontSide = getOrientation(meta, false);
		if (side == frontSide)
		{
			return icon_front;
		}

		if (side == 1)
		{
			return this.icon_top;
		}
		if (side == 0)
		{
			return this.icon_bottom;
		}

		return this.icon_side;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
	{
		int frontSide = determineOrientation(world, x, y, z, entity);
		world.setBlockMetadataWithNotify(x, y, z, frontSide, 2);

	}

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p6, float p7, float p8, float p9)
    {
        switch (this.level) {
        case 1:
            player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER_1.ordinal, world, x, y, z);
            break;
        case 2:
            player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER_2.ordinal, world, x, y, z);
            break;
        case 3:
            player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER_3.ordinal, world, x, y, z);
            break;
        case 4:
            player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER_4.ordinal, world, x, y, z);
            break;
        case 5:
            player.openGui(QuantumFlux.instance, GUI.QUIBIT_CLUSTER_5.ordinal, world, x, y, z);
            break;
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p1)
    {
        switch (this.level) {
        case 2:
            return new TileEntityQuibitCluster_2();
        case 3:
            return new TileEntityQuibitCluster_3();
        case 4:
            return new TileEntityQuibitCluster_4();
        case 5:
            return new TileEntityQuibitCluster_5();
        case 1:
        default:
            return new TileEntityQuibitCluster_1();
        }

    }

}
