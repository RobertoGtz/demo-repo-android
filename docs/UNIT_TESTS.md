# Unit Test Guide — demo-repo-android (Kotlin / JUnit)

> This document defines how unit tests must be written in this project.
> The agent must follow these patterns exactly when creating or modifying test files.

---

## Test Structure

Every test file must follow this structure:

```kotlin
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
class ExampleViewModelTest {

    // Mocks
    private val mockUseCase: GetExampleUseCase = mockk()

    // Subject under test
    private lateinit var sut: ExampleViewModel

    @Before
    fun setUp() {
        sut = ExampleViewModel(useCase = mockUseCase)
    }

    @Test
    fun `loadData emits Success state when use case returns data`() = runTest(UnconfinedTestDispatcher()) {
        // Arrange
        coEvery { mockUseCase() } returns Result.success(listOf("item1", "item2"))

        // Act
        sut.loadData()

        // Assert
        assertIs<UiState.Success<*>>(sut.uiState.value)
        coVerify(exactly = 1) { mockUseCase() }
    }

    @Test
    fun `loadData emits Error state when use case throws`() = runTest(UnconfinedTestDispatcher()) {
        // Arrange
        coEvery { mockUseCase() } returns Result.failure(Exception("network error"))

        // Act
        sut.loadData()

        // Assert
        assertIs<UiState.Error>(sut.uiState.value)
    }

    @Test
    fun `loadData emits Empty state when use case returns empty list`() = runTest(UnconfinedTestDispatcher()) {
        // Arrange
        coEvery { mockUseCase() } returns Result.success(emptyList())

        // Act
        sut.loadData()

        // Assert
        assertIs<UiState.Empty>(sut.uiState.value)
    }

    @Test
    fun `initial state is Loading`() {
        assertIs<UiState.Loading>(sut.uiState.value)
    }
}
```

---

## Naming Conventions

| What | Convention | Example |
|---|---|---|
| Test file | `{ClassName}Test.kt` | `HomeViewModelTest.kt` |
| Test method | Backtick sentence describing behavior | `` `loadData emits Error when network fails` `` |
| Mock variable | `mock{ClassName}` | `mockGetHomeUseCase` |

---

## Required Test Cases per Class

### ViewModel
- Initial `uiState` value is `Loading`
- Emits `Success` with correct data when use case succeeds
- Emits `Error` when use case returns `Result.failure`
- Emits `Empty` when use case returns an empty list
- Each public method is verified to call the correct use case exactly once

### UseCase
- Returns `Result.success` with correctly mapped data when repository succeeds
- Returns `Result.failure` with the expected exception when repository fails
- Does not call the repository more than once per invocation

### Repository
- Returns `Result.success` with mapped domain model when data source returns raw data
- Returns `Result.failure` wrapping the original exception when data source throws
- Calls the correct data source method

---

## Coroutine Testing Rules

- Always use `runTest(UnconfinedTestDispatcher())` — never `runBlocking`
- Replace `Dispatchers.IO` / `Dispatchers.Main` with a `TestDispatcher` injected via constructor
- Use `advanceUntilIdle()` only when testing time-based delays (e.g. debounce, retry backoff)
- Collect `StateFlow` values using `turbine` or `toList()` within `runTest` when testing emission sequences

---

## MockK Rules

- Use `mockk()` for interface mocks — never extend classes manually
- Use `coEvery { }` for suspending functions, `every { }` for regular functions
- Use `coVerify(exactly = N) { }` to assert call counts on suspend functions
- Use `slot<T>()` to capture arguments when you need to assert what was passed
- Use `relaxed = true` only on mocks where you don't care about any interaction

---

## UiState Sealed Class

All ViewModels must emit state via this sealed class pattern:

```kotlin
sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    object Empty   : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
```

Tests must use `assertIs<UiState.X>()` — never cast directly with `as`.

---

## Coverage Requirements

- **ViewModels**: all public methods + all `UiState` transitions
- **UseCases**: all `Result` branches
- **Repositories**: success path, failure path, empty response

---

## File Location

```
app/src/test/kotlin/com/demo/app/
├── presentation/
│   └── viewmodels/
│       └── HomeViewModelTest.kt
├── domain/
│   └── usecases/
│       └── GetHomeDataUseCaseTest.kt
└── data/
    └── repositories/
        └── HomeRepositoryTest.kt
```
