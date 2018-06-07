package ir.fallahpoor.ca.featuredcategories.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.reactivex.disposables.Disposable;
import ir.fallahpoor.ca.domain.interactor.GetFeaturedCategoriesUseCase;
import ir.fallahpoor.ca.featuredcategories.model.CategoryModelDataMapper;
import ir.fallahpoor.ca.featuredcategories.view.FeaturedCategoriesView;

public class FeaturedCategoriesPresenterImpl extends MvpBasePresenter<FeaturedCategoriesView>
        implements FeaturedCategoriesPresenter {

    private GetFeaturedCategoriesUseCase getFeaturedCategoriesUseCase;
    private CategoryModelDataMapper categoryModelDataMapper;
    private Disposable disposable;

    public FeaturedCategoriesPresenterImpl(
            GetFeaturedCategoriesUseCase getFeaturedCategoriesUseCase,
            CategoryModelDataMapper categoryModelDataMapper) {
        this.getFeaturedCategoriesUseCase = getFeaturedCategoriesUseCase;
        this.categoryModelDataMapper = categoryModelDataMapper;
    }

    @Override
    public void getFeaturedCategories() {

        ifViewAttached(view -> {
            view.hideRetry();
            view.showLoading();
        });

        disposable = getFeaturedCategoriesUseCase.execute(null)
                .subscribe(
                        categories ->
                                ifViewAttached(
                                        view -> {
                                            view.hideLoading();
                                            view.hideRetry();
                                            view.renderCategories(categoryModelDataMapper.transform(categories));
                                        }),
                        throwable ->
                                ifViewAttached(view -> {
                                    view.hideLoading();
                                    view.showRetry();
                                }));
    }

    @Override
    public void destroy() {
        super.destroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
