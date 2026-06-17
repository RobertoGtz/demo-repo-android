# Gaia Agent Rules — demo-repo-android (Kotlin / Android)

> These rules are injected into every agent prompt. Follow them strictly when generating, modifying, or reviewing code in this repository.

---

## Architecture

This project follows **MVVM + Clean Architecture**. Layer responsibilities:

- `presentation/screens/` → Fragments own view lifecycle only. No business logic.
- `presentation/viewmodels/` → UI state via `StateFlow` or `LiveData`. Calls UseCases only.
- `domain/models/` → plain Kotlin data classes, zero Android imports.
- `domain/usecases/` → single-responsibility use cases. Call repositories via interfaces.
- `data/repositories/` → implements domain repository interfaces.
- `data/datasources/` → network/local data access. Never called from ViewModels directly.

---

## Code Rules

- Use **Kotlin Coroutines + Flow** for all async operations. Do **not** use RxJava, `AsyncTask`, or `Thread`.
- UI layer must use **Fragments with ViewBinding**. Do **not** use `kotlin-android-extensions` (synthetic), `DataBinding`, or `findViewById`.
- ViewModels must extend `ViewModel` or `AndroidViewModel`. No business logic in `Fragment` or `Activity`.
- Expose UI state as a **sealed class** (`Loading`, `Success`, `Error`) via `StateFlow` — never expose raw mutable state to the UI.
- All dependencies must be **injected via constructor** — no `object` singletons, no `companion object getInstance()`.
- ViewModels must call **UseCases**, not repositories directly.
- All `suspend` functions in repositories must be wrapped in `try/catch` and return `Result<T>`.
- No hardcoded strings in `.kt` files — use `res/values/strings.xml`.
- No hardcoded dimensions or colors — use `res/values/dimens.xml` and `res/values/colors.xml`.
- All new code must be **Kotlin** — do not create `.java` files.

---

## Test Rules

- Every new ViewModel must have JUnit tests in `app/src/test/`.
- Every new UseCase must have unit tests covering all execution branches.
- Test file naming: `{ClassName}Test.kt` — must mirror the source class name.
- Use **JUnit 4 or JUnit 5** — no third-party test runners.
- Use **MockK** for mocking — avoid manual fakes unless the class is trivially simple.
- Use `kotlinx-coroutines-test` for coroutine testing (`runTest`, `UnconfinedTestDispatcher`).
- Every test must cover: **loading state**, **success state**, and **error/exception state**.
- UI/integration tests (when required by the spec) go in `app/src/androidTest/` using Espresso.

---

## What NOT to do

- Do **not** modify `app/src/main/kotlin/com/demo/app/MainActivity.kt` — it is the Activity entry point.
- Do **not** modify `app/build.gradle.kts` or the root `build.gradle.kts` unless the task explicitly requires a new dependency.
- Do **not** introduce **Hilt** or **Dagger** unless the feature spec explicitly requires it.
- Do **not** use Java — all new files must be Kotlin.
- Do **not** commit `build/`, `.gradle/`, or `local.properties` files.
