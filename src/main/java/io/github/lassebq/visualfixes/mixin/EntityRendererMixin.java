package io.github.lassebq.visualfixes.mixin;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {
	@Inject(
		method = "renderOnFire(Lnet/minecraft/entity/Entity;DDDF)V",
		at = @At("HEAD")
	)
	private void renderOnFire(Entity entity, double d, double d1, double d2, float f, CallbackInfo ci) {
		GL11.glPushMatrix();
		GL11.glTranslatef(0, -entity.eyeHeight, 0);
	}

	@Inject(
		method = "renderOnFire(Lnet/minecraft/entity/Entity;DDDF)V",
		at = @At("TAIL")
	)
	private void renderOnFire(CallbackInfo ci) {
		GL11.glPopMatrix();
	}

}
