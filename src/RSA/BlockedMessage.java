/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

package RSA;

/**
 * Converts strings to blocked format and vice versa
 */
public class BlockedMessage
{
    /**
     * Internally, the message is stored as a string and converted to block
     * format only on request.
     * 
     * This approach makes it easier to append variable-length strings which
     * don't align with the block size.
     */
    private String message;
    
    /**
     * Specifies the size of each block in characters
     */
    private int blockSize;
    
    public BlockedMessage(int blockSize)
    {
        this.blockSize = blockSize;
    }
    
    public BlockedMessage(String ascii, int blockSize)
    {
        this(blockSize);
        message = ascii;
    }
    
    public BlockedMessage(BigInt[] blocks, int blockSize)
    {
        this(blockSize);
        // TODO implement this
    }
    
    
    
    /**
     * Get the number of ASCII characters in the message
     * 
     * @return  number of ASCII characters
     */
    public int count()
    {
        return message.length();
    }
    
    /**
     * Get the number of blocks used to compose the message
     * 
     * @return  number of blocks
     */
    public int countBlocks()
    {
        // TODO implement this
        return 0;
    }
    
    /**
     * Append a string to the message
     * 
     * @param ascii 
     */
    public void append(String ascii)
    {
        message += ascii;
    }
    
    /**
     * Append a pre-encoded block to the message
     * 
     * @param block 
     */
    public void append(BigInt block)
    {
        // TODO implement this
    }
    
    /**
     * Get a single block of the message
     * 
     * @param index     0 <= index < countBlocks()
     * @return          Encoded block at index
     */
    public BigInt getBlock(int index)
    {
        // TODO implement this
        
        // asciiToBlock(message.substring(begin, end));
        
        return null;
    }
    
    /**
     * Get the entire message encoded as an array of blocks
     * 
     * @return  Array of blocks
     */
    public BigInt[] getBlocks()
    {
        BigInt[] blocks = new BigInt[count()];
        
        for(int i = 0; i < blocks.length; ++i)
        {
            blocks[i] = getBlock(i);
        }
        
        return blocks;
    }
    
    /**
     * Get the message decoded as ASCII text
     * 
     * @return  decoded ASCII message
     */
    public String getAscii()
    {
        return message;
    }
    
    
    
    /**
     * Encode a small ASCII string as a block
     * 
     * @param ascii     ASCII text to encode
     * @return          ASCII text encoded as block
     */
    private static BigInt encodeBlock(String ascii)
    {
        // TODO implement this
        return null;
    }
    
    /**
     * Decode a single block back to ASCII format
     * 
     * @param block     encoded message block to decode
     * @return          decoded ASCII text of block
     */
    private static String decodeBlock(BigInt block)
    {
        // TODO implement this
        return null;
    }
    
    /**
     * Map a single character to an integer value
     * 
     * @param c     Character to encode
     * @return      Integer representation of character
     * @throws Exception 
     */
    private static int encodeChar(char c) throws Exception
    {
        switch(c)
        {
            case '\0': return 0;
            case (char)11: return 1;
            case '\t': return 2;
            case '\n': return 3;
            case '\r': return 4;
            case ' ': return 5;
            case '!': return 6;
            case '"': return 7;
            case '#': return 8;
            case '$': return 9;
            case '%': return 10;
            case '&': return 11;
            case '\'': return 12;
            case '(': return 13;
            case ')': return 14;
            case '*': return 15;
            case '+': return 16;
            case ',': return 17;
            case '-': return 18;
            case '.': return 19;
            case '/': return 20;
            case '0': return 21;
            case '1': return 22;
            case '2': return 23;
            case '3': return 24;
            case '4': return 25;
            case '5': return 26;
            case '6': return 27;
            case '7': return 28;
            case '8': return 29;
            case '9': return 30;
            case ':': return 31;
            case ';': return 32;
            case '<': return 33;
            case '=': return 34;
            case '>': return 35;
            case '?': return 36;
            case '@': return 37;
            case 'A': return 38;
            case 'B': return 39;
            case 'C': return 40;
            case 'D': return 41;
            case 'E': return 42;
            case 'F': return 43;
            case 'G': return 44;
            case 'H': return 45;
            case 'I': return 46;
            case 'J': return 47;
            case 'K': return 48;
            case 'L': return 49;
            case 'M': return 50;
            case 'N': return 51;
            case 'O': return 52;
            case 'P': return 53;
            case 'Q': return 54;
            case 'R': return 55;
            case 'S': return 56;
            case 'T': return 57;
            case 'U': return 58;
            case 'V': return 59;
            case 'W': return 60;
            case 'X': return 61;
            case 'Y': return 62;
            case 'Z': return 63;
            case '[': return 64;
            case '\\': return 65;
            case ']': return 66;
            case '^': return 67;
            case '_': return 68;
            case '`': return 69;
            case 'a': return 70;
            case 'b': return 71;
            case 'c': return 72;
            case 'd': return 73;
            case 'e': return 74;
            case 'f': return 75;
            case 'g': return 76;
            case 'h': return 77;
            case 'i': return 78;
            case 'j': return 79;
            case 'k': return 80;
            case 'l': return 81;
            case 'm': return 82;
            case 'n': return 83;
            case 'o': return 84;
            case 'p': return 85;
            case 'q': return 86;
            case 'r': return 87;
            case 's': return 88;
            case 't': return 89;
            case 'u': return 90;
            case 'v': return 91;
            case 'w': return 92;
            case 'x': return 93;
            case 'y': return 94;
            case 'z': return 95;
            case '{': return 96;
            case '|': return 97;
            case '}': return 98;
            case '~': return 99;
                
            default: 
            throw new IllegalArgumentException("No mappting for character" + c);
        }
    }
    
    /**
     * Map an integer to a character; inverse function of encodeChar()
     * 
     * @param value     integer to map to character
     * @return          character that the value represents
     * @throws Exception 
     */
    private static char decodeChar(int value) throws Exception
    {
        switch(value)
        {
            case 0: return '\0';
            case 1: return (char)11;
            case 2: return '\t';
            case 3: return '\n';
            case 4: return '\r';
            case 5: return ' ';
            case 6: return '!';
            case 7: return '"';
            case 8: return '#';
            case 9: return '$';
            case 10: return '%';
            case 11: return '&';
            case 12: return '\'';
            case 13: return '(';
            case 14: return ')';
            case 15: return '*';
            case 16: return '+';
            case 17: return ',';
            case 18: return '-';
            case 19: return '.';
            case 20: return '/';
            case 21: return '0';
            case 22: return '1';
            case 23: return '2';
            case 24: return '3';
            case 25: return '4';
            case 26: return '5';
            case 27: return '6';
            case 28: return '7';
            case 29: return '8';
            case 30: return '9';
            case 31: return ':';
            case 32: return ';';
            case 33: return '<';
            case 34: return '=';
            case 35: return '>';
            case 36: return '?';
            case 37: return '@';
            case 38: return 'A';
            case 39: return 'B';
            case 40: return 'C';
            case 41: return 'D';
            case 42: return 'E';
            case 43: return 'F';
            case 44: return 'G';
            case 45: return 'H';
            case 46: return 'I';
            case 47: return 'J';
            case 48: return 'K';
            case 49: return 'L';
            case 50: return 'M';
            case 51: return 'N';
            case 52: return 'O';
            case 53: return 'P';
            case 54: return 'Q';
            case 55: return 'R';
            case 56: return 'S';
            case 57: return 'T';
            case 58: return 'U';
            case 59: return 'V';
            case 60: return 'W';
            case 61: return 'X';
            case 62: return 'Y';
            case 63: return 'Z';
            case 64: return '[';
            case 65: return '\\';
            case 66: return ']';
            case 67: return '^';
            case 68: return '_';
            case 69: return '`';
            case 70: return 'a';
            case 71: return 'b';
            case 72: return 'c';
            case 73: return 'd';
            case 74: return 'e';
            case 75: return 'f';
            case 76: return 'g';
            case 77: return 'h';
            case 78: return 'i';
            case 79: return 'j';
            case 80: return 'k';
            case 81: return 'l';
            case 82: return 'm';
            case 83: return 'n';
            case 84: return 'o';
            case 85: return 'p';
            case 86: return 'q';
            case 87: return 'r';
            case 88: return 's';
            case 89: return 't';
            case 90: return 'u';
            case 91: return 'v';
            case 92: return 'w';
            case 93: return 'x';
            case 94: return 'y';
            case 95: return 'z';
            case 96: return '{';
            case 97: return '|';
            case 98: return '}';
            case 99: return '~';
                
            default: 
            throw new IllegalArgumentException("No mapping for value: " +value);
        }
    }
}
