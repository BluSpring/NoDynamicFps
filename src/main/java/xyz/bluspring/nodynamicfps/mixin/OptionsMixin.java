package xyz.bluspring.nodynamicfps.mixin;

import net.minecraft.client.InactivityFpsLimit;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Options.class)
public abstract class OptionsMixin {
    @Unique private static final Component INACTIVITY_FPS_LIMIT_TOOLTIP_DISABLED = Component.translatable("options.inactivityFpsLimit.disabled.tooltip");

    @Inject(method = "method_61969", at = @At("HEAD"), cancellable = true)
    private static void useDisabledTooltip(InactivityFpsLimit inactivityFpsLimit, CallbackInfoReturnable<Tooltip> cir) {
        if (inactivityFpsLimit.getSerializedName().equals("disabled")) {
            cir.setReturnValue(Tooltip.create(INACTIVITY_FPS_LIMIT_TOOLTIP_DISABLED));
        }
    }
}
