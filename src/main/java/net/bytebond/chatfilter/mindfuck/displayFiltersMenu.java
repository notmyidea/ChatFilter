package net.bytebond.chatfilter.mindfuck;

import net.bytebond.chatfilter.yaml.filterStorage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

import java.util.ArrayList;
import java.util.List;


public class displayFiltersMenu extends Menu {
	//@Position(start = StartPosition.TOP_LEFT, value = 1)

	private final List<Button> nonoButtons;
	//@Position(start = StartPosition.BOTTOM_LEFT, value = 2)
	//private final Button goBackButton;

	public displayFiltersMenu() {
		this.nonoButtons = new ArrayList<>();
		this.setTitle(ChatColor.GOLD + "ChatFilter menu V.1.3.2");
		int numberOfFilteredWords = filterStorage.getNonoWords().size();
		int itemsPerRow = 9;
		int numberOfRows = (int) Math.ceil((double) numberOfFilteredWords / itemsPerRow + 1);
		numberOfRows = Math.min(numberOfRows, 5);

		this.setSize(numberOfRows * itemsPerRow);
	}

	// </EDITED BY GHC>

	public void populateMenu() {
		List<String> nonoWords = filterStorage.getNonoWords();
		//for (int i = 0; i < Math.min(nonoWords.size(), 45); i++) {
		for (int i = 0; i < nonoWords.size(); i++) {
			String nonoWord = nonoWords.get(i);
			Button nonoButton = new Button() {
				@Override
				public void onClickedInMenu(Player player, Menu menu, ClickType click) {
					filterStorage.removeNonoWord(click.name()); // IDK if that gives me the item name but yolo
					//filterStorage.removeNonoWord(nonoWord);
					nonoButtons.remove(this); // remove this button from the list
					if (menu instanceof displayFiltersMenu) {
						((displayFiltersMenu) menu).restartMenu(); // update the menu display
					}
				}

				@Override
				public ItemStack getItem() {
					return ItemCreator.of(CompMaterial.BOOK).name(nonoWord).make();
				}
			};
			this.addButton(i, nonoButton);
			this.setItem(i, nonoButton.getItem());
		}
		// <EDITED BY GHC>


		// this.goBackButton = new Button() {
		//	@Override
		//	public void onClickedInMenu(Player player, Menu menu, ClickType click) {
		//		//new chatfilterMenu().displayTo(player.getPlayer());
		//	}
		//	@Override
		//	public ItemStack getItem() {
		//
		//		return ItemCreator.of(CompMaterial.BREAD).name("Go Back").make();
		//	}
		//};

	}


	public void addButton(int index, Button button) {
		while (nonoButtons.size() <= index) {
			nonoButtons.add(null);
		}
		nonoButtons.set(index, button);
	}

	protected String[] getInfo() {
		return new String[]{
				"This is the filter menu page",
				"where you can add, remove or edit",
				"your chat filters"
		};
	}

	@Override
	public ItemStack getItemAt(int slot) {
		if (slot < nonoButtons.size()) {
			Button button = nonoButtons.get(slot);
			if (button != null) {
				return button.getItem();
			}
		}
		return NO_ITEM;
	}
}