package dog.comp;

import dog.comp.command.NetherBorder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorldBorderFix implements ModInitializer {
    public static final String MOD_ID = "worldborderfix";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            NetherBorder.register(dispatcher);
        });

        LOGGER.info("WorldBorderFix mod initialized.");
    }
}
