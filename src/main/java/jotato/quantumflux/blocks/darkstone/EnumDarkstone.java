package jotato.quantumflux.blocks.darkstone;

import jotato.quantumflux.registers.BlockRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public enum EnumDarkstone implements IStringSerializable
{
  plain(0, "plain"),
  lamp(1, "lamp"),
  ornate(2, "ornate"),
  tile(3, "tile");

  public int getMetadata()
  {
    return this.meta;
  }

  @Override
  public String toString()
  {
    return this.name;
  }

  public static EnumDarkstone byMetadata(int meta)
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
  private static final EnumDarkstone[] META_LOOKUP = new EnumDarkstone[values().length];

  private EnumDarkstone(int i_meta, String i_name)
  {
    this.meta = i_meta;
    this.name = i_name;
  }

  static
  {
    for (EnumDarkstone level : values()) {
      META_LOOKUP[level.getMetadata()] = level;
    }
  }
  
  public static ItemStack getDarkstoneType(EnumDarkstone type){
	  return new ItemStack(BlockRegister.darkstone,1,type.meta);
  }
}
