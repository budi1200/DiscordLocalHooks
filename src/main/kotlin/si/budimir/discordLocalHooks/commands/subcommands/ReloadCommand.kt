package si.budimir.discordLocalHooks.commands.subcommands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import si.budimir.discordLocalHooks.DiscordLocalHooksMain
import si.budimir.discordLocalHooks.commands.SubCommandBase
import si.budimir.discordLocalHooks.util.MessageHelper
import si.budimir.discordLocalHooks.util.Permissions

class ReloadCommand: SubCommandBase {
    override fun execute(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if(DiscordLocalHooksMain.instance.mainConfig.reloadConfig()){
            MessageHelper.reloadPrefix()
            MessageHelper.sendMessage(sender as Player, "<green>Plugin Reloaded!")
        }else{
            MessageHelper.sendMessage(sender as Player, "<red>Failed to reload plugin!")
        }
        return true
    }

    override fun getPermission(): String {
        return Permissions.RELOAD.getPerm()
    }

    override fun getDesc(): String {
        return "Reloads the plugin"
    }
}