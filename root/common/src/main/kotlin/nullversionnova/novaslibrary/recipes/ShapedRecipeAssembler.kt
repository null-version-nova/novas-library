package nullversionnova.novaslibrary.recipes

import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeProvider.inventoryTrigger
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.ItemLike
import java.util.function.Consumer
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.crafting.Ingredient
//#if MC >= 11903
import net.minecraft.data.recipes.RecipeCategory

class ShapedRecipeAssembler(val category: RecipeCategory,val id: ResourceLocation, val output: ItemLike, val quantity: Int = 1) {
    constructor(id: ResourceLocation,output: ItemLike,quantity: Int = 1) : this(RecipeCategory.MISC,id, output, quantity)
//#else

//$class ShapedRecipeAssembler(val id: ResourceLocation, val output: ItemLike, val quantity: Int = 1) {
//#endif

    private lateinit var ingredients : MutableMap<Char,Ingredient>
    var shape : String? = null
    fun withIngredients(vararg input: Pair<Char,ItemLike>) : ShapedRecipeAssembler {
        input.forEach {
            ingredients[it.first] = Ingredient.of(it.second)
        }
        return this
    }
    @JvmName("withTagIngredients")
    fun withIngredients(vararg input: Pair<Char,TagKey<Item>>) : ShapedRecipeAssembler {
        input.forEach {
            ingredients[it.first] = Ingredient.of(it.second)
        }
        return this
    }
    fun setShape(input: String) : ShapedRecipeAssembler {
        shape = input
        return this
    }
    fun send(closure: Consumer<FinishedRecipe>) {
        //#if MC>=11903
        val recipe = ShapedRecipeBuilder(category,output,quantity)
        //#else
        //$val recipe = ShapedRecipeBuilder(output,quantity)
        //#endif
        ingredients.forEach { map -> recipe.define(map.key,map.value) }
        shape!!.split('\n').forEach { shape ->
            recipe.pattern(shape)
        }
        recipe.unlockedBy("InventoryChangeTrigger", inventoryTrigger())
        recipe.save(closure,id)
    }
}