package si.budimir.discordLocalHooks.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import si.budimir.discordLocalHooks.commands.subcommands.LogCommand
import si.budimir.discordLocalHooks.commands.subcommands.ReloadCommand
import si.budimir.discordLocalHooks.commands.subcommands.StoreCommand
import si.budimir.discordLocalHooks.util.MessageHelper

class DhCommand : CommandExecutor, TabExecutor {
    private val subCommands: MutableMap<String, SubCommandBase> = HashMap()
    private var subCommandsList: List<String> = emptyList()

    init {
         subCommands["reload"] = ReloadCommand()
         subCommands["store"] = StoreCommand()
         subCommands["log"] = LogCommand()

        subCommandsList = subCommands.keys.toList()
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if(args.isNotEmpty()) run {
            val sc: SubCommandBase = subCommands[args[0]] ?: return false
            val reqPerm: String = sc.getPermission()

            if(reqPerm == "" || sender.hasPermission(reqPerm)){
                sc.execute(sender, command, label, args)
                return true
            }else{
                MessageHelper.sendMessage(sender as Player, "lang.missingPermissions", mutableMapOf())
            }
        }

        return false
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<String>
    ): List<String> {
        return when {
            args[0] == "" -> {
                subCommandsList
            }
            args.size == 1 -> {
                subCommandsList.filter { it.contains(args[0], ignoreCase = true) }
            }
            else -> {
                val sc: SubCommandBase = subCommands[args[0]] ?: return emptyList()
                sc.onTabComplete(sender, args.toList())
            }
        }
    }
}