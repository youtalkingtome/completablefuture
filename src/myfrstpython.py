# simple_greeting.py

def greet(name):
    """Function to greet the person whose name is passed as an argument."""
    return f"Hello, {name}!"

def main():
    name = input("Enter your name: ")
    greeting = greet(name)
    print(greeting)

if __name__ == "__main__":
    main()
