package io.github.lassebq.creative.mixin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import io.github.lassebq.creative.CreativeInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.living.player.InputPlayerEntity;
import net.minecraft.client.gui.screen.Screen;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Shadow public void openScreen(Screen screen) {}
	@Shadow public InputPlayerEntity player;

	@Redirect(
		method = "tick()V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/Minecraft;openScreen(Lnet/minecraft/client/gui/screen/Screen;)V"
		),
		slice = @Slice(
			from = @At(
				value = "FIELD",
				target = "Lnet/minecraft/client/options/GameOptions;inventoryKey:Lnet/minecraft/client/options/KeyBinding;"
				// opcode = Opcodes.GETFIELD
			),
			to = @At(
				value = "FIELD",
				target = "Lnet/minecraft/client/options/GameOptions;dropKey:Lnet/minecraft/client/options/KeyBinding;"
				// opcode = Opcodes.GETFIELD
			)
		),
		require = 0 // Some other mod may inject condition for creative. If not - we assume game is unmodded
	)
	public void openInventory(Minecraft mc, Screen screen) {
		openScreen(new CreativeInventoryScreen(this.player));
	}

}
