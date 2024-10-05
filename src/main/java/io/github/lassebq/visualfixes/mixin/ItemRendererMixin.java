package io.github.lassebq.visualfixes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.Block;
import net.minecraft.client.render.item.ItemRenderer;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

	@Redirect(method = "render(Lnet/minecraft/entity/ItemEntity;DDDFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;isFullCube()Z"))
	public boolean yesFullBlock(Block block) {
		return true;
	}
}
