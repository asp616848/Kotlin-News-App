# GeekNews (Kotlin-News-App)

GeekNews is a Kotlin/Jetpack Compose Android news reader built as the teaching project for a **workshop on Android development at Plaksha University**. It fetches top headlines and search results from [NewsAPI](https://newsapi.org/) and displays them in a scrollable feed, with an in-app WebView to read the full article.

Much of the code is intentionally left as **guided fill-in-the-blank exercises** (`_____` placeholders annotated with `// Q1:`, `// Q2:`, ... comments) so workshop attendees learn Compose modifiers, LiveData/ViewModel wiring, and NewsAPI integration by completing the blanks themselves. A companion file, `basics_snippets.md`, contains standalone Kotlin language snippets (variables, control flow, classes, loops) used to teach core syntax before diving into the app.

## Features

- Fetch top headlines by category (General, Business, Entertainment, Health, Science, Sports, Technology) via NewsAPI's "top headlines" endpoint.
- Search articles by keyword via NewsAPI's "everything" endpoint.
- Scrollable article list with thumbnail, title, and source name (image loading via Coil).
- In-app article reader using an embedded `WebView`.
- Light/dark theme previews built with Jetpack Compose.

## Tech Stack

- **Kotlin**, native Android, minSdk 26 / targetSdk & compileSdk 34.
- **Jetpack Compose** (Material 3) for the entire UI.
- **MVVM**: `ViewModel` + `LiveData` (`ViewModel.kt`) holds and exposes the fetched article list.
- **Navigation Compose** with type-safe routes (`kotlinx.serialization`) for Home ↔ Article navigation.
- **NewsAPI** via the [News-API-Java](https://github.com/KwabenBerko/News-Api-Java) client library (`com.github.KwabenBerko:News-API-Java`).
- **Coil** (`io.coil-kt:coil-compose`) for async image loading.

## NewsAPI Key

The app calls NewsAPI through `API.kt`, which currently holds an empty placeholder:

```kotlin
object API {
    val apiKey = ""
}
```

To run the app you need your own free API key from [newsapi.org](https://newsapi.org/) — sign up and paste it into `API.kt` (or better, load it from local, untracked config such as `local.properties` / a Gradle secrets plugin so it never gets committed). No key is checked into this repository.

## Getting Started

1. Clone the repo and open it in **Android Studio**.
2. Complete the fill-in-the-blank exercises in `MainActivity.kt`, `ViewModel.kt`, `HomeScreen.kt`, `Route.kt`, and `FullScreen.kt` (each blank is marked `_____` with a `// Qn:` comment explaining what's expected).
3. Add your NewsAPI key to `API.kt` as described above.
4. Sync Gradle and run on an emulator or device (Android 8.0 / API 26+).

## Project Structure

```
app/src/main/java/com/example/abhijeet/geekNews/
├── API.kt                     # Holds the NewsAPI key
├── MainActivity.kt            # App entry point, Scaffold, navigation host
├── ViewModel/ViewModel.kt     # Fetches headlines/search results via NewsAPI, exposes LiveData
├── ui/
│   ├── HomeScreen.kt          # Article list, category bar, search
│   ├── FullScreen.kt          # WebView-based article reader
│   ├── Route.kt               # Type-safe navigation routes
│   └── theme/                 # Compose theme (colors, typography)
└── basics_snippets.md         # Kotlin language teaching snippets
```

## Notes

- This is a workshop/teaching artifact, not a production app: several files ship intentionally incomplete for hands-on learning.
- No offline caching, dark-mode toggle, or user preferences are implemented yet (open items for further exercises).
