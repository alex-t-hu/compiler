# Inputs a number and prints whether it is even or odd
# 
# @author Alex Hu
# @version 4.13.22
	.text 0x00400000
	.globl main
main:
	#inputs the number
	li $v0, 5
	syscall
	move $t0, $v0
	
	#divides the number by 2
	li $t2, 2
	div $t0, $t2
	mfhi $t1
	
	#prints the appropriate message
	beq $t1, 0, even
	la $a0, msg2
	j after
	even:
	la $a0, msg1
	after:
	li $v0, 4
	syscall
	
	li $v0, 10
	syscall
	.data
msg1:
	.asciiz "Even"
msg2:
	.asciiz "Odd"
