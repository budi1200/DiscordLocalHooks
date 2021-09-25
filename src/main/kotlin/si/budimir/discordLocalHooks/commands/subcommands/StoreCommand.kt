package si.budimir.discordLocalHooks.commands.subcommands

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import si.budimir.discordLocalHooks.DiscordLocalHooksMain
import si.budimir.discordLocalHooks.WebHookHandler
import si.budimir.discordLocalHooks.commands.SubCommandBase
import si.budimir.discordLocalHooks.util.Embed
import si.budimir.discordLocalHooks.util.EmbedContent
import si.budimir.discordLocalHooks.util.Permissions

class StoreCommand: SubCommandBase {
    private val plugin = DiscordLocalHooksMain.instance

    override fun execute(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.size < 3) {
            plugin.logger.info("Error sending store command, not enough arguments")
            return true
        }

        val title = "Obvestilo nakupa v spletni trgovini"
        plugin.logger.info(args[1])
        val color = args[1].toInt()

        val message = args.drop(2).joinToString(" ")

        val embed = EmbedContent(title, message, color, null, null, null, null)

        sendEmbed(embed)

        return true
    }

    private fun sendEmbed(entry: EmbedContent) {
        val embed = Embed(arrayListOf(entry))

        val json = Json.encodeToJsonElement(embed)
        WebHookHandler.send(json.toString(), plugin.mainConfig.getString("webhooks.store"))
    }

    override fun getPermission(): String {
        return Permissions.STORE.getPerm()
    }

    override fun getDesc(): String {
        return "Send store message"
    }
}