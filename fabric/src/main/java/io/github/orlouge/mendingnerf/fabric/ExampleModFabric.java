package io.github.orlouge.mendingnerf.fabric;

import io.github.orlouge.mendingnerf.MendingNerfMod;
import net.fabricmc.api.ModInitializer;

public class ExampleModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MendingNerfMod.init();
    }
}
