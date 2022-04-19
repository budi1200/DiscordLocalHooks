package si.budimir.discordLocalHooks.config

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import si.budimir.discordLocalHooks.DiscordLocalHooksMain

class MainConfig(private val plugin: DiscordLocalHooksMain) : ConfigBase(plugin, "config.yml") {
    private var miniMessage = MiniMessage.builder().build()

    fun getString(key: String): String {
        var value: String? = getConfig()!!.getString(key)

        if (value == null) {
            plugin.logger.warning("Missing config value for $key")
            value = ""
        }

        return value
    }

    fun getParsedString(key: String, placeholders: Map<String, String> = hashMapOf()): Component {
        val resolver = TagResolver.resolver(placeholders.map { Placeholder.parsed(it.key, it.value) })

        return Component
            .text("")
            .decoration(TextDecoration.ITALIC, false)
            .append(
                miniMessage.deserialize(getString(key), resolver)
            )
    }

    fun getNonMarkdownParsed(key: String, placeholders: Map<String, String> = hashMapOf()): Component {
        val resolver = TagResolver.resolver(placeholders.map { Placeholder.parsed(it.key, it.value) })

        return miniMessage.deserialize(getString(key), resolver)
    }
}