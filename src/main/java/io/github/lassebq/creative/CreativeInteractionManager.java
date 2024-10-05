package io.github.lassebq.creative;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Session;
import net.minecraft.client.interaction.ClientPlayerInteractionManager;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CreativeInteractionManager extends ClientPlayerInteractionManager {

	public CreativeInteractionManager(Minecraft minecraft) {
		super(minecraft);
		this.creative = true;
	}
	public void spawn(PlayerEntity player) {
		for (int var2 = 0; var2 < 9; ++var2) {
			if (player.inventory.inventorySlots[var2] == null) {
				this.minecraft.player.inventory.inventorySlots[var2] = new ItemStack(((Block) Session.f_1364632.get(var2)).id);
			} else {
				this.minecraft.player.inventory.inventorySlots[var2].size = 1;
			}
		}

	}

	public boolean useBlock(PlayerEntity player, World world, ItemStack stack, int x, int y, int z, int face) {
		int var8 = world.getBlock(x, y, z);
		if (var8 > 0 && Block.BY_ID[var8].use(world, x, y, z, player)) {
			return true;
		} else if (stack == null) {
			return false;
		} else {
			int stackSize = stack.size;
			int dmg = stack.metadata;
			boolean use = stack.use(player, world, x, y, z, face);
			stack.size = stackSize;
			stack.metadata = dmg;
			return use;
		}
	}

	public boolean hasGui() {
	   return false;
	}

}
