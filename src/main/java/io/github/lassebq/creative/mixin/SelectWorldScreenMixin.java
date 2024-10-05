package io.github.lassebq.creative.mixin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import io.github.lassebq.creative.CreativeInteractionManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.interaction.ClientPlayerInteractionManager;

@Mixin(SelectWorldScreen.class)
public class SelectWorldScreenMixin extends Screen {
	@Redirect(
		method = "loadWorld(I)V",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/client/Minecraft;interactionManager:Lnet/minecraft/client/interaction/ClientPlayerInteractionManager;"
			// opcode = Opcodes.PUTFIELD
		),
		require = 0 // Some other mod may inject condition for creative. If not - we assume game is unmodded
	)
	public void setCreativeInteractionManager(Minecraft mc, ClientPlayerInteractionManager val) {
		this.minecraft.interactionManager = new CreativeInteractionManager(this.minecraft);
	}
}
