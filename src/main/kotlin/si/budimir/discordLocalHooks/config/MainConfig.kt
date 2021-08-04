package si.budimir.discordLocalHooks.config

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import si.budimir.discordLocalHooks.DiscordLocalHooksMain

class MainConfig(private val plugin: DiscordLocalHooksMain) : ConfigBase(plugin, "config.yml") {

    fun getString(key: String): String {
        var value: String? = getConfig()!!.getString(key)

        if (value == null) {
            plugin.logger.warning("Missing config value for $key")
            value = ""
        }

        return value
    }

    fun getParsedString(key: String, placeholders: Map<String, String> = hashMapOf()): Component {
        return Component
            .text("")
            .decoration(TextDecoration.ITALIC, false)
            .append(
                MiniMessage.markdown().parse(getString(key), placeholders)
            )
    }

    fun getNonMarkdownParsed(key: String, placeholders: Map<String, String> = hashMapOf()): Component {
        return MiniMessage.get().parse(getString(key), placeholders)
    }
}