(ns onelinejson.core-test
  (:require [clojure.test :refer :all]
            [midje.sweet :refer :all]
            [onelinejson.core :refer :all]))

(facts "about core/sanitize-headers"
       (fact "it keeps x-platform header"
             (sanitize-headers {"x-platform" "1"})
             => {"x-platform" "1"})

       (doseq [header [:cache-fubar :connection :version :pragma
                       :accept-language :referer :cookie :authorization
                       :x-access-token :x-hidden-secret]]
         (fact "it removes " + header
               (sanitize-headers {"cache-fubar" "1"})
               => {})))
