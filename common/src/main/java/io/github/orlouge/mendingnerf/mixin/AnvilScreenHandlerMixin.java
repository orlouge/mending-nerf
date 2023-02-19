package io.github.orlouge.mendingnerf.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {
    @Shadow private int repairItemUsage;
    private ItemStack savedSecondItem = ItemStack.EMPTY;

    public AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Inject(method = "onTakeOutput", at = @At("HEAD"))
    public void onOnTakeOutput(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        if (this.repairItemUsage > 0) {
            ItemStack item1 = this.input.getStack(0);
            ItemStack item2 = this.input.getStack(1);
            if (!item1.isEmpty() && !item2.isEmpty() && item1.getItem().canRepair(item1, item2) && EnchantmentHelper.getLevel(Enchantments.MENDING, item1) > 0) {
                this.savedSecondItem = item2.copy();
            }
        }
    }

    @Inject(method = "onTakeOutput", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;set(I)V"))
    public void onOnTakeOutputAfter(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        if (this.savedSecondItem != null && !this.savedSecondItem.isEmpty()) {
            this.input.setStack(1, this.savedSecondItem);
            this.savedSecondItem = ItemStack.EMPTY;
        }
    }
}
