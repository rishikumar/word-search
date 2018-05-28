# Word Search

This application allows you to search for words in a two-dimensional matrix of characters. When we use the term 'word', we refer to a sequence of connected characters, which can be linked either horizonally or vertically in the matrix.

This is best described with an illustration (see `data/matrix2.csv`). Given the following matrix: 

```
t    r    a    i    t
b    y    i    n    y
x    t    e    s    u
q    w    e    r    i
```

Some valid words in this matrix: 

- train 
- bytes
- tins
- eeia - this is a valid "word" in the context of this domain, even though its not a real word in english.

An a couple invalid word examples: 

- xter - this is not valid because you cannot diagonically link letters together
- zebra - not valid because the letter 'z' does not exist in the matrix (the rest of the letters aren't checked in this case)
- trart - not valid because you cannot reuse letters

## Assumptions

- We're only using lowercase letters
- No lookups are being made against a dictionary - any sequence of characters that can be linked together in the matrix is valid
- Diagonically links are not current supported, but it should be easy to add since its just an adjument to the distance computation.

## My Solution

The solution creates a map of the chracter elements to the list of positions they have in the matrix, i.e. an inverted index. The logic for processing an input word goes like this: 

- For each letter, get the list of positions that it occupies in the matrix. For example, 'i' in the example of occupies three positions (zero-based): `(0,3), (1, 2), (3,3) `
- As you process each letter build a set of potential paths for each word. A path consists of a sequence of position objects that are connected together by a distance of 1 position. 
  - Distance is computed by comparing the end of the path with a new position candidate. If the last path position and the new one are only `distance=1` apart, then its a candiate path that should be considered with the position. (The distance is computed by summing the absolute value of the differences of the row/column value differences between the positions). 
- As you process, remove paths that are not viable (do not have a continuous sequence of positions that are `distance=1` away.)
- If any paths remain after processing the complete word, you know you've found a match



## Running it

Building an uber jar to execute the code from the command line: 

```sh
gradle clean shadowJar
```

To execute the code from the command line you can do the following: 

```sh
java \
-jar build/libs/word-search-1.0-SNAPSHOT-all.jar \
--matrix-path=data/matrix2.csv \
--words=trait,bytes,tins,eeia,xter,zebra,trart
```

This will execute the example from the first section and produce the following output: 

```verilog
2018-05-28 12:06:01 INFO  WordSearch:33 - Input Matrix:
    t    r    a    i    t
    b    y    i    n    y
    x    t    e    s    u
    q    w    e    r    i

2018-05-28 12:06:01 INFO  WordSearch:34 - Input Words: [trait, bytes, tins, eeia, xter, zebra, trart]
2018-05-28 12:06:01 INFO  WordSearch:39 - Matching Words: [trait, bytes, tins, eeia]
```



## Creating Data Files

To create your own data files, you can copy one of the existing matrices in `/data` and make the changes to it. The data is stored in a csv format.  Here is the contents of `matrix2.csv`, from the example above. 

```
t,r,a,i,t
b,y,i,n,y
x,t,e,s,u
q,w,e,r,i
```

## Running Tests

Unit tests can be run by doing the following: 

```
gradle clean test
```

