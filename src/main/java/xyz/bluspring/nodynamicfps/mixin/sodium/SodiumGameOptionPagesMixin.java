package xyz.bluspring.nodynamicfps.mixin.sodium;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.caffeinemc.mods.sodium.api.config.structure.EnumOptionBuilder;
import net.caffeinemc.mods.sodium.api.config.structure.OptionBuilder;
import net.caffeinemc.mods.sodium.client.gui.SodiumConfigBuilder;
import net.minecraft.client.InactivityFpsLimit;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.function.Function;

@Pseudo
@Mixin(value = SodiumConfigBuilder.class, remap = false)
public abstract class SodiumGameOptionPagesMixin {
    @WrapOperation(method = "buildPerformancePage", at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/api/config/structure/EnumOptionBuilder;setTooltip(Ljava/util/function/Function;)Lnet/caffeinemc/mods/sodium/api/config/structure/EnumOptionBuilder;"))
    private static <E extends Enum<E>> EnumOptionBuilder<E> appendDisabledOptionTooltip(EnumOptionBuilder<E> instance, Function<E, Component> eComponentFunction, Operation<EnumOptionBuilder<E>> original) {
        if (checkIsLimiterOption(instance)) {
            return instance.setTooltip(s -> Component.translatable("options.inactivityFpsLimit." + ((InactivityFpsLimit) s).getSerializedName() + ".tooltip"));
        }

        return original.call(instance, eComponentFunction);
    }

    @Unique
    private static boolean checkIsLimiterOption(OptionBuilder instance) {
        var id = ((OptionBuilderImplAccessor) instance).getId();

        if (id == null)
            return false;

        return id.getNamespace().equals("sodium") && id.getPath().equals("performance.inactivity_fps_limit");
    }
}
