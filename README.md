# Diamond kata using Property Based Testing in Kotlin

A Kotlin implementation of the [Seb Rose's](https://twitter.com/sebrose) Diamond Kata that follows [ToF-'s Property Based Testing approach](https://github.com/ToF-/DiamondKata/blob/master/Haskell/Specs.hs) in Haskell. 

## Kata Description

Given a letter, print a diamond starting with 'A' with the supplied letter at the widest point.

### Examples
```
Input: A
Output: A

Input: B
Output:   A
         B B
          A

Input: C
Output:   A
         B B
        C   C
         B B
          A
```