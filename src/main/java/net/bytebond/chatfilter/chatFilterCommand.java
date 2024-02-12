package net.bytebond.chatfilter;

import net.bytebond.chatfilter.mindfuck.chatfilterMenu;
import net.bytebond.chatfilter.yaml.filterStorage;
import org.mineacademy.fo.command.SimpleCommand;
import org.mineacademy.fo.exception.CommandException;
import org.mineacademy.fo.settings.SimpleLocalization;

public class chatFilterCommand extends SimpleCommand {
	// SimpleCommand.class ll.53~


	@Override
	protected void onCommand() {
		if (args.length == 0) {
			if (sender.hasPermission("chatfilter.admin")) {
				checkConsole();
				new chatfilterMenu().displayTo(getPlayer());
			} else {
				throw new CommandException(new java.lang.String[]{"&c" + SimpleLocalization.Commands.PERMS_NO});
			}


		} else if (args.length > 1) {
			if (args[0].equals("add")) {
				if (sender.hasPermission("chatfilter.admin")) {
					filterStorage.addNonoWord(args[1]);
					tell("Successfully added " + args[1] + " to the nono-words list!");

				} else {
					throw new CommandException(new java.lang.String[]{"&c" + SimpleLocalization.Commands.PERMS_NO});
				}

			}
			// remove command


		} else {
			tell("Usage: /chatfilter <add/remove> <word>");
		}


	}


	protected chatFilterCommand() {
		super("chatfilter|cf|chatf|cfilter");
		setDescription("Opens the ChatFilter menu");
		setPermission("chatfilter.admin");
		setUsage("Usage is self-explanatory");
		setMinArguments(0);

	}

}
