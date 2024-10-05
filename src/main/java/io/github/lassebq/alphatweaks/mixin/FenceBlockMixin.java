package io.github.lassebq.alphatweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

@Mixin(FenceBlock.class)
public class FenceBlockMixin extends Block {

	protected FenceBlockMixin(int id, int sprite) {
		super(id, sprite, Material.WOOD);
	}

	@Inject(
		method = "canSurvive(Lnet/minecraft/world/World;III)Z",
		at = @At("HEAD"),
		cancellable = true
	)
	public void canSurvive(World world, int x, int y, int z, CallbackInfoReturnable<Boolean> ci) {
		ci.setReturnValue(super.canSurvive(world, x, y, z));
	}
}
