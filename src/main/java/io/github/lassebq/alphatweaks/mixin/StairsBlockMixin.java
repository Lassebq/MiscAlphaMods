package io.github.lassebq.alphatweaks.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.lassebq.alphatweaks.OnPlacedByEntity;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

@Mixin(StairsBlock.class)
public class StairsBlockMixin extends Block implements OnPlacedByEntity {
	@Shadow private Block baseBlock;

	protected StairsBlockMixin(int id, Block block) {
	   super(id, block.sprite, block.material);
	}

	@Override
	public void updateShape(WorldView world, int x, int y, int z) {
		this.setShape(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public Box getCollisionShape(World world, int x, int y, int z) {
	   return super.getCollisionShape(world, x, y, z);
	}

	@Inject(
		method = "getDropItem(ILjava/util/Random;)I",
		at = @At("HEAD"),
		cancellable = true
	)
	public void getDropItem(int metadata, Random random, CallbackInfoReturnable<Integer> ci) {
		ci.setReturnValue(this.id);
	}

	@Inject(
		method = "getBaseDropCount(Ljava/util/Random;)I",
		at = @At("HEAD"),
		cancellable = true
	)
	public void getBaseDropCount(Random random, CallbackInfoReturnable<Integer> ci) {
		ci.setReturnValue(1);
	}

	@Inject(
		method = "dropItems(Lnet/minecraft/world/World;IIIIF)V",
		at = @At("HEAD"),
		cancellable = true
	)
	public void dropItems(World world, int x, int y, int z, int metadata, float luck, CallbackInfo ci) {
		super.dropItems(world, x, y, z, metadata, luck);
		ci.cancel();
	}

	@Inject(
		method = "dropItems(Lnet/minecraft/world/World;IIII)V",
		at = @At("HEAD"),
		cancellable = true
	)
	public void dropItems(World world, int x, int y, int z, int metadata, CallbackInfo ci) {
		super.dropItems(world, x, y, z, metadata);
		ci.cancel();
	}

	@Inject(
		method = "neighborChanged(Lnet/minecraft/world/World;IIII)V",
		at = @At("HEAD"),
		cancellable = true
	)
	public void neighborChanged(World world, int x, int y, int z, int neighborBlockId, CallbackInfo ci) {
		super.neighborChanged(world, x, y, z, neighborBlockId);
		ci.cancel();
	}

	@Inject(
		method = "onAdded(Lnet/minecraft/world/World;III)V",
		at = @At("HEAD"),
		cancellable = true
	)
	public void onAdded(World world, int x, int y, int z, CallbackInfo ci) {
		this.neighborChanged(world, x, y, z, 0);
		this.baseBlock.onAdded(world, x, y, z);
		ci.cancel();
	}

	@Override
	public void onPlaced(World world, int x, int y, int z, LivingEntity entity) {
		int var6 = MathHelper.floor((double)(entity.yaw * 4.0F / 360.0F) + 0.5) & 3;
		if (var6 == 0) {
			world.setBlockMetadata(x, y, z, 2);
		}
	
		if (var6 == 1) {
			world.setBlockMetadata(x, y, z, 1);
		}
	
		if (var6 == 2) {
			world.setBlockMetadata(x, y, z, 3);
		}
	
		if (var6 == 3) {
			world.setBlockMetadata(x, y, z, 0);
		}

	}
}
