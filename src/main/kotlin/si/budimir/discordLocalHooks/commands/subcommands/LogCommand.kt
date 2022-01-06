package si.budimir.discordLocalHooks.commands.subcommands

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import si.budimir.discordLocalHooks.DiscordLocalHooksMain
import si.budimir.discordLocalHooks.WebHookHandler
import si.budimir.discordLocalHooks.commands.SubCommandBase
import si.budimir.discordLocalHooks.util.*
import java.time.Instant

class LogCommand: SubCommandBase {
    // dlp log <color> <player> <message>
    private val plugin = DiscordLocalHooksMain.instance

    override fun execute(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val title = "Overwatch | Log"
        val color = args[1].toInt()

        val message = args.drop(3).joinToString(" ")
        val thumbnail = Thumbnail("https://crafthead.net/cube/${args[2]}")

        val embed = EmbedContent(title, message, color, Instant.now().toString(), null, thumbnail, null)

        sendEmbed(embed)

        return true
    }

    private fun sendEmbed(entry: EmbedContent) {
        val embed = Embed(arrayListOf(entry))

        val json = Json.encodeToJsonElement(embed)
        WebHookHandler.send(json.toString(), plugin.mainConfig.getString("webhooks.matrix"))
    }

    override fun getPermission(): String {
        return Permissions.STORE.getPerm()
    }

    override fun getDesc(): String {
        return "Send store message"
    }
}