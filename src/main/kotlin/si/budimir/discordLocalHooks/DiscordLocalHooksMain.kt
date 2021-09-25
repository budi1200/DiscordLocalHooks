package si.budimir.discordLocalHooks

import fr.xephi.authme.api.v3.AuthMeApi
import org.bukkit.plugin.java.JavaPlugin
import si.budimir.discordLocalHooks.commands.DhCommand
import si.budimir.discordLocalHooks.config.MainConfig
import si.budimir.discordLocalHooks.listeners.AuthMeWrongPassListener


class DiscordLocalHooksMain: JavaPlugin() {
    lateinit var mainConfig: MainConfig
    lateinit var dhCommand: DhCommand
    lateinit var authmeApi: AuthMeApi

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

        // Event listeners
        server.pluginManager.registerEvents(AuthMeWrongPassListener(this), this)

        // Hooks
        authmeApi = AuthMeApi.getInstance()

        logger.info("DiscordLocalHooks Loaded!")
    }

    override fun onDisable() {
        super.onDisable()
    }
}