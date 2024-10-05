package io.github.lassebq.visualfixes.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.Block;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.WorldView;

@Mixin(CactusBlock.class)
public class CactusBlockMixin extends Block {
	private CactusBlockMixin(int id, int sprite, Material material) {
		super(id, sprite, material);
	}

	@Override
	public boolean shouldRenderFace(WorldView world, int x, int y, int z, int face) {
		if(face == 2 || face == 3 || face == 4 || face == 5) {
			return true;
		}
		return super.shouldRenderFace(world, x, y, z, face);
	}
}
