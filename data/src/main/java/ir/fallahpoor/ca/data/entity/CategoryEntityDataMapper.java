package ir.fallahpoor.ca.data.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import ir.fallahpoor.ca.domain.Category;

public class CategoryEntityDataMapper {

    @Inject
    CategoryEntityDataMapper() {
    }

    private Category transform(CategoryEntity categoryEntity) {

        Category category = null;

        if (categoryEntity != null) {
            category = new Category();
            category.setId(categoryEntity.getId());
            category.setName(categoryEntity.getName());
            category.setDescription(categoryEntity.getDescription());
        }

        return category;

    }

    public List<Category> transform(Collection<CategoryEntity> categoryEntityCollection) {

        final List<Category> categoryList = new ArrayList<>(20);

        for (CategoryEntity categoryEntity : categoryEntityCollection) {

            final Category category = transform(categoryEntity);

            if (category != null) {
                categoryList.add(category);
            }

        }

        return categoryList;

    }

}
