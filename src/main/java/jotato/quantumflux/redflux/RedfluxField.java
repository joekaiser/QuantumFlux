package jotato.quantumflux.redflux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Base on King Lemming's CoFHLib/RegistryEnderAttuned. All Kudos to him
 *
 */
public final class RedfluxField {

	private static Map<String, List<IRedfluxExciter>> quantumLinks = new HashMap<String, List<IRedfluxExciter>>();

	public static List<IRedfluxExciter> getLinks(String owner) {
		if (owner == null) {
			return new ArrayList<IRedfluxExciter>();
		}
		return quantumLinks.get(owner);
	}

	public static void registerLink(IRedfluxExciter item) {
		if (item.getOwner() != null) {
			if (!quantumLinks.containsKey(item.getOwner())) {
				quantumLinks.put(item.getOwner(),
						new ArrayList<IRedfluxExciter>());
			}

			if (!quantumLinks.get(item.getOwner()).contains(item)) {
				quantumLinks.get(item.getOwner()).add(item);
			}
		}
	}

	public static void removeLink(IRedfluxExciter item) {
		if (item.getOwner() != null) {
			quantumLinks.get(item.getOwner()).remove(item);
		}
	}

}
