package david.arias.addressmanager.config.navigation

sealed class AppScreens(val route: String) {

    data object AddressList : AppScreens("address-list")

    data object AddressDetail : AppScreens("address-detail")
}