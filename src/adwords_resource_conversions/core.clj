(ns adwords-resource-conversions.core
  "Handler that servers a static resource and notifies Google adwords conversion tracking about this."
  (:require [ring.middleware
             [resource :as res]
             [content-type :as ct]
             [not-modified :as nm]]
            [ring.util.response :as rsp]
            [org.httpkit.client :as http]
            [clojure.tools.logging :as log]))

(defn not-found-handler [request]
  (rsp/not-found "<h1>404 - File not found</h1>"))

(defn create-serve-static
  "Creates a Ring handler that serves a static resource and notifies Google."
  [google-notification-url]
  (fn [request]
    (let [incoming-headers (:headers request)
          google-options {:as :text, :headers (dissoc incoming-headers "host")}
          google-response (http/get google-notification-url google-options)
          response ((-> not-found-handler 
                      (res/wrap-resource "public")
                      (ct/wrap-content-type)
                      (nm/wrap-not-modified)) request)]
      (log/debug "Original request headers: " (pr-str incoming-headers))
      (log/debug "google-request-options: " (pr-str google-options))
      (log/debug "Google request returned status " (:status @google-response))
      (log/debug "Google request returned body <<" (:body @google-response) ">>")
      response)))

