# 🎵 **Music Player App** 🎶

This is a basic Android Music Player application that allows users to play, pause, navigate between tracks, and control the volume of music tracks.

## 🌟 **Features**

- **▶️ Play and Pause**: Start or pause the current track.
- **⏭️ Track Navigation**: Navigate to the next or previous track.
- **🔊 Volume Control**: Adjust the volume using a seek bar or max/min buttons.
- **⏹ Track Progress**: Visualize and control track progress using a seek bar.
- **🎨 Dynamic UI**: Updates song titles and album art dynamically.

## 📊 **Technologies Used**

- **Java**: Programming language for app development.
- **Android SDK**: Android development framework.
- **MediaPlayer API**: For audio playback.
- **Glide**: For loading and displaying images.
- **EdgeToEdge**: To handle edge-to-edge UI layout.

## 🗂️ **File Structure**

### 🔧 **Main Components**

- **`MainActivity.java`**: Contains the core logic for the music player functionality.
- **`res/layout/activity_main.xml`**: Defines the UI layout for the main screen.
- **`res/raw/`**: Stores audio files for the music player.
- **`res/drawable/`**: Stores album art and other image assets.

### 🛸 **Key UI Elements**

- **`ImageButton`**: Play/Pause, Previous, Next, Volume Max, and Volume Min controls.
- **`SeekBar`**: For controlling volume and track progress.
- **`TextView`**: Displays the name of the current song.
- **`ImageView`**: Displays the album art.

## 🔄 **How It Works**

1. **Initialization**: 
   - The app initializes UI components and sets up listeners for button clicks and seek bar changes.

2. **Playback Control**:
   - Tracks are played using the `MediaPlayer` API.
   - Users can pause/resume playback and navigate between tracks.

3. **Volume Adjustment**:
   - The app uses the `AudioManager` to manage volume changes.
   - Users can set the volume using a seek bar or dedicated buttons for max/min volume.

4. **Dynamic UI Updates**:
   - The `Glide` library is used to load album art dynamically.
   - The track name is updated based on the currently playing song.

## 📜 **Resources**

- **Audio Files**: Stored in the `res/raw` folder.
- **Album Art**: Images stored in the `res/drawable` folder and loaded using `Glide`.

## 🔍 **Prerequisites**

- **Android Studio**: To build and run the app.
- **Java Development Kit (JDK)**: Ensure JDK is installed.
- **Gradle**: The project uses Gradle for build management.

## 🚀 **How to Run**

1. Clone the repository or copy the source code.
2. Open the project in Android Studio.
3. Place your audio files in the `res/raw` folder with appropriate names.
4. Place corresponding album art in the `res/drawable` folder.
5. Build and run the app on an emulator or physical device.

## 🔄 **Customization**

- Add more songs by placing audio files in `res/raw` and updating the `songNames` array in `MainActivity.java`.
- Add corresponding album art in `res/drawable` with matching names.

## 🚫 **Known Issues**

- Playback might not resume properly if the app is closed unexpectedly.
- SeekBar updates might experience minor delays during playback.

## 🌟 **Future Enhancements**

- Add playlist support.
- Implement shuffle and repeat functionality.
- Enhance UI with Material Design components.
- Support streaming from online sources.

## 🔒 **License**

This project is for educational purposes and is free to use and modify.

---

Feel free to contribute or suggest improvements! 🚀

