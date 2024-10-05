package io.github.lassebq.visualfixes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.block.Block;
import net.minecraft.entity.particle.Particle;
import net.minecraft.entity.particle.ParticleManager;
import net.minecraft.world.World;

@Mixin(ParticleManager.class)
public class ParticleManagerMixin {
	// @Shadow protected World world;

	// @ModifyArg(
	// method = "addBlockMiningParticles(III)V",
	// index = 1,
	// at = @At(
	// 	value = "INVOKE",
	// 	target = "Lnet/minecraft/entity/particle/ParticleManager;addParticle(Lnet/minecraft/entity/particle/Particle;)V"
	// 	)
	// )
	// private Particle getParticle(Particle particle, int x, int y, int z) {
	// 	Block block = Block.BY_ID[world.getBlock(x, y, z)];
	// 	particle.miscTexColumn = block.getSprite(2, world.getBlockMetadata(x, y, z));
	// 	return particle;
	// }

}
