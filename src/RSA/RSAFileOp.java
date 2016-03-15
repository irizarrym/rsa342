/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

package RSA;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

/**
 * Provides facilities for encrypting and decrypting files using RSA encryption
 */
public final class RSAFileOp
{
    /**
     * Loads an RSA public key from an XML-formatted file
     * 
     * @param filePath  Path to public key file
     * @return          RSAPublicKey object
     * 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     * 
     * @see http://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
     */
    public static RSAPublicKey loadPublicKey(String filePath)
    throws ParserConfigurationException, SAXException, IOException
    {
        // Initialization code
        File inputFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        
        // Get root element
        Element root = doc.getDocumentElement();
        
        // Extract e and n values
        String eValue =
            root.getElementsByTagName("evalue").item(0).getTextContent();
        String nValue =
            root.getElementsByTagName("nvalue").item(0).getTextContent();
        BigInt e = new BigInt(eValue);
        BigInt n = new BigInt(nValue);
        
        return new RSAPublicKey(e, n);
    }
    
    /**
     * Loads an RSA private key from an XML-formatted file
     * 
     * @param filePath  Path to private key file
     * @return          RSAPrivateKey object
     * 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     * 
     * @see http://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
     */
    public static RSAPrivateKey loadPrivateKey(String filePath)
    throws ParserConfigurationException, SAXException, IOException
    {
        // Initialization code
        File inputFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        
        // Get root element
        Element root = doc.getDocumentElement();
        
        // Extract e and n values
        String dValue =
            root.getElementsByTagName("dvalue").item(0).getTextContent();
        String nValue =
            root.getElementsByTagName("nvalue").item(0).getTextContent();
        BigInt d = new BigInt(dValue);
        BigInt n = new BigInt(nValue);
        
        return new RSAPrivateKey(d, n);
    }
    
    /**
     * Saves an RSAPublicKey to an XML-formatted file
     * 
     * @param filePath  Path to write public key to
     * @param key       Public key to save to file
     * 
     * @throws ParserConfigurationException
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     * 
     * @see http://www.tutorialspoint.com/java_xml/java_dom_create_document.htm
     */
    public static void savePublicKey(String filePath, RSAPublicKey key)
    throws ParserConfigurationException, TransformerConfigurationException, TransformerException, IOException
    {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        
        // Create rsakey element
        Element rsakey = doc.createElement("rsakey");
        doc.appendChild(rsakey);
        
        // Create evalue element
        Element evalue = doc.createElement("evalue");
        rsakey.appendChild(evalue);
        evalue.appendChild(doc.createTextNode(key.eValue().toString()));
        
        // Create nvalue element
        Element nvalue = doc.createElement("nvalue");
        rsakey.appendChild(nvalue);
        nvalue.appendChild(doc.createTextNode(key.nValue().toString()));
        
        // Write to file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }
    
    /**
     * Saves an RSAPrivateKey to an XML-formatted file
     * 
     * @param filePath  Path to write private key to
     * @param key       Private key to save to file
     * 
     * @throws ParserConfigurationException
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     * 
     * @see http://www.tutorialspoint.com/java_xml/java_dom_create_document.htm
     */
    public static void savePrivateKey(String filePath, RSAPrivateKey key)
    throws ParserConfigurationException, TransformerConfigurationException, TransformerException, IOException
    {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        
        // Create rsakey element
        Element rsakey = doc.createElement("rsakey");
        doc.appendChild(rsakey);
        
        // Create dvalue element
        Element evalue = doc.createElement("dvalue");
        rsakey.appendChild(evalue);
        evalue.appendChild(doc.createTextNode(key.dValue().toString()));
        
        // Create nvalue element
        Element nvalue = doc.createElement("nvalue");
        rsakey.appendChild(nvalue);
        nvalue.appendChild(doc.createTextNode(key.nValue().toString()));
        
        // Write to file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }
    
    /**
     * Encodes a file from ASCII to blocked format
     * 
     * @param inPath    Path to input ASCII text file to encode in blocked format
     * @param outPath   Path to output file containing file encoded in blocked format
     * @param blockSize Size of each block written per line in outFile
     */
    public static void blockFile(String inPath, String outPath, int blockSize)
    throws FileNotFoundException, IOException, Exception
    {
        // Read file for blocking
        BlockedMessage2 msg = new BlockedMessage2(blockSize);
        File inFile = new File(inPath);
        String result = new String(Files.readAllBytes(inFile.toPath()));
        msg.appendString(result);
        
        // Write file in blocked format
        PrintWriter outFile = new PrintWriter(outPath);
        for(String block : msg.getBlocks())
        {
            outFile.println(block);
        }
        
        // Close files
        outFile.close();
    }
    
    /**
     * Decodes a file from blocked to ASCII format
     * 
     * (Block size is inferred from file per line)
     * @param inPath    path to input blocked file
     * @param outPath   path to output ASCII file
     */
    public static void unblockFile(String inPath, String outPath) 
    throws FileNotFoundException, Exception
    {
        // This will contain the result of decoding the text
        String result = "";
        
        // Read blocks from file
        Scanner inFile = new Scanner(new File(inPath));
        if(inFile.hasNextLine())
        {
            // Read first line to infer block size
            String firstLine = inFile.nextLine();
            BlockedMessage2 msg = new BlockedMessage2(firstLine.length() / 2);
            msg.appendBlock(firstLine);
        
            // Read in all blocks from file
            while(inFile.hasNextLine())
            {
                msg.appendBlock(inFile.nextLine());
            }
            
            result = msg.toString();
        }
        
        // Write ASCII output to file
        PrintWriter outFile = new PrintWriter(outPath, "UTF-8");
        outFile.print(result);
        
        // Close files
        inFile.close();
        outFile.close();
    }
    
    /**
     * Encrypts a blocked file using the specified RSA key
     * 
     * @param inFile    path to input blocked file to encrypt
     * @param outFile   path to output file in encrypted blocked format
     * @param key       public key to use for encryption
     */
    public static void encryptFile(String inPath, String outPath, RSAPublicKey key)
    throws FileNotFoundException, UnsupportedEncodingException
    {
        // Open files for reading and writing
        Scanner inFile = new Scanner(new File(inPath));
        PrintWriter outFile = new PrintWriter(outPath, "UTF-8");
        
        // Write encrypted blocks to output file
        while(inFile.hasNextLine())
        {
            BigInt b = new BigInt(inFile.nextLine());
            b = key.encrypt(b);
            outFile.println(b.toString());
        }
        
        // Close files
        inFile.close();
        outFile.close();
    }
    
    /**
     * Decrypts a blocked file using the specified RSA key
     * 
     * @param inFile    path to input encrypted blocked file to decrypt
     * @param outFile   path to output file in raw blocked format
     * @param key       private key to use for decryption
     */
    public static void decryptFile(String inPath, String outPath, RSAPrivateKey key)
    throws FileNotFoundException, UnsupportedEncodingException
    {
        // Open files for reading and writing
        Scanner inFile = new Scanner(new File(inPath));
        PrintWriter outFile = new PrintWriter(outPath, "UTF-8");
        
        // Number of digits on each line
        int digits = 0;
        
        // Write encrypted blocks to output file
        while(inFile.hasNextLine())
        {
            BigInt b = new BigInt(inFile.nextLine());
            b = key.decrypt(b);
            
            // Infer number of digits on each line
            if(digits == 0)
            {
                digits = b.toString().length();
                
                // Number of digits must be even
                if(digits % 2 == 1) digits += 1;
            }
            
            outFile.println(b.toString(digits));
        }
        
        // Close files
        inFile.close();
        outFile.close();
    }
}
