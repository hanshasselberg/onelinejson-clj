(ns onelinejson.core
  (require [clojure.data.json :as json]))

(defn wrap-logger [handler]
  (fn [request]
    (let [start (System/currentTimeMillis)]
      (let [response (handler request)
            finish (System/currentTimeMillis)
            duration  (- finish start)
            data {
                  "debug_info" {}
                  "response" {
                              "status" (get response :status)
                              "duration" duration}
                  "request" {
                             "method" (get request :request-method)
                             "path" (get request :uri)
                             "headers" (get request :headers)
                             "params" (get request :query-string)
                             "ip" (get request :remote-addr)}
                  }]
        println (json/write-str data)
        response))))
