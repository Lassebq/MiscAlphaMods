package io.github.lassebq.alphatweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.lassebq.alphatweaks.SlabItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

@Mixin(Block.class)
public class BlockMixin {
	@Inject(method = "<clinit>()V",
	at = @At("RETURN"))
	private static void init(CallbackInfo ci) {
		Item.BY_ID[Block.STONE_SLAB.id] = null; // Avoids CONFLICT @ id message
		new SlabItem(Block.STONE_SLAB.id - 256);
	}
}
