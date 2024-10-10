package io.github.lassebq.alphatweaks.mixin;

import java.io.File;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import io.github.lassebq.alphatweaks.OverworldChunkGenerator;
import net.minecraft.block.Block;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkCache;
import net.minecraft.world.chunk.ChunkSource;
import net.minecraft.world.chunk.storage.AlphaChunkStorage;

@Mixin(World.class)
public abstract class WorldMixin {
    @Shadow public long seed;
    @Shadow public abstract int getBlock(int x, int y, int z);
    @Shadow public abstract boolean canBuildIn(Box box);
    @Shadow public abstract boolean setBlockMetadataQuietly(int x, int y, int z, int metadata);
    @Shadow public abstract void onBlockChanged(int x, int y, int z, int blockId);
    @Shadow public abstract void updateNeighbors(int x, int y, int z, int blockId);

    @Overwrite
    public void setBlockMetadata(int x, int y, int z, int metadata) {
        if(this.setBlockMetadataQuietly(x, y, z, metadata)) {
            int var5 = this.getBlock(x, y, z);
            this.updateNeighbors(x, y, z, var5);
        }
    }

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

    @Overwrite
    public ChunkSource createChunkCache(File file) {
        return new ChunkCache((World)(Object)this, new AlphaChunkStorage(file, true), new OverworldChunkGenerator((World)(Object)this, this.seed));
    }
}
