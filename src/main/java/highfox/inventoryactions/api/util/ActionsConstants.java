package highfox.inventoryactions.api.util;

import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionsConstants {
    public static final String MODID = "inventoryactions";
    public static final Logger LOG = LogManager.getLogger(MODID);

    public static ResourceLocation modId(String path) {
        return id(MODID, path);
    }

    public static ResourceLocation id(String namespace, String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }

    public static ResourceLocation parseId(String id) {
        return ResourceLocation.parse(id);
    }
}
