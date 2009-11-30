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

(defn -reset [this])

(defn -getAction [this observation]
      (let [moves (make-array (. Boolean TYPE) 5)]
 	   (aset moves 1 true)
 	   moves))

;(defn -getType []
;  (Agent.AGENT_TYPE/AI))

(defn -getName
  ([this]  "clojure-bot"))


;;     public static final int KEY_LEFT = 0;
;;     public static final int KEY_RIGHT = 1;
;;     public static final int KEY_DOWN = 2;
;;     public static final int KEY_JUMP = 3;
;;     public static final int KEY_SPEED = 4;
;;     public static final int KEY_UP = 5;
;;     public static final int KEY_PAUSE = 6;
;;     public static final int KEY_DUMP_CURRENT_WORLD = 7;
;;     public static final int KEY_LIFE_UP = 8;
;;     public static final int KEY_WIN = 9;

; to compile bot:
; cd ~/cs/myclojure/marioai

; Start clojure with proper classpath
; java -cp /Users/jorge/cs/marioai/src/ch/idsia/ai/agents:/Users/jorge/cs/clojure/clojure.jar:bin clojure.main $1

; (set! *compile-path* "/Users/jorge/cs/myclojure/marioai/bin")

; (compile 'bot1)


;  To run compiled bot:
; java -cp bin:/Users/jorge/cs/marioai/classes:/Users/jorge/cs/clojure/clojure.jar ch.idsia.scenarios.Play bot1

; Be sure to put CLASSES in cp not SRC!!
