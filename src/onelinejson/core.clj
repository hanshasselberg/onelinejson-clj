(ns onelinejson.core
  (require [clojure.data.json :as json]))

(defn sanitize-headers
  "Omits sensitive or noisy headers."
  [headers]
  (let [excludes #"^(cache-.*|connection|version|pragma|accept-language|referer|cookie|authorization|x-access-token|.*hidden.*)$"]
    (remove (fn [header] (re-matches excludes (key header))) headers)))

(defn data
  "Generates map conforming to the onelinejson format from request, response and duration to be printed later."
  [request response duration]
  { "debug_info" {}
   "response" { "status" (get response :status)
               "duration" duration}
   "request" { "method" (get request :request-method)
              "path" (get request :uri)
              "headers" (sanitize-headers (get request :headers))
              "params" (get request :query-string)
              "ip" (get request :remote-addr)}})

(defn log
  "Prints json to stdout."
  [message]
  (println (json/write-str message)))

(defn wrap-logger [handler]
  "Provides wrapper for ring"
  (fn [request]
    (let [start (System/currentTimeMillis)]
      (let [response (handler request)
            finish (System/currentTimeMillis)
            duration  (- finish start)]
        (log (data request response duration))
        response))))
