package xyz.bluspring.nodynamicfps;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;

public class NoDynamicFps implements Runnable {
    @Override
    public void run() {
        ClassTinkerers.enumBuilder(FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_9927"), int.class, String.class, String.class)
            .addEnum("DISABLED", 2, "disabled", "options.inactivityFpsLimit.disabled")
            .build();
    }
}
