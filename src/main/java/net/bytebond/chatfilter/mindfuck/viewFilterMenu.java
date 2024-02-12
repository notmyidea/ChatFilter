package net.bytebond.chatfilter.mindfuck;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;

public class viewFilterMenu extends Menu {

	public viewFilterMenu() {
		this.setTitle(ChatColor.GOLD + "ChatFilter menu V.1.2.1"); // grab from ${project.version} in ../pom.xml
		this.setSize(9 * 1);
		this.setSlotNumbersVisible();
		
	}


	protected String[] getInfo() {
		return new String[]{
				"Here you can see all filters",
				"set in the nono-words.yml",
				"in your plugins config",
				"folder"
		};
	}


	public ItemStack getItemAt(int slot) {

		return NO_ITEM;
	}


}
