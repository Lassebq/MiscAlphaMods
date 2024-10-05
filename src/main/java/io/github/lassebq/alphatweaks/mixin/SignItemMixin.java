package io.github.lassebq.alphatweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.item.SignItem;

@Mixin(SignItem.class)
public class SignItemMixin {

    @Redirect(
        method = "use(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/living/player/PlayerEntity;Lnet/minecraft/world/World;IIII)Z",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/living/player/PlayerEntity;openSignEditor(Lnet/minecraft/block/entity/SignBlockEntity;)V"
        )

    )
    public void onPlaced(PlayerEntity player, SignBlockEntity sign) {
        if(sign != null) {
            player.openSignEditor(sign);
        }
    }
}
