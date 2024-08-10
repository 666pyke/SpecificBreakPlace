package org.me.pyke.specificbreakplace;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.Vector3;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.SetFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Set;

public class SpecificBreakPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        org.bukkit.Location bukkitLocation = event.getBlock().getLocation();
        Material blockType = event.getBlock().getType();

        Location wgLocation = new Location(
                BukkitAdapter.adapt(bukkitLocation.getWorld()),
                Vector3.at(bukkitLocation.getX(), bukkitLocation.getY(), bukkitLocation.getZ())
        );

        RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
        ApplicableRegionSet regions = query.getApplicableRegions(wgLocation);

        StateFlag blockBreakFlag = (StateFlag) WorldGuard.getInstance().getFlagRegistry().get("block-break");
        StateFlag.State blockBreakState = regions.queryState(WorldGuardPlugin.inst().wrapPlayer(player), blockBreakFlag);

        if (blockBreakState == StateFlag.State.DENY) {
            Set<Material> allowedBlocks = queryValue(player, regions, SpecificBreakPlace.CUSTOM_BLOCK_PLACE);

            event.setCancelled(allowedBlocks == null || !allowedBlocks.contains(blockType));
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        org.bukkit.Location bukkitLocation = event.getBlock().getLocation();
        Material blockType = event.getBlock().getType();

        Location wgLocation = new Location(
                BukkitAdapter.adapt(bukkitLocation.getWorld()),
                Vector3.at(bukkitLocation.getX(), bukkitLocation.getY(), bukkitLocation.getZ())
        );

        RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
        ApplicableRegionSet regions = query.getApplicableRegions(wgLocation);

        StateFlag blockBreakFlag = (StateFlag) WorldGuard.getInstance().getFlagRegistry().get("block-break");
        StateFlag.State blockBreakState = regions.queryState(WorldGuardPlugin.inst().wrapPlayer(player), blockBreakFlag);

        if (blockBreakState == StateFlag.State.DENY) {
            Set<Material> allowedBlocks = queryValue(player, regions, SpecificBreakPlace.CUSTOM_BLOCK_BREAK);

            event.setCancelled(allowedBlocks == null || !allowedBlocks.contains(blockType));
        }
    }

    /// imi plac fetele

    private Set<Material> queryValue(Player player, ApplicableRegionSet regions, SetFlag<Material> flag) {
        return regions.queryValue(WorldGuardPlugin.inst().wrapPlayer(player), flag);
    }
}
