package ir.fallahpoor.ca.domain.interactor;

import java.util.List;

import io.reactivex.Observable;
import ir.fallahpoor.ca.domain.Category;
import ir.fallahpoor.ca.domain.executor.PostExecutionThread;
import ir.fallahpoor.ca.domain.executor.ThreadExecutor;
import ir.fallahpoor.ca.domain.repository.CategoryRepository;

public class GetFeaturedCategoriesUseCase extends UseCase<List<Category>, Void> {

    private static final int CATEGORIES_COUNT = 5;
    private CategoryRepository categoryRepository;

    public GetFeaturedCategoriesUseCase(CategoryRepository categoryRepository,
                                        ThreadExecutor threadExecutor,
                                        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.categoryRepository = categoryRepository;
    }

    @Override
    Observable<List<Category>> buildUseCaseObservable(Void unused) {
        return categoryRepository.getCategories(CATEGORIES_COUNT);
    }

}
