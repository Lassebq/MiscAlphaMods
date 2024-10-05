package io.github.lassebq.visualfixes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.client.render.BlockRenderer;

@Mixin(BlockRenderer.class)
public class BlockRendererMixin {
	@Inject(method = "tessellateFence(Lnet/minecraft/block/Block;III)Z", at = @At("RETURN"), cancellable = true)
	public void tessellateFence(Block block, int x, int y, int z, CallbackInfoReturnable<Boolean> ci) {
		ci.setReturnValue(true);
	}
}
