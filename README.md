# NasaMarsPhotos


Right Now this is a minimal viable product, I am planning to increase features to this to make this production ready.
(This application is currently in active development.)

I am building this application because I love space exploration.

About the applications:


Language: Kotlin

Application Arcitecture: MVVM

DataBase: RooRoom Persistence Library (https://developer.android.com/training/data-storage/room)

Network: Retrofit Library (https://square.github.io/retrofit/)

Dependency Injection: Hilt (Library build on top of Dagger2) Currently in alpha (https://developer.android.com/training/dependency-injection/hilt-android)

Architecture Plan:

I will make this as a single activity application.
This app will use single viewmodel for different fragments, these fragments will be accessible by BottomNavigationView with Navigation Graph to navigatte between multiple fragments.
ViewModel will hold Live data from Room Database and UI will use this to show Data on the UI.
View Model will have instance of Repository. 

Repository will have reference of NetworkManager and RoomDatabase interfaces. 

These Interfaces will have implementations like NetworkManagerImpl and RoomDatabaseImpl respectively.

Also, we will use Work Manager to fetch Rover Data from API at a particular time and save it in the database. Because of this the user will have the ability to use this application in offline mode.

Features: Will update this as soon as I have completed gathering requiremtents




