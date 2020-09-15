# Cryptography
Write the following computer programs to simulate a mini data encryption system
(MDES). Since it is just a simulation, the bit and the binary strings in the following can
be expressed as integers 0 and 1.
1. Let Z32 corresponding to 26 English characters, space, dot (.), comma (,), question
mark (?) and two brackets (left bracket and right bracket). So the following corre-
spondence are used.
: ; ? ( )
26 27 28 29 30 31
Each element in Z32 can be expressed as a 5-bit string. Write a program to translate
English text to binary strings according to the above correspondence. Also write a
program to do the inverse.
2. Write a program to implement a function f as follows. The inputs of the function
are a bitstring B of length 8 and a round key K which is a bitstring of length 12.
First, B is expanded into 12 bits in such a way that the bits in the even positions
(from left to right) are appended to the end of the string. (For example, 10110101 is
expanded to 101101010111.) Then the expanded string is XORed with the round key
K. The resulting 12-bit is split into two parts B1 and B2 such that each part has 6
bits. Finally, B1 passes through S-box S1 and B2 passes through S2 (using a method
similar to that in DES). The output of f is a 8-bit string obtained from the output
of two S-boxes. The S-boxes are as follows.
S1 =
15 1 8 14 6 11 3 4 9 7 2 13 12 0 5 10
3 13 4 7 15 2 8 14 12 0 1 10 6 9 11 5
0 14 7 11 10 4 13 1 5 8 12 6 9 3 2 15
13 8 10 1 3 15 4 2 11 6 7 12 0 5 14 9

and
S2 =
7 13 14 3 0 6 9 10 1 2 8 5 11 12 4 15
13 8 11 5 6 15 0 3 4 7 2 12 1 10 14 9
10 6 9 0 12 11 7 13 15 1 3 14 5 2 8 4
3 15 0 6 10 1 13 8 9 4 5 11 12 7 2 14
3. Write a program of a encryption system as follows. First use the program in 1 to
translate the English text to binary strings. Then divide the plaintext (binary strings)
into blocks of 16 bitstrings. Each block is divided into L0 and R0 where L0 comprises
the rst 8 bits and R0 the last 8 bits. Then use the following iterations for 1  i  2,
where f is dened in 2:
Li = Ri􀀀1
Ri = Li􀀀1  f(Ri􀀀1;Ki):
The key is a 24-bit binary string. The rst round key uses the rst 12 bits and the
second round key uses the last 12 bits. The output (ciphertext block) is L2R2.
4. Suppose the plaintext is \how do you like computer science" and the key is
f101101010010100101101011g:
Use the above programs to compute the ciphertext. The ciphertext is written in
English letters (from the correspondence described in 1.).
