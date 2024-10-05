package io.github.lassebq.creative.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

	public PlayerEntityMixin(World w) {
		super(w);
	}

	@Shadow public boolean damage(Entity entity, int damage) { return false; };
	@Shadow public byte f_9684671;

	public boolean voidDamage;

	@Inject(method = "<init>(Lnet/minecraft/world/World;)V", at = @At("TAIL"), require = 0)
	public void init(World world, CallbackInfo ci) {
		this.f_9684671 = 100;
	}

	@Inject(method = "damage(Lnet/minecraft/entity/Entity;I)Z", at = @At("HEAD"), require = 0, cancellable = true)
	public void damage(Entity entity, int i, CallbackInfoReturnable<Boolean> ci) {
		if(!voidDamage)
			ci.setReturnValue(false);
	}

	@Override
	protected void tickVoid() {
		voidDamage = true;
		this.damage(null, 4);
		voidDamage = false;
	}
}
