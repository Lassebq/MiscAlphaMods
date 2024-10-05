package io.github.lassebq.alphatweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolItem;

@Mixin(AxeItem.class)
public class AxeItemMixin extends ToolItem {
	@Shadow private static Block[] EFFECTIVE_BLOCKS = new Block[]{Block.PLANKS, Block.BOOKSHELF, Block.LOG, Block.CHEST, Block.CRAFTING_TABLE, Block.JUKEBOX, Block.WOODEN_DOOR, Block.LADDER, Block.WALL_SIGN, Block.STANDING_SIGN, Block.FENCE, Block.OAK_STAIRS, Block.WOODEN_PRESSURE_PLATE};
	public AxeItemMixin(int i, int j) {
		super(i, 3, j, EFFECTIVE_BLOCKS);
	}
}
