package io.github.lassebq.visualfixes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.model.entity.HumanoidModel;

@Mixin(HumanoidModel.class)
public class HumanoidModelMixin extends Model {
	@Shadow private ModelPart hat;
	@Shadow private boolean sneaking;

	@Inject(method = "setAngles(FFFFFF)V", at = @At("TAIL"))
	public void setAngles(float handSwing, float handSwingAmount, float age, float yaw, float pitch, float scale, CallbackInfo ci) {
		if(sneaking) {
			hat.pivotY = 1.0F;
		} else {
			hat.pivotY = 0.0F;
		}
	}
}
