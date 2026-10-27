package com.github.debris.ommc.feat;

import com.github.debris.ommc.config.MainConfig;
import net.minecraft.KeyBinding;
import net.minecraft.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class HeldKeys {

    private static boolean attackHeld;
    private static boolean useHeld;

    public static void releaseInactiveKeys(Minecraft client) {
        if (!canHoldKeys(client)) {
            update(client);
        }
    }

    public static void update(Minecraft client) {
        boolean inputActive = canHoldKeys(client);
        boolean holdAttack = inputActive && MainConfig.HoldAttack.isOn();
        boolean holdUse = inputActive && MainConfig.HoldUse.isOn();

        updateKey(client.gameSettings.keyBindAttack, holdAttack, attackHeld, inputActive);
        updateKey(client.gameSettings.keyBindUseItem, holdUse, useHeld, inputActive);
        attackHeld = holdAttack;
        useHeld = holdUse;
    }

    private static boolean canHoldKeys(Minecraft client) {
        return client.theWorld != null && client.thePlayer != null && client.inGameHasFocus && !client.isGuiOpen(true);
    }

    private static void updateKey(KeyBinding keyBinding, boolean held, boolean wasHeld, boolean inputActive) {
        if (held) {
            // pressed is the held state; pressTime is a queue drained by runTick's while loops.
            if (!wasHeld && !keyBinding.pressed && keyBinding.pressTime == 0) {
                keyBinding.pressTime = 1;
            }
            keyBinding.pressed = true;
        } else if (wasHeld) {
            keyBinding.pressed = inputActive && isPhysicallyPressed(keyBinding.keyCode);
            if (!inputActive) {
                keyBinding.pressTime = 0;
            }
        }
    }

    private static boolean isPhysicallyPressed(int keyCode) {
        if (keyCode < 0) {
            return Mouse.isButtonDown(keyCode + 100);
        }
        return keyCode > 0 && Keyboard.isKeyDown(keyCode);
    }
}
