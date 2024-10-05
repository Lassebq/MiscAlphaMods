package io.github.lassebq.visualfixes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.Block;
import net.minecraft.entity.particle.Particle;
import net.minecraft.entity.particle.ParticleManager;
import net.minecraft.world.World;

@Mixin(ParticleManager.class)
public abstract class ParticleManagerMixin {
	@Shadow protected World world;

	@Redirect(
	method = "addBlockMiningParticles(III)V",
	at = @At(
		value = "INVOKE",
		target = "Lnet/minecraft/entity/particle/ParticleManager;addParticle(Lnet/minecraft/entity/particle/Particle;)V"
		)
	)
	private void getParticle(ParticleManager particleManager, Particle particle, int x, int y, int z) {
		Block block = Block.BY_ID[world.getBlock(x, y, z)];
		particle.miscTexColumn = block.getSprite(2, world.getBlockMetadata(x, y, z));
		particleManager.addParticle(particle);
	}

}
