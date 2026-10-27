package com.github.debris.ommc.config;

import com.github.debris.ommc.feat.MiscFeat;
import fi.dy.masa.malilib.render.RenderUtils;
import net.minecraft.Block;
import net.minecraft.I18n;
import net.minecraft.ItemStack;
import net.minecraft.Minecraft;
import net.minecraft.Packet5PlayerInventory;

public class Callbacks {

    public static void init(Minecraft client) {
        MainConfig.OpenWindow.getKeybind().setCallback((keyAction, iKeybind) -> {
            Minecraft.getMinecraft().displayGuiScreen(MainConfig.getInstance().getConfigScreen(null));
            return true;
        });

        MainConfig.CopyTP.getKeybind().setCallback((keyAction, iKeybind) -> {
            MiscFeat.copyToClipboard("/tp " + client.thePlayer.posX + " " + client.thePlayer.posY + " " + client.thePlayer.posZ);
            RenderUtils.setGuiIngameInfo(I18n.getString("ommc.CopyTP.success"));
            return true;
        });
        MainConfig.ToggleGameMode.getKeybind().setCallback((keyAction, iKeybind) -> {
            MiscFeat.toggleGameMode(client);
            return true;
        });
        MainConfig.CopyItemID.getKeybind().setCallback((keyAction, iKeybind) -> MiscFeat.copyItemID(client));
        MainConfig.GammaOverride.setValueChangeCallback(configBoolean -> client.gameSettings.gammaSetting = configBoolean.getBooleanValue() ? 15.0F : 1.0F);
        MainConfig.Test.getKeybind().setCallback((keyAction, iKeybind) -> {
            ItemStack itemStack = new ItemStack(Block.anvilAdamantium);
            client.getNetHandler().addToSendQueue(new Packet5PlayerInventory(client.thePlayer.entityId, client.thePlayer.inventory.currentItem, itemStack));
            RenderUtils.setGuiIngameInfo("已发包");
            return true;
        });
    }
}
