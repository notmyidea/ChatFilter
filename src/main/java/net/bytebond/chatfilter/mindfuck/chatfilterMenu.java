package net.bytebond.chatfilter.mindfuck;

import net.bytebond.chatfilter.listener.chatFilterState;
import net.bytebond.chatfilter.yaml.filterStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.button.annotation.Position;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

import java.util.UUID;

public class chatfilterMenu extends Menu {

	//@Position(start = StartPosition.TOP_LEFT, value = 1)


	@Position(3)

	private final Button AddFilterButton;
	@Position(8)
	private final Button versionButton;
	@Position(4)
	private final Button ListCurrentFilters;

	//@Position(4)
	//private final Button ViewFiltersButton;

	public chatfilterMenu() {
		this.setTitle(ChatColor.GOLD + "ChatFilter menu V.1.3.2"); // grab from ${project.version} in ../pom.xml
		this.setSize(9 * 1);
		//this.setSlotNumbersVisible();
		this.AddFilterButton = new Button() {
			@Override
			public void onClickedInMenu(Player player, Menu menu, ClickType click) {
				player.closeInventory();
				chatFilterState.setPlayerName(player.getName());
				tell("Please type the word you want to add to the filter");
			}

			@Override
			public ItemStack getItem() {
				return ItemCreator.of(CompMaterial.WRITABLE_BOOK).name("Create a new filter").lore(" Seperate multiple words by commas \n ex: word1,word2,word3 \n  \nSelecting this will close the inventory \n and listen to your next chat input \n write 'stop' to stop the process.").make();
			}
		};
		/*this.ViewFiltersButton = new Button() {
			@Override
			public void onClickedInMenu(Player player, Menu menu, ClickType click) {
				//displayFiltersMenu menu1 = new displayFiltersMenu();
				//menu1.displayTo(player);
				//menu1.populateMenu();

			}

			@Override
			public ItemStack getItem() {
				return ItemCreator.of(CompMaterial.BOOK).name("View current filters").make();
			}
		};
		*/
		this.ListCurrentFilters = new Button() {
			@Override
			public void onClickedInMenu(Player player, Menu menu, ClickType click) {
				player.closeInventory();
				if (filterStorage.isListEmpty()) {
					tell("The list is empty");
					return;

				} else {
					tell("The current list of nono words is: " + filterStorage.getNonoWords());
				}

			}

			@Override
			public ItemStack getItem() {
				return ItemCreator.of(CompMaterial.PAPER).name("List current filters").lore("\nThis will list all current \nfilters in the chat.").make();
			}
		};


		this.versionButton = new Button() {
			@Override
			public void onClickedInMenu(Player player, Menu menu, ClickType click) {

			}

			@Override
			public ItemStack getItem() {
				//return ItemCreator.of(CompMaterial.PLAYER_HEAD).name("Version 1.3").lore("https://github.com/notmyidea/ChatFilter").make();
				ItemStack playerHead = ItemCreator.of(CompMaterial.PLAYER_HEAD).name("Version 1.3.2").make();
				SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
				UUID uuid = UUID.fromString("29490773-9700-4b31-a14e-5599137e0af3");
				skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
				playerHead.setItemMeta(skullMeta);
				return ItemCreator.of(playerHead).name("Version 1.3").lore("https://github.com/notmyidea/ChatFilter").make();
			}
		};
	}

	protected String[] getInfo() {
		return new String[]{
				"This is the main menu page",
				"where you can customize your",
				"ChatFilter options, add Filters",
				"and view punishments and punished",
				"players."
		};
	}


	public ItemStack getItemAt(int slot) {

		return NO_ITEM;
	}


}
