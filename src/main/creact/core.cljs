(ns creact.core)

(defn node-type [element _]
  (if (string? element)
    :string
    :element))

(defmulti render node-type)

(defmethod render :string
  [text container]
  (->> (js/document.createTextNode text)
    (.appendChild container)))

(defmethod render :element
  [[type props] container]
  (let [dom (js/document.createElement (name type))]
    (when props
      (doseq [[prop-name prop-val] (dissoc props :children)]
        (aset dom (name prop-name) prop-val))
      (doseq [child (:children props)]
        (render child dom)))
    (.appendChild ^js/HTMLElement container dom)))