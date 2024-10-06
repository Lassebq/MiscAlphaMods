package io.github.lassebq.alphatweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow protected boolean f_3372370;
    @Shadow public Entity vehicle;
    @Shadow public boolean onGround;
    @Shadow public float prevHorizontalSpeed;
    @Shadow public float horizontalVelocity;
    @Shadow public abstract boolean isSneaking();

    @Unique public boolean sneaking;

    @Inject(
        method = "move(DDD)V",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/entity/Entity;onGround:Z",
            shift = Shift.BEFORE
        )
    )
    public void storeState(double dx, double dy, double dz, CallbackInfo ci) {
        sneaking = this.onGround && this.isSneaking();
    }

    @Redirect(
        method = "move(DDD)V",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/entity/Entity;f_3372370:Z"
        )
    )
    public boolean stepCondition(Entity entity) {
        if(!this.f_3372370 || sneaking || vehicle != null) {
            horizontalVelocity = prevHorizontalSpeed;
        }
        return f_3372370 && vehicle == null;
    }

}
