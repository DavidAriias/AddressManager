package david.arias.addressmanager.config.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import david.arias.addressmanager.domain.repositories.AddressRepository
import david.arias.addressmanager.infraestructure.repositories.AddressRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAddressRepository(
        impl: AddressRepositoryImpl
    ): AddressRepository
}