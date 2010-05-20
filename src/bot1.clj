; Mario AI Bot
; author: Tyler Green
; tyler.green2@gmai.com
(ns bot1
  (:gen-class
   :name bot1
   :implements [ch.idsia.ai.agents.Agent]
   :extends ch.idsia.ai.agents.human.HumanKeyboardAgent
   )
  (:import 
   [ch.idsia.ai.agents.human HumanKeyboardAgent]
   [ch.idsia.ai.agents Agent]
   [ch.idsia.mario.engine.sprites Mario]
   [ch.idsia.mario.environments Environment]
   [ch.idsia.utils MathX]
   )
  (:use :reload-all marioUtils)
  )

(defn -reset [this])

(defn -getName [this] "Clojure Bot")

(defn button-masher [obs]
  (let [c (controller)]
    (press right c)
    (when (get-toggle)  ; can change this to pressing?
      (do
	(press speed c)
	(when (. obs isMarioOnGround)
	  (press jump c))))
    c))

; can't figure out case statement
; render the observation in ascii
(defn pretty [code]
  (cond (= code (byte 0)) "_"
	true "|"))

; draw the scene
(defn print-matrix [matrix ypos]
  (doseq [[i x] (zipmap (range 0 300 5) matrix)]
    (do (doseq [[j y] (zipmap (range) x)]
	  (cond (and (> 5 (- i ypos) 0) (= j 11))
		(print "M")
		true (print (pretty y))))
	(println))))

(defn high-jumper [obs]
  (let [c (controller)
	pos (. obs getMarioFloatPos)]
    (print-matrix (. obs getLevelSceneObservationZ 0)
		  (aget pos 1))
    (println "NEXT")
    (press right c)
    (when (get-toggle)
      (press speed c))
    (unless (and (. obs isMarioOnGround)
		 (pressing? jump prev-move))
      (press jump c))
    (reset! prev-move (seq c))
    c))

(def env (atom nil))

(defn -integrateObservation [this obs]
  (reset! env obs))

(defn -getAction [this]
;  (button-masher obs)
  (high-jumper @env)
)

