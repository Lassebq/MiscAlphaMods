package io.github.lassebq.alphatweaks.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.StoneSlabBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

@Mixin(StoneSlabBlock.class)
public class StoneSlabBlockMixin extends Block {
	@Shadow private boolean doubleSlab;

	public StoneSlabBlockMixin(int id, boolean doubleSlab) {
		super(id, 6, Material.STONE);
	}

	@Override
	public int getBaseDropCount(Random random) {
		return doubleSlab ? 2 : 1;
	}

	@Inject(
		method = "onAdded(Lnet/minecraft/world/World;III)V",
		at = @At("HEAD"),
		cancellable = true
	)
	public void onAdded(World world, int x, int y, int z, CallbackInfo ci) {
		super.onAdded(world, x, y, z);
		ci.cancel();
	}
}
