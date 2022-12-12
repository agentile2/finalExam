Token code rules

Token:		Regex:			Token Code:
start_stmnt		‘start’				0
end_stmnt		‘trats’				99
nd_file			‘end’				-1
blk_lft			‘{‘				95
blk_rt			‘}’				96
param_sep		;				20

			
real_lit		‘[0-9]+.[0-9]+			5	
nat_lit			[0-9]+				6
bool_lit		[true,false]			7
char_lit		[A-Za-z0-9]			8
string_lit		[A-Za-z0-9]			9

Loop_stint		‘repeat’			12
Wthr_stmnt 		’whether’			13

strg_typ		‘word’				26
nat_num			‘num’				27
char_typ		‘char’				28
real_typ		‘real’				29
bool_typ		Bool				1

var_id			“@[A-Za-z_]+”			44
func_id			“#[A-Za-z]+”			45

decl_re			‘Declare’			55
ass_n			‘=‘				66	

add_itn			‘+’				31
sub_trct		‘-‘				32
mult_ply		‘*’				33	
div_de			‘/‘				34
Mod_le			‘%’				35
lss_thn			‘<‘				36
gr_thn			‘>’				37	
lss_eqthn		‘=<‘				38
gr_eqthn		‘>=‘				39
eq_to			‘==‘				40
nt_eq			‘!=‘				41
unary_neg		‘!’				42
log_not			!!				43
log_and			&&				44
log_or			|				45

Lt_prnth		‘(‘				97	
rt_prnth		‘)’				98


Question 10:  choose 3 syntactically valid assignment statements with at least 7 tokens to show these rules failing or passing semantic rules.
1. declare Bool TRUE = 55 + 2;
2. "hello" + "world" = greeting
3. declare NUM = helloWorld

Question 11. Axiomatic Semantics ( find the weakest precondition) : 
a. Weakest Precondition: a must be greater than 0 and b must be greater than 1
(a>0 && b>1)

b. Weakest Precondition: x must be less than 0.
(3*x<0)

c. Weakest Precondition: x must be less than 0.
(a * 2 * (b-1) – 1 < 0)

d. Weakest preconditon: b must be greater than 5. 
(b>5)

