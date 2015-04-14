package jotato.quantumflux.items;


import jotato.quantumflux.blocks.QFBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemExciterUpgrade extends ItemBase
{

    public ItemExciterUpgrade()
    {
        super("exciterUpgrade");
    }
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world,
                player, true);
        if (world.isRemote)
            return itemStack;

        if (movingobjectposition != null) {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                int x = movingobjectposition.blockX;
                int y = movingobjectposition.blockY;
                int z = movingobjectposition.blockZ;
                
                Block target = world.getBlock(x, y, z);
                
                if (target == QFBlocks.rfExciter) {
                    
                    if(QFBlocks.rfExciter.addUpgrade(world, x, y, z, 1)){
                        itemStack.stackSize--;
                    }
                }
            }

        }
        return itemStack;

    }
}
