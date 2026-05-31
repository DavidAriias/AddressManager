package david.arias.catgallery.config.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import david.arias.catgallery.domain.repositories.CatRepository
import david.arias.catgallery.infraestructure.repositories.CatRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCatRepository(
        repositoryImpl: CatRepositoryImpl
    ): CatRepository
}