# 🛒 Cartify - Modern E-commerce App

> **Cartify is a beautifully designed, modern Android e-commerce application built with Jetpack Compose, Hilt, Room, and Firebase. It provides a seamless shopping experience with real-time product synchronization and local cart management.**

---

---

## 🚀 Features

- 🌐 **Real-time Synchronization:** Fetches products and categories dynamically from Firebase Firestore.
- 🔐 **Secure Authentication:** Implements Firebase Authentication for user sign-in and sign-up.
- 💾 **Local Persistence:** Uses Room Database to manage the shopping cart locally for offline responsiveness.
- 🏗️ **Dependency Injection:** Built with Google Hilt for a scalable and maintainable codebase.
- 🎨 **Modern UI:** 100% Jetpack Compose for a declarative, fluid, and reactive user interface.
- 📦 **Image Loading:** Efficient image rendering and caching using Coil 3.
- ⚡ **Performance:** Uses Kotlin Symbol Processing (KSP) for optimized build times.

---

## 🎨 Tech Stack

- **Language:** [Kotlin](https://kotlinlang.org/)
- **UI Framework:** [Jetpack Compose](https://developer.android.com/compose)
- **Dependency Injection:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Backend:** [Firebase](https://firebase.google.com/) (Firestore & Auth)
- **Local Storage:** [Room Database](https://developer.android.com/training/data-storage/room)
- **Architecture:** MVVM + Repository Pattern
- **Image Loading:** [Coil 3](https://coil-kt.github.io/coil/)
- **Asynchronous:** Kotlin Coroutines & Flow

---

## 🏗️ Project Structure

```text
com.surajvanshsv.cartify_ecomemerceapp/
├── di/                 # Hilt Modules (Dependency Providers)
├── model/              # Data models for Products, Categories, and Cart
├── repositories/       # Handles data logic between Firestore & Room
├── room/               # Room Database, DAOs, and Entities for local cart
├── screens/            # UI Composables (Home, Cart, ProductDetails, etc.)
├── utils/              # Helper classes (Firestore Initialization, etc.)
├── viewmodels/         # UI State management and business logic
└── MainActivity.kt      # Entry point of the application
```

---

## ⚙️ Installation & Run

1. **Clone this repo:**
   ```bash
   git clone https://github.com/surajpsk12/CartifyApp.git
   cd CartifyApp
   ```

2. **Open in Android Studio (Ladybug or newer recommended).**

3. **Firebase Setup:**
   - Create a project in the [Firebase Console](https://console.firebase.google.com/).
   - Add an Android app with the package name `com.surajvanshsv.cartify_ecomemerceapp`.
   - Download the `google-services.json` and place it in the `app/` directory.
   - Enable **Anonymous Authentication** and **Cloud Firestore**.

4. **Sync Project with Gradle Files and Run.**

---

## 🧪 Future Enhancements

* 💳 Integration of Payment Gateways (Stripe/Razorpay).
* 📦 Order Tracking and History.
* 🔔 Push Notifications for deals and order updates.
* 🌓 Support for Dark Mode and Dynamic Color.

---

## 🤝 Contribution

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change or improve.

---

## 📜 License

MIT © 2025 [Suraj Kumar](https://github.com/surajpsk12)

---

## 📬 Connect with Me

*   **Suraj Kumar**
*   **Email**: [sk658139@gmail.com](mailto:sk658139@gmail.com)
*   **LinkedIn**: [linkedin.com/in/surajvansh12](https://www.linkedin.com/in/surajvansh12/)
*   **GitHub**: [github.com/surajpsk12](https://github.com/surajpsk12)
