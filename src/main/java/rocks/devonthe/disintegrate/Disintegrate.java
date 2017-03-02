/*
 * Disintegrate - A Discord bot for Sponge-based Minecraft servers
 * Copyright (C) 2017 Mohron
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Disintegrate is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Disintegrate.  If not, see <http://www.gnu.org/licenses/>.
 */

package rocks.devonthe.disintegrate;

import com.google.inject.Inject;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameAboutToStartServerEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import java.nio.file.Path;

import static rocks.devonthe.disintegrate.PluginInfo.*;

@Plugin(
	id = ID,
	name = NAME,
	version = VERSION,
	description = DESCRIPTION,
	dependencies = {
		@Dependency(id = "nucleus", version = NUCLEUS_VERSION, optional = true)
	},
	authors = {AUTHORS}
)
public class Disintegrate {
	private static Disintegrate instance;

	@Inject
	private Logger logger;
	@Inject
	private PluginContainer pluginContainer;
	@Inject
	@ConfigDir(sharedRoot = false)
	private Path configDir;
	@Inject
	@DefaultConfig(sharedRoot = false)
	private ConfigurationLoader<CommentedConfigurationNode> configLoader;

	private boolean enabled = true;

	@Listener
	public void onPostInitialization(GamePostInitializationEvent event) {
		getLogger().info(String.format("%s %s is initializing...", NAME, VERSION));

		instance = this;
	}

	@Listener
	public void onAboutToStart(GameAboutToStartServerEvent event) {
		if (!enabled) return;
	}

	@Listener
	public void onServerStarted(GameStartedServerEvent event) {
		if (!enabled) return;
	}

	@Listener
	public void onGameStopping(GameStoppingServerEvent event) {
		if (!enabled) return;
		getLogger().info(String.format("%S %S is stopping...", NAME, VERSION));
	}

	public static Disintegrate getInstance() {
		return instance;
	}

	public PluginContainer getPluginContainer() {
		return pluginContainer;
	}

	public Logger getLogger() {
		return logger;
	}
}
