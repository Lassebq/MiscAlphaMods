package io.github.lassebq.visualfixes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.texture.TextureManager;
import net.minecraft.client.render.texture.WaterSprite;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Shadow WaterSprite waterSprite;
	@Shadow TextureManager textureManager;

	@Inject(
		method = "init()V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/render/texture/TextureManager;addSprite(Lnet/minecraft/client/render/texture/TextureAtlas;)V",
			shift = Shift.AFTER
		)

	)
	public void init(CallbackInfo ci) {
		waterSprite.f_5526904 = textureManager.load("/water.png");
	}
}
