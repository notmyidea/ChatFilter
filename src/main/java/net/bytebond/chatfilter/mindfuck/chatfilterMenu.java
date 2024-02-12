package net.bytebond.chatfilter.mindfuck;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.button.annotation.Position;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

public class chatfilterMenu extends Menu {

	//@Position(start = StartPosition.TOP_LEFT, value = 1)
	@Position(3)
	private final Button viewFiltersButton;

	@Position(4)
	private final Button viewPunishedButton;

	@Position(5)
	private final Button viewLogButton;

	@Position(8)
	private final Button versionButton;

	public chatfilterMenu() {
		this.setTitle(ChatColor.GOLD + "ChatFilter menu V.1.2.1"); // grab from ${project.version} in ../pom.xml
		this.setSize(9 * 1);
		//this.setSlotNumbersVisible();
		this.viewFiltersButton = new Button() {
			@Override
			public void onClickedInMenu(Player player, Menu menu, ClickType click) {
				new viewFilterMenu().displayTo(player);
			}

			@Override
			public ItemStack getItem() {
				return ItemCreator.of(CompMaterial.PAPER).name("View Filters").make();
			}

		};
		this.viewPunishedButton = new Button() {
			@Override
			public void onClickedInMenu(Player player, Menu menu, ClickType click) {

			}

			@Override
			public ItemStack getItem() {
				return ItemCreator.of(CompMaterial.IRON_BARS).name("View Punished Players").make();
			}

		};
		this.viewLogButton = new Button() {
			@Override
			public void onClickedInMenu(Player player, Menu menu, ClickType click) {

			}

			@Override
			public ItemStack getItem() {
				return ItemCreator.of(CompMaterial.BOOK).name("View Logs").make();
			}
		};

		// kind of useless but the menu looks unsymmetrical without it
		this.versionButton = new Button() {
			@Override
			public void onClickedInMenu(Player player, Menu menu, ClickType click) {

			}

			@Override
			public ItemStack getItem() {
				return ItemCreator.of(CompMaterial.PLAYER_HEAD).name("Version 1.2.1").make();
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
