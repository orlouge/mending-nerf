package io.github.orlouge.mendingnerf.mixin;

import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ExperienceOrbEntity.class)
public class ExperienceOrbEntityMixin {
	@Inject(at = @At("HEAD"), method = "repairPlayerGears", cancellable = true)
	private void onRepairPlayerGears(PlayerEntity player, int amount, CallbackInfoReturnable<Integer> info) {
		info.setReturnValue(amount);
		info.cancel();
	}
}
