package io.github.lassebq.panorama.mixin;

import java.awt.image.BufferedImage;

import org.lwjgl.opengl.ARBVertexBlend;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.BufferBuilder;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.util.math.MathHelper;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
	@Shadow private float randomFloat;

	@Unique
	private int panoramaTex;

	@Unique
	private void color(BufferBuilder bufferBuilder, int i, int j)
    {
        int k = i >> 16 & 0xff;
        int l = i >> 8 & 0xff;
        int i1 = i & 0xff;
        bufferBuilder.color(k, l, i1, j);
    }

	@Unique
    private void func_35355_b(int i, int j, float f)
    {
        BufferBuilder bufferBuilder = BufferBuilder.INSTANCE;
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GLU.gluPerspective(120F, 1.0F, 0.05F, 10F);
        GL11.glMatrixMode(ARBVertexBlend.GL_MODELVIEW0_ARB);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glRotatef(180F, 1.0F, 0.0F, 0.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        int k = 8;
        for(int l = 0; l < k * k; l++)
        {
            GL11.glPushMatrix();
            float f1 = ((float)(l % k) / (float)k - 0.5F) / 64F;
            float f2 = ((float)(l / k) / (float)k - 0.5F) / 64F;
            float f3 = 0.0F;
            GL11.glTranslatef(f1, f2, f3);
            GL11.glRotatef(MathHelper.sin((randomFloat + f) / 400F) * 25F + 20F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-(randomFloat + f) * 0.1F, 0.0F, 1.0F, 0.0F);
            for(int i1 = 0; i1 < 6; i1++)
            {
                GL11.glPushMatrix();
                if(i1 == 1)
                {
                    GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
                }
                if(i1 == 2)
                {
                    GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
                }
                if(i1 == 3)
                {
                    GL11.glRotatef(-90F, 0.0F, 1.0F, 0.0F);
                }
                if(i1 == 4)
                {
                    GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
                }
                if(i1 == 5)
                {
                    GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
                }
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, minecraft.textureManager.load((new StringBuilder()).append("/title/bg/panorama").append(i1).append(".png").toString()));
                bufferBuilder.start();
                color(bufferBuilder, 0xffffff, 255 / (l + 1));
                float f4 = 0.0F;
                bufferBuilder.vertex(-1D, -1D, 1.0D, 0.0F + f4, 0.0F + f4);
                bufferBuilder.vertex(1.0D, -1D, 1.0D, 1.0F - f4, 0.0F + f4);
                bufferBuilder.vertex(1.0D, 1.0D, 1.0D, 1.0F - f4, 1.0F - f4);
                bufferBuilder.vertex(-1D, 1.0D, 1.0D, 0.0F + f4, 1.0F - f4);
                bufferBuilder.end();
                GL11.glPopMatrix();
            }

            GL11.glPopMatrix();
            GL11.glColorMask(true, true, true, false);
        }

        bufferBuilder.offset(0.0D, 0.0D, 0.0D);
        GL11.glColorMask(true, true, true, true);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(ARBVertexBlend.GL_MODELVIEW0_ARB);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

	@Unique
    private void func_35354_a(float f)
    {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, panoramaTex);
        GL11.glCopyTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, 256, 256);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        GL11.glColorMask(true, true, true, false);
        BufferBuilder bufferBuilder = BufferBuilder.INSTANCE;
        bufferBuilder.start();
        byte byte0 = 3;
        for(int i = 0; i < byte0; i++)
        {
            bufferBuilder.color(1.0F, 1.0F, 1.0F, 1.0F / (float)(i + 1));
            int j = width;
            int k = height;
            float f1 = (float)(i - byte0 / 2) / 256F;
            bufferBuilder.vertex(j, k, drawOffset, 0.0F + f1, 0.0D);
            bufferBuilder.vertex(j, 0.0D, drawOffset, 1.0F + f1, 0.0D);
            bufferBuilder.vertex(0.0D, 0.0D, drawOffset, 1.0F + f1, 1.0D);
            bufferBuilder.vertex(0.0D, k, drawOffset, 0.0F + f1, 1.0D);
        }

        bufferBuilder.end();
        GL11.glColorMask(true, true, true, true);
    }

	@Unique
    private void drawPanorama(int i, int j, float f)
    {
        GL11.glViewport(0, 0, 256, 256);
        func_35355_b(i, j, f);
        func_35354_a(f);
        func_35354_a(f);
        func_35354_a(f);
        func_35354_a(f);
        func_35354_a(f);
        func_35354_a(f);
        func_35354_a(f);
        func_35354_a(f);
        GL11.glViewport(0, 0, minecraft.width, minecraft.height);
        BufferBuilder bufferBuilder = BufferBuilder.INSTANCE;
        bufferBuilder.start();
        float f1 = width <= height ? 120F / (float)height : 120F / (float)width;
        float f2 = ((float)height * f1) / 256F;
        float f3 = ((float)width * f1) / 256F;
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        bufferBuilder.color(1.0F, 1.0F, 1.0F, 1.0F);
        int k = width;
        int l = height;
        bufferBuilder.vertex(0.0D, l, drawOffset, 0.5F - f2, 0.5F + f3);
        bufferBuilder.vertex(k, l, drawOffset, 0.5F - f2, 0.5F - f3);
        bufferBuilder.vertex(k, 0.0D, drawOffset, 0.5F + f2, 0.5F - f3);
        bufferBuilder.vertex(0.0D, 0.0D, drawOffset, 0.5F + f2, 0.5F + f3);
        bufferBuilder.end();
    }

	@Inject(method = "init()V", at = @At(value = "HEAD"))
	public void init(CallbackInfo ci) {
        panoramaTex = minecraft.textureManager.bind(new BufferedImage(256, 256, 2));
	}

	@Redirect(method = "render(IIF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;renderBackground()V"))
	public void injectPanorama(TitleScreen screen, int mouseX, int mouseY, float tickDelta) {
		drawPanorama(mouseX, mouseY, tickDelta);
		fillGradient(0, 0, width, height, 0xaaffffff, 0xffffff);
		fillGradient(0, 0, width, height, 0, 0xaa000000);
	}

	@Inject(method = "render(IIF)V", require = 0, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;render(IIF)V"))
	public void renderVersion(CallbackInfo ci) {
		String drawnString = "Minecraft Alpha v1.1.2_01"; // TODO get this string dynamically
		this.drawString(this.textRenderer, drawnString, 2, this.height - 10, 16777215);
	}

	@Redirect(
		method = "render(IIF)V",
		require = 0,
		at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;drawString(Lnet/minecraft/client/render/TextRenderer;Ljava/lang/String;III)V"))
	public void removeDarkText(TitleScreen screen, TextRenderer textRenderer, String text, int x, int y, int color) {
		if(color == 0x808080) {
			return;
		}
		drawString(textRenderer, text, x, y, color);
	}
}
