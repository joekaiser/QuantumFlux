package jotato.quantumflux.blocks;

import jotato.quantumflux.ConfigurationManager;
import jotato.quantumflux.QuantumFlux;
import jotato.quantumflux.gui.QFGuiHandler.GUI;
import jotato.quantumflux.tileentity.TileEntityIncinerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockIncinerator extends BlockContainerBase
{

    @SideOnly(Side.CLIENT)
    private IIcon icon_top;
    @SideOnly(Side.CLIENT)
    private IIcon icon_front;
    @SideOnly(Side.CLIENT)
    private IIcon icon_front_active;
    @SideOnly(Side.CLIENT)
    private IIcon icon_side;

    protected BlockIncinerator()
    {
        super(Material.iron, "incinerator", 1, "pickaxe", 1);
        setStepSound(soundTypeMetal);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister ir)
    {
        this.icon_top = ir.registerIcon(getTexture("incinerator_top"));
        this.icon_front = ir.registerIcon(getTexture("incinerator_front"));
        this.icon_front_active = ir.registerIcon(getTexture("incinerator_front_active"));
        this.icon_side = ir.registerIcon(getTexture("incinerator_side"));
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        int frontSide = getOrientation(meta, false);
        if (side == frontSide)
        {
            // todo: update icon based on active state
            return icon_front;
        }

        if (side == 1 || side == 0)
        {
            return this.icon_top;
        }

        return this.icon_side;
    }

    // @Override
    // public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    // {
    // int meta = world.getBlockMetadata(x, y, z);
    // int frontSide = getOrientation(meta, false);
    // if (side == frontSide)
    // {
    // TileEntity e = world.getTileEntity(x, y, z);
    // if (e != null && e instanceof IActive)
    // {
    // IActive state = (IActive) e;
    // if (state.isActive())
    // return icon_front_active;
    // }
    // return icon_front;
    // }
    //
    // if (side == 1 || side == 0)
    // {
    // return this.icon_top;
    // }
    //
    // return this.icon_side;
    // }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
    {
        int frontSide = determineOrientation(world, x, y, z, entity);
        world.setBlockMetadataWithNotify(x, y, z, frontSide, 2);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p6, float p7, float p8, float p9)
    {
        player.openGui(QuantumFlux.instance, GUI.INCINERATOR.ordinal, world, x, y, z);
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p1)
    {
        return new TileEntityIncinerator();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        TileEntityIncinerator incinerator = (TileEntityIncinerator) world.getTileEntity(x, y, z);
        if (incinerator != null)
        {
            for (int i = 0; i < incinerator.getSizeInventory(); ++i)
            {
                ItemStack itemstack = incinerator.getStackInSlot(i);

                if (itemstack != null)
                {
                    float f = world.rand.nextFloat() * 0.6F + 0.1F;
                    float f1 = world.rand.nextFloat() * 0.6F + 0.1F;
                    float f2 = world.rand.nextFloat() * 0.6F + 0.1F;

                    while (itemstack.stackSize > 0)
                    {
                        int j = world.rand.nextInt(21) + 10;

                        if (j > itemstack.stackSize)
                        {
                            j = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j;
                        EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(
                                itemstack.getItem(), j, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound(((NBTTagCompound) itemstack.getTagCompound().copy()));
                        }

                        float f3 = 0.025F;
                        entityitem.motionX = (double) ((float) world.rand.nextGaussian() * f3);
                        entityitem.motionY = (double) ((float) world.rand.nextGaussian() * f3 + 0.1F);
                        entityitem.motionZ = (double) ((float) world.rand.nextGaussian() * f3);
                        world.spawnEntityInWorld(entityitem);
                    }
                }
            }
            world.func_147453_f(x, y, z, block);
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

}
