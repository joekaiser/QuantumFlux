package jotato.quantumflux.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public final class NbtUtils
{
	/**
	 * returns the NbtTag on an given Itemstack or creates one if needed
	 * @param stack
	 * @return
	 */
    public static NBTTagCompound getNbtCounpound(ItemStack stack){
    	return stack.getTagCompound()==null?new NBTTagCompound():stack.getTagCompound();
    }
	
	/**
	 * confirms the existence of a key in a given nbt tag
	 * @param stack
	 * @param key
	 * @return
	 */
    public static boolean keyExists(ItemStack stack, String key)
    { 
      return stack.getTagCompound()==null?false:stack.getTagCompound().hasKey(key);
    }
    
    /**
     * gets the given key as an integer from an itemstack's nbt data
     * @param stack
     * @param key
     * @return the value stored or 0 if not found
     */
    public static int getInt(ItemStack stack, String key){
    	if(!keyExists(stack, key))
    		return 0;
    	
    	return stack.getTagCompound().getInteger(key);
    }
   
    /**
     * set an integer on an itemstack's nbt
     * @param stack
     * @param key
     * @param value
     * @return
     */
    public static ItemStack setInt(ItemStack stack, String key, int value){
    	NBTTagCompound tag = getNbtCounpound(stack);
    	tag.setInteger(key, value);
    	stack.setTagCompound(tag);
    	
    	return stack;
    }
    
   

}
