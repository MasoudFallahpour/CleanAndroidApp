package ir.fallahpoor.ca.featuredcategories.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import ir.fallahpoor.ca.featuredcategories.view.FeaturedCategoriesView;

public interface FeaturedCategoriesPresenter extends MvpPresenter<FeaturedCategoriesView> {

    void getFeaturedCategories();

}
