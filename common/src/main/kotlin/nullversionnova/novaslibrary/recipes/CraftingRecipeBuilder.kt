package nullversionnova.novaslibrary.recipes

import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.ItemLike
import java.lang.AssertionError

class CraftingRecipeBuilder(val id: ResourceLocation, val output: () -> ItemLike, val quantity: Int = 1) {
    constructor(namespace: String, path: String, output: () -> ItemLike, quantity: Int = 1) : this(ResourceLocation(namespace,path),output,quantity)
    lateinit var ingredients : Map<Char,() -> ItemLike>
    var has_shape : Boolean? = null
    var shape : String? = null
    fun withIngredients(vararg input: Pair<Char,() -> ItemLike>) : CraftingRecipeBuilder {
        ingredients = mapOf(*input)
        return this
    }
    fun setShape(input: String) : CraftingRecipeBuilder {
        has_shape = true
        shape = input
        return this
    }
    fun setList(input: String) : CraftingRecipeBuilder {
        has_shape = false
        shape = input
        return this
    }
    fun build() {
        when(has_shape) {
            null -> return
            true -> ProviderSender.sendShapedRecipe(this)
            false -> ProviderSender.sendShapelessRecipe(this)
        }
    }
}