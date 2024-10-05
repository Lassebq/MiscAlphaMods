package io.github.lassebq.alphatweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.Block;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolItem;

@Mixin(PickaxeItem.class)
public class PickaxeItemMixin extends ToolItem {
	@Shadow private static Block[] EFFECTIVE_BLOCKS = new Block[]{Block.COBBLESTONE, Block.DOUBLE_STONE_SLAB, Block.STONE_SLAB, Block.STONE, Block.MOSSY_COBBLESTONE, Block.IRON_ORE, Block.IRON_BLOCK, Block.COAL_ORE, Block.GOLD_BLOCK, Block.GOLD_ORE, Block.DIAMOND_ORE, Block.DIAMOND_BLOCK, Block.ICE, Block.REDSTONE_ORE, Block.LIT_REDSTONE_ORE, Block.FURNACE, Block.LIT_FURNACE, Block.STONE_STAIRS, Block.MOB_SPAWNER, Block.BRICKS, Block.STONE_PRESSURE_PLATE, Block.RAIL};
	public PickaxeItemMixin(int i, int j) {
		super(i, 2, j, EFFECTIVE_BLOCKS);
	}
}
