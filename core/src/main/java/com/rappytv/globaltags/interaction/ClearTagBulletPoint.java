package com.rappytv.globaltags.interaction;

import com.rappytv.globaltags.GlobalTagAddon;
import com.rappytv.globaltags.api.Util;
import com.rappytv.globaltags.config.GlobalTagConfig;
import com.rappytv.globaltags.wrapper.enums.GlobalPermission;
import com.rappytv.globaltags.wrapper.model.PlayerInfo;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.client.entity.player.interaction.BulletPoint;
import net.labymod.api.client.gui.icon.Icon;

public class ClearTagBulletPoint implements BulletPoint {

    private final GlobalTagConfig config;

    public ClearTagBulletPoint(GlobalTagAddon addon) {
        this.config = addon.configuration();
    }

    @Override
    public Component getTitle() {
        return Component.translatable("globaltags.context.clearTag.name");
    }

    @Override
    public Icon getIcon() {
        return GlobalTagAddon.roundIcon;
    }

    @Override
    public void execute(Player player) {
        GlobalTagAddon.getAPI().getApiHandler().resetTag(player.getUniqueId(), (response) -> Laby.references().chatExecutor().displayClientMessage(
            Component.empty()
                .append(GlobalTagAddon.prefix)
                .append(Util.getResponseComponent(response))
        ));
    }

    @Override
    public boolean isVisible(Player player) {
        if(!this.config.enabled().get() || !this.config.showBulletPoints().get()) {
            return false;
        }
        PlayerInfo<?> executor = GlobalTagAddon.getAPI().getCache().get(Laby.labyAPI().getUniqueId());
        PlayerInfo<?> target = GlobalTagAddon.getAPI().getCache().get(player.getUniqueId());
        return executor != null && executor.hasPermission(GlobalPermission.MANAGE_TAGS)
            && target != null && target.getTag() != null;
    }
}
