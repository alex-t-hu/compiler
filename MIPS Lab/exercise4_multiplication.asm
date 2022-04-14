# Inputs two numbers from the user and multiplies them
# 
# @author Alex Hu
# @version 4.13.22
	.text 0x00400000
	.globl main
main:
	# li $v0, 4
	# la $a0, msg
	# syscall 
	
	li $v0, 5
	syscall
	move $t0, $v0
	
	li $v0, 5
	syscall
	move $t1, $v0
	
	mult $t0, $t1
	mflo $t2
	
	li $v0, 1
	move $a0, $t2
	syscall
	
	li $v0, 10
	syscall
#	.data
# msg:
#	.asciiz "Hello World!\n"