{:provided
 {:repositories
  [["releases"
    {:url "https://artifactory.wunderlist.io/artifactory/libs-release-local"
     :username :env/artifactory_user
     :password :env/artifactory_password
     :sign-releases false}]
   ["snapshots"
    {:url "https://artifactory.wunderlist.io/artifactory/libs-snapshot-local"
     :username :env/artifactory_user
     :password :env/artifactory_password
     :sign-releases false}]]}}
