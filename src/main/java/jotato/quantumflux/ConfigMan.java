package jotato.quantumflux;

import net.minecraftforge.common.config.Configuration;

public class ConfigMan
{
    private static Configuration config;

    public static int incinerator_output;
    public static int incinerator_buffer;
    public static int incinerator_burnTime;

    public static int quibitCluster_baseStorage;
    public static int quibitCluster_baseTransferRate;
    public static int quibitCluster_multiplier;
    
    public static boolean zpe_particles;
    public static int zpe_maxPowerGen;
    
    public static int redfluxField_buffer;
    public static int rfExciter_buffer;
    public static int rfExciter_output;

    public static void init(Configuration configuration)
    {
        config = configuration;
        config.load();
        hydrateConifg();
        config.save();
    }

    public static void hydrateConifg()
    {
        incinerator_output = config.getInt("output", "entropyAccelerator", 10, 1, 32, "The RF generated per tick");
        incinerator_buffer = config.getInt("buffer", "entropyAccelerator", 1000, 100, 10000, "The amount of energy that can be stored in the block");
        incinerator_burnTime = config.getInt("burnTime", "entropyAccelerator", 100, 20, 1000, "How many ticks an item will burn");

        quibitCluster_baseStorage = config.getInt("baseStorage", "quibitCluster", 500000, 100000, 1000000, "The base amount of RF the Quibit Clusters can hold");
        quibitCluster_baseTransferRate = config.getInt("baseTransferRate", "quibitCluster", 100, 50, 1000, "The base RF/tick the Quibit Clusters can do");
        quibitCluster_multiplier = config.getInt("multiplier", "quibitCluster", 5, 4, 6, "The base RF/tick the Quibit Clusters can do");
        
        zpe_maxPowerGen = config.getInt("powerGen", "zeroPointExtractor", 256, 256, 2048, "The maximum amount of rf/t it can generate");
        zpe_particles = config.getBoolean("particles", "zeroPointExtractor", true, "Enable particle effect");
        
        redfluxField_buffer = config.getInt("buffer", "redfluxField", 100000000, 100000, Integer.MAX_VALUE, "The internal storage of each player's field");
        rfExciter_buffer = config.getInt("rfExciter_buffer", "redfluxField", 100000, 10000, 100000000, "The internal storage of each RF Exciter");
        rfExciter_output = config.getInt("rfExciter_output", "redfluxField", 50000, 100, 500000, "The max output an RF Exciter can do");
    }
}
