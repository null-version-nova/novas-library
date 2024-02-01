package nullversionnova.novaslibrary.test

import net.minecraft.world.item.Items
import nullversionnova.novaslibrary.NovasLibrary
import nullversionnova.novaslibrary.registry.CreativeTabRegistry

object TestTabs : CreativeTabRegistry(NovasLibrary.MOD_ID) {
    val TEST_TAB by registerWithIcon("test_tab") { Items.WHEAT }
}