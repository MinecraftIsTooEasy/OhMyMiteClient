package com.github.debris.ommc.mixins;

import com.github.debris.ommc.config.MainConfig;
import com.github.debris.ommc.feat.HeldKeys;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Inject(method = "runTick", at = @At("HEAD"))
    private void releaseInactiveHeldKeys(CallbackInfo ci) {
        HeldKeys.releaseInactiveKeys((Minecraft) (Object) this);
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityClientPlayerMP;isUsingItem()Z", ordinal = 0))
    private void updateHeldKeys(CallbackInfo ci) {
        HeldKeys.update((Minecraft) (Object) this);
    }

    @ModifyReturnValue(method = "inDevMode", at = @At("RETURN"))
    private static boolean overrideDev(boolean original) {
        if (original) return true;
        return MainConfig.DevClient.getBooleanValue();
    }

    @ModifyReturnValue(method = "getLimitFramerate", at = @At("RETURN"))
    private int unlockFPS(int original) {
        if (original == 2) return 2;
        if (MainConfig.FPSUnlocked.getBooleanValue()) return Integer.MAX_VALUE;
        return original;
    }
}
