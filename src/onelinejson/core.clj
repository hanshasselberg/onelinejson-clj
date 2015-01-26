(ns onelinejson.core
  (require [clojure.data.json :as json]))

(defn sanitize-headers
  [headers]
  (let [excludes #"^(cache-.*|connection|version|pragma|accept-language|referer|cookie|authorization|x-access-token|.*hidden.*)$"]
    (remove (fn [header] (re-matches excludes (key header))) headers)))

(defn log-data
  [request response duration]
  { "debug_info" {}
   "response" { "status" (get response :status)
               "duration" duration}
   "request" { "method" (get request :request-method)
              "path" (get request :uri)
              "headers" (sanitize-headers (get request :headers))
              "params" (get request :query-string)
              "ip" (get request :remote-addr)}})

(defn wrap-logger [handler]
  (fn [request]
    (let [start (System/currentTimeMillis)]
      (let [response (handler request)
            finish (System/currentTimeMillis)
            duration  (- finish start)]
        (json/write-str (log-data request response duration))
        response))))
