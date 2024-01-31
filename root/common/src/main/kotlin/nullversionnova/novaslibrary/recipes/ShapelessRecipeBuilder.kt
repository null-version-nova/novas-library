package nullversionnova.novaslibrary.recipes

import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.world.level.ItemLike
//#if MC >= 11903
import net.minecraft.data.recipes.RecipeCategory

//#endif

object ShapelessRecipeBuilder {
    fun shapeless(item: ItemLike, quantity: Int = 1) : ShapelessRecipeBuilder {
        //#if MC>=11903
        return shapeless(RecipeCategory.MISC,item,quantity)
        //#else
        //$return ShapelessRecipeBuilder.shapeless(item,quantity)
        //#endif
    }
    //#if MC>=11903
    fun shapeless(category: RecipeCategory, item: ItemLike, quantity: Int = 1) : ShapelessRecipeBuilder {
        return ShapelessRecipeBuilder.shapeless(category,item,quantity)
    }
    //#endif
}