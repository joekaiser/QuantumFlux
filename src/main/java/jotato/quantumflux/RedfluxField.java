package jotato.quantumflux;

import java.util.HashMap;
import java.util.UUID;

import cofh.api.energy.EnergyStorage;

public class RedfluxField
{

    private static HashMap<UUID, EnergyStorage> playerFields = new HashMap<UUID, EnergyStorage>();

    public static EnergyStorage getField(UUID playerId){
        if(playerFields.containsKey(playerId)){
            return playerFields.get(playerId);
        }else
        {
            EnergyStorage storage = new EnergyStorage(ConfigMan.redfluxField_buffer,Integer.MAX_VALUE);
            playerFields.put(playerId, storage);
            return storage;
        }
    }

}
