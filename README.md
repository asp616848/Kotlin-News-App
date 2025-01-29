# GeekNews 📚📰
A modern Android app to fetch and display the latest news articles using the [NewsAPI](https://newsapi.org/)! The app is built with Jetpack Compose for a clean and intuitive user interface and leverages Kotlin for robust development.

## Features ✨
- 🌎 Fetch top headlines across various categories (e.g., general, technology, sports).
- 🔍 Search news articles based on keywords.
- 📜 View detailed articles in-app with seamless navigation.
- 🎨 Modern UI built with Jetpack Compose.
- 📰 Real-time updates powered by LiveData.

## Screenshots 📷
_To be added
## Tech Stack 🛠️
- **Programming Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **UI Framework**: Jetpack Compose
- **Networking**: [NewsAPI](https://newsapi.org/)
- **Libraries**:
    - [News API Client](https://github.com/KwabenBerko/News-Api-Java)


## Prerequisites ⚙️
- Android Studio Flamingo or later.
- An API Key from [NewsAPI](https://newsapi.org/).

## Getting Started 🚀
Follow these steps to run the app on your local machine:

### 1. Clone the Repository
```bash  
gh repo clone asp616848/Kotlin-News-App
cd Kotlin-News-App
```  

### 2. Add Your API Key
Replace the placeholder in `API.kt` with your NewsAPI key:
```kotlin  
object API {
    const val KEY = "YOUR_API_KEY"
}
```  

### 3. Build and Run
- Open the project in Android Studio.
- Sync Gradle files.
- Build and run the app on an emulator or physical device.

## Project Structure 📂
```
├── MainActivity.kt           # Entry point of the app
├── ViewModel                 # Contains ViewModel logic
│   └── ViewModel.kt          # Manages API data and LiveData
├── ui                        # Contains all UI components
│   ├── HomePage.kt           # Displays top headlines
│   ├── NewsArticlePage.kt    # Displays individual article details
│   ├── theme                 # App theme and styling
├── API.kt                    # Stores the NewsAPI key
└── utils                     # Utility classes (if any)
```  

## To-Do 🗒️
- [ ] Add offline caching for articles.
- [ ] Implement user preferences for favorite categories.
- [ ] Add dark mode toggle.

## Contributing 🤝
Contributions are always welcome! If you’d like to contribute:
1. Fork the repo.
2. Create a new branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -m "Add some feature"`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Open a pull request.

## License 📜
This project is licensed under the MIT License. See the `LICENSE` file for details.

## Acknowledgements 🙏
- [NewsAPI](https://newsapi.org/) for providing the data.
- Jetpack Compose for the amazing UI framework.

---