from random import choice

computer = choice(["rock", "paper", "scissors"])
person = input("player's choice")

winner = ""

if computer == "rock" and person == "scissors":
    winner = "computer"
elif computer == "paper" and person == "scissors":
    winner = "person"
elif comptuer == "scissors" and person == "scissors":
    winner = "tie game"
else:
    winner = "me"
