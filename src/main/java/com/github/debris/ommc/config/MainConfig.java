package com.github.debris.ommc.config;

import com.github.debris.ommc.OhMyMiteClient;
import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.ConfigTab;
import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.*;
import fi.dy.masa.malilib.hotkeys.KeybindMulti;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import fi.dy.masa.malilib.util.KeyCodes;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

import static com.github.debris.ommc.config.ConfigFactory.*;

public class MainConfig extends SimpleConfigs {
    // boolean tweaks
    public static final ConfigBoolean DisableScoreBoard = ofBoolean("ommc.disableScoreboard");
    public static final ConfigBoolean AutoQuitGame = ofBoolean("ommc.autoQuitGame");
    public static final ConfigBoolean GammaOverride = ofBooleanWithDependency("ommc.gammaOverride", "gamma_free", "config.comment.ommc.gammaOverride");
    public static final ConfigBoolean DevClient = ofBoolean("ommc.devClient");
    public static final ConfigBoolean FPSUnlocked = ofBoolean("ommc.fpsUnlocked");
    public static final ConfigBoolean RotateFree = ofBoolean("ommc.rotateFree", "config.comment.ommc.rotateFree");
    public static final ConfigBoolean RemoveGuiContainerCD = ofBoolean("ommc.removeGuiContainerCooldown", "config.comment.ommc.removeGuiContainerCooldown");
    public static final ConfigBoolean CreativeOneHit = ofBoolean("ommc.creativeOneHit");
    public static final ConfigBoolean PermanentSneakAllowInGUIs = ofBoolean("ommc.permanentSneakAllowInGuis", "config.comment.ommc.permanentSneakAllowInGuis");


    // numerical
    public static final ConfigInteger PeriodicAttackInterval = ofInteger("ommc.periodicAttackInterval", 10, 1, 200, "config.comment.ommc.periodicAttackInterval");
    public static final ConfigInteger PeriodicUseInterval = ofInteger("ommc.periodicUseInterval", 10, 1, 200, "config.comment.ommc.periodicUseInterval");
    public static final ConfigDouble FlySpeedLevel = ofDouble("ommc.flySpeedLevel", 0.99d, 0.0d, 1.0d);
    public static final ConfigDouble FlySpeedVertical = ofDouble("ommc.flySpeedVertical", 0.99d, 0.0d, 1.0d);
    public static final ConfigInteger OverrideUseInterval = ofInteger("ommc.overrideUseInterval", 250, 1, 250, "config.comment.ommc.overrideUseInterval");
    public static final ConfigInteger TasksPerTick = ofInteger("ommc.tasksPerTick", 5, 0, 20, "config.comment.ommc.tasksPerTick");
    public static final ConfigDouble QuitGameThreshold = ofDouble("ommc.quitGameThreshold", 2.0D, 0.0D, Double.MAX_VALUE, false, "config.comment.ommc.quitGameThreshold");
    public static final ConfigInteger QuitGameSpeed = ofInteger("ommc.quitGameSpeed", 4, 2, 100, "config.comment.ommc.quitGameSpeed");
    public static final ConfigInteger QuitGameCounter = ofInteger("ommc.quitGameCounter", 60, 1, 100, "config.comment.ommc.quitGameCounter");


    // press
    public static final ConfigHotkey OpenWindow = ofHotkey("ommc.openWindow", KeyCodes.getStorageString(Keyboard.KEY_X, Keyboard.KEY_C), "config.comment.ommc.openWindow");
    public static final ConfigHotkey CopyTP = ofHotkey("ommc.copyTp", KeyCodes.getStorageString(Keyboard.KEY_F3, Keyboard.KEY_C), "config.comment.ommc.copyTp");
    public static final ConfigHotkey ToggleGameMode = ofHotkey("ommc.toggleGameMode", KeyCodes.getStorageString(Keyboard.KEY_F3, Keyboard.KEY_F4), "config.comment.ommc.toggleGameMode");
    public static final ConfigHotkey CopyItemID = ofHotkey("ommc.copyItemId", KeybindMulti.fromStorageString(
            KeyCodes.getStorageString(Keyboard.KEY_LCONTROL, Keyboard.KEY_C),
            KeybindSettings.GUI
    ), "config.comment.ommc.copyItemId");


    // toggle
    public static final ConfigToggle AutoForward = ofToggle("ommc.autoForward");
    public static final ConfigToggle AutoLeft = ofToggle("ommc.autoLeft");
    public static final ConfigToggle AutoBack = ofToggle("ommc.autoBack");
    public static final ConfigToggle AutoRight = ofToggle("ommc.autoRight");
    public static final ConfigToggle AutoJump = ofToggle("ommc.autoJump");
    public static final ConfigToggle PermanentSneak = new ConfigToggle("ommc.autoSneak", "config.comment.ommc.autoSneak");
    public static final ConfigToggle FakeSneaking = new ConfigToggle("ommc.fakeSneaking", "config.comment.ommc.fakeSneaking");
    public static final ConfigToggle HoldAttack = ofToggle("ommc.holdAttack");
    public static final ConfigToggle PeriodicAttack = ofToggle("ommc.periodicAttack");
    public static final ConfigToggle HoldUse = ofToggle("ommc.holdUse");
    public static final ConfigToggle PeriodicUse = ofToggle("ommc.periodicUse");
    public static final ConfigToggle CullEntityRender = ofToggle("ommc.cullEntityRender");
    public static final ConfigToggle FastFlying = ofToggle("ommc.fastFlying");


    // debug
    public static final ConfigHotkey Test = ofHotkey("ommc.test", Keyboard.KEY_Y, "config.comment.ommc.test");
    public static final ConfigBoolean InstantCrafting = ofBoolean("InstantCrafting");


    private static final MainConfig Instance;


    public static final List<ConfigBase<?>> booleanTweaks;
    public static final List<ConfigBase<?>> numerical;
    public static final List<ConfigHotkey> press;
    public static final List<ConfigToggle> toggle;


    public static final List<ConfigBase<?>> ALL_VALUES;
    public static final List<ConfigHotkey> ALL_HOTKEYS;
    public static final List<ConfigBase<?>> ALL_CONFIGS;
    public static final List<ConfigTab> configTabs;

    public MainConfig(String name, List<ConfigHotkey> hotkeys, List<?> values, String comment) {
        super(name, hotkeys, values, comment);
    }

    public static MainConfig getInstance() {
        return Instance;
    }

    @Override
    public List<ConfigTab> getConfigTabs() {
        return configTabs;
    }

    static {
        booleanTweaks = List.of(
                DisableScoreBoard,
                AutoQuitGame,
                GammaOverride,
                DevClient,
                FPSUnlocked,
                RotateFree,
                RemoveGuiContainerCD,
                CreativeOneHit,
                PermanentSneakAllowInGUIs
        );

        numerical = List.of(
                PeriodicAttackInterval,
                PeriodicUseInterval,
                FlySpeedLevel,
                FlySpeedVertical,
                OverrideUseInterval,
                TasksPerTick,
                QuitGameThreshold,
                QuitGameSpeed,
                QuitGameCounter
        );

        press = List.of(
                OpenWindow,
                CopyTP,
                ToggleGameMode,
                CopyItemID
        );

        toggle = List.of(
                AutoForward,
                AutoLeft,
                AutoBack,
                AutoRight,
                AutoJump,
                PermanentSneak,
                FakeSneaking,
                HoldAttack,
                PeriodicAttack,
                HoldUse,
                PeriodicUse,
                CullEntityRender,
                FastFlying
        );

        ImmutableList.Builder<ConfigBase<?>> valuesBuilder = ImmutableList.builder();
        valuesBuilder.addAll(booleanTweaks);
        valuesBuilder.addAll(numerical);
        valuesBuilder.addAll(press);
        valuesBuilder.addAll(toggle);
        ALL_CONFIGS = valuesBuilder.build();


        ALL_HOTKEYS = new ArrayList<>();
        ALL_VALUES = new ArrayList<>();

        for (ConfigBase<?> config : ALL_CONFIGS) {
            if (config instanceof ConfigHotkey configHotkey) {
                ALL_HOTKEYS.add(configHotkey);
            } else {
                ALL_VALUES.add(config);
            }
        }


        ImmutableList.Builder<ConfigTab> configTabBuilder = ImmutableList.builder();
        configTabBuilder.add(new ConfigTab("generic", ALL_CONFIGS));
        configTabBuilder.add(new ConfigTab("ommc.toggles", booleanTweaks));
        configTabBuilder.add(new ConfigTab("ommc.numerical", numerical));
        configTabBuilder.add(new ConfigTab("ommc.press", press));
        configTabBuilder.add(new ConfigTab("ommc.toggle", toggle));
        configTabs = configTabBuilder.build();

        Instance = new MainConfig(OhMyMiteClient.MOD_NAME_SIMPLE + "Main", ALL_HOTKEYS, ALL_VALUES, "一些生电功能");
    }
}
