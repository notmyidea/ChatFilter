package net.bytebond.chatfilter.listener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebond.chatfilter.yaml.filterStorage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.mineacademy.fo.annotation.AutoRegister;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AutoRegister
public final class chatListener implements Listener {


	@Getter
	private static final Listener instance = new chatListener();

	@EventHandler
	public void onChat(PlayerChatEvent event) {
		final Player player = event.getPlayer();

		if (player.hasPermission("chatfilter.bypass")) {
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


