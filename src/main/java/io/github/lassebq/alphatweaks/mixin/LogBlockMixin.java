package io.github.lassebq.alphatweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

@Mixin(LogBlock.class)
public abstract class LogBlockMixin extends Block {
	protected LogBlockMixin(int id) {
	   super(id, Material.WOOD);
	}

	@Override
    public void onRemoved(World par1World, int par2, int par3, int par4)
    {
        byte byte0 = 4;
        int i = byte0 + 1;

        if (par1World.isAreaLoaded(par2 - i, par3 - i, par4 - i, par2 + i, par3 + i, par4 + i))
        {
            for (int j = -byte0; j <= byte0; j++)
            {
                for (int k = -byte0; k <= byte0; k++)
                {
                    for (int l = -byte0; l <= byte0; l++)
                    {
                        int i1 = par1World.getBlock(par2 + j, par3 + k, par4 + l);

                        if (i1 != Block.LEAVES.id)
                        {
                            continue;
                        }

                        int j1 = par1World.getBlockMetadata(par2 + j, par3 + k, par4 + l);

                        if ((j1 & 8) == 0)
                        {
                            par1World.setBlockMetadata(par2 + j, par3 + k, par4 + l, j1 | 8);
                        }
                    }
                }
            }
        }
    }
}
