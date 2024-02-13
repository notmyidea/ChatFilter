package net.bytebond.chatfilter.listener;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.event.Listener;
import org.mineacademy.fo.annotation.AutoRegister;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AutoRegister
public class chatFilterState implements Listener {

	@Getter
	private static String playerName;

	public static void setPlayerName(String playerName) {
		chatFilterState.playerName = playerName;
	}


}
