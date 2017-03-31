package main.de.grzb.szeibernaeticks;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{
    public static final String MODID = "Szeibernaetics";
    public static final String VERSION = "0.1";
    
    //Forge uses this to reference this mod. By creating it ourselves instead of having it be generated, we too have a reference to it.
    @Instance
    public static Main instance = new Main();
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
                    
    }
            
    @EventHandler
    public void init(FMLInitializationEvent e) {
                    
    }
            
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
                    
    }
}
