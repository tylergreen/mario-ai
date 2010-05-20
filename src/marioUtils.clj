(ns marioUtils)

; this is needed to start the trial

(defmacro defs [& bindings]
  `(do ~@(map #(cons 'def %) (partition 2 bindings))))

(defmacro unless [p & body]
  `(when (not ~p) ~@body))

(def toggle (atom true))
(defn get-toggle []
  (if @toggle
    (reset! toggle false)
    (reset! toggle true)))

(def prev-move (atom []))

; control codes
(defs
  left   0
  right  1 
  down   2
  jump   3
  speed  4
  up     5
  pause  6 )

(defn press [button controller]
  (aset controller button true))

(defn release [button controller]
  (aset controller button false))

(defn pressing? [button controller]
  (nth @controller button))

; not idiomatic clojure but abstracts over what java want simply
(defn controller [] (make-array (. Boolean TYPE) 5))





