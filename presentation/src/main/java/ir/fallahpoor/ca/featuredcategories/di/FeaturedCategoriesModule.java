package ir.fallahpoor.ca.featuredcategories.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ir.fallahpoor.ca.UiThread;
import ir.fallahpoor.ca.data.entity.CategoryEntityDataMapper;
import ir.fallahpoor.ca.data.executor.JobExecutor;
import ir.fallahpoor.ca.data.repository.CategoryRepositoryImpl;
import ir.fallahpoor.ca.domain.executor.PostExecutionThread;
import ir.fallahpoor.ca.domain.executor.ThreadExecutor;
import ir.fallahpoor.ca.domain.interactor.GetFeaturedCategoriesUseCase;
import ir.fallahpoor.ca.domain.repository.CategoryRepository;
import ir.fallahpoor.ca.featuredcategories.model.CategoryModelDataMapper;
import ir.fallahpoor.ca.featuredcategories.presenter.FeaturedCategoriesPresenter;
import ir.fallahpoor.ca.featuredcategories.presenter.FeaturedCategoriesPresenterImpl;
import ir.fallahpoor.ca.featuredcategories.view.CategoriesAdapter;

@Module
public class FeaturedCategoriesModule {

    private Context context;

    public FeaturedCategoriesModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    CategoriesAdapter provideCategoriesAdapter(Context context) {
        return new CategoriesAdapter(context);
    }

    @Provides
    FeaturedCategoriesPresenter provideFeaturedCategoriesPresenter(
            GetFeaturedCategoriesUseCase getFeaturedCategoriesUseCase,
            CategoryModelDataMapper categoryModelDataMapper) {
        return new FeaturedCategoriesPresenterImpl(getFeaturedCategoriesUseCase,
                categoryModelDataMapper);
    }

    @Provides
    GetFeaturedCategoriesUseCase provideGetFeaturedCategoriesUseCase(
            CategoryRepository categoryRepository,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new GetFeaturedCategoriesUseCase(categoryRepository, threadExecutor,
                postExecutionThread);
    }

    @Provides
    CategoryRepository provideCategoryRepository(
            CategoryEntityDataMapper categoryEntityDataMapper) {
        return new CategoryRepositoryImpl(categoryEntityDataMapper);
    }

    @Provides
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    PostExecutionThread providePostExecutionThread(UiThread uiThread) {
        return uiThread;
    }

}
