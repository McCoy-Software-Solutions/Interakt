package dansapps.interakt.commands.console;

import dansapps.interakt.commands.abs.InteraktCommand;
import dansapps.interakt.data.PersistentData;
import dansapps.interakt.exceptions.ZeroFriendshipsExistentException;
import dansapps.interakt.utils.Logger;
import preponderous.ponder.system.abs.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class StatsCommand extends InteraktCommand {
    private Logger logger;

    public StatsCommand(Logger logger) {
        super(new ArrayList<>(List.of("stats")), new ArrayList<>(List.of("interakt.stats")));
        this.logger = logger;
    }

    @Override
    public boolean execute(CommandSender sender) {
        try {
            sender.sendMessage("Number of actors: " + PersistentData.getInstance().getActors().size());
            sender.sendMessage("Number of worlds: " + PersistentData.getInstance().getWorlds().size());
            sender.sendMessage("Number of regions: " + PersistentData.getInstance().getRegions().size());
            sender.sendMessage("Number of squares: " + PersistentData.getInstance().getSquares().size());
            sender.sendMessage("Number of elapsed time partitions: " + PersistentData.getInstance().getTimePartitions().size());
            sender.sendMessage("Number of action records: " + PersistentData.getInstance().getActionRecords().size());
            sender.sendMessage("Number of entity records: " + PersistentData.getInstance().getEntityRecords().size());
            sender.sendMessage("Most active actor: " + PersistentData.getInstance().getActorWithMostActionRecords().getName());
            sender.sendMessage("Least active actor: " + PersistentData.getInstance().getActorWithLeastActionRecords().getName());
            sender.sendMessage("Most well travelled: " + PersistentData.getInstance().getMostWellTravelledActor().getName());

            try {
                sender.sendMessage("Most friendly actor: " + PersistentData.getInstance().getMostFriendlyActor().getName());
            } catch(ZeroFriendshipsExistentException e) {
                sender.sendMessage("Most friendly actor: N/A");
            }

            sender.sendMessage("Minutes elapsed: " + PersistentData.getInstance().getSecondsElapsed()/60);
            return true;
        }
        catch (Exception e) {
            logger.logError("Something went wrong when printing stats.");
            return false;
        }
    }

    @Override
    public boolean execute(CommandSender sender, String[] strings) {
        return execute(sender);
    }
}