package ir.fallahpoor.ca.featuredcategories.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

import ir.fallahpoor.ca.featuredcategories.model.CategoryModel;

public interface FeaturedCategoriesView extends MvpView {

    void showLoading();

    void hideLoading();

    void showRetry();

    void hideRetry();

    void renderCategories(List<CategoryModel> categories);

}
