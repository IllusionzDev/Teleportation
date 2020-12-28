    /*
     *  Plugin made by IllusionDev (Illusion#3247)
     */

public final class Main extends JavaPlugin implements CommandExecutor, TabCompleter {
    public static Logger console = null;

    @Override
    public void onEnable() {
        console = new Logger();
        console.Output("Success", true, "Successfully started!");
    }

    @Override
    public void onDisable() {
        console.Output("Warning", true, "Shutting plugin down...");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
        {
            console.Output("Error", true, "Command sender needs to be a player.");
            return true;
        }

        Player ply = (Player)sender;

        if (label.equalsIgnoreCase("go"))
        {
            if (args.length == 0)
            {
                ply.sendMessage(ChatColor.RED + "Incorrect command usage! /go [player name]");
                return true;
            }

            if (!ply.hasPermission("Teleportation.Allow"))
            {
                ply.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                return true;
            }

            String playerName = args[0];

            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
            {
                if (onlinePlayer.getName().equalsIgnoreCase(playerName))
                {
                    if (ply.getUniqueId().equals(onlinePlayer.getUniqueId()))
                    {
                        ply.sendMessage(ChatColor.RED + "You can't teleport to yourself.");
                        continue;
                    }

                    Location teleportTo = onlinePlayer.getLocation();
                    ply.teleport(teleportTo);
                    ply.sendMessage(ChatColor.GREEN + "You have teleported to " + ChatColor.BOLD + onlinePlayer.getName() + ChatColor.GREEN + ".");
                    return true;
                }
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (command.getName().equalsIgnoreCase("go"))
        {
            List<String> list = new ArrayList<>();
            if (args.length == 1)
            {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                {
                    list.add(onlinePlayer.getName());
                }
            }
            return list;
        }

        return null;
    }
}
