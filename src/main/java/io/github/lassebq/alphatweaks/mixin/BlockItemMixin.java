package io.github.lassebq.alphatweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import io.github.lassebq.alphatweaks.OnPlacedByEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(BlockItem.class)
public class BlockItemMixin {

    @Redirect(
        method = "use(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/living/player/PlayerEntity;Lnet/minecraft/world/World;IIII)Z",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/Block;updateMetadataOnPlaced(Lnet/minecraft/world/World;IIII)V"
        )

    )
    public void onPlaced(Block block, World world, int x, int y, int z, int face, ItemStack itemStack, PlayerEntity player) {
        block.updateMetadataOnPlaced(world, x, y, z, face);
        ((OnPlacedByEntity)block).onPlaced(world, x, y, z, player);
    }
}
