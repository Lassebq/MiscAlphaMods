package io.github.lassebq.visualfixes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.LoadingScreenRenderer;

@Mixin(LoadingScreenRenderer.class)
public class LoadingScreenRendererMixin {
	@Shadow private String title;
	@Shadow public void setTitle(String title) {}

	@Redirect(
		method = "updateProgress(Ljava/lang/String;)V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/render/LoadingScreenRenderer;setTitle(Ljava/lang/String;)V"
		)
	)
	public void updateProgress(LoadingScreenRenderer instance, String title, String paramTitle) {
		setTitle(paramTitle);
	}

	@Inject(
		method = "setTitle(Ljava/lang/String;)V",
		at = @At("HEAD")
	)
	public void setTitleFix(String paramTitle, CallbackInfo ci) {
		this.title = paramTitle;
	}
}
