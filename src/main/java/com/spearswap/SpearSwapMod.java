package com.spearswap;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpearSwapMod implements ModInitializer {
    public static final String MOD_ID = "spearswap";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("SpearSwap loaded.");
    }
}
