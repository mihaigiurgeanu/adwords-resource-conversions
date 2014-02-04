(ns adwords-resource-conversions.Servlet
  "The servlet that serves the static resource, notifying Google"
  (:require [adwords-resource-conversions.core :as core]
            [ring.util.servlet :as ring-servlet])
  (:gen-class :extends javax.servlet.GenericServlet))

(defn -service [^javax.servlet.GenericServlet servlet
                ^javax.servlet.ServletRequest request
                ^javax.servlet.ServletResponse response]
  (let [google-notification-url (.. servlet (getServletConfig) (getInitParameter "google.notification.url"))
        static-handler (core/create-serve-static google-notification-url)]
    ((ring-servlet/make-service-method static-handler) servlet request response)))
