// Source code is decompiled from a .class file using FernFlower decompiler.
package io.github.lassebq.creative;

import java.util.List;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class SimpleInventory implements Inventory {
   private String name;
   private int size;
   private ItemStack[] stacks;
//    private List listeners;

   public SimpleInventory(String name, int size) {
      this.name = name;
      this.size = size;
      this.stacks = new ItemStack[size];
   }

   public ItemStack getStack(int slot) {
      return this.stacks[slot];
   }

   public ItemStack removeStack(int slot, int amount) {
      if (this.stacks[slot] != null) {
         ItemStack var3;
         if (this.stacks[slot].size <= amount) {
            var3 = this.stacks[slot];
            this.stacks[slot] = null;
            this.markDirty();
            return var3;
         } else {
            var3 = this.stacks[slot].split(amount);
            if (this.stacks[slot].size == 0) {
               this.stacks[slot] = null;
            }

            this.markDirty();
            return var3;
         }
      } else {
         return null;
      }
   }

   public void setStack(int slot, ItemStack stack) {
      this.stacks[slot] = stack;
      if (stack != null && stack.size > this.getMaxStackSize()) {
         stack.size = this.getMaxStackSize();
      }

      this.markDirty();
   }

   public int getSize() {
      return this.size;
   }

   public String getInventoryName() {
      return this.name;
   }

   public int getMaxStackSize() {
      return 64;
   }

   public void markDirty() {
    //   if (this.listeners != null) {
    //      for(int var1 = 0; var1 < this.listeners.size(); ++var1) {
    //         ((InventoryListener)this.listeners.get(var1)).onInventoryChanged(this);
    //      }
    //   }

   }

   public boolean isValid(PlayerEntity player) {
      return true;
   }

   public void onOpen() {
   }

   public void onClose() {
   }
}
