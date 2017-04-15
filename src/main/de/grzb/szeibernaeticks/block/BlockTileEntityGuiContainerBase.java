package main.de.grzb.szeibernaeticks.block;

import main.de.grzb.szeibernaeticks.Szeibernaeticks;
import main.de.grzb.szeibernaeticks.tileentity.TileEntityGuiContainerBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * A base class to create blocks with an inventory and GUI (like
 * chests/containers, machines etc.).<br>
 * Note that the GUI_ID is currently unused, but I decided to automatically
 * assign each GUI an ID anyway.
 * 
 * @see TileEntityGuiContainerBase
 * @author yuri
 *
 */
public abstract class BlockTileEntityGuiContainerBase extends BlockTileEntityContainerBase {

    public BlockTileEntityGuiContainerBase(String name, Material material) {
        super(name, material);
    }

    /**
     * Opens the GUI for a player from the server-side.
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
            EnumFacing side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if(!(tileEntity instanceof TileEntityGuiContainerBase)) {
                return false;
            }
            player.openGui(Szeibernaeticks.instance, ((TileEntityGuiContainerBase) tileEntity).getGuiId().getId(),
                    world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

}
