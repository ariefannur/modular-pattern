# Android Modular-pattern

### Architecture 
Use modular with clean achitecture 
1. `:core` module -> base class and common dependency helper
2. `:feature:search` module -> feature for search user github
2. `:feature:detail` module -> feature for display detail user and list repo of user

Each module split with 3 main data layer
1. data : remote and local data storage 
2. domain : UseCase for business logic 
3. presentation : Activity or fragment 

use MVVM pattern

  ```ditaa {cmd=true args=["-E"]}
  +--------+   +----------+    +----------+
  |        |-->+          +--> |          |
  | Model  |   |ViewModel |    | View     |
  |        |   |          |    |          |
  |        |<--|          |<-- |          |
  +---+----+   +----------+    +----------+
   
  ```
### Dependency
1. Coroutine
2. Hilt 
3. Glide
4. Room
5. Retrofit
6. OkHttp

### Test & build

cover unit test for DataSource, Repository and ViewModel
`./gradlew test`

`./gradlew build`

#### Git history
`git log`

#### Integrated with github action
use github action for CI (continuous integration) with private repository here https://github.com/ariefannur/modular-pattern/actions

##### WIP Integration test

Mock web server sample `DetailUserActivityTest.kt`

##### Integration with jenkins