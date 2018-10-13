package com.github.vr_f.sqlparser;

import java_cup.runtime.Symbol;
import java.util.LinkedList;
import java.util.HashMap;

%%

%class Lexer
%cup
%caseless

%{
  LinkedList<HashMap> stateStack = new LinkedList<HashMap>() {{
      addLast(new HashMap<String, Object>() {{
          put("state", YYINITIAL);
          put("p_nest", 0);
      }});
  }};

  private void pushState(final Integer state) {
      stateStack.addLast(new HashMap<String, Object>() {{
          put("state", state);
          put("p_nest", 0);
      }});
      yybegin(state);
  }

  private void popState() {
      stateStack.pollLast();
      HashMap<String, Object> state = stateStack.getLast();

      if (null == state) {
          yyerror("Cannot pop empty state stack");
      }

      yybegin((Integer)state.get("state"));
  }

  private void increaseParenthesisNesting() throws NullPointerException {
      HashMap<String, Object> state = stateStack.getLast();
      state.put("p_nest", (Integer)state.get("p_nest") + 1);
  }

  private void decreaseParenthesisNesting() throws NullPointerException {
      HashMap<String, Object> state = stateStack.getLast();
      state.put("p_nest", (Integer)state.get("p_nest") - 1);
  }

  private Integer getParenthesisNesting() throws NullPointerException {
      HashMap<String, Object> state = stateStack.getLast();
      return (Integer) state.get("p_nest");
  }

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }

  private void yyerror(String message, Object... params) {
      throw new Error(String.format(message, params));
  }

%}

%state COMMENT
%state BETWEEN

%%

<YYINITIAL> AND             { return symbol(sym.AND); }

<BETWEEN> AND               {
    if (getParenthesisNesting() == 0) {
        popState();
        return symbol(sym.BETWEENAND);
    } else {
        return symbol(sym.AND);
    }
}

<YYINITIAL, BETWEEN> {
  ADDDATE                  { return symbol(sym.ADDDATE); }
  AGAINST                  { return symbol(sym.AGAINST); }
  ALL                      { return symbol(sym.ALL); }
  ANY                      { return symbol(sym.ANY); }
  AS                       { return symbol(sym.AS); }
  ASC                      { return symbol(sym.ASC); }
  ASCII                    { return symbol(sym.ASCII); }
  BETWEEN                  { pushState(BETWEEN); return symbol(sym.BETWEEN); }
  BINARY                   { return symbol(sym.BINARY); }
  BOTH                     { return symbol(sym.BOTH); }
  BY                       { return symbol(sym.BY); }
  CASE                     { return symbol(sym.CASE); }
  CAST                     { return symbol(sym.CAST); }
  CHAR                     { return symbol(sym.CHAR); }
  CHARACTER                { return symbol(sym.CHARACTER); }
  COLUMNS                  { return symbol(sym.COLUMNS); }
  CONVERT                  { return symbol(sym.CONVERT); }
  CROSS                    { return symbol(sym.CROSS); }
  CURRENT_DATE             { return symbol(sym.CURRENT_DATE); }
  CURRENT_TIME             { return symbol(sym.CURRENT_TIME); }
  CURRENT_TIMESTAMP        { return symbol(sym.CURRENT_TIMESTAMP); }
  DATE                     { return symbol(sym.DATE); }
  DATETIME                 { return symbol(sym.DATETIME); }
  NUMERIC|DEC|DECIMAL      { return symbol(sym.DECIMAL); }
  DESC                     { return symbol(sym.DESC); }
  DISTINCT                 { return symbol(sym.DISTINCT); }
  DISTINCTROW              { return symbol(sym.DISTINCTROW); }
  DIV                      { return symbol(sym.DIV); }
  DUMPFILE                 { return symbol(sym.DUMPFILE); }
  ELSE                     { return symbol(sym.ELSE); }
  ENCLOSED                 { return symbol(sym.ENCLOSED); }
  END                      { return symbol(sym.END); }
  ESCAPE                   { return symbol(sym.ESCAPE); }
  ESCAPED                  { return symbol(sym.ESCAPED); }
  EXISTS                   { return symbol(sym.EXISTS); }
  EXTRACT                  { return symbol(sym.EXTRACT); }
  FIELDS                   { return symbol(sym.FIELDS); }
  FOR                      { return symbol(sym.FOR); }
  FORCE                    { return symbol(sym.FORCE); }
  FROM                     { return symbol(sym.FROM); }
  GROUP                    { return symbol(sym.GROUP); }
  HAVING                   { return symbol(sym.HAVING); }
  HIGH_PRIORITY            { return symbol(sym.HIGH_PRIORITY); }
  IF                       { return symbol(sym.IF); }
  IGNORE                   { return symbol(sym.IGNORE); }
  IN                       { return symbol(sym.IN); }
  INNER                    { return symbol(sym.INNER); }
  INTERVAL                 { return symbol(sym.INTERVAL); }
  INTO                     { return symbol(sym.INTO); }
  IS                       { return symbol(sym.IS); }
  JOIN                     { return symbol(sym.JOIN); }
  INDEX|KEY                { return symbol(sym.KEY); }
  LEADING                  { return symbol(sym.LEADING); }
  LEFT                     { return symbol(sym.LEFT); }
  LIKE                     { return symbol(sym.LIKE); }
  LIMIT                    { return symbol(sym.LIMIT); }
  LINES                    { return symbol(sym.LINES); }
  MATCH                    { return symbol(sym.MATCH); }
  MOD                      { return symbol(sym.MOD); }
  NATURAL                  { return symbol(sym.NATURAL); }
  NOT                      { return symbol(sym.NOT); }
  NULL                     { return symbol(sym.NULL); }
  OFFSET                   { return symbol(sym.OFFSET); }
  ON                       { return symbol(sym.ON); }
  OPTIONALLY               { return symbol(sym.OPTIONALLY); }
  OR                       { return symbol(sym.OR); }
  ORDER                    { return symbol(sym.ORDER); }
  OUTER                    { return symbol(sym.OUTER); }
  OUTFILE                  { return symbol(sym.OUTFILE); }
  PARTITION                { return symbol(sym.PARTITION); }
  REGEXP|RLIKE             { return symbol(sym.REGEXP); }
  RIGHT                    { return symbol(sym.RIGHT); }
  ROLLUP                   { return symbol(sym.ROLLUP); }
  SELECT                   { return symbol(sym.SELECT); }
  SET                      { return symbol(sym.SET); }
  SIGNED                   { return symbol(sym.SIGNED); }
  SOME                     { return symbol(sym.SOME); }
  SOUNDS[ \t\n]+           { return symbol(sym.SOUNDSLIKE); }
  SQL_BIG_RESULT           { return symbol(sym.SQL_BIG_RESULT); }
  SQL_CALC_FOUND_ROWS      { return symbol(sym.SQL_CALC_FOUND_ROWS); }
  SQL_SMALL_RESULT         { return symbol(sym.SQL_SMALL_RESULT); }
  SQL_BUFFER_RESULT        { return symbol(sym.SQL_BUFFER_RESULT); }
  SQL_CACHE                { return symbol(sym.SQL_CACHE); }
  SQL_NO_CACHE             { return symbol(sym.SQL_NO_CACHE); }
  STARTING                 { return symbol(sym.STARTING); }
  STRAIGHT_JOIN            { return symbol(sym.STRAIGHT_JOIN); }
  SUBDATE                  { return symbol(sym.SUBDATE); }
  TERMINATED               { return symbol(sym.TERMINATED); }
  THEN                     { return symbol(sym.THEN); }
  TIME                     { return symbol(sym.TIME); }
  TRAILING                 { return symbol(sym.TRAILING); }
  UNICODE                  { return symbol(sym.UNICODE); }
  UNSIGNED                 { return symbol(sym.UNSIGNED); }
  USE                      { return symbol(sym.USE); }
  USING                    { return symbol(sym.USING); }
  WHEN                     { return symbol(sym.WHEN); }
  WHERE                    { return symbol(sym.WHERE); }
  WITH                     { return symbol(sym.WITH); }
  XOR                      { return symbol(sym.XOR); }

  MICROSECOND              { return symbol(sym.MICROSECOND); }
  SECOND                   { return symbol(sym.SECOND); }
  MINUTE                   { return symbol(sym.MINUTE); }
  HOUR                     { return symbol(sym.HOUR); }
  DAY                      { return symbol(sym.DAY); }
  WEEK                     { return symbol(sym.WEEK); }
  MONTH                    { return symbol(sym.MONTH); }
  QUARTER                  { return symbol(sym.QUARTER); }
  YEAR                     { return symbol(sym.YEAR); }
  SECOND_MICROSECOND       { return symbol(sym.SECOND_MICROSECOND); }
  MINUTE_MICROSECOND       { return symbol(sym.MINUTE_MICROSECOND); }
  MINUTE_SECOND            { return symbol(sym.MINUTE_SECOND); }
  HOUR_MICROSECOND         { return symbol(sym.HOUR_MICROSECOND); }
  HOUR_SECOND              { return symbol(sym.HOUR_SECOND); }
  HOUR_MINUTE              { return symbol(sym.HOUR_MINUTE); }
  DAY_MICROSECOND          { return symbol(sym.DAY_MICROSECOND); }
  DAY_SECOND               { return symbol(sym.DAY_SECOND); }
  DAY_MINUTE               { return symbol(sym.DAY_MINUTE); }
  DAY_HOUR                 { return symbol(sym.DAY_HOUR); }
  YEAR_MONTH               { return symbol(sym.YEAR_MONTH); }

  -?[0-9]+                 { return symbol(sym.INTNUM, Integer.parseInt(yytext())); }

  -?[0-9]+"."[0-9]* |
  -?"."[0-9]+     |
  -?[0-9]+E[-+]?[0-9]+    |
  -?[0-9]+"."[0-9]*E[-+]?[0-9]+ |
  -?"."[0-9]+E[-+]?[0-9]+  { return symbol(sym.APPROXNUM, Float.parseFloat(yytext())); }

  TRUE                     { return symbol(sym.BOOL, "TRUE"); }
  FALSE                    { return symbol(sym.BOOL, "FALSE"); }
  UNKNOWN                  { return symbol(sym.BOOL, "UNKNOWN"); }

  '(\\.|''|[^'\n])*'   |
  \"(\\.|\"\"|[^\"\n])*\"  { return symbol(sym.STRING, yytext()); }

  '(\\.|[^'\n])*$          { yyerror("Unterminated string \"%s\"", yytext()); }
  \"(\\.|[^\"\n])*$        { yyerror("Unterminated string \"%s\"", yytext()); }

  X'[0-9A-F]+' |
  0X[0-9A-F]+              { return symbol(sym.STRING, yytext()); }

  0B[01]+      |
  B'[01]+'                 { return symbol(sym.STRING, yytext()); }

  "+"                      { return symbol(sym.PLUS); }
  "-"                      { return symbol(sym.MINUS); }
  "*"                      { return symbol(sym.STAR); }
  "/"                      { return symbol(sym.SLASH); }
  "%"                      { return symbol(sym.PERCENT); }
  "&"                      { return symbol(sym.AMPERSAND); }
  "~"                      { return symbol(sym.TILDE); }
  "|"                      { return symbol(sym.PIPE); }
  "^"                      { return symbol(sym.CARET); }
  "("                      { increaseParenthesisNesting(); return symbol(sym.OP); }
  ")"                      { decreaseParenthesisNesting(); return symbol(sym.CP); }
  ","                      { return symbol(sym.COMMA); }
  "."                      { return symbol(sym.DOT); }
  ";"                      { return symbol(sym.SEMICOLON); }
  "!"                      { return symbol(sym.EM); }

  "&&"                     { return symbol(sym.AND); }
  "||"                     { return symbol(sym.OR); }

  "="                      { return symbol(sym.COMPARISON, "="); }
  ">"                      { return symbol(sym.COMPARISON, ">"); }
  "<"                      { return symbol(sym.COMPARISON, "<"); }
  ">="                     { return symbol(sym.COMPARISON, ">="); }
  "<="                     { return symbol(sym.COMPARISON, "<="); }
  "!="                     { return symbol(sym.COMPARISON, "!="); }
  "<>"                     { return symbol(sym.COMPARISON, "<>"); }
  "<=>"                    { return symbol(sym.COMPARISON, "<=>"); }

  "<<"                     { return symbol(sym.SHIFT, "<<"); }
  ">>"                     { return symbol(sym.SHIFT, ">>"); }

  ":="                     { return symbol(sym.ASSIGN); }

  COUNT                    { return symbol(sym.COUNT); }
  DATE_ADD/"("             { return symbol(sym.DATE_ADD); }
  DATE_SUB/"("             { return symbol(sym.DATE_SUB); }
  SUBSTR(ING)?/"("         { return symbol(sym.SUBSTRING); }
  TRIM/"("                 { return symbol(sym.TRIM); }


  [A-Za-z][A-Za-z0-9_]*    { return symbol(sym.NAME, yytext()); }

  [A-Za-z][A-Za-z0-9_]*\.[A-Za-z][A-Za-z0-9_]* { return symbol(sym.DOTNAME, yytext()); }

  `[^`/\\.\n]+`            {
    String txt = yytext();
    return symbol(sym.ESCNAME, txt.substring(1, txt.length() - 1));
  }

  `[^`\n]*$                { yyerror("unterminated quoted name %s", yytext()); }

  @[0-9a-z_.$]+ |
  @\"[^\"\n]+\" |
  @`[^`\n]+` |
  @'[^'\n]+'               { return symbol(sym.VARIABLE, yytext().substring(1)); }

  @\"[^\"\n]*$             { yyerror("unterminated quoted user variable %s", yytext()); }
  @`[^`\n]*$               { yyerror("unterminated quoted user variable %s", yytext()); }
  @'[^'\n]*$               { yyerror("unterminated quoted user variable %s", yytext()); }

  #.*                      {}
  "--"[ \t].*              {}

  "/*"                     { pushState(COMMENT); }

  [ \t\n]                  {}
  .                        { yyerror("Illegal character <%s>", yytext()); }
}

<COMMENT>{
  "*/"                      { popState(); }
  [^]                       {}
  <<EOF>>                   { yyerror("unclosed comment"); }
}

[^] { yyerror("Illegal character <%s>", yytext()); }