package jotato.quantumflux;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationManager
{
    private static Configuration config;
    public static int incinerator_Output;
    public static int incinerator_Buffer;
    public static int incinerator_burnTime;

    public static void init(Configuration configuration)
    {
        config = configuration;
        config.load();
        hydrateConifg();
    }

    public static void hydrateConifg()
    {
        incinerator_Output = config.getInt("output", "incinerator", 10, 1, 100, "The RF generated per tick");
        incinerator_Buffer = config.getInt("buffer", "incinerator", 100000, 10000, 1000000, "The amount of energy that can be stored in the block");
        incinerator_Buffer = config.getInt("burnTime", "incinerator", 100, 20, 1000, "How many ticks an item will burn");
        if (config.hasChanged())
            config.save();
    }
}
