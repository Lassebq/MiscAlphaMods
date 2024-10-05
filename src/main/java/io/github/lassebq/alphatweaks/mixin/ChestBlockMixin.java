package io.github.lassebq.alphatweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import io.github.lassebq.alphatweaks.OnPlacedByEntity;
import net.minecraft.block.BlockWithBlockEntity;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

@Mixin(ChestBlock.class)
public abstract class ChestBlockMixin extends BlockWithBlockEntity implements OnPlacedByEntity {
    protected ChestBlockMixin(int id) {
        super(id, Material.WOOD);
    }

    @Overwrite
    public int getSprite(WorldView world, int x, int y, int z, int face) {
        if (face == 1) {
            return this.sprite - 1;
        } else if (face == 0) {
            return this.sprite - 1;
        } else {
            int var6 = world.getBlock(x, y, z - 1);
            int var7 = world.getBlock(x, y, z + 1);
            int var8 = world.getBlock(x - 1, y, z);
            int var9 = world.getBlock(x + 1, y, z);
            int var10;
            int var13 = world.getBlockMetadata(x, y, z);
            if (var6 != this.id && var7 != this.id) {
                if (var8 != this.id && var9 != this.id) {
                    return face == var13 ? this.sprite + 1 : this.sprite;
                } else if (face != 4 && face != 5) {
                    var10 = 0;
                    if (var8 == this.id) {
                        var10 = -1;
                    }

                    if (face == 3) {
                        var10 = -1 - var10;
                    }
                    return (face == var13 ? this.sprite + 16 : this.sprite + 32) + var10;
                } else {
                    return this.sprite;
                }
            } else if (face != 2 && face != 3) {
                var10 = 0;
                if (var6 == this.id) {
                    var10 = -1;
                }

                if (face == 4) {
                    var10 = -1 - var10;
                }

                return (face == var13 ? this.sprite + 16 : this.sprite + 32) + var10;
            } else {
                return this.sprite;
            }
        }
    }

    @Override
    public void onPlaced(World world, int x, int y, int z, LivingEntity entity) {
        int var6 = world.getBlock(x, y, z - 1);
        int var7 = world.getBlock(x, y, z + 1);
        int var8 = world.getBlock(x - 1, y, z);
        int var9 = world.getBlock(x + 1, y, z);
        byte var10 = 0;
        int var11 = MathHelper.floor((double) (entity.yaw * 4.0F / 360.0F) + 0.5) & 3;
        if (var11 == 0) {
            var10 = 2;
        }

        if (var11 == 1) {
            var10 = 5;
        }

        if (var11 == 2) {
            var10 = 3;
        }

        if (var11 == 3) {
            var10 = 4;
        }

        if (var6 != this.id && var7 != this.id && var8 != this.id && var9 != this.id) {
            world.setBlockMetadata(x, y, z, var10);
        } else {
            if ((var6 == this.id || var7 == this.id) && (var10 == 4 || var10 == 5)) {
                if (var6 == this.id) {
                    world.setBlockMetadata(x, y, z - 1, var10);
                } else {
                    world.setBlockMetadata(x, y, z + 1, var10);
                }

                world.setBlockMetadata(x, y, z, var10);
            }

            if ((var8 == this.id || var9 == this.id) && (var10 == 2 || var10 == 3)) {
                if (var8 == this.id) {
                    world.setBlockMetadata(x - 1, y, z, var10);
                } else {
                    world.setBlockMetadata(x + 1, y, z, var10);
                }

                world.setBlockMetadata(x, y, z, var10);
            }
        }

    }
}
