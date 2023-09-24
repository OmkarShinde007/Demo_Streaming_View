Create a user interface for the home screen of a streaming app on Android App

**Requirements:**

- Display a list of movies in a grid view.
- In portrait mode, each row of the grid view should contain 3 movie items, while in landscape mode, each row should contain 7 items.
- Implement a search option for the movie list that displays matching items.
- Highlight the names of the searched movies in the list.
- Utilize 3 JSON datasets for local data operations.
- Load each dataset sequentially as the user scrolls.
- Develop the app using Kotlin, Dagger for dependency injection, and follow the MVVM architecture.
- Use JSON data and images within the app.

**Supporting Components:**
- Create a `MainActivity` responsible for launching the `HomeMovieListFragment` and handling Material Toolbar events.
- Implement the `HomeMovieListFragment` to display the movie list using a RecyclerView, fetching data from local JSON and presenting it in a GridView.
- Create a `MovieListAdapter` to manage data for the RecyclerView and `MovieListViewHolder` to define the RecyclerView item views.
- Utilize a `MyViewModel` to manage and provide data to the corresponding views.
- Develop a `MyRepository` to fetch data from local JSON and provide it to the ViewModel.
- Introduce `MyApplication`, `AppComponent`, and `AppModule` for dependency injection. Use `AppComponent` to inject classes into activities and fragments.
- Define `MovieDataClasses` to hold and retrieve JSON data.
- Create two enums, `PosterImageValue` and `MovieListFileName`, to obtain file names.
- Apply the MVVM architecture and organize classes into their respective packages.

Pending :
- Implement functionality to load the next dataset when scrolling to the last element of the first dataset.

**Video :**
- [Demo Video](https://drive.google.com/file/d/18t9movThL8QonZXDhf7JCxtPB_AajK7Q/view?usp=drive_link)

Screenshots:
Portrait :
- [Home Screen](https://drive.google.com/file/d/1Nm2BFBfSmYzqTMKE4jLqKUBKhcsntfS6/view?usp=drive_link)
- [Search Screen](https://drive.google.com/file/d/18KAYTKsL9DzBDWA2R8nyXpjSbaluS_fV/view?usp=drive_link)
- [After Search](https://drive.google.com/file/d/1N__6oAAfPDfd4MoAUsSKVQmf-X4oRose/view?usp=drive_link) 

Landscape :
- [Home Screen](https://drive.google.com/file/d/1OFT1POj-ecWR2wjlwA_qbk19CuoBhhqm/view?usp=drive_link)
- [Search Screen](https://drive.google.com/file/d/1mEmRnczphQm8qKEljBuqYQb9wAbU-SAY/view?usp=drive_link)
- [After Search](https://drive.google.com/file/d/1yWhZbu2mQ1_0IIrWQ-zE9sVz06vJb5YZ/view?usp=drive_link)

APK : https://drive.google.com/file/d/1657RItkP1lUpw5VCy-I7KbFB-8OijQrv/view?usp=drive_link
AAB Bundle : https://drive.google.com/file/d/1HXBpiHMC6hqw5hXXR11M5X5GGL_--zpz/view?usp=drive_link

Additionally, provide a demo video and screenshots for both portrait and landscape modes, including the home screen, search screen, and a view after a search with no matching values. Lastly, mention that you have manually tested all the app's functionalities.
