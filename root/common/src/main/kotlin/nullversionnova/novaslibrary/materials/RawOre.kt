package nullversionnova.novaslibrary.materials

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import nullversionnova.novaslibrary.datagen.GeneralDataProcessing
import nullversionnova.novaslibrary.interfaces.Material
import nullversionnova.novaslibrary.recipes.ShapedRecipeAssembler
import nullversionnova.novaslibrary.recipes.ShapelessRecipeAssembler
import nullversionnova.novaslibrary.registry.BlockRegistry
import nullversionnova.novaslibrary.registry.GenericRegistry
import nullversionnova.novaslibrary.registry.ItemRegistry
import nullversionnova.novaslibrary.registry.Registries
import nullversionnova.novaslibrary.test.TestTabs

class RawOre(val id: ResourceLocation) : Material {
    constructor(namespace: String, path: String) : this(ResourceLocation(namespace,path))
    // Registries
    private val block_registry = BlockRegistry(id.namespace)
    private val item_registry = ItemRegistry(id.namespace)

    // Objects
    val ORE : Item by item_registry.registerWithProperties(id.path)
    val BLOCK by block_registry.registerWithItem("${id.path}_block") { Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)) }

    override fun register() {
        block_registry.register()
        item_registry.register()

        GeneralDataProcessing.registerRecipe {
            ShapedRecipeAssembler(ResourceLocation(id.namespace,"${id.path}_to_${id.path}_block"),BLOCK)
                .withIngredients( 'x' to ORE )
                .setShape("xxx\nxxx\nxxx")
                .send(it)
            ShapelessRecipeAssembler(ResourceLocation(id.namespace,"${id.path}_block_to_${id.path}"),ORE,9)
                .requires(BLOCK)
                .send(it)
        }
    }
}