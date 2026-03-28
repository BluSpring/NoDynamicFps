package xyz.bluspring.nodynamicfps;

import com.chocohead.mm.api.ClassTinkerers;

public class NoDynamicFps implements Runnable {
    @Override
    public void run() {
        ClassTinkerers.enumBuilder("net.minecraft.client.InactivityFpsLimit", String.class, String.class)
            .addEnum("DISABLED", "disabled", "options.inactivityFpsLimit.disabled")
            .build();
    }
}
