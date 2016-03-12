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
public class BlockedMessage2
{
    private String message;
    private int blockSize;
    
    /**
     * @param blockSize     Number of characters per block
     * @throws Exception    If blockSize <= 0
     */
    public BlockedMessage2(int blockSize) throws Exception
    {
        if(blockSize <= 0)
        {
            throw new Exception("block size must be positive value");
        }
        
        this.blockSize = blockSize;
        message = "";
    }
    
    /**
     * @param text          Text to initialize message with
     * @param blockSize     Number of characters per block
     * @throws Exception    If text contains invalid characters with
     *                      undefined mappings or if blockSize <= 0
     */
    public BlockedMessage2(String text, int blockSize) throws Exception
    {
        this(blockSize);
        appendString(text);
    }
    
    /**
     * @param blocks        Encoded blocks to initialize message with
     * @param blockSize     Number of characters per block
     * @throws Exception    If blocks are not properly formatted or
     *                      if blockSize <= 0
     */
    public BlockedMessage2(String[] blocks, int blockSize) throws Exception
    {
        this(blockSize);
        
        for(String b : blocks)
        {
            appendBlock(b);
        }
    }
    
    /**
     * Append string to message
     * 
     * @param text          String to append
     * @throws Exception    If text contains invalid character with
     *                      undefined mappings
     */
    public void appendString(String text) throws Exception
    {
        verifyString(text);
        message += text;
    }
    
    /**
     * Append encoded block to message
     * 
     * @param block         Encoded block to append
     * @throws Exception    If block is not properly formatted
     */
    public void appendBlock(String block) throws Exception
    {
        message += decodeBlock(block);
    }
    
    /**
     * The number of characters in the message
     * 
     * @return  Length of message
     */
    public int length()
    {
        return message.length();
    }
    
    /**
     * Get the minimum number of blocks needed to encode the message
     * by the block size
     * 
     * @return  number of blocks
     */
    public int countBlocks()
    {
        int result = message.length() / blockSize;
        if(message.length() % blockSize > 0)
        {
            result += 1;
        }
        
        return result;
    }
    
    /**
     * Get a single encoded block for the message
     * 
     * @param index         The n^th block to retrieve
     * @return              The encoded block at index
     * @throws Exception    If index is out of bounds
     */
    public String getBlock(int index) throws Exception
    {
        if(index < 0 || index >= countBlocks())
        {
            throw new IllegalArgumentException("index out of bounds");
        }
        
        int lower = index*blockSize;
        int upper = blockSize*(index + 1);
        if(upper > message.length()) upper = message.length();
        
        String sub = message.substring(lower, upper);
        return encodeBlock(sub);
    }
    
    /**
     * Get the entire message encoded as an array of blocks
     * 
     * @return  Array of encoded blocks
     */
    public String[] getBlocks() throws Exception
    {
        String[] blocks = new String[countBlocks()];
        
        for(int i = 0; i < blocks.length; ++i)
        {
            blocks[i] = getBlock(i);
        }
        
        return blocks;
    }
    
    /**
     * Get the message decoded as ASCII text
     * 
     * @return  Message decoded in ASCII format
     */
    public String toString()
    {
        return message;
    }
    
    
    
    /**
     * Encode a block of ASCII text
     * 
     * @param text          The string to encode
     * @return              String as encoded block
     * @throws Exception    If string is too large to fit in a single block
     *                      or contains invalid characters
     */
    private String encodeBlock(String text) throws Exception
    {
        if(text.length() > blockSize)
        {
            throw new Exception("String is larger than block size");
        }
        
        String result = "";
        
        for(int i = text.length() - 1; i >= 0; --i)
        {
            result += encode(text.charAt(i));
        }
        
        while(result.length() < 2*blockSize)
        {
            result = "00" + result;
        }
        
        return result;
    }
    
    /**
     * Decode a block and get the original ASCII text
     * 
     * @param block         Encoded block
     * @return              Decoded block as ASCII text
     * @throws Exception    If block does not fit block size or has
     *                      non-numeric characters
     */
    private String decodeBlock(String block) throws Exception
    {
        if(block.length() != 2*blockSize)
        {
            throw new Exception("Block does not match block size");
        }
        
        String result = "";
        
        if(block.length() % 2 != 0)
        {
            throw new Exception("block contains odd number of characters");
        }
        
        for(int i = block.length(); i >= 2; i -= 2)
        {
            String code = block.substring(i-2, i);
            char c = decode(code);
            if(c == '\0') break;
            result += c;
        }
        
        return result;
    }
    
    /**
     * Maps a single character to a numeric value encoded as a string
     * 
     * @param c             Character to encode
     * @return              2-character string containing code for c
     * @throws Exception    If c is an invalid character (there is no mapping
     *                      defined for that character)
     */
    private static String encode(char c) throws Exception
    {
        switch(c)
        {
            case '\0':      return "00";
            case (char)11:  return "01";
            case '\t':      return "02";
            case '\n':      return "03";
            case '\r':      return "04";
            case ' ':       return "05";
            case '!':       return "06";
            case '"':       return "07";
            case '#':       return "08";
            case '$':       return "09";
            case '%':       return "10";
            case '&':       return "11";
            case '\'':      return "12";
            case '(':       return "13";
            case ')':       return "14";
            case '*':       return "15";
            case '+':       return "16";
            case ',':       return "17";
            case '-':       return "18";
            case '.':       return "19";
            case '/':       return "20";
            case '0':       return "21";
            case '1':       return "22";
            case '2':       return "23";
            case '3':       return "24";
            case '4':       return "25";
            case '5':       return "26";
            case '6':       return "27";
            case '7':       return "28";
            case '8':       return "29";
            case '9':       return "30";
            case ':':       return "31";
            case ';':       return "32";
            case '<':       return "33";
            case '=':       return "34";
            case '>':       return "35";
            case '?':       return "36";
            case '@':       return "37";
            case 'A':       return "38";
            case 'B':       return "39";
            case 'C':       return "40";
            case 'D':       return "41";
            case 'E':       return "42";
            case 'F':       return "43";
            case 'G':       return "44";
            case 'H':       return "45";
            case 'I':       return "46";
            case 'J':       return "47";
            case 'K':       return "48";
            case 'L':       return "49";
            case 'M':       return "50";
            case 'N':       return "51";
            case 'O':       return "52";
            case 'P':       return "53";
            case 'Q':       return "54";
            case 'R':       return "55";
            case 'S':       return "56";
            case 'T':       return "57";
            case 'U':       return "58";
            case 'V':       return "59";
            case 'W':       return "60";
            case 'X':       return "61";
            case 'Y':       return "62";
            case 'Z':       return "63";
            case '[':       return "64";
            case '\\':      return "65";
            case ']':       return "66";
            case '^':       return "67";
            case '_':       return "68";
            case '`':       return "69";
            case 'a':       return "70";
            case 'b':       return "71";
            case 'c':       return "72";
            case 'd':       return "73";
            case 'e':       return "74";
            case 'f':       return "75";
            case 'g':       return "76";
            case 'h':       return "77";
            case 'i':       return "78";
            case 'j':       return "79";
            case 'k':       return "80";
            case 'l':       return "81";
            case 'm':       return "82";
            case 'n':       return "83";
            case 'o':       return "84";
            case 'p':       return "85";
            case 'q':       return "86";
            case 'r':       return "87";
            case 's':       return "88";
            case 't':       return "89";
            case 'u':       return "90";
            case 'v':       return "91";
            case 'w':       return "92";
            case 'x':       return "93";
            case 'y':       return "94";
            case 'z':       return "95";
            case '{':       return "96";
            case '|':       return "97";
            case '}':       return "98";
            case '~':       return "99";
                
            default: 
            throw new Exception("No mappting for character: " + c);
        }
    }
    
    /**
     * Maps a 2-character numeric string to a single character
     * 
     * @param code          Numeric string of length 2, i.e. "56"
     * @return              Character mapped to that code
     * @throws Exception    If code is not exactly two characters or contains
     *                      non-numeric characters
     */
    private static char decode(String code) throws Exception
    {
        switch(code)
        {
            case "0":
            case "00":  return '\0';
            case "1":
            case "01":  return (char)11;
            case "2":
            case "02":  return '\t';
            case "3":
            case "03":  return '\n';
            case "4":
            case "04":  return '\r';
            case "5":
            case "05":  return ' ';
            case "6":
            case "06":  return '!';
            case "7":
            case "07":  return '"';
            case "8":
            case "08":  return '#';
            case "9":
            case "09":  return '$';
            case "10":  return '%';
            case "11":  return '&';
            case "12":  return '\'';
            case "13":  return '(';
            case "14":  return ')';
            case "15":  return '*';
            case "16":  return '+';
            case "17":  return ',';
            case "18":  return '-';
            case "19":  return '.';
            case "20":  return '/';
            case "21":  return '0';
            case "22":  return '1';
            case "23":  return '2';
            case "24":  return '3';
            case "25":  return '4';
            case "26":  return '5';
            case "27":  return '6';
            case "28":  return '7';
            case "29":  return '8';
            case "30":  return '9';
            case "31":  return ':';
            case "32":  return ';';
            case "33":  return '<';
            case "34":  return '=';
            case "35":  return '>';
            case "36":  return '?';
            case "37":  return '@';
            case "38":  return 'A';
            case "39":  return 'B';
            case "40":  return 'C';
            case "41":  return 'D';
            case "42":  return 'E';
            case "43":  return 'F';
            case "44":  return 'G';
            case "45":  return 'H';
            case "46":  return 'I';
            case "47":  return 'J';
            case "48":  return 'K';
            case "49":  return 'L';
            case "50":  return 'M';
            case "51":  return 'N';
            case "52":  return 'O';
            case "53":  return 'P';
            case "54":  return 'Q';
            case "55":  return 'R';
            case "56":  return 'S';
            case "57":  return 'T';
            case "58":  return 'U';
            case "59":  return 'V';
            case "60":  return 'W';
            case "61":  return 'X';
            case "62":  return 'Y';
            case "63":  return 'Z';
            case "64":  return '[';
            case "65":  return '\\';
            case "66":  return ']';
            case "67":  return '^';
            case "68":  return '_';
            case "69":  return '`';
            case "70":  return 'a';
            case "71":  return 'b';
            case "72":  return 'c';
            case "73":  return 'd';
            case "74":  return 'e';
            case "75":  return 'f';
            case "76":  return 'g';
            case "77":  return 'h';
            case "78":  return 'i';
            case "79":  return 'j';
            case "80":  return 'k';
            case "81":  return 'l';
            case "82":  return 'm';
            case "83":  return 'n';
            case "84":  return 'o';
            case "85":  return 'p';
            case "86":  return 'q';
            case "87":  return 'r';
            case "88":  return 's';
            case "89":  return 't';
            case "90":  return 'u';
            case "91":  return 'v';
            case "92":  return 'w';
            case "93":  return 'x';
            case "94":  return 'y';
            case "95":  return 'z';
            case "96":  return '{';
            case "97":  return '|';
            case "98":  return '}';
            case "99":  return '~';
                
            default: 
            throw new Exception("No mapping for value: " + code);
        }
    }
    
    /**
     * Checks the string for invalid characters with no defined mapping
     * 
     * @param text          String to check
     * @throws Exception    If an invalid character is found in the string
     */
    private void verifyString(String text) throws Exception
    {
        for(int i = 0; i < text.length(); ++i)
        {
            encode(text.charAt(i));
        }
    }
}
