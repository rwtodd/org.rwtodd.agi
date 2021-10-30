# AGI-Tools

This is a set of clojure tools for exploring Sierra AGI
adventure game resources.

*Update 2021-10-19*: Since I wrote the original clojure code, I have
since written more comprehensive resource extractors in Go and java
(both in other branches of this repo), and also one in C# (in another
private repo).  **Now,** I am going to rewrite the clojure code and
hopefully make the ultimate version.

I've tagged the last commit of the old clojure version with the tag
`old-clojure` to make the old files easier to find.

## Extractor Utility

The extractor can be run from deps.edn:

~~~~~~ bash
$ clj -M:extract -- --help
$ clj -M:extract -- /path/to/game --info --words
~~~~~~

... but I'll probaby get it packaged into an uberjar at some point.

## Basic Library Use

### Resources
The resource library is easy to use at a high level.

~~~~~~ clojure
(require [org-rwtodd.agi.resources :as r])

(def game (r/load-game-info "path-to-game"))
(load-sound game 21)
~~~~~~
