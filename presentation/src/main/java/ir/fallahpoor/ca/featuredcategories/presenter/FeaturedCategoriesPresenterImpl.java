package ir.fallahpoor.ca.featuredcategories.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;

import ir.fallahpoor.ca.domain.Category;
import ir.fallahpoor.ca.domain.interactor.DefaultObserver;
import ir.fallahpoor.ca.domain.interactor.GetFeaturedCategoriesUseCase;
import ir.fallahpoor.ca.featuredcategories.model.CategoryModelDataMapper;
import ir.fallahpoor.ca.featuredcategories.view.FeaturedCategoriesView;

public class FeaturedCategoriesPresenterImpl extends MvpBasePresenter<FeaturedCategoriesView>
        implements FeaturedCategoriesPresenter {

    private GetFeaturedCategoriesUseCase getFeaturedCategoriesUseCase;
    private CategoryModelDataMapper categoryModelDataMapper;

    public FeaturedCategoriesPresenterImpl(
            GetFeaturedCategoriesUseCase getFeaturedCategoriesUseCase,
            CategoryModelDataMapper categoryModelDataMapper) {
        this.getFeaturedCategoriesUseCase = getFeaturedCategoriesUseCase;
        this.categoryModelDataMapper = categoryModelDataMapper;
    }

    @Override
    public void getFeaturedCategories() {
        ifViewAttached(false, FeaturedCategoriesView::showLoading);
        getFeaturedCategoriesUseCase.execute(new FeaturedCategoriesObserver(), null);
    }

    private final class FeaturedCategoriesObserver extends DefaultObserver<List<Category>> {

        @Override
        public void onComplete() {
            ifViewAttached(false, FeaturedCategoriesView::hideLoading);
        }

        @Override
        public void onError(Throwable e) {
            ifViewAttached(false, view -> {
                view.hideLoading();
                view.error();
            });
        }

        @Override
        public void onNext(List<Category> categories) {
            ifViewAttached(false,
                    view -> view.renderCategories(categoryModelDataMapper.transform(categories)));
        }

    }

    @Override
    public void destroy() {
        super.destroy();
        getFeaturedCategoriesUseCase.dispose();
    }

}
