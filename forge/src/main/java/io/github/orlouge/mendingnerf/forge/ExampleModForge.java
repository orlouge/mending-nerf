package io.github.orlouge.mendingnerf.forge;

import io.github.orlouge.mendingnerf.MendingNerfMod;
import net.minecraftforge.fml.common.Mod;

@Mod(MendingNerfMod.MOD_ID)
public class ExampleModForge {
    public ExampleModForge() {
        // Submit our event bus to let architectury register our content on the right time
        MendingNerfMod.init();
    }
}
