package io.github.lassebq.alphatweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import io.github.lassebq.alphatweaks.OctaveNoiseGetter;
import net.minecraft.world.gen.noise.OctaveNoiseGenerator;
import net.minecraft.world.gen.noise.SimplexNoiseGenerator;

@Mixin(OctaveNoiseGenerator.class)
public class OctaveNoiseGeneratorMixin implements OctaveNoiseGetter {
    @Shadow private SimplexNoiseGenerator[] samplers;
    @Shadow private int samplerCount;

    public double getNoise(double x, double y, double z) {
        double value = 0.0D;
        double pow = 1.0D;
        
        for(int i = 0; i < this.samplerCount; ++i) {
            value += this.samplers[i].m_9172372(x * pow, y * pow, z * pow) / pow;
            pow /= 2.0D;
        }
        return value;
    }
}
