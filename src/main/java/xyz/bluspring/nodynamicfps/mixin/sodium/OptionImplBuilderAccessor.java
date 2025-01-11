package xyz.bluspring.nodynamicfps.mixin.sodium;

import net.caffeinemc.mods.sodium.client.gui.options.OptionImpl;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.gen.Accessor;

@Pseudo
@Mixin(OptionImpl.Builder.class)
public interface OptionImplBuilderAccessor {
    @Accessor
    Component getName();
}
