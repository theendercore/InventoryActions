package highfox.inventoryactions.api.itemprovider;

import highfox.inventoryactions.api.serialization.IDeserializer;
import highfox.inventoryactions.api.serialization.TypeDeserializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.DeferredRegister;

import static highfox.inventoryactions.api.util.ActionsConstants.modId;

/**
 * A type of item provider serializer
 */
public class ItemProviderType extends TypeDeserializer<IItemProvider> {
    /**
     * The item provider serializers registry key. Use with {@link DeferredRegister} to
     * register custom item providers
     *
     * <pre>
     * DeferredRegister.create(ItemProviderType.ITEM_PROVIDER_SERIALIZERS_KEY, modid);
     * </pre>
     */
    public static final ResourceKey<Registry<ItemProviderType>> ITEM_PROVIDER_SERIALIZERS_KEY = ResourceKey.createRegistryKey(modId("item_provider_serializers"));

    /**
     * Constructs a item provider deserializer type
     *
     * @param deserializer the item provider deserializer
     */
    public ItemProviderType(IDeserializer<? extends IItemProvider> deserializer) {
        super(deserializer);
    }

}
