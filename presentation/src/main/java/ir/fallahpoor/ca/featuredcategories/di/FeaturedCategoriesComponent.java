package ir.fallahpoor.ca.featuredcategories.di;

import dagger.Component;
import ir.fallahpoor.ca.featuredcategories.view.FeaturedCategoriesActivity;

@Component(modules = FeaturedCategoriesModule.class)
public interface FeaturedCategoriesComponent {
    void inject(FeaturedCategoriesActivity activity);
}
