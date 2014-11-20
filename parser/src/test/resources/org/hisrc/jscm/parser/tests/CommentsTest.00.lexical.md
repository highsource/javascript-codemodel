* `Comment // My Comment`
  * `IDENTIFIER_NAME` `Comment`
* `Comment0 // My Comment
Comment1 // My Comment //`
  * `IDENTIFIER_NAME` `Comment0`
  * `IDENTIFIER_NAME` `Comment1`
* `[// My Comment\u2028]`
  * `LBRACKET` `[`
  * `RBRACKET` `]`
* `[// My Comment\u2029]`
  * `LBRACKET` `[`
  * `RBRACKET` `]`
* `[// My Comment
]`
  * `LBRACKET` `[`
  * `RBRACKET` `]`
* `[/*
*/]`
  * `LBRACKET` `[`
  * `RBRACKET` `]`