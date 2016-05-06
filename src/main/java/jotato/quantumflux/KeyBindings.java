package jotato.quantumflux;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyBindings {

    public static KeyBinding magnetkey;

    public static void init() {
    	magnetkey = new KeyBinding("key.quantumflux.magnet", Keyboard.KEY_M, "key.categories.gameplay");
        ClientRegistry.registerKeyBinding(magnetkey);
    }
}