package tw.xiaotuzi.trialcooldown.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.level.block.entity.trialspawner.TrialSpawner;
import tw.xiaotuzi.trialcooldown.TrialSpawnerCooldownMod;

@Mixin(TrialSpawner.class)
public abstract class TrialSpawnerMixin {
    @Shadow @Final @Mutable
    private int targetCooldownLength;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void trialSpawnerCooldown$setConfiguredCooldown(CallbackInfo ci) {
        this.targetCooldownLength = TrialSpawnerCooldownMod.cooldownTicks;
    }
}
