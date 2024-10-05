package io.github.lassebq.visualfixes.mixin;

import org.lwjgl.opengl.GL11;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screen.inventory.menu.InventoryMenuScreen;


@Mixin(InventoryMenuScreen.class)
public class InventoryMenuScreenMixin {
	@Shadow protected void drawForeground() {};

	@Inject(method = "render(IIF)V", at = @At("TAIL"))
	private void glDisableLighting(int x, int y, float f, CallbackInfo ci) {
		GL11.glDisable(GL11.GL_LIGHTING);
	}

	@Inject(method = "render(IIF)V",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/client/entity/living/player/InputPlayerEntity;inventory:Lnet/minecraft/entity/player/PlayerInventory;",
			shift = Shift.AFTER//,
			//opcode = Opcodes.IFNULL
		)
	)
	private void shiftHeldItem(int x, int y, float f, CallbackInfo ci) {
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		this.drawForeground();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef(0.0F, 0.0F, 96.0F);
	}

	@Redirect(method = "render(IIF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/inventory/menu/InventoryMenuScreen;drawForeground()V"))
	public void discardDrawForeground(InventoryMenuScreen screen) {}
}
