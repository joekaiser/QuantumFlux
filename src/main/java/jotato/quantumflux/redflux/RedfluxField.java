package jotato.quantumflux.redflux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jotato.quantumflux.Logger;

/**
 * Base on King Lemming's CoFHLib/RegistryEnderAttuned. All Kudos to him
 *
 */
public final class RedfluxField
{

	private static Map<String, List<IRedfluxProvider>> quantumLinks = new HashMap<String, List<IRedfluxProvider>>();

	public static List<IRedfluxProvider> getLinks(String owner)
	{
		if (owner == null)
		{
			return new ArrayList<IRedfluxProvider>();
		}
		List<IRedfluxProvider> providers = quantumLinks.get(owner);
		if (providers == null)
		{
			providers = new ArrayList<IRedfluxProvider>();
		}
		return providers;
	}

	public static void registerLink(IRedfluxProvider item)
	{
		Logger.debug("link " + item.toString() + " to " + item.getOwner());
		if (item.getOwner() != null)
		{
			if (!quantumLinks.containsKey(item.getOwner()))
			{
				quantumLinks.put(item.getOwner(), new ArrayList<IRedfluxProvider>());
			}

			if (!quantumLinks.get(item.getOwner()).contains(item))
			{
				quantumLinks.get(item.getOwner()).add(item);
			}
		}
	}

	public static void removeLink(IRedfluxProvider item)
	{
		Logger.debug("remove link " + item.toString() + " to " + item.getOwner());
		if (item.getOwner() != null)
		{
			quantumLinks.get(item.getOwner()).remove(item);
		}
	}

	public static void purge()
	{
		quantumLinks.clear();
	}

	public static int requestEnergy(int value, boolean simulate, String owner)
	{

		int tosend = 0;

		// Since there are multiple items on the network that can send energy
		// we will loop over all of them until we have the amount requested
		// or there is no more left
		for (IRedfluxProvider link : getLinks(owner))
		{
			if (link.canSend())
			{
				tosend += link.requestEnergy(value - tosend, simulate);

				if (tosend == value)
				{
					break;
				}
			}
		}

		return tosend;
	}
}
