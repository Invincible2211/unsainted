package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.resources.ExceptionMessagesKeys;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.HashMap;
import java.util.UUID;

public class AssetsManager
{

    private final static HashMap<UUID, Object> ASSETS = new HashMap<>();

    public static Object getAssetById (UUID key)
    {
        return ASSETS.get(key);
    }

    public static UUID getIdFromObject (Object object)
    {
        for (UUID key :
                ASSETS.keySet()) {
            if (ASSETS.get(key) == object) {
                return key;
            }
        }
        GlobalLogger.warning(ExceptionMessagesKeys.NO_ID_FOUND_EXCEPTION);
        return null;
    }


    public static void saveAsset (UUID uuid, Object object)
    {
        ASSETS.put(uuid, object);

    }

}
