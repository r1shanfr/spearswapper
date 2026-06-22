package com.spearswap;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

public class SpearSwapClient implements ClientModInitializer {

    private static final int DELAY_MS = 35;
    private static KeyBinding spearSwapKey;

    private boolean macroRunning = false;
    private int macroStep = 0;
    private long nextStepTime = 0L;

    @Override
    public void onInitializeClient() {
        spearSwapKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.spearswap.activate",
                InputUtil.Type.MOUSE,
                GLFW.GLFW_MOUSE_BUTTON_5,
                "category.spearswap"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!macroRunning && spearSwapKey.wasPressed()) {
                startMacro(client);
            }
            if (macroRunning) {
                tickMacro(client);
            }
        });
    }

    private void startMacro(MinecraftClient client) {
        if (client.player == null) return;
        macroRunning = true;
        macroStep = 0;
        nextStepTime = System.currentTimeMillis();
    }

    private void tickMacro(MinecraftClient client) {
        if (client.player ==
