# YourPlants 🌿

**YourPlants** is a modern Android application designed to help users track their moods and thoughts through voice recordings ("Echos"). Built with a focus on clean code, reactive UI, and robust audio management.

---

## 🏗 Architecture
The project follows **Clean Architecture** principles and the **MVI (Model-View-Intent)** pattern to ensure a scalable and testable codebase.

### Layers:
*   **Presentation:** Built with **Jetpack Compose**. ViewModels manage a single `State` and process user `Actions`.
*   **Domain:** Contains the business logic, Use Cases, and Repository interfaces.
*   **Data:** Implementation of repositories, **Room Database**, and local audio file management.

---

## 📂 Project Structure
The project is organized by features to keep the codebase modular:

```
📦 app/src/main/java/com/ahanafrifat/yourplants
├── 🧩 core/                          # Shared modules & design system
│   ├── 🧱 base/                      # MVI base classes (ViewModel, State, Action)
│   ├── 🎨 theme/                     # Design system (Color.kt, Type.kt, Theme.kt)
│   ├── 🛠 util/                      # Audio extensions & date formatters
│   └── 🧩 components/                # Global reusable UI (Buttons, Cards, Dialogs)
│
├── 💾 data/                          # Data layer (infrastructure)
│   ├── 🗄 local/                     # Room database, DAOs & SQL entities
│   ├── 📦 repository/                # Domain repository implementations
│   ├── 🔄 mapper/                    # Data → Domain model conversion
│   └── 🎙 audio/                     # Low-level MediaRecorder & MediaPlayer logic
│
├── 🧠 domain/                        # Business logic layer (pure Kotlin)
│   ├── 📄 model/                     # Business entities (Echo, Mood, Topic)
│   ├── 📜 repository/                # Repository interfaces
│   └── ⚙️ usecase/                   # Business rules (e.g., SaveEchoUseCase)
│
└── 🎨 presentation/                  # UI layer (Jetpack Compose)
    ├── 🔊 echos/                     # Feature: Echo list & feed
    │   ├── 🧠 EchosViewModel.kt       # Logic for the main feed
    │   ├── 📊 EchosState.kt           # State for the feed list
    │   └── 🧩 components/             # List UI (EchoItem, DateHeader)
    │
    ├── 🎙 create_echo/               # Feature: Voice recording & creation
    │   ├── 🖥 CreateEchoScreen.kt     # Recording interface
    │   ├── 🧠 CreateEchoViewModel.kt  # Amplitude & timer logic
    │   └── 🧩 components/             # Waveform & mood selector
    │
    ├── ⚙️ settings/                  # Feature: User preferences
    │   └── 🖥 SettingsScreen.kt       # App settings & theme toggles
    │
    └── 🧪 preview/                   # Compose preview utilities
        └── 📄 SampleData.kt           # Mock data for UI previews
```
---

## 🛠 Tech Stack & Libraries
*   **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern declarative UI.
*   **Dependency Injection:** [Koin](https://insert-koin.io/) - Pragmatic Kotlin DI.
*   **Database:** [Room](https://developer.android.com/training/data-storage/room) - Local SQLite persistence.
*   **Async/Flow:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html).
*   **Audio:** Custom wrappers for `MediaRecorder` and `MediaPlayer`.
*   **Navigation:** [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) for type-safe routing.
*   **Logging:** [Timber](https://github.com/JakeWharton/timber).

---

## 🎙 Audio Handling Features
*   **Amplitude Visualization:** Captures real-time audio levels to create a dynamic recording wave.
*   **Normalization:** Audio amplitudes are normalized to ensure consistent UI visualization.
*   **Permission Management:** Graceful handling of `RECORD_AUDIO` permissions using MVI events.
*   **State Management:** Playback and recording states are lifecycle-aware to prevent memory leaks.

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Ladybug or newer.
- Android SDK 26+ (Android 8.0+).

### Installation
1. Clone the repository:
    2. Open the project in Android Studio.
3. Sync Gradle and run the app on a physical device for the best audio experience.

---

## 📸 Screenshots

| Home Screen | Recording Screen | Recording Done Screen | Recording Discard Screen | Settings Screen |
| :---: | :---: |
| ![Placeholder](<img src="screenshots/home_screen_1.jpeg" alt="Home Screen">) | ![Placeholder](<img src="screenshots/recording_screen_1.jpeg" alt="Recording Screen">) | ![Placeholder](<img src="screenshots/new_entry_fill_text.jpeg" alt="Recording Done Screen">) | ![Placeholder](<img src="screenshots/recording_discard.jpeg" alt="Recording Discard Screen">) | ![Placeholder](<img src="screenshots/settings.jpeg" alt="Settings Screen">) |

---

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.