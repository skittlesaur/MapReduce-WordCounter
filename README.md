# Word Counter using MapReduce pattern

An example of how MapReduce would work in a word counter program in Java.

### How does it work?
1. The input file gets splitted into lines.
2. The lines are then splitted into words based on a regex specified in the Config.java file.
3. The work is distributed between the machines, each getting almost an equal amount of words to map.
4. After the words have been mapped to a value, they are grouped based on their keys.
5. The reduce function gets called after and groups the pairs into a smaller set.
6. The results are written to a text file.


This project is for the Distributed & Web-based Systems course.
