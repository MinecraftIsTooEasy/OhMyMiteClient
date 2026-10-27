package com.github.debris.ommc.mixins.entity;

import com.github.debris.ommc.config.MainConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import net.minecraft.Entity;
import net.minecraft.EntityClientPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @WrapOperation(method = "moveEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/Entity;isSneaking()Z", ordinal = 0))
    private boolean applyFakeSneakingAtLedges(Entity entity, Operation<Boolean> original, @Share("ommc$actualSneaking") LocalBooleanRef actualSneaking) {
        boolean sneaking = original.call(entity);
        actualSneaking.set(sneaking);
        return sneaking || (MainConfig.FakeSneaking.isOn() && entity instanceof EntityClientPlayerMP);
    }

    @ModifyVariable(method = "moveEntity", at = @At("LOAD"), slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/Entity;stepHeight:F", ordinal = 0)), index = 20, require = 2, allow = 2)
    private boolean restoreSneakingAfterEdgeProtection(boolean sneaking, @Share("ommc$actualSneaking") LocalBooleanRef actualSneaking) {
        // Restore both later reads: stepping may be skipped, but walking must use the real state.
        return sneaking && actualSneaking.get();
    }
}
