package main.de.grzb.szeibernaeticks.block;

import main.de.grzb.szeibernaeticks.tileentity.TileEntityGuiContainerAssembler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTileEntityGuiContainerAssembler extends BlockTileEntityGuiContainerBase {

    public BlockTileEntityGuiContainerAssembler() {
        super("assembler", Material.IRON);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityGuiContainerAssembler(this);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        world.removeTileEntity(pos);
    }

}
