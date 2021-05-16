# Weather-Forecast

- Simple weather forecast app that is created with Kotlin.

## Table of content
* [Functions](#functions).    
* [Architecture](#architecture).     
* [Data flow](#data-flow).    
* [Folder structure](#folder-structure).    
* [Technologies](#technologies).    
* [Check list](#check-list).    
* [Run](#run).     
* [Launch](#launch).     
* [Set up](#set-up).    
* [License](#license).     

## Functions:
- Search weather forecast by city name in 7 days from current day
- Weather forecast include info:
    - Date
    - Average temperature - in Celsius
    - Pressure
    - Humidity
    - Descriptions
- Info will be cached until end of day.

## Architecture:
![alt text](https://miro.medium.com/max/1834/1*q2AL8a9a1ZN6m5OxgLJMvg.png)

As the image above, we follow clean architecture with 3 layers:  
### 1. App/Presentation layer:  
    - A layer that interacts with the UI, mainly Android Stuff like Activities, Fragments, ViewModel, etc. It would include both domain and data layers.  
   ### 2. Data layer:    
    - The data module consists of server models, mappers, API services, and repository implementations. It includes the domain layer.
   ### 3. Domain layer:    
    - The domain layer contains the business logic of the application, consists of models, use cases and repositories interface
  
  Explaination some keyword:  
  ### Usecases:  
Each individual functionality or business logic unit can be called a use case. Like fetching data from a network or reading data from database, preferences, etc can be referred to as use cases.
### Mappers:  
Mappers by the name itself are suggesting that it maps from one type to another type. Actually, in our apps, we use the same object which we receive from the server to set details to UI.

    
## Data flow:
![alt text](https://miro.medium.com/max/3118/1*LldbQQRy3_ujZHbUU7X64Q.png)

## Folder structure:
Following Clean Architecture so having 4 modules:
### 1. App module:  
- Main module contains all things relative to android application (Application, Activity, Fragments, ViewModels...)
- Include 3 others module: data, domain, configurations
### 2. Domain module:  
- Contains all usecases, repository interface definition, model that is used to update data onto UI
### 3. Data module:  
- Implement all repository from Domain module.
- Constain mapper class to map data from remote/local database response to model that domain module need.
- Constain room database to cache data into local data base.
- Constain data store to save key-value variable
- Constain remote service.
- Definition network part such as retrofit, parse exception...
### 4. Configurations module:  
- Constain all configurations of application such as: app id, ssl certificates, base url, domain pattern...

## Technologies:
Project is created with:
* Kotlin - v1.4.32
* Coroutine - v1.4.3
* Room database - v2.3.0
* Retrofit - v2.9.0
* Dagger - v2.35.1
* Data store - v1.0.0-beta01
* Scale dp-sp - v1.0.6
* Rootbeer - v0.0.9

Test part:
* Mockito - v3.9.0
* Mockito nhaarman - v2.2.0

## Check list:
1. Programming language: Kotlin is required, Java is optional. ✅
2. Design app's architecture (suggest MVVM) ✅
3. Apply LiveData mechanism ✅
4. UI should be looks like in attachment. ✅
5. Write UnitTests ✅
6. Acceptance Tests
7. Exception handling ✅
8. Caching handling ✅
9. Secure Android app from:   
a. Decompile APK.  
b. Rooted device ✅.  
c. Data transmission via network ✅.  
d. Encryption for sensitive information.  
10. Accessibility for Disability Supports:   
a. Talkback: Use a screen reader ✅.  
b. Scaling Text: Display size and font size: To change the size of items on your screen, adjust the display size or font size ✅.  
11. Entity relationship diagram for the database and solution diagrams for the components, infrastructure design if any ✅.  
12. Readme file includes: ✅  
a. Brief explanation for the software development principles, patterns & practices being applied  
b. Brief explanation for the code folder structure and the key Java/Kotlin libraries and frameworks being used  
c. All the required steps in order to get the application run on local computer  
d. Checklist of items the candidate has done.

## Run:
* Android studio 4.1.2
* Gradle v6.1.1

## Launch:
Min SDK: 19

## Set up:
Install on window os:
* gradlew installDebug

Intall on MAC os
* ./gradlew installDebug


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

