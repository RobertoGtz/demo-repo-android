/**
 * Custom Reviewer agent — demo-repo-android (Kotlin / MVVM + Clean Architecture)
 * Loaded dynamically by PluginLoader. Uses GAIA_HARNESS_ROOT to resolve harness.
 */

const REVIEW_CHECKLIST = `
## PR Review Checklist — demo-repo-android (Kotlin / Android)

### Architecture compliance
- [ ] Fragments own view lifecycle only — zero business logic in Fragment or Activity.
- [ ] ViewModels expose UI state via StateFlow as sealed class (Loading / Success / Error).
- [ ] ViewModels call UseCases only — never repositories directly.
- [ ] UseCases are single-responsibility.
- [ ] Domain models are plain Kotlin data classes — zero Android imports.
- [ ] DataSources are NOT called directly from ViewModels.

### Code quality
- [ ] Kotlin Coroutines + Flow used — no RxJava, AsyncTask, or Thread.
- [ ] ViewBinding used — no kotlin-android-extensions, DataBinding, or findViewById.
- [ ] All dependencies injected via constructor — no singletons.
- [ ] All suspend functions in repositories wrapped in try/catch returning Result<T>.
- [ ] No hardcoded strings — res/values/strings.xml used.
- [ ] No hardcoded dimensions/colors — dimens.xml / colors.xml used.
- [ ] All new files are Kotlin — no .java files created.
- [ ] No Hilt/Dagger added unless spec required it.

### Test coverage
- [ ] Every new ViewModel has JUnit tests.
- [ ] Every new UseCase has unit tests covering all branches.
- [ ] Tests cover: loading state, success state, error/exception state.
- [ ] MockK used for mocking.
- [ ] kotlinx-coroutines-test used for coroutine testing.

### Forbidden files check
- [ ] MainActivity.kt NOT modified.
- [ ] app/build.gradle.kts NOT modified (unless task required new dependency).
- [ ] Root build.gradle.kts NOT modified.
`.trim();

const harnessRoot = process.env.GAIA_HARNESS_ROOT!;
const { ReviewerAgent } = require(`${harnessRoot}/dist/agents/reviewer`);

export default class AndroidReviewer extends ReviewerAgent {
  name = 'AndroidReviewer';

  async execute(context: any): Promise<any> {
    context.job.platform = 'android';
    context.extraPromptContext = REVIEW_CHECKLIST;
    return super.execute(context);
  }
}
