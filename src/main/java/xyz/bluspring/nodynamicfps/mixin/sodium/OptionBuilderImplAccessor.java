package xyz.bluspring.nodynamicfps.mixin.sodium;

import net.caffeinemc.mods.sodium.client.config.builder.OptionBuilderImpl;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.gen.Accessor;

@Pseudo
@Mixin(OptionBuilderImpl.class)
public interface OptionBuilderImplAccessor {
    @Accessor
    Identifier getId();
}
