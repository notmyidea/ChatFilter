package net.bytebond.chatfilter.listener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebond.chatfilter.yaml.filterStorage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.annotation.AutoRegister;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AutoRegister
public final class chatListener implements Listener {


	@Getter
	private static final Listener instance = new chatListener();

	@EventHandler
	public void onChat(PlayerChatEvent event) {
		final Player player = event.getPlayer();

		// If the player's name matches the stored name
		if (player.getName().equals(chatFilterState.getPlayerName())) {
			// Block the chat input and add it to the list
			String message = event.getMessage();
			if (message.equalsIgnoreCase("stop")) {
				chatFilterState.setPlayerName(null);
				Common.tell(player, "the process has been stopped");
				event.setCancelled(true);
				return;
			} else {
				String[] words = message.split(",");
				for (String word : words) {
					filterStorage.addNonoWord(word.trim());
				}
				event.setCancelled(true);

				if (words.length == 1) {
					Common.tell(player, "The word '" + message + "' has been added to the filter!");
				} else {
					Common.tell(player, "The words '" + message + "' have been added to the filter in " + words.length + " runs!");
				}
				//Common.tell(player, "The word(s) '" + message + "' has been added to the filter in " + words.length + " runs!");
				// Reset the stored player name
				chatFilterState.setPlayerName(null);
			}
		} else if (player.hasPermission("chatfilter.bypass")) {
			return;
		}

		String originalMessage = event.getMessage();
		String censoredMessage = censorNonoWords(originalMessage);
		event.setMessage(censoredMessage);
	}

	private String censorNonoWords(String message) {
		String[] words = message.split(" ");
		StringBuilder censoredMessage = new StringBuilder();

		for (String word : words) {
			if (filterStorage.containsNoNoWord(word)) {
				int wordLength = word.length();
				String starts = "*".repeat(wordLength);
				censoredMessage.append(starts).append(" ");
			} else {
				censoredMessage.append(word).append(" ");
			}
		}


		return censoredMessage.toString().trim();
		//	if (filterStorage.containsNoNoWord(message)) {
		//		event.setCancelled(true);
		//		tell(player, "You are not allowed to say that word");
		// }

		//alles ok
	}


}


