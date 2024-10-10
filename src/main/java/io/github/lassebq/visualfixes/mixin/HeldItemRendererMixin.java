package io.github.lassebq.visualfixes.mixin;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.mojang.blaze3d.vertex.BufferBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.HeldItemRenderer;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {
    @Shadow private Minecraft minecraft;

    @Overwrite
    private void renderSuffocatingEffect(float tickDelta, int sprite) {
        BufferBuilder var3 = BufferBuilder.INSTANCE;
        this.minecraft.player.getBrightness(tickDelta);
        float var4 = 0.1F;
        GL11.glColor4f(var4, var4, var4, 0.5F);
        GL11.glPushMatrix();
        float var5 = -1.0F;
        float var6 = 1.0F;
        float var7 = -1.0F;
        float var8 = 1.0F;
        float var9 = -0.5F;
        float var10 = 0.0078125F;
        int var15 = (sprite & 15) << 4;
        int var16 = sprite & 240;
        float var11 = (float) var15 / 256.0F - var10;
        float var12 = ((float) var15 + 15.99F) / 256.0F + var10;
        float var13 = (float) var16 / 256.0F - var10;
        float var14 = ((float) var16 + 15.99F) / 256.0F + var10;
        var3.start();
        var3.vertex((double) var5, (double) var7, (double) var9, (double) var12, (double) var14);
        var3.vertex((double) var6, (double) var7, (double) var9, (double) var11, (double) var14);
        var3.vertex((double) var6, (double) var8, (double) var9, (double) var11, (double) var13);
        var3.vertex((double) var5, (double) var8, (double) var9, (double) var12, (double) var13);
        var3.end();
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
