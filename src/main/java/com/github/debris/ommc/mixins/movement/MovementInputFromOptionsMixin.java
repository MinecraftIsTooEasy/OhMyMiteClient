package com.github.debris.ommc.mixins.movement;

import com.github.debris.ommc.config.MainConfig;
import net.minecraft.Minecraft;
import net.minecraft.MovementInput;
import net.minecraft.MovementInputFromOptions;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MovementInputFromOptions.class)
public abstract class MovementInputFromOptionsMixin extends MovementInput {

    @Inject(method = "updatePlayerMoveState", at = @At(value = "FIELD", target = "Lnet/minecraft/MovementInputFromOptions;sneak:Z", opcode = Opcodes.PUTFIELD, ordinal = 0, shift = At.Shift.AFTER))
    private void updateAutomaticMovement(CallbackInfo ci) {
        if (MainConfig.AutoForward.isOn()) {
            this.moveForward = 1.0F;
        }
        if (MainConfig.AutoLeft.isOn()) {
            this.moveStrafe = 1.0F;
        }
        if (MainConfig.AutoBack.isOn()) {
            this.moveForward = -1.0F;
        }
        if (MainConfig.AutoRight.isOn()) {
            this.moveStrafe = -1.0F;
        }
        if (MainConfig.AutoJump.isOn()) {
            this.jump = true;
        }
        if (MainConfig.PermanentSneak.isOn()
                && (MainConfig.PermanentSneakAllowInGUIs.getBooleanValue() || !Minecraft.getMinecraft().isGuiOpen(false))) {
            this.sneak = true;
        }
    }
}
