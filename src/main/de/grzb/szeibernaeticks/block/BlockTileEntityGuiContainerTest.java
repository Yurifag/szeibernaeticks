package main.de.grzb.szeibernaeticks.block;

import main.de.grzb.szeibernaeticks.tileentity.TileEntityGuiContainerTest;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTileEntityGuiContainerTest extends BlockTileEntityGuiContainerBase {
    
    public BlockTileEntityGuiContainerTest() {
        super("test", Material.ROCK);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityGuiContainerTest();
    }
}
