package ir.fallahpoor.ca.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ir.fallahpoor.ca.domain.Category;

public interface CategoryRepository {

    Observable<List<Category>> getCategories(int categoriesCount);

}
