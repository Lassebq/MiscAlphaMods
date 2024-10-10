package io.github.lassebq.alphatweaks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.MathHelper;

public class CaveWorldCarver extends net.minecraft.world.gen.carver.CaveWorldCarver {
    protected void placeBranch(int i, int j, byte[] bs, double d, double e, double f, float g, float h, float k, int l, int m, double n) {
        placeBranch(this.random.nextLong(), i, j, bs, d, e, f, g, h, k, l, m, n);
    }

    protected void placeBranch(long seed, int i, int j, byte[] bs, double d, double e, double f, float g, float h, float k, int l, int m, double n) {
        double var17 = (double) (i * 16 + 8);
        double var19 = (double) (j * 16 + 8);
        float var21 = 0.0F;
        float var22 = 0.0F;
        Random var23 = new Random(seed);
        if (m <= 0) {
            int var24 = this.range * 16 - 16;
            m = var24 - var23.nextInt(var24 / 4);
        }

        boolean var52 = false;
        if (l == -1) {
            l = m / 2;
            var52 = true;
        }

        int var25 = var23.nextInt(m / 2) + m / 4;

        for (boolean var26 = var23.nextInt(6) == 0; l < m; ++l) {
            double var27 = 1.5 + (double) (MathHelper.sin((float) l * 3.1415927F / (float) m) * g * 1.0F);
            double var29 = var27 * n;
            float var31 = MathHelper.cos(k);
            float var32 = MathHelper.sin(k);
            d += (double) (MathHelper.cos(h) * var31);
            e += (double) var32;
            f += (double) (MathHelper.sin(h) * var31);
            if (var26) {
                k *= 0.92F;
            } else {
                k *= 0.7F;
            }

            k += var22 * 0.1F;
            h += var21 * 0.1F;
            var22 *= 0.9F;
            var21 *= 0.75F;
            var22 += (var23.nextFloat() - var23.nextFloat()) * var23.nextFloat() * 2.0F;
            var21 += (var23.nextFloat() - var23.nextFloat()) * var23.nextFloat() * 4.0F;
            if (!var52 && l == var25 && g > 1.0F) {
                this.placeBranch(seed, i, j, bs, d, e, f, var23.nextFloat() * 0.5F + 0.5F, h - 1.5707964F, k / 3.0F, l,
                        m,
                        1.0);
                this.placeBranch(seed, i, j, bs, d, e, f, var23.nextFloat() * 0.5F + 0.5F, h + 1.5707964F, k / 3.0F, l,
                        m,
                        1.0);
                return;
            }

            if (var52 || var23.nextInt(4) != 0) {
                double var33 = d - var17;
                double var35 = f - var19;
                double var37 = (double) (m - l);
                double var39 = (double) (g + 2.0F + 16.0F);
                if (var33 * var33 + var35 * var35 - var37 * var37 > var39 * var39) {
                    return;
                }

                if (!(d < var17 - 16.0 - var27 * 2.0) && !(f < var19 - 16.0 - var27 * 2.0)
                        && !(d > var17 + 16.0 + var27 * 2.0) && !(f > var19 + 16.0 + var27 * 2.0)) {
                    int var53 = MathHelper.floor(d - var27) - i * 16 - 1;
                    int var34 = MathHelper.floor(d + var27) - i * 16 + 1;
                    int var54 = MathHelper.floor(e - var29) - 1;
                    int var36 = MathHelper.floor(e + var29) + 1;
                    int var55 = MathHelper.floor(f - var27) - j * 16 - 1;
                    int var38 = MathHelper.floor(f + var27) - j * 16 + 1;
                    if (var53 < 0) {
                        var53 = 0;
                    }

                    if (var34 > 16) {
                        var34 = 16;
                    }

                    if (var54 < 1) {
                        var54 = 1;
                    }

                    if (var36 > 120) {
                        var36 = 120;
                    }

                    if (var55 < 0) {
                        var55 = 0;
                    }

                    if (var38 > 16) {
                        var38 = 16;
                    }

                    boolean var56 = false;

                    int var40;
                    int var43;
                    for (var40 = var53; !var56 && var40 < var34; ++var40) {
                        for (int var41 = var55; !var56 && var41 < var38; ++var41) {
                            for (int var42 = var36 + 1; !var56 && var42 >= var54 - 1; --var42) {
                                var43 = (var40 * 16 + var41) * 128 + var42;
                                if (var42 >= 0 && var42 < 128) {
                                    if (bs[var43] == Block.FLOWING_WATER.id || bs[var43] == Block.WATER.id) {
                                        var56 = true;
                                    }

                                    if (var42 != var54 - 1 && var40 != var53 && var40 != var34 - 1 && var41 != var55
                                            && var41 != var38 - 1) {
                                        var42 = var54;
                                    }
                                }
                            }
                        }
                    }

                    if (!var56) {
                        for (var40 = var53; var40 < var34; ++var40) {
                            double var57 = ((double) (var40 + i * 16) + 0.5 - d) / var27;

                            for (var43 = var55; var43 < var38; ++var43) {
                                double var44 = ((double) (var43 + j * 16) + 0.5 - f) / var27;
                                int var46 = (var40 * 16 + var43) * 128 + var36;
                                boolean var47 = false;

                                for (int var48 = var36 - 1; var48 >= var54; --var48) {
                                    double var49 = ((double) var48 + 0.5 - e) / var29;
                                    if (var49 > -0.7 && var57 * var57 + var49 * var49 + var44 * var44 < 1.0) {
                                        byte var51 = bs[var46];
                                        if (var51 == Block.GRASS.id) {
                                            var47 = true;
                                        }

                                        if (var51 == Block.STONE.id || var51 == Block.DIRT.id
                                                || var51 == Block.GRASS.id) {
                                            if (var48 < 10) {
                                                bs[var46] = (byte) Block.FLOWING_LAVA.id;
                                            } else {
                                                bs[var46] = 0;
                                                if (var47 && bs[var46 - 1] == Block.DIRT.id) {
                                                    bs[var46 - 1] = (byte) Block.GRASS.id;
                                                }
                                            }
                                        }
                                    }

                                    --var46;
                                }
                            }
                        }

                        if (var52) {
                            break;
                        }
                    }
                }
            }
        }

    }
}
