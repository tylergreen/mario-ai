**HOW TO COMPILE**
cd ~/cs/clojure/marioai

Start clojure with proper classpath
java -cp /Users/jorge/cs/marioai/classes:/Users/jorge/cs/clojure/clojure-core/clojure.jar:bin:src clojure.main $1

or run ./mclj

(set! *compile-path* "path-to-bin/bin")

(compile 'bot1)

**HOW TO RUN**
To run compiled bot: (in marioai dir)
java -cp
bin:/Users/jorge/cs/marioai/classes:/Users/jorge/cs/clojure/clojure-core/clojure.jar
ch.idsia.scenarios.Play bot1

or ./runm

Be sure to put CLASSES in cp not SRC!!

**TO DO**
next steps:
1) Now that we have observation, we want to figure out where
 we currently are in that observation

2) Once we know where we are, want to predict where we will be

