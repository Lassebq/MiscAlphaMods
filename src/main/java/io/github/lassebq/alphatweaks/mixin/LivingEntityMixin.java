package io.github.lassebq.alphatweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(World world) {
        super(world);
    }

    @Inject(
        method = "tickAi()V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/living/LivingEntity;moveEntityWithVelocity(FF)V",
            shift = Shift.BEFORE
        )
    )
    public void fixVelocity(CallbackInfo ci) {
        if (Math.abs(this.velocityX) < 0.005) {
			this.velocityX = 0.0;
		}

		if (Math.abs(this.velocityY) < 0.005) {
			this.velocityY = 0.0;
		}

		if (Math.abs(this.velocityZ) < 0.005) {
			this.velocityZ = 0.0;
		}
    }
}
