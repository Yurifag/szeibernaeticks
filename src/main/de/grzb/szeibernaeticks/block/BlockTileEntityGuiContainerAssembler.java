package main.de.grzb.szeibernaeticks.block;

import main.de.grzb.szeibernaeticks.tileentity.TileEntityGuiContainerAssembler;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
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
    public void onEntityWalk(World world, BlockPos pos, Entity entity) {
        world.spawnEntity(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), false));
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityGuiContainerAssembler(this);
    }

}
