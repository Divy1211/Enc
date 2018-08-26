# Enc
1. I have coded an encryption algorithm in java, my code is a bit messy but i think it will be readable enough you can simply run the code by compiling the java file and then running it.
2. It is similar to the enigma machine's workings. if you find a bug you can open a pull request

# Prerequisities
1. You should have Java installed.

# How the program works

![Main](https://github.com/Divy1211/Enc/blob/master/images/Main.PNG)

1. Input an initial state into the top text field. The initial states consist of 3 numbers each being from 0 to 25 sperated by spaces for eg. 0 5 25 is a valid initial state while -1 5 26 is not depending on the initial state, the output may change so to correctly decrypt a message, inital state should be right.

2. Enter your text which you might want to encrypt or decrypt in the top text area. after clicking the button, encrypted text will appear in the bottom text area.

3. An example:

![E1](https://github.com/Divy1211/Enc/blob/master/images/s1.PNG)

4. If the initial state is changed, output is different

![E2](https://github.com/Divy1211/Enc/blob/master/images/s2.PNG)

5. To decrypt enter the encrypted text and make sure that the initial state is right (or message will be different!)

![Decryted](https://github.com/Divy1211/Enc/blob/master/images/s1d.PNG)
