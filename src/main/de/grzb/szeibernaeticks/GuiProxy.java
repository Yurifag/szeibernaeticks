package main.de.grzb.szeibernaeticks;

import main.de.grzb.szeibernaeticks.client.gui.GuiFactory;
import main.de.grzb.szeibernaeticks.client.gui.GuiId;
import main.de.grzb.szeibernaeticks.container.GuiContainerBase;
import main.de.grzb.szeibernaeticks.tileentity.TileEntityGuiContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {
    private static final GuiId[] GUI_IDS = GuiId.values();

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);
        if(tileEntity instanceof TileEntityGuiContainerBase) {
            return ((TileEntityGuiContainerBase) tileEntity).getContainer(player.inventory);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);
        if(tileEntity instanceof TileEntityGuiContainerBase) {
            GuiContainerBase container = ((TileEntityGuiContainerBase) tileEntity).getContainer(player.inventory);
            return new GuiFactory(container).getGui(GUI_IDS[id]);
        }
        return null;
    }
}
