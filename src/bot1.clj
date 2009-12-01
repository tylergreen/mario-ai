(ns bot1
    (:gen-class
     :name bot1
     :implements [ch.idsia.ai.agents.Agent]
     )
    (:import 
     [ch.idsia.ai.agents Agent]
     [ch.idsia.mario.engine.sprites Mario]
     [ch.idsia.mario.environments Environment]
     [ch.idsia.utils MathX])
    )

(defmacro defs [& bindings]
  `(do ~@(map #(cons 'def %) (partition 2 bindings))))

(defmacro unless [p & body]
  `(when (not ~p) ~@body))

(defn -reset [this])

(def toggle (atom true))
(defn get-toggle []
  (if @toggle
    (reset! toggle false)
    (reset! toggle true)))

(def prev-move (atom []))

(defs
  left   0
  right  1 
  down   2
  jump   3
  speed  4
  up     5
  pause 6 )

(defn press [button controller]
  (aset controller button true))

(defn release [button controller]
  (aset controller button false))

(defn pressing? [button controller]
  (nth @controller button))

(defn button-masher [obs]
  (let [control (make-array (. Boolean TYPE) 5)]
    (press right control)
    (when (get-toggle)
      (do
	(press speed control)
	(when (. obs isMarioOnGround)
	  (press jump control))))
    control))

; has completed a whole level! Twice!  Unbelievable
(defn high-jumper [obs]
  (let [control (make-array (. Boolean TYPE) 5)]
    (press right control)
    (when (get-toggle)
      (press speed control))
    (unless (and (. obs isMarioOnGround) (pressing? jump prev-move))
      (press jump control))
    (reset! prev-move (seq control))
    control))

(defn -getAction [this obs]
;  (button-masher obs)
  (high-jumper obs))

(defn -getName [this] "Clojure Bot")
  
; to compile bot:
; cd ~/cs/myclojure/marioai

; Start clojure with proper classpath
; java -cp /Users/jorge/cs/marioai/classes:/Users/jorge/cs/clojure/clojure-core/clojure.jar:bin:src clojure.main $1

; (set! *compile-path* "/Users/jorge/cs/clojure/marioai/bin")

; (compile 'bot1)


;  To run compiled bot: (in myclojure/marioai)
; java -cp bin:/Users/jorge/cs/marioai/classes:/Users/jorge/cs/clojure/clojure-core/clojure.jar ch.idsia.scenarios.Play bot1

; Be sure to put CLASSES in cp not SRC!!

