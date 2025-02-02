package dev.rvbsm.fsit;

import dev.rvbsm.fsit.config.ConfigData;
import dev.rvbsm.fsit.config.FSitConfig;
import dev.rvbsm.fsit.event.ServerBlockEvents;
import dev.rvbsm.fsit.event.ServerEntityEvents;
import dev.rvbsm.fsit.event.ServerConnectionEvents;
import dev.rvbsm.fsit.packet.ConfigSyncC2SPacket;
import dev.rvbsm.fsit.packet.RidePacket;
import dev.rvbsm.fsit.packet.SpawnSeatC2SPacket;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class FSitMod implements ModInitializer {

	static final ConfigData config = new ConfigData();

	@Contract("_, _ -> new")
	public static @NotNull String getTranslationKey(String type, String id) {
		return String.join(".", type, "fsit", id);
	}

	@Contract(value = "_, _, _ -> new", pure = true)
	public static @NotNull Text getTranslation(String type, String id, Object... args) {
		final String translationKey = FSitMod.getTranslationKey(type, id);
		return Text.translatable(translationKey, args);
	}

	public static ConfigData getConfig() {
		return FSitMod.config;
	}

	public static void loadConfig() {
		FSitConfig.load(FSitMod.config);
	}

	@Override
	public void onInitialize() {
		FSitMod.loadConfig();

		UseBlockCallback.EVENT.register(ServerBlockEvents::useOnBlock);
		UseEntityCallback.EVENT.register(ServerEntityEvents::useOnPlayer);
		ServerPlayConnectionEvents.JOIN.register(ServerConnectionEvents::onConnect);
		ServerPlayConnectionEvents.DISCONNECT.register(ServerConnectionEvents::onDisconnect);

		ServerPlayNetworking.registerGlobalReceiver(ConfigSyncC2SPacket.TYPE, ConfigSyncC2SPacket::receive);
		ServerPlayNetworking.registerGlobalReceiver(SpawnSeatC2SPacket.TYPE, SpawnSeatC2SPacket::receive);
		ServerPlayNetworking.registerGlobalReceiver(RidePacket.TYPE, RidePacket::receive);
	}
}
