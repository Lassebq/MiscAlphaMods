package io.github.lassebq.visualfixes.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.Block;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.material.Material;

@Mixin(GrassBlock.class)
public class GrassBlockMixin extends Block {
	private GrassBlockMixin(int id, Material material) {
		super(id, material);
	}

	@Override
	public int getSprite(int face) {
		if (face == 1) {
			return 0;
		} else if (face == 0) {
			return 2;
		} else {
			return 3;
		}
	}
}
