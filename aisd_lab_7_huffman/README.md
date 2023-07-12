# Huffman encoding and decoding
Huffmans algorithm is a compression algorithm, which means, that this allows to limit memory complexity of file.

To achieve that, algorithm uses a special tree- Huffmann tree. <br />
To build the thee, encoding count frequency of each letter. Every letter and frequency creates a node on a stack. Nodes are connected together wihth usage of special node that only has frequency. After that operation connected nodes are removed from stack, and new node is added. The process is repeated as long as one node is left on a stack.

When assumed, that path to every left path is 0 and to right is 1, we can create codes with length strongly connected with frequency of sign. When numbers are written as bits, the coding gives compression. The tree must be written somewhere too (in my case in separate file).
To decode tree is read from file, and translated letter by letter.
