package io.github.lassebq.alphatweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.Block;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WheatSeedsItem;
import net.minecraft.world.World;

@Mixin(WheatSeedsItem.class)
public class WheatSeedsItemMixin {
    @Shadow private int plantBlock;

    @Overwrite
    public boolean use(ItemStack stack, PlayerEntity player, World world, int x, int y, int z, int face) {
        if (face != 1) {
            return false;
        } else {
            int var8 = world.getBlock(x, y, z);
            if (var8 == Block.FARMLAND.id && world.canReplace(this.plantBlock, x, y + 1, z, false)) {
                world.setBlock(x, y + 1, z, this.plantBlock);
                --stack.size;
                return true;
            } else {
                return false;
            }
        }
    }
}
