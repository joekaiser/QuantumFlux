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
    public static boolean zpe_enabled;
    public static boolean zpe_doesDamage;
    public static int zpe_damageRange;
    
    public static int redfluxField_buffer;
    public static int rfExciter1_output;
    public static int rfExciter_maxUpgrades;
    public static int quibitcell_output;
    
    public static int magnet_strength;
    public static boolean enableSteelRecipe;
    
    public static int battlesuit_maxEnergy;
    public static int battlesuit_chargeRate;
    public static int battlesuit_drain;
    
    public static int eviscerator_maxEnergy;
    public static int eviscerator_chargeRate;
    
    public static int imaginaryTime_energyRequirement;
    public static int imaginaryTime_tickIncrease;
    public static int imaginaryTime_range;
    public static int imaginaryTime_chargeRate;
    

    public static void init(Configuration configuration)
    {
        config = configuration;
        config.load();
        hydrateConifg();
        config.save();
    }

    public static void hydrateConifg()
    {
        incinerator_output = config.getInt("output", "entropyAccelerator", 2, 2, 8, "The RF generated per tick");
        incinerator_buffer = config.getInt("buffer", "entropyAccelerator", 2000, 100, 10000, "The amount of energy that can be stored in the block");
        incinerator_burnTime = config.getInt("burnTime", "entropyAccelerator", 20, 10, 1000, "How many ticks an item will burn");

        quibitCluster_baseStorage = config.getInt("baseStorage", "quibitCluster", 500000, 100000, 1000000, "The base amount of RF the Quibit Clusters can hold");
        quibitCluster_baseTransferRate = config.getInt("baseTransferRate", "quibitCluster", 100, 50, 1000, "The base RF/tick the Quibit Clusters can do");
        quibitCluster_multiplier = config.getInt("multiplier", "quibitCluster", 5, 4, 6, "The multiplyer between each level of the clusters");
        
        zpe_maxPowerGen = config.getInt("powerGen", "zeroPointExtractor", 150, 32, 256, "The maximum amount of rf/t it can generate");
        zpe_particles = config.getBoolean("particles", "zeroPointExtractor", true, "Enable particle effect");
        zpe_enabled= config.getBoolean("enabled", "zeroPointExtractor", true, "Enable the Zero Point Extractor");
        zpe_doesDamage = config.getBoolean("doesDamage", "zeroPointExtractor",true,"Deal damage to Entities in range");
        zpe_damageRange = config.getInt("damageRange", "zeroPointExtractor", 2, 2, 4, "Entities within N blocks will receive damage");
        
        redfluxField_buffer = config.getInt("buffer", "redfluxField", 2500000, 100000, Integer.MAX_VALUE, "The internal storage of each player's field");
        rfExciter1_output = config.getInt("rfExciter_baseOutput", "redfluxField", 100, 100, 1000, "The output without any upgrades");
        quibitcell_output = config.getInt("quibitCell_output", "redfluxField", 100, 100, 500, "How much rf/tick the quibit cell can charge per item");
        rfExciter_maxUpgrades = config.getInt("rfExciter_maxUpgrades", "redfluxField", 640, 100, 1000, "How many upgrades can be applied to an Exciter");
        
        enableSteelRecipe = config.getBoolean("enableSteelRecipe", "misc", true, "If you don't want my vanilla-like steel recipe, set this to false. **WARNING** you will need another mod to provide steel under the 'ingotSteel' oredictionary name");
        magnet_strength = config.getInt("magnetStrength", "misc",6, 4, 14, "How far the magnet can reach");
        
        imaginaryTime_energyRequirement =config.getInt("energyRequirement", "imaginaryTime", 500, 50, 1000, "How much energy is used per work cycle. The higher the number the more RF must be pumped in before work will be done");
        imaginaryTime_tickIncrease=config.getInt("tickRateIncrease", "imaginaryTime", 1, 1, 4, "How many extra ticks each block will get per cycle");
        imaginaryTime_range=config.getInt("range", "imaginaryTime", 3, 2, 7, "The area of effect along the X&Z. Y is always 2");
        imaginaryTime_chargeRate=config.getInt("chargeRate", "imaginaryTime", 1000,25,1000,"How much RF/T it can accept. Setting this lower than the energyRequirement means it won't work every tick");
        
        battlesuit_chargeRate = config.getInt("chargeRate","battlesuit",80,50,500,"How fast the Battlesuit can charge");
        battlesuit_maxEnergy = config.getInt("maxEnergy", "battlesuit", 50000, 10000, 100000, "The maximum amount of energy that the battlesuit can hold");
        battlesuit_drain = config.getInt("drain", "battlesuit", 2, 0, 5, "The amount of energy drained every 100 ticks from battlesuits that have special abilities");
        
        eviscerator_chargeRate = config.getInt("chargeRate","eviscerator",500,20,500,"How fast the Eviscerator can charge");
        eviscerator_maxEnergy = config.getInt("maxEnergy", "eviscerator", 50000, 10000, 100000, "The maximum amount of energy that the Eviscerator can hold");
    }
}
