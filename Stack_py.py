def get_precedence(op):
    if op in ('+', '-'): return 1
    if op in ('*', '/'): return 2
    return 0

def infix_to_postfix(expression):
    stack = []
    postfix = []
    tokens = expression.replace('(', ' ( ').replace(')', ' ) ').split()

    for char in tokens:
        if char.isalnum():
            postfix.append(char)
        elif char == '(':
            stack.append(char)
        elif char == ')':
            while stack and stack[-1] != '(':
                postfix.append(stack.pop())
            stack.pop()
        else:
            while stack and get_precedence(stack[-1]) >= get_precedence(char):
                postfix.append(stack.pop())
            stack.append(char)
    
    while stack:
        postfix.append(stack.pop())
    return postfix

def evaluate_postfix(postfix_list):
    stack = []
    print("\n--- Step by Step Perhitungan (Stack Process) ---")
    
    for token in postfix_list:
        if token.isdigit():
            stack.append(int(token))
            print(f"Push operand: {token} \t\t Stack: {stack}")
        else:
            val2 = stack.pop()
            val1 = stack.pop()
            if token == '+': res = val1 + val2
            elif token == '-': res = val1 - val2
            elif token == '*': res = val1 * val2
            elif token == '/': res = val1 / val2
            stack.append(res)
            print(f"Operasi {val1} {token} {val2}: {res} \t Stack: {stack}")
            
    return stack[0]

# Main Program
expr = "3 + 5 * ( 2 - 8 )"
print(f"Infix: {expr}")
postfix_tokens = infix_to_postfix(expr)
print(f"Postfix: {' '.join(postfix_tokens)}")

result = evaluate_postfix(postfix_tokens)
print(f"\nHasil Akhir: {result}")