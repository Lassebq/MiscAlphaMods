package io.github.lassebq.alphatweaks;

import net.minecraft.block.Block;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SlabItem extends BlockItem {
	public SlabItem(int i) {
		super(i);
	}

	public boolean use(ItemStack stack, PlayerEntity player, World world, int x, int y, int z, int side)
    {
        if (stack.size == 0)
        {
            return false;
        }

        int i = world.getBlock(x, y, z);
        // int j = world.getBlockMetadata(x, y, z);
        // int k = j & 7;
        // boolean flag = (j & 8) != 0;

        // if ((side == 1 && !flag || side == 0 && flag) && i == Block.stairSingle.blockID && k == item.itemDmg)
        if (side == 1 && i == Block.STONE_SLAB.id)
        {
            if (world.canBuildIn(Block.DOUBLE_STONE_SLAB.getCollisionShape(world, x, y, z)) && world.setBlock(x, y, z, Block.DOUBLE_STONE_SLAB.id))
            {
                world.playSound((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F, Block.DOUBLE_STONE_SLAB.sound.getStepSound(), (Block.DOUBLE_STONE_SLAB.sound.getVolume() + 1.0F) / 2.0F, Block.DOUBLE_STONE_SLAB.sound.getPitch() * 0.8F);
				--stack.size;
            }

            return true;
        }

		return super.use(stack, player, world, x, y, z, side);
    }
}
