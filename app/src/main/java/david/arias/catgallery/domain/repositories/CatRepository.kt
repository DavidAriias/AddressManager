package david.arias.catgallery.domain.repositories

import david.arias.catgallery.domain.entities.Breed
import david.arias.catgallery.domain.entities.CatImage

interface CatRepository {

    suspend fun getBreeds(): List<Breed>

    suspend fun getImagesByBreed(
        breedId: String,
        limit: Int
    ): List<CatImage>

}