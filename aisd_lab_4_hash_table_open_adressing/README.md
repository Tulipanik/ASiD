# Hashing algorithm- open addressing
The concept here is the same as in previous algorithm, but the concept of solving collisions is different. <br />
When on designated plase is an element, the algorithm determinates new position. There are 3 equations to do that:
* Linear- hash = (key % m + i) % m;
* Double- hash = (key % m + i * (1 + (key % (m - 3)))) % m
