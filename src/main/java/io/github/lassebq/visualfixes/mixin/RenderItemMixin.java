package io.github.lassebq.visualfixes.mixin;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.texture.TextureManager;
import net.minecraft.item.ItemStack;

@Mixin(ItemRenderer.class)
public class RenderItemMixin {
	@Inject(method = "renderGuiItem(Lnet/minecraft/client/render/TextRenderer;Lnet/minecraft/client/render/texture/TextureManager;Lnet/minecraft/item/ItemStack;II)V",
	at = @At(
			value = "CONSTANT",
			args = "floatValue=210F"
		)//,
	// slice = @Slice(
	// 	from = @At(value = "INVOKE:FIRST", remap = false, target = "Lorg/lwjgl/opengl/GL11;glPushMatrix()V"),
	// 	to = @At(value = "INVOKE:FIRST", remap = false, target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V")
	// )
	)
	public void fixBlockScale(TextRenderer textRenderer, TextureManager textureManager, ItemStack itemStack, int x, int y, CallbackInfo ci) {
		GL11.glScalef(1.0F, 1.0F, -1.0F);
	}
}
