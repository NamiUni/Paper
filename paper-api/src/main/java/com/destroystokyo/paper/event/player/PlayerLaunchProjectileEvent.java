package com.destroystokyo.paper.event.player;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * Called when a player shoots a projectile.
 * <p>
 * Notably this event is not called for arrows as the player does not launch them, rather shoots them with the help
 * of a bow or crossbow. A plugin may listen to {@link EntityShootBowEvent}
 * for these actions instead.
 */
@NullMarked
public class PlayerLaunchProjectileEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Projectile projectile;
    private final ItemStack itemStack;
    private boolean consumeItem = true;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerLaunchProjectileEvent(final Player shooter, final ItemStack itemStack, final Projectile projectile) {
        super(shooter);
        this.itemStack = itemStack;
        this.projectile = projectile;
    }

    /**
     * Gets the projectile which will be launched by this event
     *
     * @return the launched projectile
     */
    public Projectile getProjectile() {
        return this.projectile;
    }

    /**
     * Get the ItemStack used to fire the projectile
     *
     * @return The ItemStack used
     */
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    /**
     * Get whether to consume the ItemStack or not
     *
     * @return {@code true} to consume
     */
    public boolean shouldConsume() {
        return this.consumeItem;
    }

    /**
     * Set whether to consume the ItemStack or not
     *
     * @param consumeItem {@code true} to consume
     */
    public void setShouldConsume(final boolean consumeItem) {
        this.consumeItem = consumeItem;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
