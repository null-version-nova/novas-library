package nullversionnova.novaslibrary.registry

import dev.architectury.registry.level.entity.EntityAttributeRegistry
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder
import net.minecraft.world.level.Level
import nullversionnova.novaslibrary.abstraction.RegistryAccessor
import nullversionnova.novaslibrary.util.RegistryDelegate

open class EntityRegistry(id: String) : GenericRegistry<EntityType<*>>(id, RegistryAccessor.ENTITY_TYPE) {
    fun <T : LivingEntity> register(path : String, builder : EntityType.Builder<T>, attributes: () -> Builder) : RegistryDelegate<EntityType<T>> {
        val key = register(path) { builder.build(path) }
        whenRegistered {
            EntityAttributeRegistry.register({key.instance}) {
                attributes()
            }
        }
        return key
    }
    fun <T: LivingEntity> register(path: String, category: MobCategory, attributes: () -> Builder, constructor: (entityType: EntityType<T>, level: Level) -> T) : RegistryDelegate<EntityType<T>> {
        return register(path, EntityType.Builder.of({ entityType, level -> constructor(entityType,level) },category),attributes)
    }
}