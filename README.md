sqlparser
# Simple SQL select statement parser
###Usage
```
Parser parser = new Parser();
Select query = parser.parse(new StringReader(queryString));
```