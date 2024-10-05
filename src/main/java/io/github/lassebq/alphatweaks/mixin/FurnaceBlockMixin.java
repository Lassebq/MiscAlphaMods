package io.github.lassebq.alphatweaks.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import io.github.lassebq.alphatweaks.OnPlacedByEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWithBlockEntity;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

@Mixin(FurnaceBlock.class)
public abstract class FurnaceBlockMixin extends BlockWithBlockEntity implements OnPlacedByEntity {
	@Unique
   	private Random random = new Random();

   	protected FurnaceBlockMixin(int id, boolean lit) {
		super(id, Material.STONE);
   	}

	@Override
   	public void onRemoved(World world, int x, int y, int z) {
		int currentBlock = world.getBlock(x, y, z);
		if(currentBlock == Block.FURNACE.id || currentBlock == Block.LIT_FURNACE.id) {
			return;
		}
		FurnaceBlockEntity var5 = (FurnaceBlockEntity)world.getBlockEntity(x, y, z);

		for(int var6 = 0; var6 < var5.getSize(); ++var6) {
			ItemStack var7 = var5.getStack(var6);
			if (var7 != null) {
				float var8 = this.random.nextFloat() * 0.8F + 0.1F;
				float var9 = this.random.nextFloat() * 0.8F + 0.1F;
				float var10 = this.random.nextFloat() * 0.8F + 0.1F;

				while(var7.size > 0) {
					int var11 = this.random.nextInt(21) + 10;
					if (var11 > var7.size) {
						var11 = var7.size;
					}

					var7.size -= var11;
					ItemEntity var12 = new ItemEntity(world, (double)((float)x + var8), (double)((float)y + var9), (double)((float)z + var10), new ItemStack(var7.itemId, var11, var7.metadata));
					float var13 = 0.05F;
					var12.velocityX = (double)((float)this.random.nextGaussian() * var13);
					var12.velocityY = (double)((float)this.random.nextGaussian() * var13 + 0.2F);
					var12.velocityZ = (double)((float)this.random.nextGaussian() * var13);
					world.addEntity(var12);
				}
			}
		}

		super.onRemoved(world, x, y, z);
	}

	@Overwrite
	public void onAdded(World world, int x, int y, int z) {
		super.onAdded(world, x, y, z);
	}

	@Override
	public void onPlaced(World world, int x, int y, int z, LivingEntity entity) {
		int var6 = MathHelper.floor((double)(entity.yaw * 4.0F / 360.0F) + 0.5) & 3;
		if (var6 == 0) {
			world.setBlockMetadata(x, y, z, 2);
		}
	
		if (var6 == 1) {
			world.setBlockMetadata(x, y, z, 5);
		}
	
		if (var6 == 2) {
			world.setBlockMetadata(x, y, z, 3);
		}
	
		if (var6 == 3) {
			world.setBlockMetadata(x, y, z, 4);
		}

	}

}
