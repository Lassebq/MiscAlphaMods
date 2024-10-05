package io.github.lassebq.alphatweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.Block;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

@Mixin(World.class)
public abstract class WorldMixin {
    @Shadow public abstract int getBlock(int x, int y, int z);
    @Shadow public abstract boolean canBuildIn(Box box);

    @Overwrite
    public boolean canReplace(int i, int j, int k, int l, boolean bl) {
        int var6 = this.getBlock(j, k, l);
        Block var7 = Block.BY_ID[var6];
        Block var8 = Block.BY_ID[i];
        Box var9 = var8.getCollisionShape((World)(Object)this, j, k, l);
        if (bl) {
            var9 = null;
        }

        if (var9 != null && !this.canBuildIn(var9)) {
            return false;
        } else if (var7 != Block.FLOWING_WATER && var7 != Block.WATER && var7 != Block.FLOWING_LAVA
                && var7 != Block.LAVA && var7 != Block.FIRE && var7 != Block.SNOW_LAYER) {
            return i > 0 && var7 == null && var8.canSurvive((World)(Object)this, j, k, l);
        } else {
            return var8.canSurvive((World)(Object)this, j, k, l);
        }
    }
}
