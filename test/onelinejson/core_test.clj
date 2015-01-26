(ns onelinejson.core-test
  (:require [clojure.test :refer :all]
            [midje.sweet :refer :all]
            [onelinejson.core :refer :all]))

(fact "fubar"
    (is (= 0 1))
    => false)
