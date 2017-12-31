package ir.fallahpoor.ca.featuredcategories.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ir.fallahpoor.ca.domain.Category;

public class CategoryModelDataMapper {

    public List<CategoryModel> transform(List<Category> categoryCollection) {

        List<CategoryModel> categoryModelCollection;

        if (categoryCollection != null && !categoryCollection.isEmpty()) {

            categoryModelCollection = new ArrayList<>();

            for (Category category : categoryCollection) {
                categoryModelCollection.add(transform(category));
            }

        } else {
            categoryModelCollection = Collections.emptyList();
        }

        return categoryModelCollection;

    }

    private CategoryModel transform(Category category) {

        if (category == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        final CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(category.getId());
        categoryModel.setName(category.getName());
        categoryModel.setDescription(category.getDescription());

        return categoryModel;

    }

}
