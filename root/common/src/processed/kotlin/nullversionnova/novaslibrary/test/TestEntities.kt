package nullversionnova.novaslibrary.test

import net.minecraft.world.entity.MobCategory
import net.minecraft.world.entity.animal.Cow
import nullversionnova.novaslibrary.NovasLibrary
import nullversionnova.novaslibrary.registry.EntityRegistry

object TestEntities : EntityRegistry(NovasLibrary.MOD_ID) {
    val TEST_ENTITY = register("test_entity",MobCategory.CREATURE, { Cow.createAttributes() }) { entityType, level ->
        Cow(entityType, level)
    }
}
