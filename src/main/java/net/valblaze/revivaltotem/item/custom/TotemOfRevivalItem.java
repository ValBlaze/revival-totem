package net.valblaze.revivaltotem.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;

import java.util.List;

public class TotemOfRevivalItem extends Item {
    public TotemOfRevivalItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user.isPlayer()) {
            if (!world.isClient) {
                List<ServerPlayerEntity> players = new java.util.ArrayList<ServerPlayerEntity>();
                for (ServerPlayerEntity player : ((ServerWorld) world).getPlayers()) {
                    if (player.isSpectator()) {
                        players.add(player);
                    }
                }

                if (!players.isEmpty()) {
                    ServerPlayerEntity player = players.get(world.random.nextInt(players.size()));
                    player.changeGameMode(GameMode.SURVIVAL);
                    world.getPlayers().forEach(playerEntity -> {
                        playerEntity.sendMessage(player.getDisplayName().copyContentOnly().append(" has been revived by ").append(user.getDisplayName().copyContentOnly()), true);
                        playerEntity.playSound(SoundEvents.BLOCK_END_PORTAL_SPAWN, 1.0F, 1.0F);
                    });
                    BlockPos spawn = world.getSpawnPos();
                    player.refreshPositionAndAngles(spawn.getX(), spawn.getY(), spawn.getZ(), world.getSpawnAngle(),0.0F);
                    player.refreshPositionAfterTeleport(spawn.getX(), spawn.getY(), spawn.getZ());
                }
            }
        }

        return super.finishUsing(stack, world, user);
    }
}
