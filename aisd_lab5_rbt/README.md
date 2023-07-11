# Red-Black tree
This tree is a type of self-balancing binary search tree. The structure keeps it's balance by giving color to every node and following some rules:
* root is always black
* every leaf is black (nill node)
* new added node is always red
* red node cannot have red childen
* red height is the same for every path from root to leaf
To follow this rules, tree performs to types of operations:
* rotations- if node has one red node, and this node has red node, there is time for rotation. In this case red child of first root becomming a root of subtree. The first root becomes left or right child (depends if we perform left or right rotation). The inside child of new route becomes the inside child of old root.
* 
