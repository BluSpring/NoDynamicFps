package xyz.bluspring.nodynamicfps.mixin.sodium;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.caffeinemc.mods.sodium.client.gui.SodiumGameOptionPages;
import net.caffeinemc.mods.sodium.client.gui.options.Option;
import net.caffeinemc.mods.sodium.client.gui.options.OptionImpl;
import net.caffeinemc.mods.sodium.client.gui.options.control.Control;
import net.caffeinemc.mods.sodium.client.gui.options.control.CyclingControl;
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
@Mixin(value = SodiumGameOptionPages.class, remap = false)
public abstract class SodiumGameOptionPagesMixin {
    @WrapOperation(method = "performance", at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/client/gui/options/OptionImpl$Builder;setTooltip(Ljava/util/function/Function;)Lnet/caffeinemc/mods/sodium/client/gui/options/OptionImpl$Builder;"))
    private static <S, T> OptionImpl.Builder<S, T> appendDisabledOptionTooltip(OptionImpl.Builder<S, T> instance, Function<T, Component> tooltip, Operation<OptionImpl.Builder<S, T>> original) {
        if (checkIsLimiterOption(instance)) {
            return instance.setTooltip(s -> Component.translatable("options.inactivityFpsLimit." + ((InactivityFpsLimit) s).getSerializedName() + ".tooltip"));
        }

        return original.call(instance, tooltip);
    }

    @WrapOperation(method = "performance", at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/client/gui/options/OptionImpl$Builder;setControl(Ljava/util/function/Function;)Lnet/caffeinemc/mods/sodium/client/gui/options/OptionImpl$Builder;"))
    private static <S, T> OptionImpl.Builder<S, T> appendDisabledOptionControl(OptionImpl.Builder<S, T> instance, Function<OptionImpl<S, T>, Control<T>> control, Operation<OptionImpl.Builder<S, T>> original) {
        if (checkIsLimiterOption(instance)) {
            var names = new ArrayList<Component>();
            for (InactivityFpsLimit value : InactivityFpsLimit.values()) {
                names.add(Component.translatable("options.inactivityFpsLimit." + value.getSerializedName()));
            }

            return instance.setControl(s -> (Control<T>) new CyclingControl<>((Option<InactivityFpsLimit>) s, InactivityFpsLimit.class, names.toArray(Component[]::new)));
        }

        return original.call(instance, control);
    }

    @Unique
    private static <S, T> boolean checkIsLimiterOption(OptionImpl.Builder<S, T> instance) {
        var name = ((OptionImplBuilderAccessor) instance).getName();

        if (name == null)
            return false;

        return name instanceof MutableComponent mutableComponent &&
            mutableComponent.getContents() instanceof TranslatableContents translatableContents &&
            translatableContents.getKey().equals("options.inactivityFpsLimit");
    }
}
