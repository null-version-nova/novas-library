package nullversionnova.novaslibrary.recipes

import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import java.util.function.Consumer
//#if MC >=11903
import net.minecraft.data.recipes.RecipeCategory

class ShapelessRecipeAssembler(val category: RecipeCategory, val id: ResourceLocation, val output: ItemLike, val quantity: Int = 1) {
    constructor(id: ResourceLocation, output: ItemLike,quantity: Int = 1) : this(RecipeCategory.MISC,id,output,quantity)
//#else
//$class ShapelessRecipeAssembler(val id: ResourceLocation, val output: ItemLike, val quantity: Int = 1) {
//#endif
    val ingredients = mutableListOf<Ingredient>()
    fun requires(vararg ingredient: Ingredient) : ShapelessRecipeAssembler {
        ingredients.addAll(ingredient)
        return this
    }
    fun requires(vararg item: ItemLike) : ShapelessRecipeAssembler {
        return requires(Ingredient.of(*item))
    }
    fun requires(tag: TagKey<Item>) : ShapelessRecipeAssembler {
        return requires(Ingredient.of(tag))
    }
    fun send(closure: Consumer<FinishedRecipe>) {
        //#if MC>=11903
        val recipe = ShapelessRecipeBuilder(category,output,quantity)
        //#else
        //$val recipe = ShapelessRecipeBuilder(output,quantity)
        //#endif
        ingredients.forEach {
            recipe.requires(it)
        }
        recipe.unlockedBy("InventoryChangeTrigger", RecipeProvider.inventoryTrigger())
        recipe.save(closure,id)
    }
}