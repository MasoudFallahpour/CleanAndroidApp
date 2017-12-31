A VERY simple Android app, a clean one!
====

Once upon a time I was reading about Clean Architecture! After reading some articles and papers and grasping the theory of clean architecture I said to myself now it's time to see clean architecture in action.
I searched for apps that were implemented using clean architecture. I found some but I had a big problem with them: they were TOO complicated.
So I decided to create a VERY simple app that just calls a web service to get a bunch of data
items and displays them in a RecyclerView.

Structure
====
The app has 3 modules (layers):
1. presentation: Stuff related to handling Android views are defined in this module. Some notes about this module:
- It's an Android module
- It depends on `data` and `domain` modules.
- It uses MVP
2. data: This module is the data provider. Things like connecting to web services is done
in this module. It depends on `domain` module.
3. domain: It's a pure java module that contains the business logic of the app. Use cases are defined in this module. This module knows nothing about other modules hence doesn't depend on aforementioned modules.

Workflow
====
The entry point of app is `FeaturedCategoriesActivity`. In method `onCreate` of `FeaturedCategoriesActivity` method `getFeaturedCategories` of `FeaturedCategoriesPresenter` is called to get the list of categories.

`FeaturedCategoriesPresenterImpl`, which implements `FeaturedCategoriesPresenter`, has two components: `GetFeaturedCategoriesUseCase` and `CategoryModelDataMapper`. `presentation` layer becomes dependent on `domain` layer because of `GetFeaturedCategoriesUseCase`.
`FeaturedCategoriesPresenterImpl` doesn't know how categories are obtained. It just knows to ask `GetFeaturedCategoriesUseCase` for categories. `GetFeaturedCategoriesUseCase` returns a `List<Category>` but our view expects a `List<CategoryModel>`. Because of that we use `CategoryModelDataMapper` to map from `List<Category>` to `List<CategoryModel>`. In this way we have a better decoupling between `presentation` and `domain` layers. 

We create a new instance of `GetFeaturedCategoriesUseCase` in `presentation` layer. In order to create this new instance we need an implementation of `CategoryRepository` (namely `CategoryRepositoryImpl`) which is defined in `data` layer. This cause `presentation` layer to depend on `data` layer.

`CategoryRepositoryImpl` is the actual place where connecting to web service is done. In this case I used Retrofit for handling REST API calls. `CategoryRepositoryImpl` obtains a `List<CatagoryEntity>` but `GetFeaturedCategoriesUseCase` of `domain` layer needs a `List<Category>`. A mapper is used to map from `List<CatagoryEntity>` to `List<Category>`.

In a nutshell the workflow of obtaining the list of categories is as follows:
1. `FeaturedCategoriesActivity` calls `FeaturedCategoriesPresenter.getFeaturedCategories()`
2. `FeaturedCategoriesPresenter` calls `GetFeaturedCategoriesUseCase.execute()`
3. `GetFeaturedCategoriesUseCase` calls `CategoryRepository.getCategories()`

Now that we have the list of categories (our data), `CategoryRepository` passes the data to `GetFeaturedCategoriesUseCase`, `GetFeaturedCategoriesUseCase` passes the data to `FeaturedCategoriesPresenter` and finally `FeaturedCategoriesPresenter` passes the data to `FeaturedCategoriesActivity`. It's now `FeaturedCategoriesActivity`'s responsibility to display the data to user.

Disclaimer
====
I've done my best to follow the principles of Clean Architecture. If you find any inconsistency with Clean
Architecture feel free to open an issue.

References
====
[The Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)

[Android architecture](http://five.agency/android-architecture-part-1-every-new-beginning-is-hard/)

[Architecting Android...The clean way?](https://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)
