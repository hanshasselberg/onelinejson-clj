(ns onelinejson.core-test
  (:require [clojure.test :refer :all]
            [midje.sweet :refer :all]
            [onelinejson.core :refer :all]))

(facts "about core/sanitize-headers"
       (fact "it keeps x-platform header"
             (sanitize-headers {"x-platform" "1"})
             => '(["x-platform" "1"]))

       (doseq [header ["cache-fubar" "connection" "version" "pragma"
                       "accept-language" "referer" "cookie" "authorization"
                       "x-access-token" "x-hidden-secret"]]
         (fact "it removes " + header
               (sanitize-headers {header "1"})
               => ())))

(facts "about core/wrap-logger"
       (fact "it returns response"
             (let [request 1
                   response 2
                   handler (fn [request] response)]
             ((wrap-logger handler) request)
             => response)))

; (facts "about core/log"
;        (fact "it prints json"
;              (log {"request" "fubar"})
;              => nil
;              (provided (println "{\"request\": \"fubar\"}") => nil)))
