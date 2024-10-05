// Source code is decompiled from a .class file using FernFlower decompiler.
package io.github.lassebq.creative;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.inventory.menu.InventoryMenuScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.slot.InventorySlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.unmapped.C_0350299;

public class CreativeInventoryScreen extends InventoryMenuScreen {
	public List items = new ArrayList();
	private static SimpleInventory inventory = new SimpleInventory("tmp", 72);
	private float scrollPosition = 0.0F;
	private boolean hasScrollBar = false;
	private boolean isMouseButtonDown;

	public CreativeInventoryScreen(PlayerEntity player) {
		this.passEvents = true;
		this.backgroundHeight = 208;
		Block[] var2 = new Block[] { Block.COBBLESTONE, Block.STONE, Block.DIAMOND_ORE, Block.GOLD_ORE, Block.IRON_ORE,
				Block.COAL_ORE, Block.REDSTONE_ORE, Block.CLAY, Block.DIAMOND_BLOCK, Block.GOLD_BLOCK,
				Block.IRON_BLOCK, Block.BEDROCK,
				Block.BRICKS, Block.MOSSY_COBBLESTONE,
				Block.STONE_SLAB, Block.OBSIDIAN,
				Block.LOG, Block.LEAVES,
				Block.DIRT, Block.GRASS, Block.SAND, Block.GRAVEL, Block.PLANKS,
				Block.SAPLING, Block.SPONGE, Block.ICE, Block.SNOW,
				Block.YELLOW_FLOWER, Block.RED_FLOWER, Block.BROWN_MUSHROOM, Block.RED_MUSHROOM, Block.REEDS,
				Block.CACTUS, Block.CHEST, Block.CRAFTING_TABLE, Block.GLASS, Block.TNT, Block.BOOKSHELF,
				Block.WOOL, Block.FURNACE, Block.JUKEBOX,
				Block.FENCE, Block.LADDER, Block.RAIL,
				Block.TORCH, Block.OAK_STAIRS, Block.STONE_STAIRS,
				Block.LEVER, Block.STONE_PRESSURE_PLATE, Block.WOODEN_PRESSURE_PLATE, Block.REDSTONE_TORCH,
				Block.STONE_BUTTON };

		int var9;
		int var8;
		for (var8 = 0; var8 < var2.length; ++var8) {
			this.items.add(new ItemStack(var2[var8], 1));
		}

		for (var8 = 256; var8 < Item.BY_ID.length; ++var8) {
			if (Item.BY_ID[var8] != null) {
				this.items.add(new ItemStack(Item.BY_ID[var8]));
			}
		}

		PlayerInventory var11 = player.inventory;

		for (var9 = 0; var9 < 9; ++var9) {
			for (int var10 = 0; var10 < 8; ++var10) {
				this.menu.add(new C_0350299(this, inventory, var10 + var9 * 8,
						8 + var10 * 18, 18 + var9 * 18));
			}
		}

		for (var9 = 0; var9 < 9; ++var9) {
			this.menu.add(new C_0350299(this, var11, var9, 8 + var9 * 18, 184));
		}

		this.scrollItems(0.0F);
	}

	public void tick() {
	}

	private InventorySlot getHoveredSlot(int mouseX, int mouseY) {
		for (int var3 = 0; var3 < this.menu.size(); ++var3) {
			C_0350299 var4 = (C_0350299) this.menu.get(var3);
			if (var4.m_7140626(mouseX, mouseY)) {
				return var4;
			}
		}

		return null;
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (mouseButton == 0 || mouseButton == 1) {
			InventorySlot var4 = this.getHoveredSlot(mouseX, mouseY);
			PlayerInventory var5 = this.minecraft.player.inventory;
			if(var4 != null) {
				if(var4.inventory == inventory) {
					ItemStack var6 = var5.cursorStack;
					ItemStack var8 = var4.getStack();
					if (var6 != null && var8 != null && var6.itemId == var8.itemId) {
						if (mouseButton == 0) {
							if (var6.size < var6.getMaxSize()) {
								++var6.size;
							}
						} else if (var6.size <= 1) {
							var5.cursorStack = null;
						} else {
							--var6.size;
						}
					} else if (var6 != null) {
						var5.cursorStack = null;
					} else if (var8 == null) {
						var5.cursorStack = null;
					} else if (var6 == null || var6.itemId != var8.itemId) {
						var5.cursorStack = var8.copy();
						var6 = var5.cursorStack;
					}
					return;
				}
			}
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	// protected void clickSlot(InventorySlot invSlot, int slot, int button) {
	// 	PlayerInventory var5;
	// 	ItemStack var6;
	// 	if (invSlot != null) {
	// 		if (invSlot.inventory == inventory) {
	// 			var5 = this.minecraft.player.inventory;
	// 			var6 = var5.getCursorStack();
	// 			ItemStack var7 = invSlot.getStack();
	// 			if (var6 != null && var7 != null && var6.itemId == var7.itemId) {
	// 				if (button == 0) {
	// 					if (quickMove) {
	// 						var6.size = var6.getMaxSize();
	// 					} else if (var6.size < var6.getMaxSize()) {
	// 						++var6.size;
	// 					}
	// 				} else if (var6.size <= 1) {
	// 					var5.setCursorStack((ItemStack) null);
	// 				} else {
	// 					--var6.size;
	// 				}
	// 			} else if (var6 != null) {
	// 				var5.setCursorStack((ItemStack) null);
	// 			} else if (var7 == null) {
	// 				var5.setCursorStack((ItemStack) null);
	// 			} else if (var6 == null || var6.itemId != var7.itemId) {
	// 				var5.setCursorStack(ItemStack.copyOf(var7));
	// 				var6 = var5.getCursorStack();
	// 				if (quickMove) {
	// 					var6.size = var6.getMaxSize();
	// 				}
	// 			}
	// 		} else {
	// 			this.menu.onClickSlot(invSlot.id, button, quickMove, this.minecraft.player);
	// 			ItemStack var8 = this.menu.getSlot(invSlot.id).getStack();
	// 			this.minecraft.interactionManager.m_4243379(var8, invSlot.id -
	// 					this.menu.slots.size() + 9 + 36);
	// 		}
	// 	} else {
	// 		var5 = this.minecraft.player.inventory;
	// 		if (var5.getCursorStack() != null) {
	// 			if (button == 0) {
	// 				this.minecraft.player.dropItem(var5.getCursorStack());
	// 				this.minecraft.interactionManager.m_2861872(var5.getCursorStack());
	// 				var5.setCursorStack((ItemStack) null);
	// 			}

	// 			if (button == 1) {
	// 				var6 = var5.getCursorStack().split(1);
	// 				this.minecraft.player.dropItem(var6);
	// 				this.minecraft.interactionManager.m_2861872(var6);
	// 				if (var5.getCursorStack().size == 0) {
	// 					var5.setCursorStack((ItemStack) null);
	// 				}
	// 			}
	// 		}
	// 	}

	// }

	public void init() {
		this.buttons.clear();
	}

	protected void drawForeground() {
		this.textRenderer.draw("Item selection", 8, 6, 4210752);
	}

	public void handleMouse() {
		super.handleMouse();
		int var1 = Mouse.getEventDWheel();
		if (var1 != 0) {
			int var2 = items.size() / 8
					- 8 + 1;
			if (var1 > 0) {
				var1 = 1;
			}

			if (var1 < 0) {
				var1 = -1;
			}

			this.scrollPosition = (float) ((double) this.scrollPosition - (double) var1 /
					(double) var2);
			if (this.scrollPosition < 0.0F) {
				this.scrollPosition = 0.0F;
			}

			if (this.scrollPosition > 1.0F) {
				this.scrollPosition = 1.0F;
			}

			scrollItems(this.scrollPosition);
		}

	}

	public void render(int mouseX, int mouseY, float tickDelta) {
		boolean var4 = Mouse.isButtonDown(0);
		int var5 = (this.width - this.backgroundWidth) / 2;
		int var6 = (this.height - this.backgroundHeight) / 2;
		int var7 = var5 + 155;
		int var8 = var6 + 17;
		int var9 = var7 + 14;
		int var10 = var8 + 160 + 2;
		if (!this.isMouseButtonDown && var4 && mouseX >= var7 && mouseY >= var8 && mouseX < var9 && mouseY < var10) {
			this.hasScrollBar = true;
		}

		if (!var4) {
			this.hasScrollBar = false;
		}

		this.isMouseButtonDown = var4;
		if (this.hasScrollBar) {
			this.scrollPosition = (float) (mouseY - (var8 + 8)) / ((float) (var10 - var8) - 16.0F);
			if (this.scrollPosition < 0.0F) {
				this.scrollPosition = 0.0F;
			}

			if (this.scrollPosition > 1.0F) {
				this.scrollPosition = 1.0F;
			}

			scrollItems(this.scrollPosition);
		}

		super.render(mouseX, mouseY, tickDelta);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(2896);
		int var11 = this.minecraft.textureManager.load("/gui/allitems.png");
		this.minecraft.textureManager.bind(var11);
		this.drawTexture(var5 + 154, var6 + 17 + (int) ((float) (var10 - var8 - 17) * this.scrollPosition), 0, 208, 16,
				16);
	}

	protected void drawBackground(float f) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int var2 = this.minecraft.textureManager.load("/gui/allitems.png");
		this.minecraft.textureManager.bind(var2);
		int var3 = (this.width - this.backgroundWidth) / 2;
		int var4 = (this.height - this.backgroundHeight) / 2;
		this.drawTexture(var3, var4, 0, 0, this.backgroundWidth, this.backgroundHeight);
	}

	protected void buttonClicked(ButtonWidget button) {
	}

	public void scrollItems(float position) {
		int var2 = this.items.size() / 8 - 8 + 1;
		int var3 = (int) ((double) (position * (float) var2) + 0.5);
		if (var3 < 0) {
			var3 = 0;
		}

		for (int var4 = 0; var4 < 9; ++var4) {
			for (int var5 = 0; var5 < 8; ++var5) {
				int var6 = var5 + (var4 + var3) * 8;
				if (var6 >= 0 && var6 < this.items.size()) {
					inventory.setStack(var5 + var4 * 8, (ItemStack) this.items.get(var6));
				} else {
					inventory.setStack(var5 + var4 * 8, (ItemStack) null);
				}
			}
		}

	}
}
