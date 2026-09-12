package top.leonx.dynlight;

import com.mojang.logging.LogUtils;
import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import org.slf4j.Logger;

public class CreateDynLight {
    public static final String MOD_ID = "createdynlight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        registerGlobalBehaviourProvider();
    }

    public static void registerGlobalBehaviourProvider() {
        var provider = new LightBehaviourProvider();
        MovementBehaviour.REGISTRY.registerProvider(provider);
        LOGGER.info("Registered LightBehaviourProvider to MovementBehaviour.REGISTRY");
    }
}
