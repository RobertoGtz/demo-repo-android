/**
 * Custom SpecAuthor agent — demo-repo-android (Kotlin / MVVM + Clean Architecture)
 * Loaded dynamically by PluginLoader. Uses GAIA_HARNESS_ROOT to resolve harness.
 */

const PROJECT_CONTEXT = `
## Project: demo-repo-android — Kotlin / Android / MVVM + Clean Architecture

### Architecture layers (MUST respect)
- presentation/screens/    → Fragments with ViewBinding. View lifecycle only. No business logic.
- presentation/viewmodels/ → UI state via StateFlow or LiveData. Calls UseCases only.
- domain/models/           → plain Kotlin data classes. Zero Android imports.
- domain/usecases/         → single-responsibility. Call repositories via interfaces.
- data/repositories/       → implements domain repository interfaces.
- data/datasources/        → network/local access. Never called from ViewModels directly.

### File path patterns
- Fragment:   app/src/main/kotlin/com/demo/app/presentation/screens/{Name}Fragment.kt
- ViewModel:  app/src/main/kotlin/com/demo/app/presentation/viewmodels/{Name}ViewModel.kt
- Layout:     app/src/main/res/layout/fragment_{name}.xml
- Model:      app/src/main/kotlin/com/demo/app/domain/models/{Name}.kt
- UseCase:    app/src/main/kotlin/com/demo/app/domain/usecases/{Name}UseCase.kt
- Repository: app/src/main/kotlin/com/demo/app/data/repositories/{Name}Repository.kt
- DataSource: app/src/main/kotlin/com/demo/app/data/datasources/{Name}DataSource.kt
- Test:       app/src/test/kotlin/com/demo/app/{feature}/{Name}Test.kt

### Naming conventions
- Classes/Files: PascalCase | Variables: camelCase
- Constants: SCREAMING_SNAKE_CASE in companion object
- Packages: lowercase.dot.separated

### Spec requirements
- Every Fragment task MUST include a ViewModel task and a JUnit test task.
- Every UseCase task MUST include unit tests covering all execution branches.
- UI state exposed as sealed class (Loading / Success / Error) via StateFlow.
- Coroutines + Flow for all async — no RxJava, AsyncTask, or Thread.
- Fragments use ViewBinding — no kotlin-android-extensions, DataBinding, or findViewById.
- ViewModels call UseCases only — never repositories directly.
- All suspend functions in repositories wrapped in try/catch returning Result<T>.
- No hardcoded strings — use res/values/strings.xml.
- No hardcoded dimensions/colors — use dimens.xml / colors.xml.
- All new code in Kotlin — no .java files.

### Forbidden files — NEVER spec tasks that touch these
- app/src/main/kotlin/com/demo/app/MainActivity.kt
- app/build.gradle.kts, build.gradle.kts
`.trim();

const harnessRoot = process.env.GAIA_HARNESS_ROOT!;
const { SpecAuthorAgent } = require(`${harnessRoot}/dist/agents/spec-author`);

export default class AndroidSpecAuthor extends SpecAuthorAgent {
  name = 'AndroidSpecAuthor';

  async execute(context: any): Promise<any> {
    context.job.platform = 'android';
    context.extraPromptContext = PROJECT_CONTEXT;
    return super.execute(context);
  }
}
