# Hashing algorithm- open addressing
The concept here is the same as in previous algorithm, but the concept of solving collisions is different. <br />
When on designated place is an element, the algorithm determinates new position. There are 3 equations to do that:
* Linear- hash = (key % m + i) % m;
* Double- hash = (key % m + i * (1 + (key % (m - 3)))) % m
* Quadratic- hash = (key % m + a * i + b * i * i) % m

hash here is an next index, key is hash code of element, m is a length of hash table and i is a count if indexes. <br />
If there's no place in hash table, the table length is resized, and elements are putted inside again. <br />
Algorithm also reconsider question of searching and deleting. If element is deleted, the empty space should be replaced with special element to perform correct searching.
