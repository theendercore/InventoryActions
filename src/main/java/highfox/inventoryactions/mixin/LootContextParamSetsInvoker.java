package highfox.inventoryactions.mixin;

import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Consumer;

@Mixin(LootContextParamSets.class)
public interface LootContextParamSetsInvoker {

    @Invoker
    public static LootContextParamSet invokeRegister(String name, Consumer<LootContextParamSet.Builder> builderConsumer) {
        throw new AssertionError();
    }

}
