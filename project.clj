(defproject adwords-resource-conversions "0.1.1-SNAPSHOT"
  :description "Google adwords conversions for static resources."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring/ring-core "1.2.1"]
                 [ring/ring-servlet "1.2.1"]
                 [http-kit "2.1.16"]
                 [javax.servlet/servlet-api "2.5"]
                 [org.clojure/tools.logging "0.2.6"]]
  :aot [adwords-resource-conversions.Servlet])
