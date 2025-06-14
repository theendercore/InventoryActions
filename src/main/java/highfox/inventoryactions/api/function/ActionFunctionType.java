package highfox.inventoryactions.api.function;

import highfox.inventoryactions.api.serialization.IDeserializer;
import highfox.inventoryactions.api.serialization.TypeDeserializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.DeferredRegister;

import static highfox.inventoryactions.api.util.ActionsConstants.modId;

/**
 * A type of function serializer
 */
public class ActionFunctionType extends TypeDeserializer<IActionFunction> {
    /**
     * The function serializers registry key. Use with {@link DeferredRegister} to
     * register custom functions
     *
     * <pre>
     * DeferredRegister.create(ActionFunctionType.FUNCTION_SERIALIZERS_KEY, modid);
     * </pre>
     */
    public static final ResourceKey<Registry<ActionFunctionType>> FUNCTION_SERIALIZERS_KEY = ResourceKey.createRegistryKey(modId( "function_serializers"));

    /**
     * Constructs a function deserializer type
     *
     * @param deserializer the function deserializer
     */
    public ActionFunctionType(IDeserializer<? extends IActionFunction> deserializer) {
        super(deserializer);
    }

}
