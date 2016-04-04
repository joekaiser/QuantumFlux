package jotato.quantumflux.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public final class NbtHelpers
{
	/**
	 * returns the NbtTag on an given Itemstack or creates one if needed
	 * @param stack
	 * @return
	 */
    public static NBTTagCompound getNbtCompound(ItemStack stack){
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
    	return getInt(stack, key,0);
    }
    /**
     * gets the given key as an integer from an itemstack's nbt data
     * @param stack
     * @param key
     * @return the value stored or defaultValue if not found
     */
    public static int getInt(ItemStack stack, String key, int defaultValue) {

		if (!keyExists(stack, key)) {
			return defaultValue;
		}
		return getNbtCompound(stack).getInteger(key);
	}
   
    /**
     * set an integer on an itemstack's nbt
     * @param stack
     * @param key
     * @param value
     * @return
     */
    public static ItemStack setInt(ItemStack stack, String key, int value){
    	NBTTagCompound tag = getNbtCompound(stack);
    	tag.setInteger(key, value);
    	stack.setTagCompound(tag);
    	
    	return stack;
    }
    
    /**
     * gets the given key as a boolean from an itemstack's nbt data
     * @param stack
     * @param key
     * @return the value stored or 0 if not found
     */
    public static boolean getBoolean(ItemStack stack, String key, boolean defaultValue) {

		if (!keyExists(stack, key)) {
			return defaultValue;
		}
		return getNbtCompound(stack).getBoolean(key);
	}
    
    /**
     * sets the given key/value as a boolean
     * @param stack
     * @param key
     * @param value
     */
    public static void setBoolean(ItemStack stack, String key, boolean value) {

    	getNbtCompound(stack).setBoolean(key, value);
	}
    
    public static NBTTagCompound getCompoundTag(ItemStack stack, String key) {

		if (!keyExists(stack, key)) {
			return null;
		}
		return getNbtCompound(stack).getCompoundTag(key);
	}
    
    public static NBTBase getTag(ItemStack stack, String key) {

		if (!keyExists(stack, key)) {
			return null;
		}
		return getNbtCompound(stack).getTag(key);
	}

	public static void setTag(ItemStack stack, String key, NBTBase value) {

		getNbtCompound(stack).setTag(key, value);
	}

}