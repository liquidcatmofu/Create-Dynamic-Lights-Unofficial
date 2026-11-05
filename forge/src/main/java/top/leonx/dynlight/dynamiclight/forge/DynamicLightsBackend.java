package top.leonx.dynlight.dynamiclight.forge;

public enum DynamicLightsBackend {
    NONE, SODIUM, RYOAMIC, CHEAP;

    public static DynamicLightsBackend select(boolean sodium, boolean ryoamic, boolean cheap) {
        if (sodium) return SODIUM;
        if (ryoamic) return RYOAMIC;
        if (cheap) return CHEAP;
        return NONE;
    }
}
