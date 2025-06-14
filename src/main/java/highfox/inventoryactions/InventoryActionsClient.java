package highfox.inventoryactions;

import com.google.common.collect.ImmutableMap;
import highfox.inventoryactions.action.InventoryAction;
import highfox.inventoryactions.api.util.ActionsConstants;
import highfox.inventoryactions.data.ActionsManager;
import highfox.inventoryactions.network.message.SyncActionsMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class InventoryActionsClient {

    public static void init(FMLJavaModLoadingContext context) {
       context.registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((minecraft, screen) -> new ConfigScreen(screen)));
    }

    public static void syncActions(SyncActionsMessage msg) {
        ImmutableMap<ResourceLocation, InventoryAction> actions = ImmutableMap.copyOf(msg.getActions());
        ActionsManager.setActions(actions);
        ActionsConstants.LOG.debug("Loaded {} inventory actions from the server", actions.size());
    }

    public static GameType getClientGameMode() {
        ClientPacketListener connection = Minecraft.getInstance().getConnection();
        PlayerInfo playerInfo = connection.getPlayerInfo(connection.getLocalGameProfile().getId());
        return playerInfo.getGameMode();
    }

    @OnlyIn(Dist.CLIENT)
    private static final class ConfigScreen extends Screen {
        private static final Component TITLE = Component.translatable(ActionsConstants.MODID + ".configScreen.title");
        private static final String ENABLE_ACTION_ICONS = ActionsConstants.MODID + ".configScreen.enableActionIcons";
        private static final Component ENABLE_ACTION_ICONS_TOOLTIP = Component.translatable(ActionsConstants.MODID + ".configScreen.enableActionIcons.tooltip");
        private final Screen lastScreen;
        private OptionsList optionsList;

        private ConfigScreen(Screen lastScreen) {
            super(TITLE);
            this.lastScreen = lastScreen;
        }

        @Override
        protected void init() {
            super.init();

            this.optionsList = new OptionsList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);

            OptionInstance<Boolean> enableActionIcons = OptionInstance.createBoolean(ENABLE_ACTION_ICONS, OptionInstance.cachedConstantTooltip(ENABLE_ACTION_ICONS_TOOLTIP), ActionConfig.displayIconForValidActions.get(), updatedValue -> {
                ActionConfig.displayIconForValidActions.set(updatedValue);
                ActionConfig.save();
            });

            this.optionsList.addBig(enableActionIcons);

            this.addWidget(this.optionsList);
            this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, button -> this.minecraft.setScreen(this.lastScreen))
                    .pos(this.width / 2 - 100, this.height - 27)
                    .size(200, 20)
                    .build());
        }

        @Override
        public void render(@NotNull GuiGraphics gui, int mouseX, int mouseY, float partialTicks) {
            this.renderBackground(gui);
            this.optionsList.render(gui, mouseX, mouseY, partialTicks);
            gui.drawCenteredString(this.font, TITLE.getVisualOrderText(), this.width / 2, 13, 0xFFFFFF);
            super.render(gui, mouseX, mouseY, partialTicks);
        }

    }

}
