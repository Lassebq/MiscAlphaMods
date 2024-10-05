package io.github.lassebq.alphatweaks.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.world.World;

@Mixin(StairsBlock.class)
public class StairsBlockMixin extends Block {
	protected StairsBlockMixin(int id, Block block) {
	   super(id, block.sprite, block.material);
	}

	@Overwrite
	public int getDropItem(int metadata, Random random) {
		return this.id;
	}

	@Overwrite
	public int getBaseDropCount(Random random) {
		return 1;
	}

	@Overwrite
	public void dropItems(World world, int x, int y, int z, int metadata, float luck) {
		super.dropItems(world, x, y, z, metadata, luck);
	}

	@Overwrite
	public void dropItems(World world, int x, int y, int z, int metadata) {
		super.dropItems(world, x, y, z, metadata);
	}
}
