# Huffman encoding and decoding
Huffmans algorithm is a compression algorithm, which means, that this allows to limit memory complexity of file. <br />
To achieve that, algorithm uses a special tree- Huffmann tree. <br />
To build the thee, encoding count frequency of each letter. Every letter and frequency creates a node on a stack. Nodes are connected together wihth usage of special node that only has frequency. After that operation connected nodes are removed from stack, and new node is added. The process is repeated as long as one node is left on a stack. 
