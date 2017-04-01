package main.de.grzb.szeibernaeticks;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{ //TODO: move curly brace up
    public static final String MODID = "szeibernaeticks";
    public static final String VERSION = "0.1";
    
    //Forge uses this to reference this mod. By creating it ourselves instead of having it be generated, we too have a reference to it.
    @Instance
    public static Main instance = new Main();
    
    @SidedProxy(modId="szeibernaeticks",clientSide="main.de.grzb.szeibernaeticks.ClientProxy", serverSide="main.de.grzb.szeibernaeticks.CommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	proxy.preInit(e);
    }
            
    @EventHandler
    public void init(FMLInitializationEvent e) {
    	proxy.init(e);
    }
            
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	proxy.postInit(e);
    }
}
