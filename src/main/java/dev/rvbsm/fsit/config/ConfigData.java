package dev.rvbsm.fsit.config;

import com.electronwill.nightconfig.core.conversion.Conversion;
import com.electronwill.nightconfig.core.conversion.Converter;
import com.electronwill.nightconfig.core.conversion.Path;
import com.electronwill.nightconfig.core.conversion.PreserveNotNull;
import com.electronwill.nightconfig.core.conversion.SpecDoubleInRange;
import com.electronwill.nightconfig.core.conversion.SpecIntInRange;
import dev.rvbsm.fsit.FSitMod;
import dev.rvbsm.fsit.config.annotation.Comment;
import dev.rvbsm.fsit.config.annotation.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public final class ConfigData {

	@Path(Fields.CONFIG_VERSION)
	@Comment(Comments.CONFIG_VERSION)
	@PreserveNotNull
	public final int configVersion = Entries.CONFIG_VERSION.defaultValue;

	@Path(Fields.SNEAK_ENABLED)
	@Comment(Comments.SNEAK_ENABLED)
	@PreserveNotNull
	public final boolean sneak;

	@Path(Fields.SNEAK_ANGLE)
	@Comment(Comments.SNEAK_ANGLE)
	@PreserveNotNull
	@SpecDoubleInRange(min = 0, max = 90)
	public final double sneakAngle;

	@Path(Fields.SNEAK_DELAY)
	@Comment(Comments.SNEAK_DELAY)
	@PreserveNotNull
	@SpecIntInRange(min = 100, max = 2000)
	public final int sneakDelay;

	@Path(Fields.SITTABLE_ENABLED)
	@Comment(Comments.SITTABLE_ENABLED)
	@PreserveNotNull
	public final boolean sittable = Entries.SITTABLE_ENABLED.defaultValue;

	@Path(Fields.SITTABLE_RADIUS)
	@Comment(Comments.SITTABLE_RADIUS)
	@PreserveNotNull
	@SpecIntInRange(min = 0, max = 4)
	public final int sittableRadius;

	@Path(Fields.SITTABLE_BLOCKS)
	@Comment(Comments.SITTABLE_BLOCKS)
	@PreserveNotNull
	@Conversion(Identifier2StringConverter.class)
	public final List<Identifier> sittableBlocks = Entries.SITTABLE_BLOCKS.defaultValue.stream().map(Identifier::new).toList();

	@Path(Fields.SITTABLE_TAGS)
	@Comment(Comments.SITTABLE_TAGS)
	@PreserveNotNull
	@Conversion(Identifier2StringConverter.class)
	public final List<Identifier> sittableTags = Entries.SITTABLE_TAGS.defaultValue.stream().map(Identifier::new).toList();

	@Path(Fields.RIDE_ENABLED)
	@Comment(Comments.RIDE_ENABLED)
	@PreserveNotNull
	public final boolean ride = Entries.RIDE_ENABLED.defaultValue;

	@Path(Fields.RIDE_RADIUS)
	@Comment(Comments.RIDE_RADIUS)
	@PreserveNotNull
	@SpecIntInRange(min = 0, max = 4)
	public final int rideRadius;

	@Environment(EnvType.CLIENT)
	@Path(Fields.RIDE_HEIGHT)
	@Comment(Comments.RIDE_HEIGHT)
	@PreserveNotNull
	@SpecDoubleInRange(min = 0, max = 1)
	public final double rideHeight = Entries.RIDE_HEIGHT.defaultValue;

	@Environment(EnvType.SERVER)
	@Path(Fields.COMMANDS_ENABLED)
	@Comment(Comments.COMMANDS_ENABLED)
	@PreserveNotNull
	public final boolean commands = Entries.COMMANDS_ENABLED.defaultValue;

	@Environment(EnvType.SERVER)
	@Path(Fields.COMMANDS_SIT)
	@Comment(Comments.COMMANDS_SIT)
	@PreserveNotNull
	public final String commandsSit = Entries.COMMANDS_SIT.defaultValue;

	@Environment(EnvType.SERVER)
	@Path(Fields.COMMANDS_CRAWL)
	@Comment(Comments.COMMANDS_CRAWL)
	@PreserveNotNull
	public final String commandsCrawl = Entries.COMMANDS_CRAWL.defaultValue;

	public ConfigData() {
		this.sneak = Entries.SNEAK_ENABLED.defaultValue;
		this.sneakAngle = Entries.SNEAK_ANGLE.defaultValue;
		this.sneakDelay = Entries.SNEAK_DELAY.defaultValue;
		this.sittableRadius = Entries.SITTABLE_RADIUS.defaultValue;
		this.rideRadius = Entries.RIDE_RADIUS.defaultValue;
	}

	public ConfigData(boolean sneak, double sneakAngle, int sneakDelay, int sittableRadius, int rideRadius) {
		this.sneak = sneak;
		this.sneakAngle = sneakAngle;
		this.sneakDelay = sneakDelay;
		this.sittableRadius = sittableRadius;
		this.rideRadius = rideRadius;
	}

	interface Fields {
		String CONFIG_VERSION = "config_version";
		String SNEAK_ENABLED = "sneak.enabled";
		String SNEAK_ANGLE = "sneak.angle";
		String SNEAK_DELAY = "sneak.delay";
		String SITTABLE_ENABLED = "sittable.enabled";
		String SITTABLE_RADIUS = "sittable.radius";
		String SITTABLE_BLOCKS = "sittable.blocks";
		String SITTABLE_TAGS = "sittable.tags";
		String RIDE_ENABLED = "misc.riding.enabled";
		String RIDE_RADIUS = "misc.riding.radius";
		String RIDE_HEIGHT = "misc.riding.height";
		String COMMANDS_ENABLED = "misc.commands.enabled";
		String COMMANDS_SIT = "misc.commands.sit";
		String COMMANDS_CRAWL = "misc.commands.crawl";
	}

	interface Comments {
		String CONFIG_VERSION = "Do not edit";
		String SNEAK_ENABLED = "Sit-on-sneak feature";
		String SNEAK_ANGLE = "Minimal pitch to sitting/crawling down";
		String SNEAK_DELAY = "Time (ms) between sneaks to sitting/crawling down";
		String SITTABLE_ENABLED = "Sit-on-use block feature";
		String SITTABLE_RADIUS = "Maximum radius for sit-on-use";
		String SITTABLE_BLOCKS = "List of block ids (e.g. \"oak_log\") available to sit";
		String SITTABLE_TAGS = "List of block tags";
		String RIDE_ENABLED = "Player riding feature";
		String RIDE_RADIUS = "Maximum radius for start riding player";
		String RIDE_HEIGHT = "Height above player's head when riding them";
		String COMMANDS_ENABLED = "Toggle pose (/sit, /crawl) commands";
		String COMMANDS_SIT = "Sitting command";
		String COMMANDS_CRAWL = "Crawling command";
	}

	public interface Entries {
		ConfigEntry<Integer> CONFIG_VERSION = new ConfigEntry<>(Fields.CONFIG_VERSION, 3);
		ConfigEntry<Boolean> SNEAK_ENABLED = new ConfigEntry<>(Fields.SNEAK_ENABLED, true);
		ConfigEntry<Double> SNEAK_ANGLE = new ConfigEntry<>(Fields.SNEAK_ANGLE, 66d);
		ConfigEntry<Integer> SNEAK_DELAY = new ConfigEntry<>(Fields.SNEAK_DELAY, 600);
		ConfigEntry<Boolean> SITTABLE_ENABLED = new ConfigEntry<>(Fields.SITTABLE_ENABLED, true);
		ConfigEntry<Integer> SITTABLE_RADIUS = new ConfigEntry<>(Fields.SITTABLE_RADIUS, 2);
		ConfigEntry<List<String>> SITTABLE_BLOCKS = new ConfigEntry<>(Fields.SITTABLE_BLOCKS, List.of());
		ConfigEntry<List<String>> SITTABLE_TAGS = new ConfigEntry<>(Fields.SITTABLE_TAGS, List.of("minecraft:slabs", "minecraft:stairs", "minecraft:logs"));
		ConfigEntry<Boolean> RIDE_ENABLED = new ConfigEntry<>(Fields.RIDE_ENABLED, false);
		ConfigEntry<Integer> RIDE_RADIUS = new ConfigEntry<>(Fields.RIDE_RADIUS, 3);
		ConfigEntry<Double> RIDE_HEIGHT = new ConfigEntry<>(Fields.RIDE_HEIGHT, 0d);
		ConfigEntry<Boolean> COMMANDS_ENABLED = new ConfigEntry<>(Fields.COMMANDS_ENABLED, true);
		ConfigEntry<String> COMMANDS_SIT = new ConfigEntry<>(Fields.COMMANDS_SIT, "sit");
		ConfigEntry<String> COMMANDS_CRAWL = new ConfigEntry<>(Fields.COMMANDS_CRAWL, "crawl");

		record ConfigEntry<T>(String key, T defaultValue) {

			public void save(T value) {
				FSitConfig.config.set(this.key, value);
			}

			public Text keyText() {
				return FSitMod.getTranslation("option", this.key);
			}

			public Text commentText() {
				return FSitMod.getTranslation("comment", this.key);
			}
		}
	}

	private static class Identifier2StringConverter implements Converter<List<Identifier>, List<String>> {

		@Override
		public List<Identifier> convertToField(List<String> value) {
			if (value == null) return List.of();
			return value.stream().filter(Identifier::isValid).distinct().map(Identifier::new).toList();
		}

		@Override
		public List<String> convertFromField(List<Identifier> value) {
			return value.stream().map(Identifier::toString).distinct().toList();
		}
	}
}
