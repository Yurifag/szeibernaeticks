package main.de.grzb.szeibernaeticks;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = Szeibernaeticks.MOD_ID, version = Szeibernaeticks.VERSION)
@Mod.EventBusSubscriber
public class Szeibernaeticks {
    public static final String MOD_ID = "szeibernaeticks";
    public static final String VERSION = "0.1";
    public static final String RESOURCE_PREFIX = MOD_ID + ":";
    
    //Forge uses this to reference this mod. By creating it ourselves instead of having it be generated, we too have a reference to it.
    @Instance
    public static Szeibernaeticks instance = new Szeibernaeticks();
    
    @SidedProxy(modId=MOD_ID,clientSide="main.de.grzb.szeibernaeticks.ClientProxy", serverSide="main.de.grzb.szeibernaeticks.CommonProxy")
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
