A VERY simple Android app, a clean one!
=======================================

Once upon a time I was reading about Clean Architecture! After reading some articles and papers and grasping the theory of clean architecture I said to myself now it's time to see clean architecture in action.
I searched for apps that were implemented using clean architecture. I found some but I had a big problem with them: TOO complicated.
So I decided to create a VERY simple app that just calls a web service to get a bunch of data
items and displays them in a RecyclerView.

How this app is structured
==========================
It is consisted of 3 modules:
1) presentation: It's an Android module. All the stuff related to Android views are handled in this module. Fragments, Activities, Adapters etc are defined in this module.
It depends on `data` and `domain` modules.
2) data: It's an Android module. This module provides the data. For example connecting to web service is done
in this module. It depends on domain module.
3) domain: It's a pure java module that contains the business logic of the app. Use cases are defined in this module. This module knows nothing about other modules and so does not depend on any other module.

Disclaimer
==========
I've done my best to follow the principles of Clean Architecture. If you find any inconsistency with Clean
Architecture feel free to open an issue.

References
==========
[The Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)

[Android architecture](http://five.agency/android-architecture-part-1-every-new-beginning-is-hard/)

[Architecting Android...The clean way?](https://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)
