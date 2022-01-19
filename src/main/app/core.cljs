(ns app.core
  (:require [creact.core :as creact]))

(def element [:div {:id "foo"
                    :children [[:a {:children ["bar"]}]
                               [:b]]}])

(def container (js/document.getElementById "root"))

(defn initialize []
  (creact/render element container))
