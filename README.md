# General Info

This app utlizes both the traditional view system (XML) and Jetpack Compose.
The app consists of 3 screens:
- Film List: (Jetpack Compose).
- Film Details: (Jetpack Compose).
- Character Details: (XML).

This app is build with the principles of MVVM applied, using Dagger Hilt for Dependecy Injection and Retrofit for REST API calls.

# Folder Structure

- Common: Holds common classes which are used in the entire project.
- Data: Holds the remote data classes and repository implementations.
- DI: Contains dependecy injection modules.
- Domain: Contains the app's data clases, the repository interface, and UseCases that are reusable across the project.
- Presentation: Contains the UI section of the application, including themes, colors, UI, ViewModels, etc.

# Notes

- The app adapts to dark mode and orientation changes.
- The repository contains an APK for easier testing in /app/release.
