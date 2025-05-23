package com.rappytv.globaltags.command.subcommands;

import com.rappytv.globaltags.GlobalTagsAddon;
import com.rappytv.globaltags.api.GlobalTagAPI;
import net.labymod.api.client.chat.command.SubCommand;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;

public class ClearCacheCommand extends SubCommand {

    private final GlobalTagAPI api;

    public ClearCacheCommand(GlobalTagAPI api) {
        super("clearcache", "cc");
        this.api = api;
    }

    @Override
    public boolean execute(String prefix, String[] arguments) {
        this.api.getCache().clear();
        this.api.getCache().resolveSelf();
        this.displayMessage(
            Component.empty()
                .append(GlobalTagsAddon.prefix)
                .append(Component.translatable(
                        "globaltags.commands.clearCache.success",
                    NamedTextColor.GREEN
                ))
        );
        return true;
    }
}
