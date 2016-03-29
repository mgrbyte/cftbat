(defproject cftbat "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main ^:skip-aot cftbat.core
  :target-path "target/%s"
  :plugins [[lein-environ "1.0.0"]]
  :env {:squiggly {:checkers [:eastwood]
                   :eastwood-exclude-linters [:unlimited-use]}}
  :profiles {:uberjar {:aot :all}}
  :resource-paths ["dev-resources" "resources"])
