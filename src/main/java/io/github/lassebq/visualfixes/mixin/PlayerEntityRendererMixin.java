package io.github.lassebq.visualfixes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.entity.HumanoidModel;
import net.minecraft.entity.living.player.PlayerEntity;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin extends LivingEntityRenderer {
	private PlayerEntityRendererMixin(Model model, float shadowSize) {
		super(model, shadowSize);
	}
	@Shadow private HumanoidModel model1;
	@Shadow private HumanoidModel model2;
	@Inject(method = "render(Lnet/minecraft/entity/living/player/PlayerEntity;DDDFF)V",
	at = @At(
		value = "INVOKE",
		shift = Shift.AFTER,
		target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;render(Lnet/minecraft/entity/living/LivingEntity;DDDFF)V")
	)
	public void render(PlayerEntity playerEntity, double d, double e, double f, float g, float h, CallbackInfo ci) {
		this.model1.hasVehicle = this.model2.hasVehicle = this.model.hasVehicle;
		this.model1.handSwingProgress = this.model2.handSwingProgress = this.model.handSwingProgress;
	}
}
