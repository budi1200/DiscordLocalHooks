package si.budimir.discordLocalHooks.util

enum class Permissions(private val perm: String) {
    RELOAD("admin"),
    STORE("store");

    fun getPerm(): String {
        return "dlh.$perm"
    }
}
