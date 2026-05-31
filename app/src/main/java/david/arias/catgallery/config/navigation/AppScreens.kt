package david.arias.catgallery.config.navigation

sealed class AppScreens(val route: String) {
    data object BreedSelection : AppScreens("breed-selection")
    data object CatGallery : AppScreens("cat-gallery") {
        fun createRoute(breedId: String,breedName: String, limit: Long) = "cat-gallery/$breedId/$breedName/$limit"
    }
}