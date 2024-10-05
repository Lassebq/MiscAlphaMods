package io.github.lassebq.alphatweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.lassebq.alphatweaks.OnPlacedByEntity;
import io.github.lassebq.alphatweaks.SlabItem;
import net.minecraft.block.Block;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

@Mixin(Block.class)
public class BlockMixin implements OnPlacedByEntity {

	@Inject(method = "<clinit>()V",
	at = @At("TAIL"))
	private static void init(CallbackInfo ci) {
		Item.BY_ID[Block.STONE_SLAB.id] = null; // Avoids CONFLICT @ id message
		new SlabItem(Block.STONE_SLAB.id - 256);
	}

	@Overwrite
	public boolean canSurvive(World world, int x, int y, int z) {
		int var5 = world.getBlock(x, y, z);
		return var5 == 0 || var5 == Block.FIRE.id || var5 == Block.SNOW_LAYER.id || Block.BY_ID[var5].material.isLiquid();
	}

	@Override
	public void onPlaced(World world, int x, int y, int z, LivingEntity entity) {
	}
	
}
