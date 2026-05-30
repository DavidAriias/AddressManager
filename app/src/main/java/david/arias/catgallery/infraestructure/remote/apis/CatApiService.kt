package david.arias.catgallery.infraestructure.remote.apis

import david.arias.catgallery.infraestructure.remote.entities.BreedResponse
import david.arias.catgallery.infraestructure.remote.entities.CatImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {

    @GET("breeds")
    suspend fun getBreeds(): List<BreedResponse>

    @GET("images/search")
    suspend fun getImagesByBreed(
        @Query("breed_ids") breedId: String,
        @Query("limit") limit: Int
    ): List<CatImageResponse>
}