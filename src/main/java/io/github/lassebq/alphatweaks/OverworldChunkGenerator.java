package io.github.lassebq.alphatweaks;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class OverworldChunkGenerator extends net.minecraft.world.gen.chunk.OverworldChunkGenerator {
   public OverworldChunkGenerator(World world, long seed) {
      super(world, seed);
      cave = new CaveWorldCarver();
   }

   public void generateBiomes(int i, int j, byte[] bs) {
      byte var4 = 64;
      double var5 = 0.03125;
      OctaveNoiseGetter noiseGen = (OctaveNoiseGetter)this.f_4386868;
      double surfaceScale = var5 * 2.0D;
		double startX = (double)(i * 16);
		double startZ = (double)(j * 16);

      for (int var7 = 0; var7 < 16; ++var7) {
			double decorationX = startX + (double)var7;
			double surfaceX = decorationX * surfaceScale;
			decorationX *= var5;

         for (int var8 = 0; var8 < 16; ++var8) {
				double decorationZ = startZ + (double)var8;
				double surfaceZ = decorationZ * surfaceScale;
				decorationZ *= var5;

            boolean var9 = noiseGen.getNoise(decorationX, decorationZ, 0.0D) + this.random.nextDouble() * 0.2D > 0.0D;
            boolean var10 = noiseGen.getNoise(decorationZ, 109.0134D, decorationX) + this.random.nextDouble() * 0.2D > 3.0D;
            int var11 = (int)(this.surfaceNoise.m_1189144(surfaceX, surfaceZ) / 3.0D + 3.0D + this.random.nextDouble() * 0.25D);
            int var12 = -1;
            byte var13 = (byte) Block.GRASS.id;
            byte var14 = (byte) Block.DIRT.id;

            for (int var15 = 127; var15 >= 0; --var15) {
               int var16 = (var7 * 16 + var8) * 128 + var15;
               if (var15 <= 0 + this.random.nextInt(6) - 1) {
                  bs[var16] = (byte) Block.BEDROCK.id;
               } else {
                  byte var17 = bs[var16];
                  if (var17 == 0) {
                     var12 = -1;
                  } else if (var17 == Block.STONE.id) {
                     if (var12 == -1) {
                        if (var11 <= 0) {
                           var13 = 0;
                           var14 = (byte) Block.STONE.id;
                        } else if (var15 >= var4 - 4 && var15 <= var4 + 1) {
                           var13 = (byte) Block.GRASS.id;
                           var14 = (byte) Block.DIRT.id;
                           if (var10) {
                              var13 = 0;
                           }

                           if (var10) {
                              var14 = (byte) Block.GRAVEL.id;
                           }

                           if (var9) {
                              var13 = (byte) Block.SAND.id;
                           }

                           if (var9) {
                              var14 = (byte) Block.SAND.id;
                           }
                        }

                        if (var15 < var4 && var13 == 0) {
                           var13 = (byte) Block.WATER.id;
                        }

                        var12 = var11;
                        if (var15 >= var4 - 1) {
                           bs[var16] = var13;
                        } else {
                           bs[var16] = var14;
                        }
                     } else if (var12 > 0) {
                        --var12;
                        bs[var16] = var14;
                     }
                  }
               }
            }
         }
      }

   }

}
