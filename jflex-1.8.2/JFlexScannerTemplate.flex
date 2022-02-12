package jFlexScanner;
/**
* This file defines a simple lexer for the compilers course 2021-2022
* When it sees a time, money, code, email, or Domain, it prints it out. 
* Otherwise, it does nothing.
* @author  Alex Hu
* @version 2/11/2022
* 
*/
import java.io.*;

%%
/* lexical functions */
/* specify that the class will be called Scanner and the function to get the next
 * token is called nextToken.  
 */
%class Scanner
%unicode
%line
%column
%public
%function nextToken
/*  return String objects - the actual lexemes */
/*  returns the String "END: at end of file */
%type String
%eofval{
return "END";
%eofval}
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]
Alpha = [a-zA-Z]
Numeric = [0-9]
Hour = 1|2|3|4|5|6|7|8|9|10|11|12
FullHour = Hour|13|14|15|16|17|18|19|20|21|22|23
Minute = (0|1|2|3|4|5){Numeric}
Alphanumeric = {Alpha}|{Numeric}
WebSafe = {Alphanumeric}|\-|_
Domain = ({WebSafe}+\.)+(uk|com|net|org|edu|gov|mil|int|biz|info|name|aero|coop|museum)

/**
 * Pattern definitions
 */
 
 
%%
/**
 * lexical rules
 */

({Hour}(am|pm))|({FullHour}:{Minute}) { return "time: " + yytext(); }
\${Numeric}*(({Numeric}\.{Numeric}+)|{Numeric}) { return "money: " + yytext(); }
\b{Numeric}{5}\b { return "code: "+yytext(); }
{Alphanumeric}+@{Domain} { return "email: "+yytext(); }
{Domain}|((http:\/\/|https:\/\/){Domain}) { return "web: "+yytext(); }
{WhiteSpace}|. { /* do nothing */ }
