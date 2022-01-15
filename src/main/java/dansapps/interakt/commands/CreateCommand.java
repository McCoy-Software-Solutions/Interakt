/*
  Copyright (c) 2022 Daniel McCoy Stephenson
  Apache License 2.0
 */
package dansapps.interakt.commands;

import dansapps.interakt.commands.abs.InteraktCommand;
import dansapps.interakt.factories.EntityFactory;
import dansapps.interakt.factories.EnvironmentFactory;
import preponderous.ponder.system.abs.CommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel McCoy Stephenson
 * @since January 7th, 2022
 */
public class CreateCommand extends InteraktCommand {

    public CreateCommand() {
        super(new ArrayList<>(List.of("create")), new ArrayList<>(List.of("interakt.create")));
    }

    @Override
    public boolean execute(CommandSender sender) {
        sender.sendMessage("Usage: create \"type\" \"name\"");
        return false;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("Not enough arguments.");
            return false;
        }

        ArrayList<String> doubleQuoteArgs;
        try {
            doubleQuoteArgs = extractArgumentsInsideDoubleQuotes(args);
        }
        catch(Exception e) {
            sender.sendMessage("Arguments must be designated in between quotation marks.");
            return false;
        }

        String type = doubleQuoteArgs.get(0);
        String name = doubleQuoteArgs.get(1);

        if (type.equalsIgnoreCase("entity")) {
            EntityFactory.getInstance().createEntity(name);
            sender.sendMessage("Entity created.");
            return true;
        }
        else if (type.equalsIgnoreCase("environment")) {
            EnvironmentFactory.getInstance().createEnvironment(name);
            sender.sendMessage("Environment created.");
            return true;
        }
        else {
            sender.sendMessage("'" + type + "' is not a supported type. Supported types include entity and environment.");
            return false;
        }
    }
}