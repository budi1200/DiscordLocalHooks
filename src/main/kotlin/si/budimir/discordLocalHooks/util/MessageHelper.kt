package si.budimir.discordLocalHooks.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player
import si.budimir.discordLocalHooks.DiscordLocalHooksMain
import si.budimir.discordLocalHooks.config.MainConfig

abstract class MessageHelper {

    companion object {
        private val plugin = DiscordLocalHooksMain.instance
        private val config: MainConfig = plugin.mainConfig
        private var pluginPrefix = config.getParsedString("pluginPrefix")
        private var miniMessage = MiniMessage.builder().build()

        fun reloadPrefix() {
            pluginPrefix = config.getParsedString("pluginPrefix")
        }

        fun sendMessage(player: Player, key: String, placeholders: MutableMap<String, String>, prefix: Boolean = true) {
            var tmp = Component.text("")

            if (prefix) {
                tmp = tmp.append(pluginPrefix)
            }

            tmp = tmp.append(config.getParsedString(key, placeholders))

            player.sendMessage(tmp)
        }

        fun sendMessage(player: Player, message: String, prefix: Boolean = true) {
            var tmp = Component.text("")

            if (prefix) {
                tmp = tmp.append(pluginPrefix)
            }

            tmp = tmp.append(miniMessage.deserialize(message))

            player.sendMessage(tmp)
        }
    }
}
