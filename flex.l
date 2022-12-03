%{
#include <stdio.h>
int count = 0;
FILE *output;
%}

%%
.+\n	{ fprintf(output, "%d %s", ++count, yytext); }
%%

int yywrap() {}

int main() {
    FILE *input = fopen("input.txt", "r");

    if (input == NULL) {
        printf("Could not open input file");
        return 1;
    }

    output = fopen("output.txt", "w");

    yyin = input;

    while (yylex() != 0);

    fclose(input);
    fclose(output);

    printf("Done\n");

    return 0;
}