package finalExam;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;


public class Main {
	public static void main(String[] args) {
		Scanner inputFile = new Scanner(System.in);
		System.out.println("Please enter file name: ");
		String fileName = inputFile.nextLine();
		inputFile.close();
		Compiler compiler = Compiler.create(fileName);
	}

	public class Token {
			// Question 1: Token class 
			// with String for lexeme representation and Int for token code
			String lexeme;
			int tokenCode;
			
			Token(String lexeme,int tokenCode) {
				this.lexeme = lexeme;
				this.tokenCode = tokenCode;
			}
	}
	//Stores codes for language - question 1 continuation 
	class Tokens{
	    //data Types
	    static Token REAL_LIT;
	    static Token NATURAL_LIT;
	    static Token BOOL_LIT;
	    static Token CHAR_LIT;
	    static Token STRING_LIT;

	    static Token START_STMNT;
	    static Token END_STMNT;
	    static Token DECL_RE;

	    //conditional statements
	    static Token IF_STMNT;
	    static Token LOOP_STMNT;

	    //data types
	    static Token STRG_TYP;
	    static Token NAT_NUM;
	    static Token CHAR_TYP;
	    static Token REAL_TYP;
	    static Token BOOL_TYP;

	    //special symbols
	    static Token ADD_ITN;
	    static Token SUB_TRCT;
	    static Token MULT_PLY;
	    static Token DIV_DE;
	    static Token EXP_NT;
	    static Token LT_PRNTH;
	    static Token RT_PRNTH;
	    static Token GR_THN;
	    static Token LSS_THN;
	    static Token GR_EQTHN;
	    static Token LSS_EQTHN;
	    static Token EQ_TO;
	    static Token NT_EQ;
	    static Token UNARY_NEG_OP;
	    static Token LOGICAL_NOT;
	    static Token LOGICAL_AND;
	    static Token LOGICAL_OR;
	    static Token BRKT_LT;
	    static Token BRKT_RT;
	    static Token PARAM_SEP;
	    static Token ASS_N;

	    static Token VAR_ID;
	    static Token FUNC_ID;

	    Tokens(){
	        VAR_ID  = new Token("@[A-Za-z_]+", 44);
	        FUNC_ID  = new Token("#[A-Za-z]+", 45);
	        
	        REAL_LIT = new Token("[0-9]+.[0-9]+", 5);
	        NATURAL_LIT = new Token("[0-9]+", 6);
	        BOOL_LIT = new Token("(True|False)", 7);
	        CHAR_LIT = new Token("[A-Za-z0-9]", 8);
	        STRING_LIT = new Token("[A-Za-z0-9]+", 9);

	        IF_STMNT = new Token("whether", 12);
	        LOOP_STMNT = new Token("repeat", 13);
	        START_STMNT = new Token("start", 0);
	        END_STMNT = new Token("trats", 99);
	        DECL_RE = new Token("declare", 55);
	        PARAM_SEP = new Token(";", 20);

	        STRG_TYP = new Token("WORD", 26);
	        NAT_NUM = new Token("NUM", 27);
	        CHAR_TYP = new Token("CHAR", 28);
	        REAL_TYP = new Token("REAL", 29);
	        BOOL_TYP = new Token("BOOL", 1);

	        ASS_N = new Token("=", 66);
	        ADD_ITN = new Token("+", 31);
	        SUB_TRCT = new Token("-", 32);
	        MULT_PLY = new Token("*", 33);
	        DIV_DE = new Token("/", 34);
	        EXP_NT = new Token("**", 34);
	        LT_PRNTH = new Token("(", 97);
	        RT_PRNTH = new Token(")", 98);
	        GR_THN = new Token(">", 37);
	        LSS_THN = new Token("<", 36);
	        GR_EQTHN = new Token(">=", 39);
	        LSS_EQTHN = new Token("<=", 38);
	        EQ_TO = new Token("==", 40);
	        NT_EQ = new Token("!=", 41);
	        UNARY_NEG_OP = new Token("!", 43);
	        LOGICAL_NOT = new Token("!!", 44);
	        LOGICAL_AND = new Token("&&", 45);
	        LOGICAL_OR = new Token("||", 46);
	        BRKT_LT = new Token("{", 97);
	        BRKT_RT = new Token("}",96);
	       
	    }
	}
	
	
	// Question 2: Compiler class
	//takes in an input file and converts it to one input str
			
	public class Compiler {
	    //Takes in an input file
	    //Converts it to one input string
	    String fileContents;
	    List<Token> tokens;

	    // Constructor that takes in the path to the input file
	    Compiler(String filepath){
	        // Retrieve the contents of the file at the given filepath
	        this.fileContents = getFileContents(filepath);

	        // Create a lexer instance and tokenize the file contents
	        Lexer lexer = new Lexer(fileContents);
	        this.tokens = lexer.tokenize();
	    }

	    // Method that takes in the path to a file and returns its contents as a string
	    public String getFileContents(String filepath)
	    {
	        String fileInput = "";
	        try{
	            // Open the file at the given filepath
	            BufferedReader myReader = new BufferedReader(new FileReader(filepath));

	            // Read the file line by line
	            String line = myReader.readLine();
	            while(line != null)
	            {
	                // Concatenate the lines into a single string, separated by a space
	                fileInput = fileInput + line + " ";
	                line = myReader.readLine();
	            }

	            // Close the reader
	            myReader.close();
	        } catch(IOException error){
	            // Print the error if there was an issue reading the file
	            System.out.println(error);
	        }
	        return fileInput;
	    }
	}

			public class Lexer {
				//Question 3: Lexer class
			  
			    //Char Classes
			    static final int CHAR = 0;
			    static final int NUM = 1;
			    static final int UNKNOWN = 99;
			    static final int ND_FILE = -1;

			    //Variables
			    List<Token> tokensList;
			    String fileContent;
			    Character currentChar;
			    int charIdx = 0;
			    int charClass;
			    String lexeme = "";
			    int currentToken;

			    // Construct  
			    Lexer(String fileContent){
			        new Tokens();
			        this.fileContent = fileContent;
			        this.tokensList = new ArrayList<Token>();
			        tokenDriver();
			    }

			    public List<Token> tokenize()
			    {
			        for(int x = 0; x < tokensList.size(); x++)
			        {
			            System.out.print(tokensList.get(x).tokenCode + ", ");
			        }
			        return tokensList;
			    }

			    public void tokenDriver()
			    {
			        getTokens();
			        while(charIdx < fileContent.length() && currentToken != ND_FILE)
			        {
			            lexerAnalyzer();
			            lexeme = "";
			        }
			    }

			    // function to get the next character from the input file
			    public void getTokens() {
			        if(charIdx < fileContent.length()) {
			            currentChar = fileContent.charAt(charIdx);
			            charIdx++;
			            if(Character.isLetter(currentChar)) {
			                charClass = CHAR;
			            }
			            else if(Character.isDigit(currentChar)) {
			                charClass = NUM;
			            }
			            else {
			                charClass = UNKNOWN;
			            }
			        }
			    }

			    //function that adds a new character to the existing lexeme 
			    public void addChar() {
			        lexeme += currentChar;
			    }

			    public void ignoreWhiteSpace() {
			        // Ignore WhiteSpace characters
			        while (Character.isWhitespace(currentChar)) {
			            getTokens();
			        }

			        // Ignore Block Comments
			        if (currentChar == '/' && fileContent.charAt(charIdx + 1) == '*') {
			            charIdx += 2;
			            while (currentChar != '*' && fileContent.charAt(charIdx + 1) != '/' && charIdx < fileContent.length()) {
			                charIdx++;
			                currentChar = fileContent.charAt(charIdx);
			            }
			            charIdx += 1;
			            getTokens();
			        }
			    }


			    //functionto check if the current lexeme and match lexeme 
			   
			    public void identifyKeyword(){
			         switch(lexeme){
			            case "start":
			                currentToken = Tokens.START_STMNT.tokenCode;
			                break;
			            case "trats":
			                currentToken = Tokens.END_STMNT.tokenCode;
			                break;
			            case "declare":
			                currentToken = Tokens.DECL_RE.tokenCode;
			                break;
			            case "whether":
			                 currentToken = Tokens.IF_STMNT.tokenCode;
			                 break;
			            case "repeat":
			                currentToken = Tokens.LOOP_STMNT.tokenCode;
			                break;
			            case "WORD":
			                currentToken = Tokens.STRG_TYP.tokenCode;
			                break;
			            case "char":
			                currentToken = Tokens.CHAR_TYP.tokenCode;
			                break;
			            case "NUM":
			                currentToken = Tokens.NAT_NUM.tokenCode;
			                break;
			            case "real":
			                currentToken = Tokens.REAL_TYP.tokenCode;
			                break;
			            case "BOOL":
			                currentToken = Tokens.BOOL_TYP.tokenCode;
			                break;
			            case "vars":
			                currentToken = Tokens.VAR_ID.tokenCode;
			                break;
			            case "funcs":
			                currentToken = Tokens.FUNC_ID.tokenCode;
			                break;
			            default:
			                if(lexeme.matches(Tokens.BOOL_LIT.lexeme))
			                {
			                    currentToken = Tokens.BOOL_LIT.tokenCode;
			                    break;
			                }
			                currentToken = ND_FILE;
			                break;
			         }
			    }
			    //function to match the regex of the lexeme to a natural or real number 
			    public void identifyVar()
			    {
			        if(lexeme.matches(Tokens.REAL_LIT.lexeme))
			        {
			            currentToken = Tokens.REAL_LIT.tokenCode;
			        }
			        else if(lexeme.matches(Tokens.NATURAL_LIT.lexeme))
			        {
			            currentToken = Tokens.NATURAL_LIT.tokenCode;
			        }
			        else if(lexeme.matches('"' + Tokens.CHAR_LIT.lexeme + '"'))
			        {
			            currentToken = Tokens.CHAR_LIT.tokenCode;
			        }
			        else if(lexeme.matches('"' + Tokens.STRING_LIT.lexeme + '"'))
			        {
			            currentToken = Tokens.STRING_LIT.tokenCode;
			        }
			        else if(lexeme.matches(Tokens.VAR_ID.lexeme))
			        {
			            currentToken = Tokens.VAR_ID.tokenCode;
			        }
			        else if(lexeme.matches(Tokens.FUNC_ID.lexeme))
			        {
			            currentToken = Tokens.FUNC_ID.tokenCode;
			        }
			        else{
			            currentToken = ND_FILE;
			        }
			    }

			 // function to recognize and find token codes for special symbols 
			    public void identUnknown()
			    {
			        switch(lexeme){
			            case "+":
			                currentToken = Tokens.ADD_ITN.tokenCode;
			                break;
			            case "-":
			                currentToken = Tokens.SUB_TRCT.tokenCode;
			                break;
			            case "*":
			                if(currentChar == '*')
			                {
			                    addChar();
			                    getTokens();
			                    currentToken = Tokens.EXP_NT.tokenCode;
			                    break;
			                }
			                currentToken = Tokens.MULT_PLY.tokenCode;
			                break;
			            case "/":
			                currentToken = Tokens.DIV_DE.tokenCode;
			                break;
			            case "(":
			                currentToken = Tokens.LT_PRNTH.tokenCode;
			                break;
			            case ")":
			                currentToken = Tokens.RT_PRNTH.tokenCode;
			                break;
			            case ">":
			                if(currentChar == '=')
			                {
			                    addChar();
			                    getTokens();
			                    currentToken = Tokens.GR_EQTHN.tokenCode;
			                    break;
			                }
			                currentToken = Tokens.GR_THN.tokenCode;
			                break;
			            case "<":
			                if(currentChar == '=')
			                {
			                    addChar();
			                    getTokens();
			                    currentToken = Tokens.LSS_EQTHN.tokenCode;
			                    break;
			                }
			                currentToken = Tokens.LSS_THN.tokenCode;
			                break;
			            case "=":
			                if(currentChar == '=')
			                {
			                    addChar();
			                    getTokens();
			                    currentToken = Tokens.EQ_TO.tokenCode;
			                    break;
			                }
			                currentToken = Tokens.ASS_N.tokenCode;
			                break;
			            case "!":
			                if(currentChar == '=')
			                {
			                    addChar();
			                    getTokens();
			                    currentToken = Tokens.NT_EQ.tokenCode;
			                    break;
			                }
			                else if(currentChar == '!')
			                {
			                    addChar();
			                    getTokens();
			                    currentToken = Tokens.LOGICAL_NOT.tokenCode;
			                    break;
			                }
			                currentToken = Tokens.UNARY_NEG_OP.tokenCode;
			                break;
			            case "&":
			                currentToken = Tokens.LOGICAL_AND.tokenCode;
			                break;
			            case "|":
			                currentToken = Tokens.LOGICAL_OR.tokenCode;
			                break;
			            case "{":
			                currentToken = Tokens.BRKT_LT.tokenCode;
			                break;
			            case "}":
			                currentToken = Tokens.BRKT_RT.tokenCode;
			                break;
			            case ";":
			                currentToken = Tokens.PARAM_SEP.tokenCode;
			                break;
			            default:
			                currentToken = ND_FILE;
			                break;
			        }
			    }

			    // function to get the codes for each char class until end of file
			    public void lexerAnalyzer()
			    {
			        ignoreWhiteSpace();
			        switch(charClass){
			            case CHAR:
			                addChar();
			                getTokens();
			                while(charClass == CHAR || charClass == UNKNOWN && currentChar == '_'){
			                    addChar();
			                    getTokens();
			                }
			                identifyKeyword();
			                break;
			            case NUM:
			                addChar();
			                getTokens();
			                while(charClass == CHAR || charClass == UNKNOWN && currentChar == '.')
			                {
			                    addChar();
			                    getTokens();
			                }
			                identifyVar();
			                break;
			            case UNKNOWN:
			                if(currentChar == '.' && Character.isDigit(fileContent.charAt(charIdx++)))
			                {
			                    addChar();
			                    getTokens();
			                }
			                else if(currentChar == '"')
			                {
			                    addChar();
			                    getTokens();
			                    while(charClass == CHAR || charClass == UNKNOWN && currentChar == '"')
			                    {
			                        addChar();
			                        getTokens();
			                    }
			                    identifyVar();
			                    break;
			                }
			                else if(currentChar == '@')
			                {
			                    addChar();
			                    getTokens();
			                    while(charClass == CHAR || charClass == UNKNOWN && currentChar == '_')
			                    {
			                        addChar();
			                        getTokens();
			                    }
			                    identifyVar();
			                    break;
			                }
			                else if(currentChar == '#')
			                {
			                    addChar();
			                    getTokens();
			                    while(charClass == CHAR)
			                    {
			                        addChar();
			                        getTokens();
			                    }
			                    identifyVar();
			                    break;
			                }
			                else{
			                    addChar();
			                    getTokens();
			                    identUnknown();
			                    break;
			                }
			            case ND_FILE:
			                currentToken = ND_FILE;
			                lexeme = "";
			                break;
			        }
			        tokensList.add(new Token(lexeme, currentToken));

			        lexeme = "";
			    }

			}

			public class Parser {
				//Question 4: Parser Class
			    //Will parse token codes that follow production rules
			    List<Token> tokensList;
			    Parser(List<Token> tokensList){
			        this.tokensList = tokensList;
			    }
			}
	}


