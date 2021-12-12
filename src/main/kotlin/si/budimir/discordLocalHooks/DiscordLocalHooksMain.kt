package si.budimir.discordLocalHooks

import org.bukkit.plugin.java.JavaPlugin
import si.budimir.discordLocalHooks.commands.DhCommand
import si.budimir.discordLocalHooks.config.MainConfig


class DiscordLocalHooksMain: JavaPlugin() {
    lateinit var mainConfig: MainConfig
    lateinit var dhCommand: DhCommand

    companion object {
        lateinit var instance: DiscordLocalHooksMain
    }

    override fun onEnable() {
        instance = this

        // Load config
        mainConfig = MainConfig(instance)

        // Commands
        dhCommand = DhCommand()
        this.getCommand("dlh")?.setExecutor(dhCommand)

        logger.info("DiscordLocalHooks Loaded!")
    }
}