package io.github.lassebq.alphatweaks.mixin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.PressurePlateBlock__ActivationRule;
import net.minecraft.block.material.Material;

@Mixin(PressurePlateBlock.class)
public class PressurePlateBlockMixin extends Block {
	@Shadow private PressurePlateBlock__ActivationRule rule;

	protected PressurePlateBlockMixin(int i, int j, PressurePlateBlock__ActivationRule pressurePlateBlock__ActivationRule) {
		super(i, j, Material.STONE);
	}

	@Redirect(
    method = "<init>(IILnet/minecraft/block/PressurePlateBlock__ActivationRule;)V",
    at = @At(
        value = "FIELD",
        target = "Lnet/minecraft/block/material/Material;STONE:Lnet/minecraft/block/material/Material;"
        // opcode = Opcodes.GETSTATIC
    )
	)
	private static Material getCustomMaterial(int i, int j, PressurePlateBlock__ActivationRule rule) {
		return j == Block.PLANKS.sprite ? Material.WOOD : Material.STONE;
	}

}
