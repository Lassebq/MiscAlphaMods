package io.github.lassebq.alphatweaks;

import net.minecraft.entity.living.LivingEntity;
import net.minecraft.world.World;

public interface OnPlacedByEntity {
    void onPlaced(World world, int x, int y, int z, LivingEntity entity);
}
