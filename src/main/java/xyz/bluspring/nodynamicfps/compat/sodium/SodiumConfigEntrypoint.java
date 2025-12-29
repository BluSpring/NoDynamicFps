package xyz.bluspring.nodynamicfps.compat.sodium;

import net.caffeinemc.mods.sodium.api.config.ConfigEntryPoint;
import net.caffeinemc.mods.sodium.api.config.structure.ConfigBuilder;
import net.minecraft.client.InactivityFpsLimit;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class SodiumConfigEntrypoint implements ConfigEntryPoint {
    private static final Identifier LIMITER_OPTION = Identifier.fromNamespaceAndPath("sodium", "performance.inactivity_fps_limit");

    @Override
    public void registerConfigLate(ConfigBuilder builder) {
        builder.registerOwnModOptions()
            .registerOptionOverlay(LIMITER_OPTION, builder.createEnumOption(Identifier.fromNamespaceAndPath("nodynamicfps", "inactivity_fps_limit"), InactivityFpsLimit.class)
                .setTooltip(s -> Component.translatable("options.inactivityFpsLimit." + s.getSerializedName() + ".tooltip"))
            );
    }
}
