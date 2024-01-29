package nullversionnova.novaslibrary.recipes

//#if MC>=11903
import net.minecraft.data.recipes.RecipeCategory
//#else
//$import net.minecraft.client.RecipeBookCategories
//#endif

//#if MC>=11903
typealias RecipeCategory = RecipeCategory
//#else
//$typealias RecipeCategory = RecipeBookCategories
//#endif

object RecipeCategories {
    val MISC =
        //#if MC>=11903
        RecipeCategory.MISC
        //#else
        //$RecipeBookCategories.CRAFTING_MISC
        //#endif
}

