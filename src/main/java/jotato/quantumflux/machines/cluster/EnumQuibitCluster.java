package jotato.quantumflux.machines.cluster;

import jotato.quantumflux.registers.BlockRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public enum EnumQuibitCluster implements IStringSerializable
{
  creative(0, "creative"),
  one(1, "one"),
  two(2, "two"),
  three(3, "three"),
  four(4, "four"),
  five(5, "five");

  public int getMetadata()
  {
    return this.meta;
  }

  @Override
  public String toString()
  {
    return this.name;
  }

  public static EnumQuibitCluster byMetadata(int meta)
  {
    if (meta < 0 || meta >= META_LOOKUP.length)
    {
      meta = 0;
    }

    return META_LOOKUP[meta];
  }

  public String getName()
  {
    return this.name;
  }

  private final int meta;
  private final String name;
  private static final EnumQuibitCluster[] META_LOOKUP = new EnumQuibitCluster[values().length];

  private EnumQuibitCluster(int i_meta, String i_name)
  {
    this.meta = i_meta;
    this.name = i_name;
  }

  static
  {
    for (EnumQuibitCluster level : values()) {
      META_LOOKUP[level.getMetadata()] = level;
    }
  }
  
  public static ItemStack getQuibitClusterFromType(EnumQuibitCluster type){
	  return new ItemStack(BlockRegister.quibitCluster,1,type.meta);
  }
}
