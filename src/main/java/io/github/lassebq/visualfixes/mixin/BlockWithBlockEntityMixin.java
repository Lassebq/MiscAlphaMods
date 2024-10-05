package io.github.lassebq.visualfixes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWithBlockEntity;
import net.minecraft.block.material.Material;

@Mixin(BlockWithBlockEntity.class)
public class BlockWithBlockEntityMixin extends Block {
	private BlockWithBlockEntityMixin(int id, int sprite, Material material) {
		super(id, sprite, material);
	}

	@Inject(method = "<init>(IILnet/minecraft/block/material/Material;)V", at = @At("RETURN"))
	protected void init(int i, int j, Material mat, CallbackInfo ci) {
		HAS_BLOCK_ENTITY[i] = true;
	}
}
