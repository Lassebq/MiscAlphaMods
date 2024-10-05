package io.github.lassebq.visualfixes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.model.entity.CowModel;

@Mixin(CowModel.class)
public class CowModelMixin {
	@Redirect(method = "<init>()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelPart;setPivot(FFF)V"))
	public void modelPivotRedirect(ModelPart part, float x, float y, float z) {
		if(x == 0.0F && y == 3.0F && z == -7.0F) {
			part.setPivot(x, 4.0F, -8.0F); // Horns should have the same pivot point as the head
			return;
		}
		part.setPivot(x, y, z);
	}
}
