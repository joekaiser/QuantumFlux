package jotato.quantumflux.items;

import cofh.api.energy.IEnergyContainerItem;
import jotato.quantumflux.QuantumFlux;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemBattleSuit extends ItemArmor implements IEnergyContainerItem
{
	
	public static ArmorMaterial material = EnumHelper.addArmorMaterial("battleSuitMatieral", 33, new int[]{4,9,7,4}, 50);

	public ItemBattleSuit(String name, int type)
	{
		super(material, 0, type);
		setUnlocalizedName(name);
		setTextureName("quantumflux:" + name);
		setCreativeTab(QuantumFlux.tab);
		GameRegistry.registerItem(this, name);
	
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
	    // TODO Auto-generated method stub
	    return  "quantumflux:textures/armor/battlesuit_layer_"+ (this.armorType == 2 ? "2" : "1") + ".png";
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
	    super.onArmorTick(world, player, itemStack);
	    
	}

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getEnergyStored(ItemStack container)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMaxEnergyStored(ItemStack container)
    {
        // TODO Auto-generated method stub
        return 0;
    }
    
    
    private boolean tagExists(ItemStack stack, String key)
    {
      NBTTagCompound tc = stack.getTagCompound();
      if (tc == null) {
        return false;
      }
      return tc.hasKey(key);
    }

}