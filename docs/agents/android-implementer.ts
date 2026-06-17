/**
 * Custom Implementer agent — demo-repo-android (Kotlin / MVVM + Clean Architecture)
 * Loaded dynamically by PluginLoader. Uses GAIA_HARNESS_ROOT to resolve harness.
 */

const PROJECT_CONTEXT = `
## Project: demo-repo-android — Kotlin / Android / MVVM + Clean Architecture

### File placement rules
- Fragment:   app/src/main/kotlin/com/demo/app/presentation/screens/{Name}Fragment.kt
- ViewModel:  app/src/main/kotlin/com/demo/app/presentation/viewmodels/{Name}ViewModel.kt
- Layout:     app/src/main/res/layout/fragment_{name}.xml
- Model:      app/src/main/kotlin/com/demo/app/domain/models/{Name}.kt
- UseCase:    app/src/main/kotlin/com/demo/app/domain/usecases/{Name}UseCase.kt
- Repository: app/src/main/kotlin/com/demo/app/data/repositories/{Name}Repository.kt
- DataSource: app/src/main/kotlin/com/demo/app/data/datasources/{Name}DataSource.kt
- Test:       app/src/test/kotlin/com/demo/app/{feature}/{Name}Test.kt

### Code rules
- Kotlin Coroutines + Flow for ALL async. No RxJava, AsyncTask, or Thread.
- Fragments with ViewBinding only — no kotlin-android-extensions, DataBinding, or findViewById.
- ViewModels extend ViewModel or AndroidViewModel. No business logic in Fragment or Activity.
- Expose UI state as sealed class (Loading / Success / Error) via StateFlow.
- All dependencies injected via constructor — no object singletons, no companion object getInstance().
- ViewModels call UseCases only — never repositories directly.
- All suspend functions in repositories wrapped in try/catch returning Result<T>.
- No hardcoded strings in .kt files — use res/values/strings.xml.
- No hardcoded dimensions or colors — use dimens.xml and colors.xml.
- All new code must be Kotlin — do not create .java files.
- No Hilt or Dagger unless spec explicitly requires it.

### Test rules
- Every ViewModel → JUnit tests in app/src/test/.
- Every UseCase → unit tests covering all execution branches.
- Test naming: {ClassName}Test.kt.
- JUnit 4 or JUnit 5 — no third-party test runners.
- MockK for mocking.
- kotlinx-coroutines-test for coroutine testing (runTest, UnconfinedTestDispatcher).
- Every test covers: loading state, success state, error/exception state.

### Forbidden files — NEVER modify
- app/src/main/kotlin/com/demo/app/MainActivity.kt
- app/build.gradle.kts, build.gradle.kts
`.trim();

const harnessRoot = process.env.GAIA_HARNESS_ROOT!;
const { ImplementerAgent } = require(`${harnessRoot}/dist/agents/implementer`);

export default class AndroidImplementer extends ImplementerAgent {
  name = 'AndroidImplementer';

  async execute(context: any): Promise<any> {
    context.job.platform = 'android';
    context.extraPromptContext = PROJECT_CONTEXT;
    return super.execute(context);
  }
}
