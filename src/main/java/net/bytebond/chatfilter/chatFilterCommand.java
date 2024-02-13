package net.bytebond.chatfilter;

import net.bytebond.chatfilter.mindfuck.chatfilterMenu;
import net.bytebond.chatfilter.yaml.filterStorage;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleCommand;
import org.mineacademy.fo.exception.CommandException;
import org.mineacademy.fo.settings.SimpleLocalization;

import java.util.List;

public class chatFilterCommand extends SimpleCommand {
	// SimpleCommand.class ll.53~


	@Override
	protected void onCommand() {
		if (args.length == 0) {
			if (sender.hasPermission("chatfilter.admin")) {
				//checkConsole();
				if (sender instanceof Player) {
					new chatfilterMenu().displayTo(getPlayer());
				} else {
					printUsage();
					tell("The menu cannot be displayed to the console, refer to the usage.");
				}

			} else {
				throw new CommandException(new java.lang.String[]{"&c" + SimpleLocalization.Commands.PERMS_NO});
			}


		} else if (args[0].equalsIgnoreCase("add")) {
			if (sender.hasPermission("chatfilter.admin")) {
				String[] words = args[1].split(",");
				for (String word : words) {
					filterStorage.addNonoWord(word.trim());
				}
				tell("Successfully added " + words.length + " words to the list!");
			} else {
				throw new CommandException(SimpleLocalization.Commands.PERMS_NO);
			}


		} else if (args[0].equalsIgnoreCase("remove")) {
			if (sender.hasPermission("chatfilter.admin")) {
				String[] words = args[1].split(",");
				for (String word : words) {
					if (filterStorage.isNonoWord(word.trim())) {
						if (args.length > 2 && args[2].equalsIgnoreCase("--all")) {
							// delete all check until false
							int deletes = 0;
							while (filterStorage.isNonoWord(word.trim())) {
								filterStorage.removeNonoWord(word.trim());
								deletes++;
							}
							tell("Successfully removed " + words.length + " words from the list in " + deletes + " runs!");
							return;
						} else {
							filterStorage.removeNonoWord(word.trim());
							tell("Successfully removed " + words.length + " words from the list!");
							return;
						}
					}
					tell("That word is not in the list!");
				}
			} else {
				throw new CommandException(SimpleLocalization.Commands.PERMS_NO);
			}

		} else if (args[0].equalsIgnoreCase("list")) {
			if (sender.hasPermission("chatfilter.admin")) {

				if (filterStorage.isListEmpty()) {
					tell("The list is empty");
					return;

				} else {
					tell("The current list of nono words is: " + filterStorage.getNonoWords());
				}

			} else {
				throw new CommandException(SimpleLocalization.Commands.PERMS_NO);
			}
		} else { // fourth option
			printUsage();
		}
	}


	protected chatFilterCommand() {
		super("chatfilter|cf|chatf");
		setDescription("Opens the ChatFilter menu");
		setPermission("chatfilter.admin");
		setUsage("Usage is self-explanatory");
		setMinArguments(0);

	}

	@Override
	public List<String> tabComplete() {
		//if (!isPlayer())
		//	return new ArrayList<>();

		Player player = (Player) sender;

		switch (args.length) {
			case 1:
				return completeLastWord("add", "remove", "list");
			case 2:
				if (args[0].equalsIgnoreCase("add")) {
					return completeLastWord("word1,word2");
				} else if (args[0].equalsIgnoreCase("remove")) {
					return completeLastWord("word1,word2");

				} else {
					return NO_COMPLETE;
				}
			case 3:
				if (args[0].equalsIgnoreCase("remove")) {
					return completeLastWord("--all");
				} else {
					return NO_COMPLETE;
				}
		}


		return null;
	}

	private void printUsage() {
		tell("Usage: /chatfilter <add/remove/list> <word>");
	}

}
