package com.destroystokyo.paper.event.player;

import java.net.InetAddress;
import java.util.UUID;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <p>
 * This event is invoked when a player has disconnected. It is guaranteed that,
 * if the server is in online-mode, that the provided uuid and username have been
 * validated.
 * <p>
 * The event is invoked for players who have not yet logged into the world, whereas
 * {@link PlayerQuitEvent} is only invoked on players who have logged into the world.
 * <p>
 * The event is invoked for players who have already logged into the world,
 * although whether or not the player exists in the world at the time of
 * firing is undefined. (That is, whether the plugin can retrieve a Player object
 * using the event parameters is undefined). However, it is guaranteed that this
 * event is invoked AFTER {@link PlayerQuitEvent}, if the player has already logged into the world.
 * <p>
 * This event is guaranteed to never fire unless {@link AsyncPlayerPreLoginEvent} has
 * been fired beforehand, and this event may not be called in parallel with
 * {@link AsyncPlayerPreLoginEvent} for the same connection.
 * <p>
 * Cancelling the {@link AsyncPlayerPreLoginEvent} guarantees the corresponding
 * {@code PlayerConnectionCloseEvent} is never called.
 * <p>
 * The event may be invoked asynchronously or synchronously. Plugins should check
 * {@link Event#isAsynchronous()} and handle accordingly.
 */
@NullMarked
public class PlayerConnectionCloseEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final UUID playerUniqueId;
    private final String playerName;
    private final InetAddress ipAddress;

    @ApiStatus.Internal
    public PlayerConnectionCloseEvent(final UUID playerUniqueId, final String playerName, final InetAddress ipAddress, final boolean async) {
        super(async);
        this.playerUniqueId = playerUniqueId;
        this.playerName = playerName;
        this.ipAddress = ipAddress;
    }

    /**
     * Returns the {@code UUID} of the player disconnecting.
     */
    public UUID getPlayerUniqueId() {
        return this.playerUniqueId;
    }

    /**
     * Returns the name of the player disconnecting.
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Returns the player's IP address.
     */
    public InetAddress getIpAddress() {
        return this.ipAddress;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
