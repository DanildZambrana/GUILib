package io.github.danildzambrana.guilib.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class TransferData {
    public static final Map<UUID, TypingData> TYPING_DATA_MAP;

    static {
        TYPING_DATA_MAP = new HashMap<>();
    }
}
