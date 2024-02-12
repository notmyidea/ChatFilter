package net.bytebond.chatfilter;

import net.bytebond.chatfilter.yaml.filterStorage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatEvent;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.plugin.SimplePlugin;


public final class chatFilter extends SimplePlugin {


	@Override
	protected void onPluginStart() {
		System.out.println("ChatFilter has been enabled");

		Common.setLogPrefix("&8[ChatFilter]&f");
		Common.setTellPrefix("&8ChatFilter // &f");

		Common.log("log message");
		Common.logNoPrefix("log message without prefix");
		Common.warning("warning message");

		Common.logFramed("log message," +
				"in a frame");

		//Menu.setSound(null);
		//Button.setInfoButtonTitle("Info");
		//Button.setInfoButtonMaterial();
	}

	@Override
	protected void onReloadablesStart() {
		registerCommand(new chatFilterCommand());

		try {
			filterStorage.saveIfNotExists();
		} catch (Exception e) {
			e.printStackTrace();
		}


		// You can check for necessary plugins and disable loading if they are missing
		//Valid.checkBoolean(HookManager.isVaultLoaded(), "You need to install Vault so that we can work with packets, offline player data, prefixes and groups.");

		// Uncomment to load variables
		// Variable.loadVariables();

		//
		// Add your own plugin parts to load automatically here
		// Please see @AutoRegister for parts you do not have to register manually
		//
	}

	@Override
	protected void onPluginPreReload() {

		// Close your database here if you use one
		//YourDatabase.getInstance().close();
	}

	/* ------------------------------------------------------------------------------- */
	/* Events */
	/* ------------------------------------------------------------------------------- */

	/**
	 * An example event that checks if the right clicked entity is a cow, and makes an explosion.
	 * You can write your events to your main class without having to register a listener.
	 *
	 * @param event
	 */

	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {


	}

	/* ------------------------------------------------------------------------------- */
	/* Static */
	/* ------------------------------------------------------------------------------- */

	/**
	 * Return the instance of this plugin, which simply refers to a static
	 * field already created for you in SimplePlugin but casts it to your
	 * specific plugin instance for your convenience.
	 *
	 * @return
	 */
	public static chatFilter getInstance() {
		return (chatFilter) SimplePlugin.getInstance();
	}
}
