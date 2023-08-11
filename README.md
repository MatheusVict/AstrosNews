# Astros News

A SpaceFlightNews API client for Android. It was created for a series of instructional videos on YouTube.

## Screenshots:

![Screnshot_1](https://github.com/MatheusVict/AstrosNews/assets/103688000/6eecab2b-2aef-4b3a-a815-b3b0f38340f3)
![Screnshot_2](https://github.com/MatheusVict/AstrosNews/assets/103688000/36be1910-108e-434c-ae71-510e257eade8)
![Screnshot_3](https://github.com/MatheusVict/AstrosNews/assets/103688000/6d8caab6-aad8-48d9-8d17-39d0ef6805f4)
![Screnshot_4](https://github.com/MatheusVict/AstrosNews/assets/103688000/581aa723-ab9f-464b-8035-353785363f1e)
![Screnshot_5](https://github.com/MatheusVict/AstrosNews/assets/103688000/908919a5-ed97-4eed-8383-b8b044f9a4ef)
![Screnshot_6](https://github.com/MatheusVict/AstrosNews/assets/103688000/d026fc48-ef19-408c-93e1-d9f45533e206)
![Screnshot_7](https://github.com/MatheusVict/AstrosNews/assets/103688000/5973b43c-4405-431a-90bc-8be26efb76cc)

- **API consumption**. The app consumes eht SpaceFlightNews Api (https://spaceflightnewsapi.net/).
- **MVVM architecture**. The app is based on modern Android components and MVVM architecture.
- **Clean Architecture**. It's development follows clean architecture practices, such as separation of core, domain, data and presentation layers.
- **UX/UI**. Creating a modern, crisp and accessible interface using Google Material Design components.
- **Cyrillic, Latin and Greek support**. Use of a font family with support for different character sets.
- **Coroutines**. Extensive use of coroutines for remote data access and other operations.
- **Flow**. Use of flows instead of LiveData.
- **Automated Testing & TDD**. Automated unit and integration tests using JUnit4 and KoinTest.
- **Flexible UseCase class**. Employ inheritance to create a flexible `UseCase<Param, Source>` class.
- **Flexible State class**. The app uses a flexible `State<T>` wrapper class capable of handling responses with type safety.
- **MockWebServer**. Use for mocking services.
- **Dependency injection with Koin**.
- **Fragment-based navigation**.

## Versions
### Version 0.1
- Basic functionality: retrieve latest Articles, Blog Posts and Mission Reports from the SpaceFlightNewsAPI
- Branded Aesthetics

### Version 0.2
- Search Articles, Blog Posts and Mission Reports by title
