package io.github.lassebq.alphatweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

	@Inject(method = "canSurvive(Lnet/minecraft/world/World;III)Z", at = @At("HEAD"), cancellable = true)
	public void canSurvive(World world, int x, int y, int z, CallbackInfoReturnable<Boolean> ci) {
		int var5 = world.getBlock(x, y, z);
		if(var5 == Block.FIRE.id || var5 == Block.SNOW_LAYER.id) {
			ci.setReturnValue(true);
		}
	}

	@Override
	public void onPlaced(World world, int x, int y, int z, LivingEntity entity) {
	}
	
}
