package si.budimir.discordLocalHooks.listeners

import fr.xephi.authme.api.v3.AuthMeApi
import fr.xephi.authme.events.FailedLoginEvent
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import si.budimir.discordLocalHooks.DiscordLocalHooksMain
import si.budimir.discordLocalHooks.WebHookHandler
import si.budimir.discordLocalHooks.util.*
import java.time.Instant

class AuthMeWrongPassListener(private val plugin: DiscordLocalHooksMain) : Listener {

    @EventHandler
    fun onWrongPassword(event: FailedLoginEvent) {
        val player = event.player
        if (!player.hasPermission("authme.staffnotify")) {
            return
        }

        val embed = buildEmbed(player)
        sendEmbed(embed)
    }

    private fun buildEmbed(player: Player): EmbedContent {
        val name = player.name
        val ip = player.address.address.toString().replace("/", "")
        val associatedPlayers = plugin.authmeApi.getNamesByIp(ip)

        val title = "Failed Login Alert"
        val thumbnail = Thumbnail("https://minotar.net/helm/${name}/40.png")
        val footer = Footer("", Instant.now().toString())

        val fields = arrayListOf<Field>()
        fields.add(Field("Player", name, true))
        fields.add(Field("IP Address", ip, true))
        fields.add(Field("Associated accounts", associatedPlayers.toString().replace(Regex("([\\[\\]])"), "")))

        return EmbedContent(
            title,
            null,
            15216198,
            null,
            footer,
            thumbnail,
            null,
            fields
        )
    }

    private fun sendEmbed(entry: EmbedContent) {
        val embed = Embed(arrayListOf(entry))

        val json = Json.encodeToJsonElement(embed)
        WebHookHandler.send(json.toString(), plugin.mainConfig.getString("webhooks.authme"))
    }
}