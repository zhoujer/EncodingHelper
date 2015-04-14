Hi!

This is EncodingHelper, by Nate Osher and Jerry Zhou.

Aside from the regular functions, we decided to make our EncodingHelper more
flexible with the inputs that it accepts with codepoints and byte strings.
Codepoints can be entered in any combination of the prefixes u, \, and +.
UTF-8 bytes can be entered with the prefix \x or x, in any combination.

Examples:

>$ java EncodingHelper -i codepoint u0041 u\0042 u+0043
String: ABC
Codepoints: U+0041 U+0042 U+0043
UTF-8: \x41\x42\x43

>$ java EncodingHelper -i codepoint "u0041 u\0042 u+0043"
String: ABC
Codepoints: U+0041 U+0042 U+0043
UTF-8: \x41\x42\x43

>$ java EncodingHelper -i utf8 'x24\xc2xa2\e2x82\xac'
String: $¢€
Codepoints: U+0024 U+00A2 U+20AC
UTF-8: \x24\xC2\xA2\xE2\x82\xAC
